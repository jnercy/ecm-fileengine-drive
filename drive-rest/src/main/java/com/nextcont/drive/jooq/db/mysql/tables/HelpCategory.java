/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables;


import com.nextcont.drive.jooq.db.mysql.Keys;
import com.nextcont.drive.jooq.db.mysql.Mysql;
import com.nextcont.drive.jooq.db.mysql.tables.records.HelpCategoryRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.types.UShort;


/**
 * help categories
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HelpCategory extends TableImpl<HelpCategoryRecord> {

    private static final long serialVersionUID = -1089536217;

    /**
     * The reference instance of <code>mysql.help_category</code>
     */
    public static final HelpCategory HELP_CATEGORY = new HelpCategory();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<HelpCategoryRecord> getRecordType() {
        return HelpCategoryRecord.class;
    }

    /**
     * The column <code>mysql.help_category.help_category_id</code>.
     */
    public final TableField<HelpCategoryRecord, UShort> HELP_CATEGORY_ID = createField("help_category_id", org.jooq.impl.SQLDataType.SMALLINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>mysql.help_category.name</code>.
     */
    public final TableField<HelpCategoryRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.CHAR.length(64).nullable(false), this, "");

    /**
     * The column <code>mysql.help_category.parent_category_id</code>.
     */
    public final TableField<HelpCategoryRecord, UShort> PARENT_CATEGORY_ID = createField("parent_category_id", org.jooq.impl.SQLDataType.SMALLINTUNSIGNED, this, "");

    /**
     * The column <code>mysql.help_category.url</code>.
     */
    public final TableField<HelpCategoryRecord, String> URL = createField("url", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>mysql.help_category</code> table reference
     */
    public HelpCategory() {
        this("help_category", null);
    }

    /**
     * Create an aliased <code>mysql.help_category</code> table reference
     */
    public HelpCategory(String alias) {
        this(alias, HELP_CATEGORY);
    }

    private HelpCategory(String alias, Table<HelpCategoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private HelpCategory(String alias, Table<HelpCategoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "help categories");
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
    public UniqueKey<HelpCategoryRecord> getPrimaryKey() {
        return Keys.KEY_HELP_CATEGORY_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<HelpCategoryRecord>> getKeys() {
        return Arrays.<UniqueKey<HelpCategoryRecord>>asList(Keys.KEY_HELP_CATEGORY_PRIMARY, Keys.KEY_HELP_CATEGORY_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpCategory as(String alias) {
        return new HelpCategory(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public HelpCategory rename(String name) {
        return new HelpCategory(name, null);
    }
}
