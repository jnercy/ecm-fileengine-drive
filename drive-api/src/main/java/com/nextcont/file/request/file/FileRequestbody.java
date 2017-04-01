package com.nextcont.file.request.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nextcont.file.ContentHints;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/16
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileRequestbody {

    private Map appProperties;

    private ContentHints contentHints;

    private String description;

    private String folderColorRgb;

    private String id;

    private String mimeType;

    private String modifiedTime;

    private String name;

    private String originalFilename;

    private List<String> parents;

    private Map properties;

    private String starred;

    private String trashed;

    private String viewedByMeTime;

    private String viewersCanCopyContent;

    private String writersCanShare;



}
