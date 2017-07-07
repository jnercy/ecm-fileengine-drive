//package com.nextcont.drive.controller;
//
//import com.nextcont.drive.mongo.MongoInnerDomQuery;
//import com.nextcont.drive.mongo.service.BaseMongoService;
//import com.nextcont.drive.utils.JsonFormat;
//import com.nextcont.drive.utils.Tuple;
//import com.nextcont.file.FileMetaData;
//import com.nextcont.file.FileRevision;
//import com.nextcont.file.request.revisions.RevisionListRequest;
//import com.nextcont.file.request.revisions.RevisionUpdateReqeustbody;
//import lombok.extern.slf4j.Slf4j;
//import org.bson.BsonArray;
//import org.bson.BsonDocument;
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static com.mongodb.client.model.Filters.eq;
//import static com.mongodb.client.model.Updates.set;
//import static com.nextcont.drive.utils.ResponseMaker.getErrorResponse;
//import static com.nextcont.drive.utils.ResponseMaker.getSuccessResponse;
//import static com.nextcont.drive.utils.TupleFactories.pairs;
//
///**
// * Created with IntelliJ IDEA.
// * User: Wangxudong
// * Date: 2017/3/10
// * Time: 14:56
// * To change this template use File | Settings | File Templates.
// */
//@RestController
//@RequestMapping("/drive/v1/files")
//@Slf4j
//public class RevisionController {
//
//    @Autowired
//    private BaseMongoService<FileMetaData> fileMetaDataService;
//
//    @Autowired
//    private BaseMongoService<FileRevision> fileRevisionService;
//
//
//    @DeleteMapping(value = "/{fileId}/revisions/{revisionId}",produces = "application/json")
//    public ResponseEntity<Object> delete(@PathVariable("fileId") String fileId, @PathVariable("revisionId") String revisionId) {
//
//        Tuple<Object,HttpStatus> result = fileMetaDataService
//                .queryOneFullField(new Document("owners.emailAddress", "jnercywang@gmail.com").append("id", fileId).append("locked", false))
//                .map(driveFile -> {
//                            List<FileRevision> revisions = driveFile.getRevisions();
//                            boolean hasRevisionId = revisions.removeIf(r -> r.getId().equals(revisionId));
//                            if (hasRevisionId) {
//                                BsonArray revisionArray = new BsonArray();
//                                revisions.forEach(r -> revisionArray.add(BsonDocument.parse(JsonFormat.toJson(r))));
//                                boolean deleteResult = fileMetaDataService.updateOne(new Document("id", fileId), set("revisions", revisionArray));
//                                return deleteResult ? pairs(getSuccessResponse("delete execute success!"), HttpStatus.OK) : pairs(getErrorResponse("delete execute failed!"),HttpStatus.BAD_REQUEST);
//                            } else
//                                return pairs(getErrorResponse("permission Not found"),HttpStatus.BAD_REQUEST);
//                        }
//                ).orElse(pairs(getErrorResponse("file not found or check failed"),HttpStatus.BAD_REQUEST));
//
//        return new ResponseEntity<>(result.v1(), result.v2());
//    }
//
//    @GetMapping(value = "/{fileId}/revisions/{revisionId}", produces = "application/json")
//    public ResponseEntity<Object> get(@PathVariable("fileId") String fileId, @PathVariable("revisionId") String revisionId, @RequestParam boolean acknowledgeAbuse) {
//        MongoInnerDomQuery.MongoInnerDomQueryBuilder builder = MongoInnerDomQuery.builder();
//        MongoInnerDomQuery query = builder
//                .parentQuery(eq("id", fileId))
//                .innerDomQuery(eq("revisions.id", revisionId))
//                .innerFieldName("revisions")
//                .build();
//        FileRevision fileRevision = fileRevisionService
//                .queryInnerDocument(query)
//                .orElse(null);
//
//        return new ResponseEntity<>(fileRevision,HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/{fileId}/revisions", produces = "application/json")
//    public ResponseEntity<?> list(@PathVariable("fileId") String fileId, RevisionListRequest request) {
//        List<FileRevision> revisions = fileMetaDataService
//                .queryOneFullField(new Document("id", fileId))
//                .map(FileMetaData::getRevisions).orElse(null);
//
//        return new ResponseEntity<>(revisions,HttpStatus.OK);
//    }
//
//
//    @PatchMapping(value = "/{fileId}/revisions/{revisionId}", produces = "application/json")
//    public ResponseEntity<Object> update(@PathVariable("fileId") String fileId, @PathVariable("revisionId") String revisionId, @RequestBody RevisionUpdateReqeustbody bodyData) {
//
//        Tuple<Object,HttpStatus> result = fileMetaDataService
//                .queryOneFullField(new Document("owners.emailAddress", "jnercywang@gmail.com").append("id", fileId)
//                        .append("locked", false))
//                .map(driveFile -> {
//                            List<FileRevision> revisions = driveFile.getRevisions();
//                            revisions.forEach(r -> {
//                                if (r.getId().equals(revisionId)) {
//                                    r.setKeepForever(bodyData.isKeepForever());
//                                    r.setPublished(bodyData.isPublished());
//                                }
//                            });
//                            BsonArray revisionsArray = new BsonArray();
//                            revisions.forEach(p -> revisionsArray.add(BsonDocument.parse(JsonFormat.toJson(p))));
//                            boolean updateResult = fileMetaDataService.updateOne(new Document("id", fileId), set("revisions", revisionsArray));
//                            return updateResult ? pairs(getSuccessResponse("update execute success!"),HttpStatus.OK) : pairs(getErrorResponse("update execute failed!"),HttpStatus.BAD_REQUEST);
//                        }
//                ).orElse(pairs(getErrorResponse("file not found or check failed"),HttpStatus.BAD_REQUEST));
//
//        return new ResponseEntity<>(result.v1(),result.v2());
//    }
//
//}
