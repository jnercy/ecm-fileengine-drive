package com.nextcont.file;

import lombok.Data;

/**
 * Created by wangxudong on 2017/6/8.
 */
@Data
public class FileMoveRequest {

    private String fileId;

    private String source;

    private String target;
}
