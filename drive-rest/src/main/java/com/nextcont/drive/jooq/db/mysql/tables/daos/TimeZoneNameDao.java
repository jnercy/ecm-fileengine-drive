/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.daos;


import com.nextcont.drive.jooq.db.mysql.tables.TimeZoneName;
import com.nextcont.drive.jooq.db.mysql.tables.records.TimeZoneNameRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.jooq.types.UInteger;


/**
 * Time zone names
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TimeZoneNameDao extends DAOImpl<TimeZoneNameRecord, com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneName, String> {

    /**
     * Create a new TimeZoneNameDao without any configuration
     */
    public TimeZoneNameDao() {
        super(TimeZoneName.TIME_ZONE_NAME, com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneName.class);
    }

    /**
     * Create a new TimeZoneNameDao with an attached configuration
     */
    public TimeZoneNameDao(Configuration configuration) {
        super(TimeZoneName.TIME_ZONE_NAME, com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneName.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneName object) {
        return object.getName();
    }

    /**
     * Fetch records that have <code>Name IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneName> fetchByName(String... values) {
        return fetch(TimeZoneName.TIME_ZONE_NAME.NAME, values);
    }

    /**
     * Fetch a unique record that has <code>Name = value</code>
     */
    public com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneName fetchOneByName(String value) {
        return fetchOne(TimeZoneName.TIME_ZONE_NAME.NAME, value);
    }

    /**
     * Fetch records that have <code>Time_zone_id IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneName> fetchByTimeZoneId(UInteger... values) {
        return fetch(TimeZoneName.TIME_ZONE_NAME.TIME_ZONE_ID, values);
    }
}
