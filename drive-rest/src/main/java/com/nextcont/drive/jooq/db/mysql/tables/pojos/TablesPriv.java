/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


/**
 * Table privileges
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TablesPriv implements Serializable {

    private static final long serialVersionUID = -1758987063;

    private String    host;
    private String    db;
    private String    user;
    private String    tableName;
    private String    grantor;
    private Timestamp timestamp;
    private String    tablePriv;
    private String    columnPriv;

    public TablesPriv() {}

    public TablesPriv(TablesPriv value) {
        this.host = value.host;
        this.db = value.db;
        this.user = value.user;
        this.tableName = value.tableName;
        this.grantor = value.grantor;
        this.timestamp = value.timestamp;
        this.tablePriv = value.tablePriv;
        this.columnPriv = value.columnPriv;
    }

    public TablesPriv(
        String    host,
        String    db,
        String    user,
        String    tableName,
        String    grantor,
        Timestamp timestamp,
        String    tablePriv,
        String    columnPriv
    ) {
        this.host = host;
        this.db = db;
        this.user = user;
        this.tableName = tableName;
        this.grantor = grantor;
        this.timestamp = timestamp;
        this.tablePriv = tablePriv;
        this.columnPriv = columnPriv;
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

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getGrantor() {
        return this.grantor;
    }

    public void setGrantor(String grantor) {
        this.grantor = grantor;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTablePriv() {
        return this.tablePriv;
    }

    public void setTablePriv(String tablePriv) {
        this.tablePriv = tablePriv;
    }

    public String getColumnPriv() {
        return this.columnPriv;
    }

    public void setColumnPriv(String columnPriv) {
        this.columnPriv = columnPriv;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TablesPriv (");

        sb.append(host);
        sb.append(", ").append(db);
        sb.append(", ").append(user);
        sb.append(", ").append(tableName);
        sb.append(", ").append(grantor);
        sb.append(", ").append(timestamp);
        sb.append(", ").append(tablePriv);
        sb.append(", ").append(columnPriv);

        sb.append(")");
        return sb.toString();
    }
}
