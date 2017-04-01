package com.nextcont.file;

import lombok.*;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/11
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriveUser {

    private final String kind = "drive#user";

    private String displayName;

    private String photoLink;

    private Long permissionId;

    private String emailAddress;

}
