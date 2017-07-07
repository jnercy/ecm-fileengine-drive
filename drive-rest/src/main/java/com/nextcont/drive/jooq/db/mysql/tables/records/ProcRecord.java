/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.records;


import com.nextcont.drive.jooq.db.mysql.enums.ProcIsDeterministic;
import com.nextcont.drive.jooq.db.mysql.enums.ProcLanguage;
import com.nextcont.drive.jooq.db.mysql.enums.ProcSecurityType;
import com.nextcont.drive.jooq.db.mysql.enums.ProcSqlDataAccess;
import com.nextcont.drive.jooq.db.mysql.enums.ProcType;
import com.nextcont.drive.jooq.db.mysql.tables.Proc;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record20;
import org.jooq.Record3;
import org.jooq.Row20;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Stored Procedures
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProcRecord extends UpdatableRecordImpl<ProcRecord> implements Record20<String, String, ProcType, String, ProcLanguage, ProcSqlDataAccess, ProcIsDeterministic, ProcSecurityType, byte[], byte[], byte[], String, Timestamp, Timestamp, String, String, String, String, String, byte[]> {

    private static final long serialVersionUID = -1062526521;

    /**
     * Setter for <code>mysql.proc.db</code>.
     */
    public void setDb(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>mysql.proc.db</code>.
     */
    public String getDb() {
        return (String) get(0);
    }

    /**
     * Setter for <code>mysql.proc.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>mysql.proc.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>mysql.proc.type</code>.
     */
    public void setType(ProcType value) {
        set(2, value);
    }

    /**
     * Getter for <code>mysql.proc.type</code>.
     */
    public ProcType getType() {
        return (ProcType) get(2);
    }

    /**
     * Setter for <code>mysql.proc.specific_name</code>.
     */
    public void setSpecificName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>mysql.proc.specific_name</code>.
     */
    public String getSpecificName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>mysql.proc.language</code>.
     */
    public void setLanguage(ProcLanguage value) {
        set(4, value);
    }

    /**
     * Getter for <code>mysql.proc.language</code>.
     */
    public ProcLanguage getLanguage() {
        return (ProcLanguage) get(4);
    }

    /**
     * Setter for <code>mysql.proc.sql_data_access</code>.
     */
    public void setSqlDataAccess(ProcSqlDataAccess value) {
        set(5, value);
    }

    /**
     * Getter for <code>mysql.proc.sql_data_access</code>.
     */
    public ProcSqlDataAccess getSqlDataAccess() {
        return (ProcSqlDataAccess) get(5);
    }

    /**
     * Setter for <code>mysql.proc.is_deterministic</code>.
     */
    public void setIsDeterministic(ProcIsDeterministic value) {
        set(6, value);
    }

    /**
     * Getter for <code>mysql.proc.is_deterministic</code>.
     */
    public ProcIsDeterministic getIsDeterministic() {
        return (ProcIsDeterministic) get(6);
    }

    /**
     * Setter for <code>mysql.proc.security_type</code>.
     */
    public void setSecurityType(ProcSecurityType value) {
        set(7, value);
    }

    /**
     * Getter for <code>mysql.proc.security_type</code>.
     */
    public ProcSecurityType getSecurityType() {
        return (ProcSecurityType) get(7);
    }

    /**
     * Setter for <code>mysql.proc.param_list</code>.
     */
    public void setParamList(byte... value) {
        set(8, value);
    }

    /**
     * Getter for <code>mysql.proc.param_list</code>.
     */
    public byte[] getParamList() {
        return (byte[]) get(8);
    }

    /**
     * Setter for <code>mysql.proc.returns</code>.
     */
    public void setReturns(byte... value) {
        set(9, value);
    }

    /**
     * Getter for <code>mysql.proc.returns</code>.
     */
    public byte[] getReturns() {
        return (byte[]) get(9);
    }

    /**
     * Setter for <code>mysql.proc.body</code>.
     */
    public void setBody(byte... value) {
        set(10, value);
    }

    /**
     * Getter for <code>mysql.proc.body</code>.
     */
    public byte[] getBody() {
        return (byte[]) get(10);
    }

    /**
     * Setter for <code>mysql.proc.definer</code>.
     */
    public void setDefiner(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>mysql.proc.definer</code>.
     */
    public String getDefiner() {
        return (String) get(11);
    }

    /**
     * Setter for <code>mysql.proc.created</code>.
     */
    public void setCreated(Timestamp value) {
        set(12, value);
    }

    /**
     * Getter for <code>mysql.proc.created</code>.
     */
    public Timestamp getCreated() {
        return (Timestamp) get(12);
    }

    /**
     * Setter for <code>mysql.proc.modified</code>.
     */
    public void setModified(Timestamp value) {
        set(13, value);
    }

    /**
     * Getter for <code>mysql.proc.modified</code>.
     */
    public Timestamp getModified() {
        return (Timestamp) get(13);
    }

    /**
     * Setter for <code>mysql.proc.sql_mode</code>.
     */
    public void setSqlMode(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>mysql.proc.sql_mode</code>.
     */
    public String getSqlMode() {
        return (String) get(14);
    }

    /**
     * Setter for <code>mysql.proc.comment</code>.
     */
    public void setComment(String value) {
        set(15, value);
    }

    /**
     * Getter for <code>mysql.proc.comment</code>.
     */
    public String getComment() {
        return (String) get(15);
    }

    /**
     * Setter for <code>mysql.proc.character_set_client</code>.
     */
    public void setCharacterSetClient(String value) {
        set(16, value);
    }

    /**
     * Getter for <code>mysql.proc.character_set_client</code>.
     */
    public String getCharacterSetClient() {
        return (String) get(16);
    }

    /**
     * Setter for <code>mysql.proc.collation_connection</code>.
     */
    public void setCollationConnection(String value) {
        set(17, value);
    }

    /**
     * Getter for <code>mysql.proc.collation_connection</code>.
     */
    public String getCollationConnection() {
        return (String) get(17);
    }

    /**
     * Setter for <code>mysql.proc.db_collation</code>.
     */
    public void setDbCollation(String value) {
        set(18, value);
    }

    /**
     * Getter for <code>mysql.proc.db_collation</code>.
     */
    public String getDbCollation() {
        return (String) get(18);
    }

    /**
     * Setter for <code>mysql.proc.body_utf8</code>.
     */
    public void setBodyUtf8(byte... value) {
        set(19, value);
    }

    /**
     * Getter for <code>mysql.proc.body_utf8</code>.
     */
    public byte[] getBodyUtf8() {
        return (byte[]) get(19);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record3<String, String, ProcType> key() {
        return (Record3) super.key();
    }

    // -------------------------------------------------------------------------
    // Record20 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row20<String, String, ProcType, String, ProcLanguage, ProcSqlDataAccess, ProcIsDeterministic, ProcSecurityType, byte[], byte[], byte[], String, Timestamp, Timestamp, String, String, String, String, String, byte[]> fieldsRow() {
        return (Row20) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row20<String, String, ProcType, String, ProcLanguage, ProcSqlDataAccess, ProcIsDeterministic, ProcSecurityType, byte[], byte[], byte[], String, Timestamp, Timestamp, String, String, String, String, String, byte[]> valuesRow() {
        return (Row20) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Proc.PROC.DB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Proc.PROC.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ProcType> field3() {
        return Proc.PROC.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Proc.PROC.SPECIFIC_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ProcLanguage> field5() {
        return Proc.PROC.LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ProcSqlDataAccess> field6() {
        return Proc.PROC.SQL_DATA_ACCESS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ProcIsDeterministic> field7() {
        return Proc.PROC.IS_DETERMINISTIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ProcSecurityType> field8() {
        return Proc.PROC.SECURITY_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<byte[]> field9() {
        return Proc.PROC.PARAM_LIST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<byte[]> field10() {
        return Proc.PROC.RETURNS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<byte[]> field11() {
        return Proc.PROC.BODY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return Proc.PROC.DEFINER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field13() {
        return Proc.PROC.CREATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field14() {
        return Proc.PROC.MODIFIED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return Proc.PROC.SQL_MODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field16() {
        return Proc.PROC.COMMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field17() {
        return Proc.PROC.CHARACTER_SET_CLIENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field18() {
        return Proc.PROC.COLLATION_CONNECTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field19() {
        return Proc.PROC.DB_COLLATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<byte[]> field20() {
        return Proc.PROC.BODY_UTF8;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getDb();
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
    public ProcType value3() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getSpecificName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcLanguage value5() {
        return getLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcSqlDataAccess value6() {
        return getSqlDataAccess();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcIsDeterministic value7() {
        return getIsDeterministic();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcSecurityType value8() {
        return getSecurityType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] value9() {
        return getParamList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] value10() {
        return getReturns();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] value11() {
        return getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getDefiner();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value13() {
        return getCreated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value14() {
        return getModified();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getSqlMode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value16() {
        return getComment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value17() {
        return getCharacterSetClient();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value18() {
        return getCollationConnection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value19() {
        return getDbCollation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] value20() {
        return getBodyUtf8();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value1(String value) {
        setDb(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value3(ProcType value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value4(String value) {
        setSpecificName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value5(ProcLanguage value) {
        setLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value6(ProcSqlDataAccess value) {
        setSqlDataAccess(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value7(ProcIsDeterministic value) {
        setIsDeterministic(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value8(ProcSecurityType value) {
        setSecurityType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value9(byte... value) {
        setParamList(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value10(byte... value) {
        setReturns(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value11(byte... value) {
        setBody(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value12(String value) {
        setDefiner(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value13(Timestamp value) {
        setCreated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value14(Timestamp value) {
        setModified(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value15(String value) {
        setSqlMode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value16(String value) {
        setComment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value17(String value) {
        setCharacterSetClient(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value18(String value) {
        setCollationConnection(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value19(String value) {
        setDbCollation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord value20(byte... value) {
        setBodyUtf8(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcRecord values(String value1, String value2, ProcType value3, String value4, ProcLanguage value5, ProcSqlDataAccess value6, ProcIsDeterministic value7, ProcSecurityType value8, byte[] value9, byte[] value10, byte[] value11, String value12, Timestamp value13, Timestamp value14, String value15, String value16, String value17, String value18, String value19, byte[] value20) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProcRecord
     */
    public ProcRecord() {
        super(Proc.PROC);
    }

    /**
     * Create a detached, initialised ProcRecord
     */
    public ProcRecord(String db, String name, ProcType type, String specificName, ProcLanguage language, ProcSqlDataAccess sqlDataAccess, ProcIsDeterministic isDeterministic, ProcSecurityType securityType, byte[] paramList, byte[] returns, byte[] body, String definer, Timestamp created, Timestamp modified, String sqlMode, String comment, String characterSetClient, String collationConnection, String dbCollation, byte[] bodyUtf8) {
        super(Proc.PROC);

        set(0, db);
        set(1, name);
        set(2, type);
        set(3, specificName);
        set(4, language);
        set(5, sqlDataAccess);
        set(6, isDeterministic);
        set(7, securityType);
        set(8, paramList);
        set(9, returns);
        set(10, body);
        set(11, definer);
        set(12, created);
        set(13, modified);
        set(14, sqlMode);
        set(15, comment);
        set(16, characterSetClient);
        set(17, collationConnection);
        set(18, dbCollation);
        set(19, bodyUtf8);
    }
}
