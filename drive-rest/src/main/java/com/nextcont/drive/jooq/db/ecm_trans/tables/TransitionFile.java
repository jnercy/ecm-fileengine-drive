/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.ecm_trans.tables;


import com.nextcont.drive.jooq.db.ecm_trans.EcmTrans;
import com.nextcont.drive.jooq.db.ecm_trans.tables.records.TransitionFileRecord;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
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
public class TransitionFile extends TableImpl<TransitionFileRecord> {

    private static final long serialVersionUID = -1600564593;

    /**
     * The reference instance of <code>ecm_trans.transition_file</code>
     */
    public static final TransitionFile TRANSITION_FILE = new TransitionFile();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TransitionFileRecord> getRecordType() {
        return TransitionFileRecord.class;
    }

    /**
     * The column <code>ecm_trans.transition_file.globalId</code>. 主表transition的globalid
     */
    public final TableField<TransitionFileRecord, String> GLOBALID = createField("globalId", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "主表transition的globalid");

    /**
     * The column <code>ecm_trans.transition_file.url</code>. 返回给caller的文件url
     */
    public final TableField<TransitionFileRecord, String> URL = createField("url", org.jooq.impl.SQLDataType.VARCHAR.length(500), this, "返回给caller的文件url");

    /**
     * The column <code>ecm_trans.transition_file.type</code>. 转换的文件类型:缩略图|预览图|主文件
     */
    public final TableField<TransitionFileRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR.length(20), this, "转换的文件类型:缩略图|预览图|主文件");

    /**
     * The column <code>ecm_trans.transition_file.duration</code>. 转换用时
     */
    public final TableField<TransitionFileRecord, Long> DURATION = createField("duration", org.jooq.impl.SQLDataType.BIGINT, this, "转换用时");

    /**
     * The column <code>ecm_trans.transition_file.location</code>. 存放位置：本地|Cloud
     */
    public final TableField<TransitionFileRecord, String> LOCATION = createField("location", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "存放位置：本地|Cloud");

    /**
     * The column <code>ecm_trans.transition_file.fullpath</code>. 对应存放位置的完整路径
     */
    public final TableField<TransitionFileRecord, String> FULLPATH = createField("fullpath", org.jooq.impl.SQLDataType.VARCHAR.length(500), this, "对应存放位置的完整路径");

    /**
     * The column <code>ecm_trans.transition_file.status</code>. 状态
     */
    public final TableField<TransitionFileRecord, String> STATUS = createField("status", org.jooq.impl.SQLDataType.VARCHAR.length(20), this, "状态");

    /**
     * The column <code>ecm_trans.transition_file.errormsg</code>. 错误信息
     */
    public final TableField<TransitionFileRecord, String> ERRORMSG = createField("errormsg", org.jooq.impl.SQLDataType.VARCHAR.length(10000), this, "错误信息");

    /**
     * Create a <code>ecm_trans.transition_file</code> table reference
     */
    public TransitionFile() {
        this("transition_file", null);
    }

    /**
     * Create an aliased <code>ecm_trans.transition_file</code> table reference
     */
    public TransitionFile(String alias) {
        this(alias, TRANSITION_FILE);
    }

    private TransitionFile(String alias, Table<TransitionFileRecord> aliased) {
        this(alias, aliased, null);
    }

    private TransitionFile(String alias, Table<TransitionFileRecord> aliased, Field<?>[] parameters) {
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
    public TransitionFile as(String alias) {
        return new TransitionFile(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TransitionFile rename(String name) {
        return new TransitionFile(name, null);
    }
}
