package bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/3
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
public class Thumbnail {

    private String imageUrl;

    private String mimeType;

    public Thumbnail(String imageUrl, String mimeType) {
        this.imageUrl = imageUrl;
        this.mimeType = mimeType;
    }
}
