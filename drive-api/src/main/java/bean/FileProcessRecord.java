package bean;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/12
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
public class FileProcessRecord {

    private String fileId;

    private String userId;

    private String name;

    private boolean ownedByMe;

    private boolean viewedByMe;

    private boolean modifyByMe;

    private Date createTime;

    private boolean folder;

    private Date modifyByMeTime;

    private Date modifiedTime;

    private long quotaBytesUsed;

    private boolean recency;

    private Date sharedWithMeTime;

    private boolean starred;

    private Date viewByMeTime;

}
