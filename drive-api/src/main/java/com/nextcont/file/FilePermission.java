package com.nextcont.file;

import lombok.*;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/11
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilePermission {

    private final String kind = "drive#permission";

    private String id;

    private String type;

    private String emailAdddress;

    private String role;

    private String displayName;

    private String photoLink;

}
