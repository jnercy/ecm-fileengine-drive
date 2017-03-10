package com.nextcont.file.request.permission;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/10
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */
@Data
public class PermissionUpdateRequestbody {

    private String expirationTime;

    private String role;
}
