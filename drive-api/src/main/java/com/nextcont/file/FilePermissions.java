package com.nextcont.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/14
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilePermissions implements Serializable{

    private List<FilePermission> permissions;
}
