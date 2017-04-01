/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.ecm_trans.tables.records;


import com.nextcont.drive.jooq.db.ecm_trans.tables.TransitionFile;

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
public class TransitionFileRecord extends TableRecordImpl<TransitionFileRecord> implements Record8<String, String, String, Long, String, String, String, String> {

    private static final long serialVersionUID = -560401521;

    /**
     * Setter for <code>ecm_trans.transition_file.globalId</code>. 主表transition的globalid
     */
    public void setGlobalid(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_file.globalId</code>. 主表transition的globalid
     */
    public String getGlobalid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>ecm_trans.transition_file.url</code>. 返回给caller的文件url
     */
    public void setUrl(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_file.url</code>. 返回给caller的文件url
     */
    public String getUrl() {
        return (String) get(1);
    }

    /**
     * Setter for <code>ecm_trans.transition_file.type</code>. 转换的文件类型:缩略图|预览图|主文件
     */
    public void setType(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_file.type</code>. 转换的文件类型:缩略图|预览图|主文件
     */
    public String getType() {
        return (String) get(2);
    }

    /**
     * Setter for <code>ecm_trans.transition_file.duration</code>. 转换用时
     */
    public void setDuration(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_file.duration</code>. 转换用时
     */
    public Long getDuration() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>ecm_trans.transition_file.location</code>. 存放位置：本地|Cloud
     */
    public void setLocation(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_file.location</code>. 存放位置：本地|Cloud
     */
    public String getLocation() {
        return (String) get(4);
    }

    /**
     * Setter for <code>ecm_trans.transition_file.fullpath</code>. 对应存放位置的完整路径
     */
    public void setFullpath(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_file.fullpath</code>. 对应存放位置的完整路径
     */
    public String getFullpath() {
        return (String) get(5);
    }

    /**
     * Setter for <code>ecm_trans.transition_file.status</code>. 状态
     */
    public void setStatus(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_file.status</code>. 状态
     */
    public String getStatus() {
        return (String) get(6);
    }

    /**
     * Setter for <code>ecm_trans.transition_file.errormsg</code>. 错误信息
     */
    public void setErrormsg(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>ecm_trans.transition_file.errormsg</code>. 错误信息
     */
    public String getErrormsg() {
        return (String) get(7);
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<String, String, String, Long, String, String, String, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<String, String, String, Long, String, String, String, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return TransitionFile.TRANSITION_FILE.GLOBALID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TransitionFile.TRANSITION_FILE.URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TransitionFile.TRANSITION_FILE.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field4() {
        return TransitionFile.TRANSITION_FILE.DURATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return TransitionFile.TRANSITION_FILE.LOCATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return TransitionFile.TRANSITION_FILE.FULLPATH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return TransitionFile.TRANSITION_FILE.STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return TransitionFile.TRANSITION_FILE.ERRORMSG;
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
        return getUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value4() {
        return getDuration();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getLocation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getFullpath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getErrormsg();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionFileRecord value1(String value) {
        setGlobalid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionFileRecord value2(String value) {
        setUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionFileRecord value3(String value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionFileRecord value4(Long value) {
        setDuration(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionFileRecord value5(String value) {
        setLocation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionFileRecord value6(String value) {
        setFullpath(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionFileRecord value7(String value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionFileRecord value8(String value) {
        setErrormsg(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransitionFileRecord values(String value1, String value2, String value3, Long value4, String value5, String value6, String value7, String value8) {
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
     * Create a detached TransitionFileRecord
     */
    public TransitionFileRecord() {
        super(TransitionFile.TRANSITION_FILE);
    }

    /**
     * Create a detached, initialised TransitionFileRecord
     */
    public TransitionFileRecord(String globalid, String url, String type, Long duration, String location, String fullpath, String status, String errormsg) {
        super(TransitionFile.TRANSITION_FILE);

        set(0, globalid);
        set(1, url);
        set(2, type);
        set(3, duration);
        set(4, location);
        set(5, fullpath);
        set(6, status);
        set(7, errormsg);
    }
}
