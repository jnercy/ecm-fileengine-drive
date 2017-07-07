/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * MySQL Foreign Servers table
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Servers implements Serializable {

    private static final long serialVersionUID = -850980107;

    private String  serverName;
    private String  host;
    private String  db;
    private String  username;
    private String  password;
    private Integer port;
    private String  socket;
    private String  wrapper;
    private String  owner;

    public Servers() {}

    public Servers(Servers value) {
        this.serverName = value.serverName;
        this.host = value.host;
        this.db = value.db;
        this.username = value.username;
        this.password = value.password;
        this.port = value.port;
        this.socket = value.socket;
        this.wrapper = value.wrapper;
        this.owner = value.owner;
    }

    public Servers(
        String  serverName,
        String  host,
        String  db,
        String  username,
        String  password,
        Integer port,
        String  socket,
        String  wrapper,
        String  owner
    ) {
        this.serverName = serverName;
        this.host = host;
        this.db = db;
        this.username = username;
        this.password = password;
        this.port = port;
        this.socket = socket;
        this.wrapper = wrapper;
        this.owner = owner;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getSocket() {
        return this.socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getWrapper() {
        return this.wrapper;
    }

    public void setWrapper(String wrapper) {
        this.wrapper = wrapper;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Servers (");

        sb.append(serverName);
        sb.append(", ").append(host);
        sb.append(", ").append(db);
        sb.append(", ").append(username);
        sb.append(", ").append(password);
        sb.append(", ").append(port);
        sb.append(", ").append(socket);
        sb.append(", ").append(wrapper);
        sb.append(", ").append(owner);

        sb.append(")");
        return sb.toString();
    }
}
