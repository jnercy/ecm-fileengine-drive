package com.nextcont.file.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/19
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileLockRequest implements Serializable{

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 用戶id
     */
    private String userId;
    /**
     * 用户token
     */
    private String token;

    /**
     *时间单位
     */
    private String units;

    /**
     *时间单位的数量
     */
    private Integer quantity;

    /**
     * 是否手动解锁
     */
    private boolean isPersonallyUnlock;
}
