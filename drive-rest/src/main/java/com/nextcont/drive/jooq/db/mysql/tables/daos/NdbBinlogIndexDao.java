/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.daos;


import com.nextcont.drive.jooq.db.mysql.tables.NdbBinlogIndex;
import com.nextcont.drive.jooq.db.mysql.tables.records.NdbBinlogIndexRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
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
public class NdbBinlogIndexDao extends DAOImpl<NdbBinlogIndexRecord, com.nextcont.drive.jooq.db.mysql.tables.pojos.NdbBinlogIndex, ULong> {

    /**
     * Create a new NdbBinlogIndexDao without any configuration
     */
    public NdbBinlogIndexDao() {
        super(NdbBinlogIndex.NDB_BINLOG_INDEX, com.nextcont.drive.jooq.db.mysql.tables.pojos.NdbBinlogIndex.class);
    }

    /**
     * Create a new NdbBinlogIndexDao with an attached configuration
     */
    public NdbBinlogIndexDao(Configuration configuration) {
        super(NdbBinlogIndex.NDB_BINLOG_INDEX, com.nextcont.drive.jooq.db.mysql.tables.pojos.NdbBinlogIndex.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ULong getId(com.nextcont.drive.jooq.db.mysql.tables.pojos.NdbBinlogIndex object) {
        return object.getEpoch();
    }

    /**
     * Fetch records that have <code>Position IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.NdbBinlogIndex> fetchByPosition(ULong... values) {
        return fetch(NdbBinlogIndex.NDB_BINLOG_INDEX.POSITION, values);
    }

    /**
     * Fetch records that have <code>File IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.NdbBinlogIndex> fetchByFile(String... values) {
        return fetch(NdbBinlogIndex.NDB_BINLOG_INDEX.FILE, values);
    }

    /**
     * Fetch records that have <code>epoch IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.NdbBinlogIndex> fetchByEpoch(ULong... values) {
        return fetch(NdbBinlogIndex.NDB_BINLOG_INDEX.EPOCH, values);
    }

    /**
     * Fetch a unique record that has <code>epoch = value</code>
     */
    public com.nextcont.drive.jooq.db.mysql.tables.pojos.NdbBinlogIndex fetchOneByEpoch(ULong value) {
        return fetchOne(NdbBinlogIndex.NDB_BINLOG_INDEX.EPOCH, value);
    }

    /**
     * Fetch records that have <code>inserts IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.NdbBinlogIndex> fetchByInserts(ULong... values) {
        return fetch(NdbBinlogIndex.NDB_BINLOG_INDEX.INSERTS, values);
    }

    /**
     * Fetch records that have <code>updates IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.NdbBinlogIndex> fetchByUpdates(ULong... values) {
        return fetch(NdbBinlogIndex.NDB_BINLOG_INDEX.UPDATES, values);
    }

    /**
     * Fetch records that have <code>deletes IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.NdbBinlogIndex> fetchByDeletes(ULong... values) {
        return fetch(NdbBinlogIndex.NDB_BINLOG_INDEX.DELETES, values);
    }

    /**
     * Fetch records that have <code>schemaops IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.NdbBinlogIndex> fetchBySchemaops(ULong... values) {
        return fetch(NdbBinlogIndex.NDB_BINLOG_INDEX.SCHEMAOPS, values);
    }
}
