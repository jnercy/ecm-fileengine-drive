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
public class ContentHints {

    private String indexableText;

    private Thumbnail thumbnail;

    public ContentHints(String indexableText, Thumbnail thumbnail) {
        this.indexableText = indexableText;
        this.thumbnail = thumbnail;
    }
}
