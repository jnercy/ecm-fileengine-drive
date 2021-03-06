/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.daos;


import com.nextcont.drive.jooq.db.mysql.tables.HelpCategory;
import com.nextcont.drive.jooq.db.mysql.tables.records.HelpCategoryRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
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
public class HelpCategoryDao extends DAOImpl<HelpCategoryRecord, com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpCategory, UShort> {

    /**
     * Create a new HelpCategoryDao without any configuration
     */
    public HelpCategoryDao() {
        super(HelpCategory.HELP_CATEGORY, com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpCategory.class);
    }

    /**
     * Create a new HelpCategoryDao with an attached configuration
     */
    public HelpCategoryDao(Configuration configuration) {
        super(HelpCategory.HELP_CATEGORY, com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpCategory.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UShort getId(com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpCategory object) {
        return object.getHelpCategoryId();
    }

    /**
     * Fetch records that have <code>help_category_id IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpCategory> fetchByHelpCategoryId(UShort... values) {
        return fetch(HelpCategory.HELP_CATEGORY.HELP_CATEGORY_ID, values);
    }

    /**
     * Fetch a unique record that has <code>help_category_id = value</code>
     */
    public com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpCategory fetchOneByHelpCategoryId(UShort value) {
        return fetchOne(HelpCategory.HELP_CATEGORY.HELP_CATEGORY_ID, value);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpCategory> fetchByName(String... values) {
        return fetch(HelpCategory.HELP_CATEGORY.NAME, values);
    }

    /**
     * Fetch a unique record that has <code>name = value</code>
     */
    public com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpCategory fetchOneByName(String value) {
        return fetchOne(HelpCategory.HELP_CATEGORY.NAME, value);
    }

    /**
     * Fetch records that have <code>parent_category_id IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpCategory> fetchByParentCategoryId(UShort... values) {
        return fetch(HelpCategory.HELP_CATEGORY.PARENT_CATEGORY_ID, values);
    }

    /**
     * Fetch records that have <code>url IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpCategory> fetchByUrl(String... values) {
        return fetch(HelpCategory.HELP_CATEGORY.URL, values);
    }
}
