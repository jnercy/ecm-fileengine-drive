package com.nextcont.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/10
 * Time: 13:30
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileGenerateId {

    //The number of IDs to return. (integer, 1-1000)
    private String kind = "drive#generatedIds";

    //The space in which the IDs can be used to create new files. Supported values are 'drive' and 'appDataFolder'. (string)
    private String space;

    private List<String> ids;
}
