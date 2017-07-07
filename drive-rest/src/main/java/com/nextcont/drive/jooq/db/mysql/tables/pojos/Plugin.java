/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * MySQL plugins
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Plugin implements Serializable {

    private static final long serialVersionUID = 130084241;

    private String name;
    private String dl;

    public Plugin() {}

    public Plugin(Plugin value) {
        this.name = value.name;
        this.dl = value.dl;
    }

    public Plugin(
        String name,
        String dl
    ) {
        this.name = name;
        this.dl = dl;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDl() {
        return this.dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Plugin (");

        sb.append(name);
        sb.append(", ").append(dl);

        sb.append(")");
        return sb.toString();
    }
}
