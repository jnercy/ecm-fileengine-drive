package com.nextcont.file.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nextcont.file.ContentHints;
import lombok.Data;

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
public class PatchMetadataReqeust {

    private String fileId;

    private String uploadType;

    private String addParents;

    private boolean keepRevisionForever;

    private String ocrLanguage;

    private String removeParents;

    private boolean useContentAsIndexableText;

    private Map appProperties;

    private ContentHints contentHints;

    private String description;

    private String folderColorRgb;

    private String mimeType;

    private String modifiedTime;

    private String name;

    private Map properties;

    private boolean starred;

    private boolean trashed;

    private String viewedByMeTime;

    private boolean viewersCanCopyContent;

    private boolean writersCanShare;
}
