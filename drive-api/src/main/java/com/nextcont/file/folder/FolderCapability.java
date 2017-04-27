package com.nextcont.file.folder;

import lombok.*;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/7
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
@Data
public class FolderCapability extends Capability{

    private boolean canAddChildren;

    private boolean canDelete;

    private boolean canDownload;

    private boolean canListChildren;

    private boolean canMoveItemIntoTeamDrive;

    private boolean canRemoveChildren;

    private boolean canRename;

    private boolean canTrash;

    private boolean canUntrash;


    public static Capability getInstance(){

        FolderCapability folderCapability = new FolderCapability();

        folderCapability.canEdit=true;
        folderCapability.canComment=true;
        folderCapability.canShare=true;
        folderCapability.canCopy=false;
        folderCapability.canReadRevisions=false;
        folderCapability.canAddChildren = true;
        folderCapability.canDelete = true;
        folderCapability.canDownload = true;
        folderCapability.canListChildren = true;
        folderCapability.canMoveItemIntoTeamDrive = true;
        folderCapability.canRemoveChildren = true;
        folderCapability.canRename = true;
        folderCapability.canTrash = true;
        folderCapability.canUntrash = true;

        return folderCapability;
    }

}
