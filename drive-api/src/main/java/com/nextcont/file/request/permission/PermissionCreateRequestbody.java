package com.nextcont.file.request.permission;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/10
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
@Data
public class PermissionCreateRequestbody {

    private String role;

    private String type;

    private boolean allowFileDiscovery;

    private String domain;

    private String emailAddress;
}
