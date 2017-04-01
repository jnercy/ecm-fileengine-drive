package com.nextcont.drive.controller;

import com.nextcont.drive.mongo.MongoInnerDomQuery;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.file.FileMetaData;
import com.nextcont.file.FileRevision;
import com.nextcont.file.request.revisions.RevisionListRequest;
import com.nextcont.file.request.revisions.RevisionUpdateReqeustbody;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static com.nextcont.drive.utils.ResponseMaker.getErrorResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/10
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/drive/v1/files")
@Slf4j
public class RevisionController {

    @Autowired
    private BaseMongoService<FileMetaData> fileMetaDataService;

    @Autowired
    private BaseMongoService<FileRevision> fileRevisionService;


    @RequestMapping(value = "/{fileId}/revisions/{revisionId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("fileId") String fileId, @PathVariable("revisionId") String revisionId) {
        String proccessRecord = fileMetaDataService
                .queryOneFullField(new Document("owners.emailAddress", "jnercywang@gmail.com").append("id", fileId).append("locked", false))
                .map(driveFile -> {
                            List<FileRevision> revisions = driveFile.getRevisions();
                            boolean hasRevisionId = revisions.removeIf(r -> r.getId().equals(revisionId));
                            if (hasRevisionId) {
                                BsonArray revisionArray = new BsonArray();
                                revisions.forEach(r -> revisionArray.add(BsonDocument.parse(JsonFormat.toJson(r))));
                                boolean deleteResult = fileMetaDataService.updateOne(new Document("id", fileId), set("revisions", revisionArray));
                                return deleteResult ? "delete execute success!" : "delete execute failed!";
                            } else
                                return "permission Not found";
                        }
                ).orElse(getErrorResponse("file not found or check failed"));
        return proccessRecord;
    }

    @RequestMapping(value = "/{fileId}/revisions/{revisionId}", method = RequestMethod.GET)
    public FileRevision get(@PathVariable("fileId") String fileId, @PathVariable("revisionId") String revisionId, @RequestParam boolean acknowledgeAbuse) {
        MongoInnerDomQuery.MongoInnerDomQueryBuilder builder = MongoInnerDomQuery.builder();
        MongoInnerDomQuery query = builder
                .parentQuery(eq("id", fileId))
                .innerDomQuery(eq("revisions.id", revisionId))
                .innerFieldName("revisions")
                .build();
        FileRevision fileRevision = fileRevisionService
                .queryInnerDocument(query)
                .orElse(null);
        return fileRevision;
    }

    @RequestMapping(value = "/{fileId}/revisions", method = RequestMethod.GET)
    public List<FileRevision> list(@PathVariable("fileId") String fileId, RevisionListRequest request) {
        List<FileRevision> revisions = fileMetaDataService
                .queryOneFullField(new Document("id", fileId))
                .map(FileMetaData::getRevisions).orElse(null);
        return revisions;
    }


    @RequestMapping(value = "/{fileId}/revisions/{revisionId}", method = RequestMethod.PATCH)
    public String update(@PathVariable("fileId") String fileId, @PathVariable("revisionId") String revisionId, @RequestBody RevisionUpdateReqeustbody bodyData) {
        String proccessRecord = fileMetaDataService
                .queryOneFullField(new Document("owners.emailAddress", "jnercywang@gmail.com").append("id", fileId)
                        .append("locked", false))
                .map(driveFile -> {
                            List<FileRevision> revisions = driveFile.getRevisions();
                            revisions.forEach(r -> {
                                if (r.getId().equals(revisionId)) {
                                    r.setKeepForever(bodyData.isKeepForever());
                                    r.setPublished(bodyData.isPublished());
                                }
                            });
                            BsonArray revisionsArray = new BsonArray();
                            revisions.forEach(p -> revisionsArray.add(BsonDocument.parse(JsonFormat.toJson(p))));
                            boolean updateResult = fileMetaDataService.updateOne(new Document("id", fileId), set("revisions", revisionsArray));
                            return updateResult ? "update execute success!" : "update execute failed!";
                        }
                ).orElse(getErrorResponse("file not found or check failed"));
        return proccessRecord;
    }

}
