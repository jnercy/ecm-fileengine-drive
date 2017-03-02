/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.ecm_trans.tables;


import com.nextcont.drive.jooq.db.ecm_trans.EcmTrans;
import com.nextcont.drive.jooq.db.ecm_trans.Keys;
import com.nextcont.drive.jooq.db.ecm_trans.tables.records.MeqauploadRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class Meqaupload extends TableImpl<MeqauploadRecord> {

    private static final long serialVersionUID = -1860377675;

    /**
     * The reference instance of <code>ecm_trans.meqaupload</code>
     */
    public static final Meqaupload MEQAUPLOAD = new Meqaupload();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MeqauploadRecord> getRecordType() {
        return MeqauploadRecord.class;
    }

    /**
     * The column <code>ecm_trans.meqaupload.globalId</code>.
     */
    public final TableField<MeqauploadRecord, String> GLOBALID = createField("globalId", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

    /**
     * The column <code>ecm_trans.meqaupload.filename</code>.
     */
    public final TableField<MeqauploadRecord, String> FILENAME = createField("filename", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * The column <code>ecm_trans.meqaupload.mimetype</code>.
     */
    public final TableField<MeqauploadRecord, String> MIMETYPE = createField("mimetype", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

    /**
     * The column <code>ecm_trans.meqaupload.contentlength</code>.
     */
    public final TableField<MeqauploadRecord, Integer> CONTENTLENGTH = createField("contentlength", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>ecm_trans.meqaupload.shardsize</code>.
     */
    public final TableField<MeqauploadRecord, Integer> SHARDSIZE = createField("shardsize", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>ecm_trans.meqaupload.createtime</code>.
     */
    public final TableField<MeqauploadRecord, Timestamp> CREATETIME = createField("createtime", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>ecm_trans.meqaupload.updatetime</code>.
     */
    public final TableField<MeqauploadRecord, Timestamp> UPDATETIME = createField("updatetime", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>ecm_trans.meqaupload.currentshard</code>.
     */
    public final TableField<MeqauploadRecord, Integer> CURRENTSHARD = createField("currentshard", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>ecm_trans.meqaupload.partfilesize</code>.
     */
    public final TableField<MeqauploadRecord, Integer> PARTFILESIZE = createField("partfilesize", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>ecm_trans.meqaupload</code> table reference
     */
    public Meqaupload() {
        this("meqaupload", null);
    }

    /**
     * Create an aliased <code>ecm_trans.meqaupload</code> table reference
     */
    public Meqaupload(String alias) {
        this(alias, MEQAUPLOAD);
    }

    private Meqaupload(String alias, Table<MeqauploadRecord> aliased) {
        this(alias, aliased, null);
    }

    private Meqaupload(String alias, Table<MeqauploadRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return EcmTrans.ECM_TRANS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<MeqauploadRecord> getPrimaryKey() {
        return Keys.KEY_MEQAUPLOAD_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<MeqauploadRecord>> getKeys() {
        return Arrays.<UniqueKey<MeqauploadRecord>>asList(Keys.KEY_MEQAUPLOAD_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Meqaupload as(String alias) {
        return new Meqaupload(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Meqaupload rename(String name) {
        return new Meqaupload(name, null);
    }
}