package com.nextcont.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by wangxudong on 2017/6/28.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {

    private Integer code;

    private String msg;

    private List<GroupInfo> data;
}
