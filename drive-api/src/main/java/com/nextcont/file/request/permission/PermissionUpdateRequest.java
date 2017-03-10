package com.nextcont.file.request.permission;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/10
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
@Data
public class PermissionUpdateRequest {

    private boolean removeExpiration;

    private boolean supportsTeamDrives;

    private boolean transferOwnership;
}
