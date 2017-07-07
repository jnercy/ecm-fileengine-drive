/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.information_schema.tables.records;


import com.nextcont.drive.jooq.db.information_schema.tables.Processlist;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProcesslistRecord extends TableRecordImpl<ProcesslistRecord> implements Record8<Long, String, String, String, String, Integer, String, String> {

    private static final long serialVersionUID = 453946924;

    /**
     * Setter for <code>information_schema.PROCESSLIST.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>information_schema.PROCESSLIST.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>information_schema.PROCESSLIST.USER</code>.
     */
    public void setUser(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>information_schema.PROCESSLIST.USER</code>.
     */
    public String getUser() {
        return (String) get(1);
    }

    /**
     * Setter for <code>information_schema.PROCESSLIST.HOST</code>.
     */
    public void setHost(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>information_schema.PROCESSLIST.HOST</code>.
     */
    public String getHost() {
        return (String) get(2);
    }

    /**
     * Setter for <code>information_schema.PROCESSLIST.DB</code>.
     */
    public void setDb(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>information_schema.PROCESSLIST.DB</code>.
     */
    public String getDb() {
        return (String) get(3);
    }

    /**
     * Setter for <code>information_schema.PROCESSLIST.COMMAND</code>.
     */
    public void setCommand(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>information_schema.PROCESSLIST.COMMAND</code>.
     */
    public String getCommand() {
        return (String) get(4);
    }

    /**
     * Setter for <code>information_schema.PROCESSLIST.TIME</code>.
     */
    public void setTime(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>information_schema.PROCESSLIST.TIME</code>.
     */
    public Integer getTime() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>information_schema.PROCESSLIST.STATE</code>.
     */
    public void setState(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>information_schema.PROCESSLIST.STATE</code>.
     */
    public String getState() {
        return (String) get(6);
    }

    /**
     * Setter for <code>information_schema.PROCESSLIST.INFO</code>.
     */
    public void setInfo(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>information_schema.PROCESSLIST.INFO</code>.
     */
    public String getInfo() {
        return (String) get(7);
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Long, String, String, String, String, Integer, String, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Long, String, String, String, String, Integer, String, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Processlist.PROCESSLIST.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Processlist.PROCESSLIST.USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Processlist.PROCESSLIST.HOST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Processlist.PROCESSLIST.DB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Processlist.PROCESSLIST.COMMAND;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return Processlist.PROCESSLIST.TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Processlist.PROCESSLIST.STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Processlist.PROCESSLIST.INFO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getHost();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getDb();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getCommand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getInfo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcesslistRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcesslistRecord value2(String value) {
        setUser(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcesslistRecord value3(String value) {
        setHost(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcesslistRecord value4(String value) {
        setDb(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcesslistRecord value5(String value) {
        setCommand(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcesslistRecord value6(Integer value) {
        setTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcesslistRecord value7(String value) {
        setState(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcesslistRecord value8(String value) {
        setInfo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcesslistRecord values(Long value1, String value2, String value3, String value4, String value5, Integer value6, String value7, String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProcesslistRecord
     */
    public ProcesslistRecord() {
        super(Processlist.PROCESSLIST);
    }

    /**
     * Create a detached, initialised ProcesslistRecord
     */
    public ProcesslistRecord(Long id, String user, String host, String db, String command, Integer time, String state, String info) {
        super(Processlist.PROCESSLIST);

        set(0, id);
        set(1, user);
        set(2, host);
        set(3, db);
        set(4, command);
        set(5, time);
        set(6, state);
        set(7, info);
    }
}
