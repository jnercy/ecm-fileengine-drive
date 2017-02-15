package com.nextcont.file;

import lombok.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/15
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */

@Getter
@Setter
@NoArgsConstructor
public class FileList<T> {

    private final String kind = "drive#fileList";

    private Integer nextPageToken;

//    private Integer currentPage;
//
//    private Integer totalPage;
//
//    private Integer pageSize;

    private List<T> files;
}
