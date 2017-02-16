package com.nextcont.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/12
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileProcessRecord implements Serializable {

    private String userId;

    private boolean ownedByMe;

    private boolean viewedByMe;

    private boolean modifyByMe;

    private String modifyByMeTime;

    private String sharedWithMeTime;

    private String viewByMeTime;

    private boolean starred;

}
