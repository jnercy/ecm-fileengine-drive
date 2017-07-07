/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.pojos;


import com.nextcont.drive.jooq.db.mysql.enums.HostAlterPriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostAlterRoutinePriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostCreatePriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostCreateRoutinePriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostCreateTmpTablePriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostCreateViewPriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostDeletePriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostDropPriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostExecutePriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostGrantPriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostIndexPriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostInsertPriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostLockTablesPriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostReferencesPriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostSelectPriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostShowViewPriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostTriggerPriv;
import com.nextcont.drive.jooq.db.mysql.enums.HostUpdatePriv;

import java.io.Serializable;

import javax.annotation.Generated;


/**
 * Host privileges;  Merged with database privileges
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Host implements Serializable {

    private static final long serialVersionUID = 1372806480;

    private String                 host;
    private String                 db;
    private HostSelectPriv         selectPriv;
    private HostInsertPriv         insertPriv;
    private HostUpdatePriv         updatePriv;
    private HostDeletePriv         deletePriv;
    private HostCreatePriv         createPriv;
    private HostDropPriv           dropPriv;
    private HostGrantPriv          grantPriv;
    private HostReferencesPriv     referencesPriv;
    private HostIndexPriv          indexPriv;
    private HostAlterPriv          alterPriv;
    private HostCreateTmpTablePriv createTmpTablePriv;
    private HostLockTablesPriv     lockTablesPriv;
    private HostCreateViewPriv     createViewPriv;
    private HostShowViewPriv       showViewPriv;
    private HostCreateRoutinePriv  createRoutinePriv;
    private HostAlterRoutinePriv   alterRoutinePriv;
    private HostExecutePriv        executePriv;
    private HostTriggerPriv        triggerPriv;

    public Host() {}

    public Host(Host value) {
        this.host = value.host;
        this.db = value.db;
        this.selectPriv = value.selectPriv;
        this.insertPriv = value.insertPriv;
        this.updatePriv = value.updatePriv;
        this.deletePriv = value.deletePriv;
        this.createPriv = value.createPriv;
        this.dropPriv = value.dropPriv;
        this.grantPriv = value.grantPriv;
        this.referencesPriv = value.referencesPriv;
        this.indexPriv = value.indexPriv;
        this.alterPriv = value.alterPriv;
        this.createTmpTablePriv = value.createTmpTablePriv;
        this.lockTablesPriv = value.lockTablesPriv;
        this.createViewPriv = value.createViewPriv;
        this.showViewPriv = value.showViewPriv;
        this.createRoutinePriv = value.createRoutinePriv;
        this.alterRoutinePriv = value.alterRoutinePriv;
        this.executePriv = value.executePriv;
        this.triggerPriv = value.triggerPriv;
    }

    public Host(
        String                 host,
        String                 db,
        HostSelectPriv         selectPriv,
        HostInsertPriv         insertPriv,
        HostUpdatePriv         updatePriv,
        HostDeletePriv         deletePriv,
        HostCreatePriv         createPriv,
        HostDropPriv           dropPriv,
        HostGrantPriv          grantPriv,
        HostReferencesPriv     referencesPriv,
        HostIndexPriv          indexPriv,
        HostAlterPriv          alterPriv,
        HostCreateTmpTablePriv createTmpTablePriv,
        HostLockTablesPriv     lockTablesPriv,
        HostCreateViewPriv     createViewPriv,
        HostShowViewPriv       showViewPriv,
        HostCreateRoutinePriv  createRoutinePriv,
        HostAlterRoutinePriv   alterRoutinePriv,
        HostExecutePriv        executePriv,
        HostTriggerPriv        triggerPriv
    ) {
        this.host = host;
        this.db = db;
        this.selectPriv = selectPriv;
        this.insertPriv = insertPriv;
        this.updatePriv = updatePriv;
        this.deletePriv = deletePriv;
        this.createPriv = createPriv;
        this.dropPriv = dropPriv;
        this.grantPriv = grantPriv;
        this.referencesPriv = referencesPriv;
        this.indexPriv = indexPriv;
        this.alterPriv = alterPriv;
        this.createTmpTablePriv = createTmpTablePriv;
        this.lockTablesPriv = lockTablesPriv;
        this.createViewPriv = createViewPriv;
        this.showViewPriv = showViewPriv;
        this.createRoutinePriv = createRoutinePriv;
        this.alterRoutinePriv = alterRoutinePriv;
        this.executePriv = executePriv;
        this.triggerPriv = triggerPriv;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDb() {
        return this.db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public HostSelectPriv getSelectPriv() {
        return this.selectPriv;
    }

    public void setSelectPriv(HostSelectPriv selectPriv) {
        this.selectPriv = selectPriv;
    }

    public HostInsertPriv getInsertPriv() {
        return this.insertPriv;
    }

    public void setInsertPriv(HostInsertPriv insertPriv) {
        this.insertPriv = insertPriv;
    }

    public HostUpdatePriv getUpdatePriv() {
        return this.updatePriv;
    }

    public void setUpdatePriv(HostUpdatePriv updatePriv) {
        this.updatePriv = updatePriv;
    }

    public HostDeletePriv getDeletePriv() {
        return this.deletePriv;
    }

    public void setDeletePriv(HostDeletePriv deletePriv) {
        this.deletePriv = deletePriv;
    }

    public HostCreatePriv getCreatePriv() {
        return this.createPriv;
    }

    public void setCreatePriv(HostCreatePriv createPriv) {
        this.createPriv = createPriv;
    }

    public HostDropPriv getDropPriv() {
        return this.dropPriv;
    }

    public void setDropPriv(HostDropPriv dropPriv) {
        this.dropPriv = dropPriv;
    }

    public HostGrantPriv getGrantPriv() {
        return this.grantPriv;
    }

    public void setGrantPriv(HostGrantPriv grantPriv) {
        this.grantPriv = grantPriv;
    }

    public HostReferencesPriv getReferencesPriv() {
        return this.referencesPriv;
    }

    public void setReferencesPriv(HostReferencesPriv referencesPriv) {
        this.referencesPriv = referencesPriv;
    }

    public HostIndexPriv getIndexPriv() {
        return this.indexPriv;
    }

    public void setIndexPriv(HostIndexPriv indexPriv) {
        this.indexPriv = indexPriv;
    }

    public HostAlterPriv getAlterPriv() {
        return this.alterPriv;
    }

    public void setAlterPriv(HostAlterPriv alterPriv) {
        this.alterPriv = alterPriv;
    }

    public HostCreateTmpTablePriv getCreateTmpTablePriv() {
        return this.createTmpTablePriv;
    }

    public void setCreateTmpTablePriv(HostCreateTmpTablePriv createTmpTablePriv) {
        this.createTmpTablePriv = createTmpTablePriv;
    }

    public HostLockTablesPriv getLockTablesPriv() {
        return this.lockTablesPriv;
    }

    public void setLockTablesPriv(HostLockTablesPriv lockTablesPriv) {
        this.lockTablesPriv = lockTablesPriv;
    }

    public HostCreateViewPriv getCreateViewPriv() {
        return this.createViewPriv;
    }

    public void setCreateViewPriv(HostCreateViewPriv createViewPriv) {
        this.createViewPriv = createViewPriv;
    }

    public HostShowViewPriv getShowViewPriv() {
        return this.showViewPriv;
    }

    public void setShowViewPriv(HostShowViewPriv showViewPriv) {
        this.showViewPriv = showViewPriv;
    }

    public HostCreateRoutinePriv getCreateRoutinePriv() {
        return this.createRoutinePriv;
    }

    public void setCreateRoutinePriv(HostCreateRoutinePriv createRoutinePriv) {
        this.createRoutinePriv = createRoutinePriv;
    }

    public HostAlterRoutinePriv getAlterRoutinePriv() {
        return this.alterRoutinePriv;
    }

    public void setAlterRoutinePriv(HostAlterRoutinePriv alterRoutinePriv) {
        this.alterRoutinePriv = alterRoutinePriv;
    }

    public HostExecutePriv getExecutePriv() {
        return this.executePriv;
    }

    public void setExecutePriv(HostExecutePriv executePriv) {
        this.executePriv = executePriv;
    }

    public HostTriggerPriv getTriggerPriv() {
        return this.triggerPriv;
    }

    public void setTriggerPriv(HostTriggerPriv triggerPriv) {
        this.triggerPriv = triggerPriv;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Host (");

        sb.append(host);
        sb.append(", ").append(db);
        sb.append(", ").append(selectPriv);
        sb.append(", ").append(insertPriv);
        sb.append(", ").append(updatePriv);
        sb.append(", ").append(deletePriv);
        sb.append(", ").append(createPriv);
        sb.append(", ").append(dropPriv);
        sb.append(", ").append(grantPriv);
        sb.append(", ").append(referencesPriv);
        sb.append(", ").append(indexPriv);
        sb.append(", ").append(alterPriv);
        sb.append(", ").append(createTmpTablePriv);
        sb.append(", ").append(lockTablesPriv);
        sb.append(", ").append(createViewPriv);
        sb.append(", ").append(showViewPriv);
        sb.append(", ").append(createRoutinePriv);
        sb.append(", ").append(alterRoutinePriv);
        sb.append(", ").append(executePriv);
        sb.append(", ").append(triggerPriv);

        sb.append(")");
        return sb.toString();
    }
}
