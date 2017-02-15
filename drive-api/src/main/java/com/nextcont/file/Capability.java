package com.nextcont.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/11
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Capability {

    private boolean canEdit;

    private boolean canComment;

    private boolean canShare;

    private boolean canCopy;

    private boolean canReadRevisions;

}
