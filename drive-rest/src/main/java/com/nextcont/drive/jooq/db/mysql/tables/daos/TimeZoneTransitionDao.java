/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.daos;


import com.nextcont.drive.jooq.db.mysql.tables.TimeZoneTransition;
import com.nextcont.drive.jooq.db.mysql.tables.records.TimeZoneTransitionRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.Record2;
import org.jooq.impl.DAOImpl;
import org.jooq.types.UInteger;


/**
 * Time zone transitions
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TimeZoneTransitionDao extends DAOImpl<TimeZoneTransitionRecord, com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneTransition, Record2<UInteger, Long>> {

    /**
     * Create a new TimeZoneTransitionDao without any configuration
     */
    public TimeZoneTransitionDao() {
        super(TimeZoneTransition.TIME_ZONE_TRANSITION, com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneTransition.class);
    }

    /**
     * Create a new TimeZoneTransitionDao with an attached configuration
     */
    public TimeZoneTransitionDao(Configuration configuration) {
        super(TimeZoneTransition.TIME_ZONE_TRANSITION, com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneTransition.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Record2<UInteger, Long> getId(com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneTransition object) {
        return compositeKeyRecord(object.getTimeZoneId(), object.getTransitionTime());
    }

    /**
     * Fetch records that have <code>Time_zone_id IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneTransition> fetchByTimeZoneId(UInteger... values) {
        return fetch(TimeZoneTransition.TIME_ZONE_TRANSITION.TIME_ZONE_ID, values);
    }

    /**
     * Fetch records that have <code>Transition_time IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneTransition> fetchByTransitionTime(Long... values) {
        return fetch(TimeZoneTransition.TIME_ZONE_TRANSITION.TRANSITION_TIME, values);
    }

    /**
     * Fetch records that have <code>Transition_type_id IN (values)</code>
     */
    public List<com.nextcont.drive.jooq.db.mysql.tables.pojos.TimeZoneTransition> fetchByTransitionTypeId(UInteger... values) {
        return fetch(TimeZoneTransition.TIME_ZONE_TRANSITION.TRANSITION_TYPE_ID, values);
    }
}
