package bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/3
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "metadatas")
public class FileMetaData implements Serializable{


    @Id
    private String fileId;

    private Map<Object,Object> appProperties;

    private ContentHints contentHints;

    private String description;

    private String mimeType;

    @JsonIgnore
    private Date modifiedTime;

    private String folderColorRgb;

    public FileMetaData refreshModifiedTime(){
        modifiedTime = new Date();
        return this;
    }

}
