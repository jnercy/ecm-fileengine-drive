/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.pojos;


import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.types.UInteger;


/**
 * Slow log
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SlowLog implements Serializable {

    private static final long serialVersionUID = -1074626856;

    private Timestamp startTime;
    private String    userHost;
    private Time      queryTime;
    private Time      lockTime;
    private Integer   rowsSent;
    private Integer   rowsExamined;
    private String    db;
    private Integer   lastInsertId;
    private Integer   insertId;
    private UInteger  serverId;
    private String    sqlText;

    public SlowLog() {}

    public SlowLog(SlowLog value) {
        this.startTime = value.startTime;
        this.userHost = value.userHost;
        this.queryTime = value.queryTime;
        this.lockTime = value.lockTime;
        this.rowsSent = value.rowsSent;
        this.rowsExamined = value.rowsExamined;
        this.db = value.db;
        this.lastInsertId = value.lastInsertId;
        this.insertId = value.insertId;
        this.serverId = value.serverId;
        this.sqlText = value.sqlText;
    }

    public SlowLog(
        Timestamp startTime,
        String    userHost,
        Time      queryTime,
        Time      lockTime,
        Integer   rowsSent,
        Integer   rowsExamined,
        String    db,
        Integer   lastInsertId,
        Integer   insertId,
        UInteger  serverId,
        String    sqlText
    ) {
        this.startTime = startTime;
        this.userHost = userHost;
        this.queryTime = queryTime;
        this.lockTime = lockTime;
        this.rowsSent = rowsSent;
        this.rowsExamined = rowsExamined;
        this.db = db;
        this.lastInsertId = lastInsertId;
        this.insertId = insertId;
        this.serverId = serverId;
        this.sqlText = sqlText;
    }

    public Timestamp getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getUserHost() {
        return this.userHost;
    }

    public void setUserHost(String userHost) {
        this.userHost = userHost;
    }

    public Time getQueryTime() {
        return this.queryTime;
    }

    public void setQueryTime(Time queryTime) {
        this.queryTime = queryTime;
    }

    public Time getLockTime() {
        return this.lockTime;
    }

    public void setLockTime(Time lockTime) {
        this.lockTime = lockTime;
    }

    public Integer getRowsSent() {
        return this.rowsSent;
    }

    public void setRowsSent(Integer rowsSent) {
        this.rowsSent = rowsSent;
    }

    public Integer getRowsExamined() {
        return this.rowsExamined;
    }

    public void setRowsExamined(Integer rowsExamined) {
        this.rowsExamined = rowsExamined;
    }

    public String getDb() {
        return this.db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public Integer getLastInsertId() {
        return this.lastInsertId;
    }

    public void setLastInsertId(Integer lastInsertId) {
        this.lastInsertId = lastInsertId;
    }

    public Integer getInsertId() {
        return this.insertId;
    }

    public void setInsertId(Integer insertId) {
        this.insertId = insertId;
    }

    public UInteger getServerId() {
        return this.serverId;
    }

    public void setServerId(UInteger serverId) {
        this.serverId = serverId;
    }

    public String getSqlText() {
        return this.sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SlowLog (");

        sb.append(startTime);
        sb.append(", ").append(userHost);
        sb.append(", ").append(queryTime);
        sb.append(", ").append(lockTime);
        sb.append(", ").append(rowsSent);
        sb.append(", ").append(rowsExamined);
        sb.append(", ").append(db);
        sb.append(", ").append(lastInsertId);
        sb.append(", ").append(insertId);
        sb.append(", ").append(serverId);
        sb.append(", ").append(sqlText);

        sb.append(")");
        return sb.toString();
    }
}
