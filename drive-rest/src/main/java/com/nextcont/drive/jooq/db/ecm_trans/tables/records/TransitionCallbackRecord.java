/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.ecm_trans.tables.records;


import com.nextcont.drive.jooq.db.ecm_trans.tables.TransitionCallback;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record6;
import org.jooq.Row6;
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
public class TransitionCallbackRecord extends TableRecordImpl<TransitionCallbackRecord> implements Record6<String, String, String, String, Timestamp, Byte> {

    private static final long serialVersionUID = 1565174869;

    /**
     * Setter for <code>ecm_trans.transition_callback.globalId</code>.
     */
    public void setGlobalid(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_callback.globalId</code>.
     */
    public String getGlobalid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>ecm_trans.transition_callback.status</code>.
     */
    public void setStatus(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_callback.status</code>.
     */
    public String getStatus() {
        return (String) get(1);
    }

    /**
     * Setter for <code>ecm_trans.transition_callback.info</code>.
     */
    public void setInfo(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_callback.info</code>.
     */
    public String getInfo() {
        return (String) get(2);
    }

    /**
     * Setter for <code>ecm_trans.transition_callback.callbackUrl</code>.
     */
    public void setCallbackurl(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_callback.callbackUrl</code>.
     */
    public String getCallbackurl() {
        return (String) get(3);
    }

    /**
     * Setter for <code>ecm_trans.transition_callback.createTime</code>.
     */
    public void setCreatetime(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_callback.createTime</code>.
     */
    public Timestamp getCreatetime() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>ecm_trans.transition_callback.aggregationStatus</code>. 聚合状态
     */
    public void setAggregationstatus(Byte value) {
        set(5, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_callback.aggregationStatus</code>. 聚合状态
     */
    public Byte getAggregationstatus() {
        return (Byte) get(5);
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<String, String, String, String, Timestamp, Byte> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<String, String, String, String, Timestamp, Byte> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return TransitionCallback.TRANSITION_CALLBACK.GLOBALID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TransitionCallback.TRANSITION_CALLBACK.STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TransitionCallback.TRANSITION_CALLBACK.INFO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return TransitionCallback.TRANSITION_CALLBACK.CALLBACKURL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return TransitionCallback.TRANSITION_CALLBACK.CREATETIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Byte> field6() {
        return TransitionCallback.TRANSITION_CALLBACK.AGGREGATIONSTATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getGlobalid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getInfo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getCallbackurl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getCreatetime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte value6() {
        return getAggregationstatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionCallbackRecord value1(String value) {
        setGlobalid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionCallbackRecord value2(String value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionCallbackRecord value3(String value) {
        setInfo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionCallbackRecord value4(String value) {
        setCallbackurl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionCallbackRecord value5(Timestamp value) {
        setCreatetime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionCallbackRecord value6(Byte value) {
        setAggregationstatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionCallbackRecord values(String value1, String value2, String value3, String value4, Timestamp value5, Byte value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TransitionCallbackRecord
     */
    public TransitionCallbackRecord() {
        super(TransitionCallback.TRANSITION_CALLBACK);
    }

    /**
     * Create a detached, initialised TransitionCallbackRecord
     */
    public TransitionCallbackRecord(String globalid, String status, String info, String callbackurl, Timestamp createtime, Byte aggregationstatus) {
        super(TransitionCallback.TRANSITION_CALLBACK);

        set(0, globalid);
        set(1, status);
        set(2, info);
        set(3, callbackurl);
        set(4, createtime);
        set(5, aggregationstatus);
    }
}
