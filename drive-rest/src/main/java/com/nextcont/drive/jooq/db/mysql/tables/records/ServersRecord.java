/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.records;


import com.nextcont.drive.jooq.db.mysql.tables.Servers;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;


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
public class ServersRecord extends UpdatableRecordImpl<ServersRecord> implements Record9<String, String, String, String, String, Integer, String, String, String> {

    private static final long serialVersionUID = 485803732;

    /**
     * Setter for <code>mysql.servers.Server_name</code>.
     */
    public void setServerName(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>mysql.servers.Server_name</code>.
     */
    public String getServerName() {
        return (String) get(0);
    }

    /**
     * Setter for <code>mysql.servers.Host</code>.
     */
    public void setHost(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>mysql.servers.Host</code>.
     */
    public String getHost() {
        return (String) get(1);
    }

    /**
     * Setter for <code>mysql.servers.Db</code>.
     */
    public void setDb(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>mysql.servers.Db</code>.
     */
    public String getDb() {
        return (String) get(2);
    }

    /**
     * Setter for <code>mysql.servers.Username</code>.
     */
    public void setUsername(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>mysql.servers.Username</code>.
     */
    public String getUsername() {
        return (String) get(3);
    }

    /**
     * Setter for <code>mysql.servers.Password</code>.
     */
    public void setPassword(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>mysql.servers.Password</code>.
     */
    public String getPassword() {
        return (String) get(4);
    }

    /**
     * Setter for <code>mysql.servers.Port</code>.
     */
    public void setPort(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>mysql.servers.Port</code>.
     */
    public Integer getPort() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>mysql.servers.Socket</code>.
     */
    public void setSocket(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>mysql.servers.Socket</code>.
     */
    public String getSocket() {
        return (String) get(6);
    }

    /**
     * Setter for <code>mysql.servers.Wrapper</code>.
     */
    public void setWrapper(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>mysql.servers.Wrapper</code>.
     */
    public String getWrapper() {
        return (String) get(7);
    }

    /**
     * Setter for <code>mysql.servers.Owner</code>.
     */
    public void setOwner(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>mysql.servers.Owner</code>.
     */
    public String getOwner() {
        return (String) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<String, String, String, String, String, Integer, String, String, String> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<String, String, String, String, String, Integer, String, String, String> valuesRow() {
        return (Row9) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Servers.SERVERS.SERVER_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Servers.SERVERS.HOST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Servers.SERVERS.DB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Servers.SERVERS.USERNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Servers.SERVERS.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return Servers.SERVERS.PORT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Servers.SERVERS.SOCKET;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Servers.SERVERS.WRAPPER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return Servers.SERVERS.OWNER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getServerName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getHost();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getDb();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getUsername();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getPort();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getSocket();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getWrapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getOwner();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServersRecord value1(String value) {
        setServerName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServersRecord value2(String value) {
        setHost(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServersRecord value3(String value) {
        setDb(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServersRecord value4(String value) {
        setUsername(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServersRecord value5(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServersRecord value6(Integer value) {
        setPort(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServersRecord value7(String value) {
        setSocket(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServersRecord value8(String value) {
        setWrapper(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServersRecord value9(String value) {
        setOwner(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServersRecord values(String value1, String value2, String value3, String value4, String value5, Integer value6, String value7, String value8, String value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ServersRecord
     */
    public ServersRecord() {
        super(Servers.SERVERS);
    }

    /**
     * Create a detached, initialised ServersRecord
     */
    public ServersRecord(String serverName, String host, String db, String username, String password, Integer port, String socket, String wrapper, String owner) {
        super(Servers.SERVERS);

        set(0, serverName);
        set(1, host);
        set(2, db);
        set(3, username);
        set(4, password);
        set(5, port);
        set(6, socket);
        set(7, wrapper);
        set(8, owner);
    }
}
