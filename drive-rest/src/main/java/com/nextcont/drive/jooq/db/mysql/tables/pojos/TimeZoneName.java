/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;

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
public class TimeZoneName implements Serializable {

    private static final long serialVersionUID = -331289059;

    private String   name;
    private UInteger timeZoneId;

    public TimeZoneName() {}

    public TimeZoneName(TimeZoneName value) {
        this.name = value.name;
        this.timeZoneId = value.timeZoneId;
    }

    public TimeZoneName(
        String   name,
        UInteger timeZoneId
    ) {
        this.name = name;
        this.timeZoneId = timeZoneId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UInteger getTimeZoneId() {
        return this.timeZoneId;
    }

    public void setTimeZoneId(UInteger timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TimeZoneName (");

        sb.append(name);
        sb.append(", ").append(timeZoneId);

        sb.append(")");
        return sb.toString();
    }
}