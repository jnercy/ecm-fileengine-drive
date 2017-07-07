/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.records;


import com.nextcont.drive.jooq.db.mysql.tables.HelpKeyword;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * help keywords
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HelpKeywordRecord extends UpdatableRecordImpl<HelpKeywordRecord> implements Record2<UInteger, String> {

    private static final long serialVersionUID = -955946687;

    /**
     * Setter for <code>mysql.help_keyword.help_keyword_id</code>.
     */
    public void setHelpKeywordId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>mysql.help_keyword.help_keyword_id</code>.
     */
    public UInteger getHelpKeywordId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>mysql.help_keyword.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>mysql.help_keyword.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<UInteger, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<UInteger, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return HelpKeyword.HELP_KEYWORD.HELP_KEYWORD_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return HelpKeyword.HELP_KEYWORD.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getHelpKeywordId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpKeywordRecord value1(UInteger value) {
        setHelpKeywordId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpKeywordRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpKeywordRecord values(UInteger value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HelpKeywordRecord
     */
    public HelpKeywordRecord() {
        super(HelpKeyword.HELP_KEYWORD);
    }

    /**
     * Create a detached, initialised HelpKeywordRecord
     */
    public HelpKeywordRecord(UInteger helpKeywordId, String name) {
        super(HelpKeyword.HELP_KEYWORD);

        set(0, helpKeywordId);
        set(1, name);
    }
}
