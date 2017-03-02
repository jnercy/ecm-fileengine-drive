/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.ecm_trans.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Meqaupload implements Serializable {

    private static final long serialVersionUID = 2099828924;

    private String    globalid;
    private String    filename;
    private String    mimetype;
    private Integer   contentlength;
    private Integer   shardsize;
    private Timestamp createtime;
    private Timestamp updatetime;
    private Integer   currentshard;
    private Integer   partfilesize;

    public Meqaupload() {}

    public Meqaupload(Meqaupload value) {
        this.globalid = value.globalid;
        this.filename = value.filename;
        this.mimetype = value.mimetype;
        this.contentlength = value.contentlength;
        this.shardsize = value.shardsize;
        this.createtime = value.createtime;
        this.updatetime = value.updatetime;
        this.currentshard = value.currentshard;
        this.partfilesize = value.partfilesize;
    }

    public Meqaupload(
        String    globalid,
        String    filename,
        String    mimetype,
        Integer   contentlength,
        Integer   shardsize,
        Timestamp createtime,
        Timestamp updatetime,
        Integer   currentshard,
        Integer   partfilesize
    ) {
        this.globalid = globalid;
        this.filename = filename;
        this.mimetype = mimetype;
        this.contentlength = contentlength;
        this.shardsize = shardsize;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.currentshard = currentshard;
        this.partfilesize = partfilesize;
    }

    public String getGlobalid() {
        return this.globalid;
    }

    public void setGlobalid(String globalid) {
        this.globalid = globalid;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimetype() {
        return this.mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public Integer getContentlength() {
        return this.contentlength;
    }

    public void setContentlength(Integer contentlength) {
        this.contentlength = contentlength;
    }

    public Integer getShardsize() {
        return this.shardsize;
    }

    public void setShardsize(Integer shardsize) {
        this.shardsize = shardsize;
    }

    public Timestamp getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getCurrentshard() {
        return this.currentshard;
    }

    public void setCurrentshard(Integer currentshard) {
        this.currentshard = currentshard;
    }

    public Integer getPartfilesize() {
        return this.partfilesize;
    }

    public void setPartfilesize(Integer partfilesize) {
        this.partfilesize = partfilesize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Meqaupload (");

        sb.append(globalid);
        sb.append(", ").append(filename);
        sb.append(", ").append(mimetype);
        sb.append(", ").append(contentlength);
        sb.append(", ").append(shardsize);
        sb.append(", ").append(createtime);
        sb.append(", ").append(updatetime);
        sb.append(", ").append(currentshard);
        sb.append(", ").append(partfilesize);

        sb.append(")");
        return sb.toString();
    }
}