package com.nextcont.file.request.revisions;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/10
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
@Data
public class RevisionUpdateReqeustbody {

    private boolean keepForever;

    private boolean publishAuto;

    private boolean published;

    private boolean publishedOutsideDomain;
}
