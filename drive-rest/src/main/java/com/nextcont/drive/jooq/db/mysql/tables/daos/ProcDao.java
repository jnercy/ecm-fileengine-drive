/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.daos;


import com.nextcont.drive.jooq.db.mysql.enums.ProcIsDeterministic;
import com.nextcont.drive.jooq.db.mysql.enums.ProcLanguage;
import com.nextcont.drive.jooq.db.mysql.enums.ProcSecurityType;
import com.nextcont.drive.jooq.db.mysql.enums.ProcSqlDataAccess;
import com.nextcont.drive.jooq.db.mysql.enums.ProcType;
import com.nextcont.drive.jooq.db.mysql.tables.Proc;
import com.nextcont.drive.jooq.db.mysql.tables.records.ProcRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.Record3;
import org.jooq.impl.DAOImpl;


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
public class ProcDao extends DAOImpl<ProcRecord, com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc, Record3<String, String, ProcType>> {

    /**
     * Create a new ProcDao without any configuration
     */
    public ProcDao() {
        super(Proc.PROC, com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc.class);
    }

    /**
     * Create a new ProcDao with an attached configuration
     */
    public ProcDao(Configuration configuration) {
        super(Proc.PROC, com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Record3<String, String, ProcType> getId(com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc object) {
        return compositeKeyRecord(object.getDb(), object.getName(), object.getType());
    }

    /**
     * Fetch records that have <code>db IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByDb(String... values) {
        return fetch(Proc.PROC.DB, values);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByName(String... values) {
        return fetch(Proc.PROC.NAME, values);
    }

    /**
     * Fetch records that have <code>type IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByType(ProcType... values) {
        return fetch(Proc.PROC.TYPE, values);
    }

    /**
     * Fetch records that have <code>specific_name IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchBySpecificName(String... values) {
        return fetch(Proc.PROC.SPECIFIC_NAME, values);
    }

    /**
     * Fetch records that have <code>language IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByLanguage(ProcLanguage... values) {
        return fetch(Proc.PROC.LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>sql_data_access IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchBySqlDataAccess(ProcSqlDataAccess... values) {
        return fetch(Proc.PROC.SQL_DATA_ACCESS, values);
    }

    /**
     * Fetch records that have <code>is_deterministic IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByIsDeterministic(ProcIsDeterministic... values) {
        return fetch(Proc.PROC.IS_DETERMINISTIC, values);
    }

    /**
     * Fetch records that have <code>security_type IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchBySecurityType(ProcSecurityType... values) {
        return fetch(Proc.PROC.SECURITY_TYPE, values);
    }

    /**
     * Fetch records that have <code>param_list IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByParamList(byte[]... values) {
        return fetch(Proc.PROC.PARAM_LIST, values);
    }

    /**
     * Fetch records that have <code>returns IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByReturns(byte[]... values) {
        return fetch(Proc.PROC.RETURNS, values);
    }

    /**
     * Fetch records that have <code>body IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByBody(byte[]... values) {
        return fetch(Proc.PROC.BODY, values);
    }

    /**
     * Fetch records that have <code>definer IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByDefiner(String... values) {
        return fetch(Proc.PROC.DEFINER, values);
    }

    /**
     * Fetch records that have <code>created IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByCreated(Timestamp... values) {
        return fetch(Proc.PROC.CREATED, values);
    }

    /**
     * Fetch records that have <code>modified IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByModified(Timestamp... values) {
        return fetch(Proc.PROC.MODIFIED, values);
    }

    /**
     * Fetch records that have <code>sql_mode IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchBySqlMode(String... values) {
        return fetch(Proc.PROC.SQL_MODE, values);
    }

    /**
     * Fetch records that have <code>comment IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByComment(String... values) {
        return fetch(Proc.PROC.COMMENT, values);
    }

    /**
     * Fetch records that have <code>character_set_client IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByCharacterSetClient(String... values) {
        return fetch(Proc.PROC.CHARACTER_SET_CLIENT, values);
    }

    /**
     * Fetch records that have <code>collation_connection IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByCollationConnection(String... values) {
        return fetch(Proc.PROC.COLLATION_CONNECTION, values);
    }

    /**
     * Fetch records that have <code>db_collation IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByDbCollation(String... values) {
        return fetch(Proc.PROC.DB_COLLATION, values);
    }

    /**
     * Fetch records that have <code>body_utf8 IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.Proc> fetchByBodyUtf8(byte[]... values) {
        return fetch(Proc.PROC.BODY_UTF8, values);
    }
}
