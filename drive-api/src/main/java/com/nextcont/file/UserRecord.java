package com.nextcont.file;

import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/12
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRecord implements Serializable {

    private final String kind = "drive#record";

    private List<String> parents;

    private String rootId;

    private boolean viewedByMe;

    private String viewedByMeTime;

    private boolean modifyByMe;

    private String modifyByMeTime;

    private String sharedWithMeTime;

    private boolean ownedByMe;

    public static UserRecord buildUserRecord(String rootId, String parents) {
        return UserRecord
                .builder()
                .ownedByMe(false)
                .viewedByMe(false)
                .modifyByMe(false)
                .parents(Arrays.asList(parents))
                .rootId(rootId)
                .build();
    }


}
