/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.ecm_trans;


import com.nextcont.drive.jooq.db.ecm_trans.tables.FastdfsFilerecord;
import com.nextcont.drive.jooq.db.ecm_trans.tables.Meqaupload;
import com.nextcont.drive.jooq.db.ecm_trans.tables.Transition;
import com.nextcont.drive.jooq.db.ecm_trans.tables.TransitionCallback;
import com.nextcont.drive.jooq.db.ecm_trans.tables.TransitionFile;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in ecm_trans
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
     * The table <code>ecm_trans.fastdfs_fileRecord</code>.
     */
    public static final FastdfsFilerecord FASTDFS_FILERECORD = com.nextcont.drive.jooq.db.ecm_trans.tables.FastdfsFilerecord.FASTDFS_FILERECORD;

    /**
     * The table <code>ecm_trans.meqaupload</code>.
     */
    public static final Meqaupload MEQAUPLOAD = com.nextcont.drive.jooq.db.ecm_trans.tables.Meqaupload.MEQAUPLOAD;

    /**
     * The table <code>ecm_trans.transition</code>.
     */
    public static final Transition TRANSITION = com.nextcont.drive.jooq.db.ecm_trans.tables.Transition.TRANSITION;

    /**
     * The table <code>ecm_trans.transition_callback</code>.
     */
    public static final TransitionCallback TRANSITION_CALLBACK = com.nextcont.drive.jooq.db.ecm_trans.tables.TransitionCallback.TRANSITION_CALLBACK;

    /**
     * The table <code>ecm_trans.transition_file</code>.
     */
    public static final TransitionFile TRANSITION_FILE = com.nextcont.drive.jooq.db.ecm_trans.tables.TransitionFile.TRANSITION_FILE;
}
