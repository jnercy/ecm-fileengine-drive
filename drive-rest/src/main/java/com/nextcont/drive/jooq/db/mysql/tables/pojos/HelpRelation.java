/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;

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
public class HelpRelation implements Serializable {

    private static final long serialVersionUID = 773176270;

    private UInteger helpTopicId;
    private UInteger helpKeywordId;

    public HelpRelation() {}

    public HelpRelation(HelpRelation value) {
        this.helpTopicId = value.helpTopicId;
        this.helpKeywordId = value.helpKeywordId;
    }

    public HelpRelation(
        UInteger helpTopicId,
        UInteger helpKeywordId
    ) {
        this.helpTopicId = helpTopicId;
        this.helpKeywordId = helpKeywordId;
    }

    public UInteger getHelpTopicId() {
        return this.helpTopicId;
    }

    public void setHelpTopicId(UInteger helpTopicId) {
        this.helpTopicId = helpTopicId;
    }

    public UInteger getHelpKeywordId() {
        return this.helpKeywordId;
    }

    public void setHelpKeywordId(UInteger helpKeywordId) {
        this.helpKeywordId = helpKeywordId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HelpRelation (");

        sb.append(helpTopicId);
        sb.append(", ").append(helpKeywordId);

        sb.append(")");
        return sb.toString();
    }
}
