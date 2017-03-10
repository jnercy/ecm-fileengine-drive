package com.nextcont.file.request.permission;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/10
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
@Data
public class PermissionCreateRequest {

    private String emailMessage;

    private boolean sendNotificationEmail;

    private boolean supportsTeamDrives;

    private boolean transferOwnership;
}
