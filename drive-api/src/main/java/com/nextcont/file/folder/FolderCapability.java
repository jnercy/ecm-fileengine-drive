package com.nextcont.file.folder;

import com.nextcont.file.Capability;
import lombok.*;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/7
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

        folderCapability.setCanEdit(true);
        folderCapability.setCanComment(true);
        folderCapability.setCanShare(true);
        folderCapability.setCanCopy(false);
        folderCapability.setCanReadRevisions(false);
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
