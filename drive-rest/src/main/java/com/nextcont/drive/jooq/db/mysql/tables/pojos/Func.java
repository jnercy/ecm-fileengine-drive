/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.mysql.tables.pojos;


import com.nextcont.drive.jooq.db.mysql.enums.FuncType;

import java.io.Serializable;

import javax.annotation.Generated;


/**
 * User defined functions
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Func implements Serializable {

    private static final long serialVersionUID = -1163628338;

    private String   name;
    private Byte     ret;
    private String   dl;
    private FuncType type;

    public Func() {}

    public Func(Func value) {
        this.name = value.name;
        this.ret = value.ret;
        this.dl = value.dl;
        this.type = value.type;
    }

    public Func(
        String   name,
        Byte     ret,
        String   dl,
        FuncType type
    ) {
        this.name = name;
        this.ret = ret;
        this.dl = dl;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getRet() {
        return this.ret;
    }

    public void setRet(Byte ret) {
        this.ret = ret;
    }

    public String getDl() {
        return this.dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }

    public FuncType getType() {
        return this.type;
    }

    public void setType(FuncType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Func (");

        sb.append(name);
        sb.append(", ").append(ret);
        sb.append(", ").append(dl);
        sb.append(", ").append(type);

        sb.append(")");
        return sb.toString();
    }
}
