/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.records;


import com.nextcont.drive.jooq.db.mysql.enums.FuncType;
import com.nextcont.drive.jooq.db.mysql.tables.Func;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * User defined functions
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FuncRecord extends UpdatableRecordImpl<FuncRecord> implements Record4<String, Byte, String, FuncType> {

    private static final long serialVersionUID = -435498306;

    /**
     * Setter for <code>mysql.func.name</code>.
     */
    public void setName(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>mysql.func.name</code>.
     */
    public String getName() {
        return (String) get(0);
    }

    /**
     * Setter for <code>mysql.func.ret</code>.
     */
    public void setRet(Byte value) {
        set(1, value);
    }

    /**
     * Getter for <code>mysql.func.ret</code>.
     */
    public Byte getRet() {
        return (Byte) get(1);
    }

    /**
     * Setter for <code>mysql.func.dl</code>.
     */
    public void setDl(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>mysql.func.dl</code>.
     */
    public String getDl() {
        return (String) get(2);
    }

    /**
     * Setter for <code>mysql.func.type</code>.
     */
    public void setType(FuncType value) {
        set(3, value);
    }

    /**
     * Getter for <code>mysql.func.type</code>.
     */
    public FuncType getType() {
        return (FuncType) get(3);
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
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<String, Byte, String, FuncType> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<String, Byte, String, FuncType> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Func.FUNC.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Byte> field2() {
        return Func.FUNC.RET;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Func.FUNC.DL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<FuncType> field4() {
        return Func.FUNC.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte value2() {
        return getRet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getDl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FuncType value4() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FuncRecord value1(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FuncRecord value2(Byte value) {
        setRet(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FuncRecord value3(String value) {
        setDl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FuncRecord value4(FuncType value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FuncRecord values(String value1, Byte value2, String value3, FuncType value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FuncRecord
     */
    public FuncRecord() {
        super(Func.FUNC);
    }

    /**
     * Create a detached, initialised FuncRecord
     */
    public FuncRecord(String name, Byte ret, String dl, FuncType type) {
        super(Func.FUNC);

        set(0, name);
        set(1, ret);
        set(2, dl);
        set(3, type);
    }
}
