package bean;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Transient;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/11
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
public class FileOwners {

    @Transient
    private final String kind = "drive#user";

    private String displayName;

    private String photoLink;

    private String permissionId;

    private String emailAddress;

}
