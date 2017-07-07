/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.daos;


import com.nextcont.drive.jooq.db.mysql.tables.HelpTopic;
import com.nextcont.drive.jooq.db.mysql.tables.records.HelpTopicRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;


/**
 * help topics
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HelpTopicDao extends DAOImpl<HelpTopicRecord, com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpTopic, UInteger> {

    /**
     * Create a new HelpTopicDao without any configuration
     */
    public HelpTopicDao() {
        super(HelpTopic.HELP_TOPIC, com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpTopic.class);
    }

    /**
     * Create a new HelpTopicDao with an attached configuration
     */
    public HelpTopicDao(Configuration configuration) {
        super(HelpTopic.HELP_TOPIC, com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpTopic.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UInteger getId(com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpTopic object) {
        return object.getHelpTopicId();
    }

    /**
     * Fetch records that have <code>help_topic_id IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpTopic> fetchByHelpTopicId(UInteger... values) {
        return fetch(HelpTopic.HELP_TOPIC.HELP_TOPIC_ID, values);
    }

    /**
     * Fetch a unique record that has <code>help_topic_id = value</code>
     */
    public com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpTopic fetchOneByHelpTopicId(UInteger value) {
        return fetchOne(HelpTopic.HELP_TOPIC.HELP_TOPIC_ID, value);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpTopic> fetchByName(String... values) {
        return fetch(HelpTopic.HELP_TOPIC.NAME, values);
    }

    /**
     * Fetch a unique record that has <code>name = value</code>
     */
    public com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpTopic fetchOneByName(String value) {
        return fetchOne(HelpTopic.HELP_TOPIC.NAME, value);
    }

    /**
     * Fetch records that have <code>help_category_id IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpTopic> fetchByHelpCategoryId(UShort... values) {
        return fetch(HelpTopic.HELP_TOPIC.HELP_CATEGORY_ID, values);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpTopic> fetchByDescription(String... values) {
        return fetch(HelpTopic.HELP_TOPIC.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>example IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpTopic> fetchByExample(String... values) {
        return fetch(HelpTopic.HELP_TOPIC.EXAMPLE, values);
    }

    /**
     * Fetch records that have <code>url IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.HelpTopic> fetchByUrl(String... values) {
        return fetch(HelpTopic.HELP_TOPIC.URL, values);
    }
}