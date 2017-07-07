/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.records;


import com.nextcont.drive.jooq.db.mysql.tables.NdbBinlogIndex;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;


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
public class NdbBinlogIndexRecord extends UpdatableRecordImpl<NdbBinlogIndexRecord> implements Record7<ULong, String, ULong, ULong, ULong, ULong, ULong> {

    private static final long serialVersionUID = 697133220;

    /**
     * Setter for <code>mysql.ndb_binlog_index.Position</code>.
     */
    public void setPosition(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>mysql.ndb_binlog_index.Position</code>.
     */
    public ULong getPosition() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>mysql.ndb_binlog_index.File</code>.
     */
    public void setFile(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>mysql.ndb_binlog_index.File</code>.
     */
    public String getFile() {
        return (String) get(1);
    }

    /**
     * Setter for <code>mysql.ndb_binlog_index.epoch</code>.
     */
    public void setEpoch(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>mysql.ndb_binlog_index.epoch</code>.
     */
    public ULong getEpoch() {
        return (ULong) get(2);
    }

    /**
     * Setter for <code>mysql.ndb_binlog_index.inserts</code>.
     */
    public void setInserts(ULong value) {
        set(3, value);
    }

    /**
     * Getter for <code>mysql.ndb_binlog_index.inserts</code>.
     */
    public ULong getInserts() {
        return (ULong) get(3);
    }

    /**
     * Setter for <code>mysql.ndb_binlog_index.updates</code>.
     */
    public void setUpdates(ULong value) {
        set(4, value);
    }

    /**
     * Getter for <code>mysql.ndb_binlog_index.updates</code>.
     */
    public ULong getUpdates() {
        return (ULong) get(4);
    }

    /**
     * Setter for <code>mysql.ndb_binlog_index.deletes</code>.
     */
    public void setDeletes(ULong value) {
        set(5, value);
    }

    /**
     * Getter for <code>mysql.ndb_binlog_index.deletes</code>.
     */
    public ULong getDeletes() {
        return (ULong) get(5);
    }

    /**
     * Setter for <code>mysql.ndb_binlog_index.schemaops</code>.
     */
    public void setSchemaops(ULong value) {
        set(6, value);
    }

    /**
     * Getter for <code>mysql.ndb_binlog_index.schemaops</code>.
     */
    public ULong getSchemaops() {
        return (ULong) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<ULong, String, ULong, ULong, ULong, ULong, ULong> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<ULong, String, ULong, ULong, ULong, ULong, ULong> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field1() {
        return NdbBinlogIndex.NDB_BINLOG_INDEX.POSITION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return NdbBinlogIndex.NDB_BINLOG_INDEX.FILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field3() {
        return NdbBinlogIndex.NDB_BINLOG_INDEX.EPOCH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field4() {
        return NdbBinlogIndex.NDB_BINLOG_INDEX.INSERTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field5() {
        return NdbBinlogIndex.NDB_BINLOG_INDEX.UPDATES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field6() {
        return NdbBinlogIndex.NDB_BINLOG_INDEX.DELETES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field7() {
        return NdbBinlogIndex.NDB_BINLOG_INDEX.SCHEMAOPS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value1() {
        return getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value3() {
        return getEpoch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value4() {
        return getInserts();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value5() {
        return getUpdates();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value6() {
        return getDeletes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value7() {
        return getSchemaops();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NdbBinlogIndexRecord value1(ULong value) {
        setPosition(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NdbBinlogIndexRecord value2(String value) {
        setFile(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NdbBinlogIndexRecord value3(ULong value) {
        setEpoch(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NdbBinlogIndexRecord value4(ULong value) {
        setInserts(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NdbBinlogIndexRecord value5(ULong value) {
        setUpdates(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NdbBinlogIndexRecord value6(ULong value) {
        setDeletes(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NdbBinlogIndexRecord value7(ULong value) {
        setSchemaops(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NdbBinlogIndexRecord values(ULong value1, String value2, ULong value3, ULong value4, ULong value5, ULong value6, ULong value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached NdbBinlogIndexRecord
     */
    public NdbBinlogIndexRecord() {
        super(NdbBinlogIndex.NDB_BINLOG_INDEX);
    }

    /**
     * Create a detached, initialised NdbBinlogIndexRecord
     */
    public NdbBinlogIndexRecord(ULong position, String file, ULong epoch, ULong inserts, ULong updates, ULong deletes, ULong schemaops) {
        super(NdbBinlogIndex.NDB_BINLOG_INDEX);

        set(0, position);
        set(1, file);
        set(2, epoch);
        set(3, inserts);
        set(4, updates);
        set(5, deletes);
        set(6, schemaops);
    }
}