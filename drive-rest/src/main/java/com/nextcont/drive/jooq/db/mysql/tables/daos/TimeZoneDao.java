/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.daos;


import com.nextcont.drive.jooq.db.mysql.enums.TimeZoneUseLeapSeconds;
import com.nextcont.drive.jooq.db.mysql.tables.TimeZone;
import com.nextcont.drive.jooq.db.mysql.tables.records.TimeZoneRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.jooq.types.UInteger;


/**
 * Time zones
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TimeZoneDao extends DAOImpl<TimeZoneRecord, com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZone, UInteger> {

    /**
     * Create a new TimeZoneDao without any configuration
     */
    public TimeZoneDao() {
        super(TimeZone.TIME_ZONE, com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZone.class);
    }

    /**
     * Create a new TimeZoneDao with an attached configuration
     */
    public TimeZoneDao(Configuration configuration) {
        super(TimeZone.TIME_ZONE, com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZone.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UInteger getId(com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZone object) {
        return object.getTimeZoneId();
    }

    /**
     * Fetch records that have <code>Time_zone_id IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZone> fetchByTimeZoneId(UInteger... values) {
        return fetch(TimeZone.TIME_ZONE.TIME_ZONE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>Time_zone_id = value</code>
     */
    public com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZone fetchOneByTimeZoneId(UInteger value) {
        return fetchOne(TimeZone.TIME_ZONE.TIME_ZONE_ID, value);
    }

    /**
     * Fetch records that have <code>Use_leap_seconds IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZone> fetchByUseLeapSeconds(TimeZoneUseLeapSeconds... values) {
        return fetch(TimeZone.TIME_ZONE.USE_LEAP_SECONDS, values);
    }
}
