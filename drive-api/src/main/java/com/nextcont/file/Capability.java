package com.nextcont.file;

import lombok.*;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/11
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Capability {

    private boolean canEdit;

    private boolean canComment;

    private boolean canShare;

    private boolean canCopy;

    private boolean canReadRevisions;

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
