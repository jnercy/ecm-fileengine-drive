package com.nextcont.file.folder;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/11
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
@Data
public class Capability {

    public  boolean canEdit;

    public boolean canComment;

    public boolean canShare;

    public boolean canCopy;

    public boolean canReadRevisions;

    public static Capability getInstance(){
        Capability capability = new Capability();
        capability.canEdit = true;
        capability.canComment = true;
        capability.canShare = true;
        capability.canCopy = true;
        capability.canReadRevisions = true;

        return capability;
    }

}
