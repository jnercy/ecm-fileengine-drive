package com.nextcont.file.request.file;

import com.nextcont.file.ContentHints;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/6
 * Time: 16:30
 * To change this template use File | Settings | File Templates.
 */
@Data
public class FileCreateRequestBody {

    private Map appProperties;

    private ContentHints contentHints;

    private String createTime;

    private String description;

    private String folderColorRgb;

    private String id;

    private String mimeType;

    private String modifiedTime;

    private String name;

    private String originalFilename;

    private String parent;

    private Map properties;

    private String starred;

    private String viewedByMeTime;

    private String viewersCanCopyContent;

    private String writersCanShare;
}
