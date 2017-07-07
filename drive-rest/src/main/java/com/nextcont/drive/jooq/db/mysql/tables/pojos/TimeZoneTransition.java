/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;

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
public class TimeZoneTransition implements Serializable {

    private static final long serialVersionUID = -175632726;

    private UInteger timeZoneId;
    private Long     transitionTime;
    private UInteger transitionTypeId;

    public TimeZoneTransition() {}

    public TimeZoneTransition(TimeZoneTransition value) {
        this.timeZoneId = value.timeZoneId;
        this.transitionTime = value.transitionTime;
        this.transitionTypeId = value.transitionTypeId;
    }

    public TimeZoneTransition(
        UInteger timeZoneId,
        Long     transitionTime,
        UInteger transitionTypeId
    ) {
        this.timeZoneId = timeZoneId;
        this.transitionTime = transitionTime;
        this.transitionTypeId = transitionTypeId;
    }

    public UInteger getTimeZoneId() {
        return this.timeZoneId;
    }

    public void setTimeZoneId(UInteger timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public Long getTransitionTime() {
        return this.transitionTime;
    }

    public void setTransitionTime(Long transitionTime) {
        this.transitionTime = transitionTime;
    }

    public UInteger getTransitionTypeId() {
        return this.transitionTypeId;
    }

    public void setTransitionTypeId(UInteger transitionTypeId) {
        this.transitionTypeId = transitionTypeId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TimeZoneTransition (");

        sb.append(timeZoneId);
        sb.append(", ").append(transitionTime);
        sb.append(", ").append(transitionTypeId);

        sb.append(")");
        return sb.toString();
    }
}
