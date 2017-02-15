package com.nextcont.file.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/19
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
@Data
public class FileShareRequest implements Serializable {

    /**
     * 用戶id
     */
    private String fileId;
    /**
     * (必填) 权限规则：owner(包含所有权限，并且有删除文件的权限),
     * edit(修改权限包含读和评论,但没有文件删除权限),
     * comment(评论权限包含读权限, read(只读权限)
     */
    private String role;

    /**
     *用户组(可选参数但userId和group必须有一个否则请求驳回)
     */
    private String group;

    /**
     *用户的id(可选参数但userId和group必须有一个否则请求驳回)
     */
    private String targetUserId;

    /**
     *用户的邮箱地址
     */
    private String emailAddress;
}
