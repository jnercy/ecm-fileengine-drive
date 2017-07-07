package com.nextcont.drive.mongo;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.nextcont.drive.aspect.AuthAspect;
import com.nextcont.drive.utils.StringUtils;
import com.nextcont.file.request.file.FileMetadataRequest;
import com.nextcont.file.request.file.FileRequestbody;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/10
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class MongoField {

    public static Bson driveFileExcludeField = fields(exclude("description", "properties", "appProperties", "imageMediaMetadata", "videoMediaMetadata"), excludeId());

    public static Bson excludeUsersRecords = exclude("userRecords");

    public static final String mimeType_Foloder = "application/vnd.google-apps.folder";


    //基础查询条件 根据rootId和文件id构建query条件
    public static Bson mongoDefaultQuery(String fileId) {
        return and(
                eq("userRecords.rootId", AuthAspect.getAuthTokenInfo().getRootid()),
                eq("id", fileId)
        );
    }

    //基础查询条件 根据rootId和文件id构建move file 条件
    public static Bson mongoReady2MoveFileQuery(String fileId, String parent) {
        if (StringUtils.isNotEmpty(parent))
            return and(
                    eq("userRecords.rootId", AuthAspect.getAuthTokenInfo().getRootid()),
                    eq("userRecords.parents", parent),
                    eq("id", fileId)
            );
        else
            return and(
                    eq("userRecords.rootId", AuthAspect.getAuthTokenInfo().getRootid()),
                    eq("id", fileId)
            );
    }

    //非锁定文件批量基础查询
    public static Bson mongoUnlockPatchQuery(List<String> fileIds) {
        return and(
                eq("userRecords.rootId", AuthAspect.getAuthTokenInfo().getRootid()),
                in("id", fileIds),
                eq("locked", false)
        );
    }

    //非锁定文件基础查询
    public static Bson mongoUnlockQuery(String fileId) {
        return and(
                eq("userRecords.rootId", AuthAspect.getAuthTokenInfo().getRootid()),
                eq("id", fileId),
                eq("locked", false)
        );
    }


    //软删除文件基础查询
    public static Bson mongoTrashQuery() {
        return and(
                eq("userRecords.rootId", AuthAspect.getAuthTokenInfo().getRootid()),
                eq("trashed", true)
        );
    }


    //文件权限添加基本查询
    public static Bson mongoCreatePermissionQuery(String fileId,String shardEmailAddress) {
        return Filters.or(
                and(
                        eq("owners.emailAddress", AuthAspect.getAuthTokenInfo().getGmail()),
                        eq("id", fileId),
                        eq("locked", false),
                        ne("permissions.emailAddress",shardEmailAddress)
                ),
                and(
                        eq("permissions.emailAddress", AuthAspect.getAuthTokenInfo().getGmail()),
                        eq("permissions.role", "writer"),
                        eq("id", fileId),
                        eq("locked", false)),
                        ne("permissions.emailAddress",shardEmailAddress)
        );
    }


    //文件列表基础查询
    public static Bson mongoListQuery(String q) {
        Document queryBson = new Document();

        Arrays.stream(q.split("and"))
                .forEach(queryParam -> {
                    queryParam = queryParam.trim();
                    if(queryParam.contains("mimeType")){
                        if(queryParam.contains("not"))
                            queryBson.append("mimeType",new Document("$ne",mimeType_Foloder));
                        else
                            queryBson.append("mimeType",mimeType_Foloder);
                    }
                    else if (queryParam.contains("trashed")) {
                        if (queryParam.contains("true"))
                            queryBson.append("trashed", true);
                        else
                            queryBson.append("trashed", false);
                    } else if (queryParam.contains("sharedWithMe")) {

                        queryBson.append("permissions.emailAddress", AuthAspect.getAuthTokenInfo().getGmail());

                        if (queryParam.contains("true"))
                            queryBson.append("permissions.role", new Document("$ne", "owner"));
                        else if (queryParam.contains("false")) {
                            queryBson.append("permissions.role", "owner");
                        }
                    } else if (queryParam.contains("starred")) {
                        if (queryParam.contains("true"))
                            queryBson.append("starred", true);
                        else
                            queryBson.append("starred", false);
                    } else if (queryParam.contains("ownedByMe")) {
                        if (queryParam.contains("true"))
                            queryBson.append("userRecords.ownedByMe", true);
                        else
                            queryBson.append("userRecords.ownedByMe", false);
                    } else if (queryParam.contains("parents")) {
                        String parentId = queryParam.split("in")[0].trim().replaceAll("\'", "");
                        queryBson.append("userRecords.parents", parentId.equals("root") ? AuthAspect.getAuthTokenInfo().getRootid() : parentId);
                    }
                });

        log.info("list mongo query {}", queryBson.toJson());
        return queryBson;

    }

    public static Bson mongoPatchMetadata(FileRequestbody patchData) {
        List<Bson> updateBsons = new ArrayList<>();
        if (patchData.getAppProperties() != null)
            updateBsons.add(set("appProperties", patchData.getAppProperties()));

        else if (patchData.getContentHints() != null)
            updateBsons.add(set("contentHints", patchData.getContentHints()));

        else if (patchData.getDescription() != null)
            updateBsons.add(set("description", patchData.getDescription()));

        else if (patchData.getFolderColorRgb() != null)
            updateBsons.add(set("folderColorRgb", patchData.getFolderColorRgb()));

        else if (patchData.getMimeType() != null)
            updateBsons.add(set("mimeType", patchData.getMimeType()));

        else if (patchData.getModifiedTime() != null)
            updateBsons.add(set("modifiedTime", patchData.getModifiedTime()));

        else if (patchData.getName() != null)
            updateBsons.add(set("name", patchData.getName()));

        else if (patchData.getProperties() != null)
            updateBsons.add(set("properties", patchData.getProperties()));

        else if (StringUtils.isNotEmpty(patchData.getStarred()))
            updateBsons.add(set("starred", patchData.getStarred().equals("true")));

        else if (StringUtils.isNotEmpty(patchData.getTrashed()))
            updateBsons.add(set("trashed", patchData.getTrashed().equals("true")));

        else if (StringUtils.isNotEmpty(patchData.getViewersCanCopyContent()))
            updateBsons.add(set("viewersCanCopyContent", patchData.getViewersCanCopyContent().equals("true")));

        else if (StringUtils.isNotEmpty(patchData.getWritersCanShare()))
            updateBsons.add(set("writersCanShare", patchData.getWritersCanShare().equals("true")));

        return combine(updateBsons);
    }

    public static Bson defaultSortBson() {
        return descending("isFolder", "modifiedTime");
    }

    public static Bson buildSortBson(String orderBy) {
        if (StringUtils.isNotEmpty(orderBy)) {
            List<String> descParams = Arrays
                    .stream(orderBy.split(","))
                    .filter(param -> param.contains("desc"))
                    .map(param -> param.replaceAll("desc", "").trim())
                    .collect(Collectors.toList());

            List<String> ascParams = Arrays
                    .stream(orderBy.split(","))
                    .filter(param -> !param.contains("desc"))
                    .map(String::trim)
                    .collect(Collectors.toList());

            return Sorts.orderBy(descending(descParams),ascending(ascParams));
        }
        else
            return defaultSortBson();
    }


}
