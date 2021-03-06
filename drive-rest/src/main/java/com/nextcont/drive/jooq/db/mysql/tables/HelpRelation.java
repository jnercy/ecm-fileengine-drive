/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables;


import com.nextcont.drive.jooq.db.mysql.Keys;
import com.nextcont.drive.jooq.db.mysql.Mysql;
import com.nextcont.drive.jooq.db.mysql.tables.records.HelpRelationRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;


/**
 * keyword-topic relation
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HelpRelation extends TableImpl<HelpRelationRecord> {

    private static final long serialVersionUID = -1585661248;

    /**
     * The reference instance of <code>mysql.help_relation</code>
     */
    public static final HelpRelation HELP_RELATION = new HelpRelation();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<HelpRelationRecord> getRecordType() {
        return HelpRelationRecord.class;
    }

    /**
     * The column <code>mysql.help_relation.help_topic_id</code>.
     */
    public final TableField<HelpRelationRecord, UInteger> HELP_TOPIC_ID = createField("help_topic_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>mysql.help_relation.help_keyword_id</code>.
     */
    public final TableField<HelpRelationRecord, UInteger> HELP_KEYWORD_ID = createField("help_keyword_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * Create a <code>mysql.help_relation</code> table reference
     */
    public HelpRelation() {
        this("help_relation", null);
    }

    /**
     * Create an aliased <code>mysql.help_relation</code> table reference
     */
    public HelpRelation(String alias) {
        this(alias, HELP_RELATION);
    }

    private HelpRelation(String alias, Table<HelpRelationRecord> aliased) {
        this(alias, aliased, null);
    }

    private HelpRelation(String alias, Table<HelpRelationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "keyword-topic relation");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Mysql.MYSQL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<HelpRelationRecord> getPrimaryKey() {
        return Keys.KEY_HELP_RELATION_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<HelpRelationRecord>> getKeys() {
        return Arrays.<UniqueKey<HelpRelationRecord>>asList(Keys.KEY_HELP_RELATION_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpRelation as(String alias) {
        return new HelpRelation(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public HelpRelation rename(String name) {
        return new HelpRelation(name, null);
    }
}
