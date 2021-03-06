/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql;


import com.nextcont.drive.jooq.db.mysql.tables.ColumnsPriv;
import com.nextcont.drive.jooq.db.mysql.tables.Db;
import com.nextcont.drive.jooq.db.mysql.tables.Event;
import com.nextcont.drive.jooq.db.mysql.tables.Func;
import com.nextcont.drive.jooq.db.mysql.tables.GeneralLog;
import com.nextcont.drive.jooq.db.mysql.tables.HelpCategory;
import com.nextcont.drive.jooq.db.mysql.tables.HelpKeyword;
import com.nextcont.drive.jooq.db.mysql.tables.HelpRelation;
import com.nextcont.drive.jooq.db.mysql.tables.HelpTopic;
import com.nextcont.drive.jooq.db.mysql.tables.Host;
import com.nextcont.drive.jooq.db.mysql.tables.NdbBinlogIndex;
import com.nextcont.drive.jooq.db.mysql.tables.Plugin;
import com.nextcont.drive.jooq.db.mysql.tables.Proc;
import com.nextcont.drive.jooq.db.mysql.tables.ProcsPriv;
import com.nextcont.drive.jooq.db.mysql.tables.Servers;
import com.nextcont.drive.jooq.db.mysql.tables.SlowLog;
import com.nextcont.drive.jooq.db.mysql.tables.TablesPriv;
import com.nextcont.drive.jooq.db.mysql.tables.TimeZone;
import com.nextcont.drive.jooq.db.mysql.tables.TimeZoneLeapSecond;
import com.nextcont.drive.jooq.db.mysql.tables.TimeZoneName;
import com.nextcont.drive.jooq.db.mysql.tables.TimeZoneTransition;
import com.nextcont.drive.jooq.db.mysql.tables.TimeZoneTransitionType;
import com.nextcont.drive.jooq.db.mysql.tables.User;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in mysql
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * Column privileges
     */
    public static final ColumnsPriv COLUMNS_PRIV = com.nextcont.drive.jooq.db.mysql.tables.ColumnsPriv.COLUMNS_PRIV;

    /**
     * Database privileges
     */
    public static final Db DB = com.nextcont.drive.jooq.db.mysql.tables.Db.DB;

    /**
     * Events
     */
    public static final Event EVENT = com.nextcont.drive.jooq.db.mysql.tables.Event.EVENT;

    /**
     * User defined functions
     */
    public static final Func FUNC = com.nextcont.drive.jooq.db.mysql.tables.Func.FUNC;

    /**
     * General log
     */
    public static final GeneralLog GENERAL_LOG = com.nextcont.drive.jooq.db.mysql.tables.GeneralLog.GENERAL_LOG;

    /**
     * help categories
     */
    public static final HelpCategory HELP_CATEGORY = com.nextcont.drive.jooq.db.mysql.tables.HelpCategory.HELP_CATEGORY;

    /**
     * help keywords
     */
    public static final HelpKeyword HELP_KEYWORD = com.nextcont.drive.jooq.db.mysql.tables.HelpKeyword.HELP_KEYWORD;

    /**
     * keyword-topic relation
     */
    public static final HelpRelation HELP_RELATION = com.nextcont.drive.jooq.db.mysql.tables.HelpRelation.HELP_RELATION;

    /**
     * help topics
     */
    public static final HelpTopic HELP_TOPIC = com.nextcont.drive.jooq.db.mysql.tables.HelpTopic.HELP_TOPIC;

    /**
     * Host privileges;  Merged with database privileges
     */
    public static final Host HOST = com.nextcont.drive.jooq.db.mysql.tables.Host.HOST;

    /**
     * The table <code>mysql.ndb_binlog_index</code>.
     */
    public static final NdbBinlogIndex NDB_BINLOG_INDEX = com.nextcont.drive.jooq.db.mysql.tables.NdbBinlogIndex.NDB_BINLOG_INDEX;

    /**
     * MySQL plugins
     */
    public static final Plugin PLUGIN = com.nextcont.drive.jooq.db.mysql.tables.Plugin.PLUGIN;

    /**
     * Stored Procedures
     */
    public static final Proc PROC = com.nextcont.drive.jooq.db.mysql.tables.Proc.PROC;

    /**
     * Procedure privileges
     */
    public static final ProcsPriv PROCS_PRIV = com.nextcont.drive.jooq.db.mysql.tables.ProcsPriv.PROCS_PRIV;

    /**
     * MySQL Foreign Servers table
     */
    public static final Servers SERVERS = com.nextcont.drive.jooq.db.mysql.tables.Servers.SERVERS;

    /**
     * Slow log
     */
    public static final SlowLog SLOW_LOG = com.nextcont.drive.jooq.db.mysql.tables.SlowLog.SLOW_LOG;

    /**
     * Table privileges
     */
    public static final TablesPriv TABLES_PRIV = com.nextcont.drive.jooq.db.mysql.tables.TablesPriv.TABLES_PRIV;

    /**
     * Time zones
     */
    public static final TimeZone TIME_ZONE = com.nextcont.drive.jooq.db.mysql.tables.TimeZone.TIME_ZONE;

    /**
     * Leap seconds information for time zones
     */
    public static final TimeZoneLeapSecond TIME_ZONE_LEAP_SECOND = com.nextcont.drive.jooq.db.mysql.tables.TimeZoneLeapSecond.TIME_ZONE_LEAP_SECOND;

    /**
     * Time zone names
     */
    public static final TimeZoneName TIME_ZONE_NAME = com.nextcont.drive.jooq.db.mysql.tables.TimeZoneName.TIME_ZONE_NAME;

    /**
     * Time zone transitions
     */
    public static final TimeZoneTransition TIME_ZONE_TRANSITION = com.nextcont.drive.jooq.db.mysql.tables.TimeZoneTransition.TIME_ZONE_TRANSITION;

    /**
     * Time zone transition types
     */
    public static final TimeZoneTransitionType TIME_ZONE_TRANSITION_TYPE = com.nextcont.drive.jooq.db.mysql.tables.TimeZoneTransitionType.TIME_ZONE_TRANSITION_TYPE;

    /**
     * Users and global privileges
     */
    public static final User USER = com.nextcont.drive.jooq.db.mysql.tables.User.USER;
}
