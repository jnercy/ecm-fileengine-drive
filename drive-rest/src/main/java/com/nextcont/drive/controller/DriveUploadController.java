package com.nextcont.drive.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.utils.HttpClient;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.StringUtils;
import com.nextcont.file.FileMetaData;
import com.nextcont.file.UploadType;
import com.nextcont.file.request.transition.TransRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static com.mongodb.client.model.Updates.*;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/9
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 */

@RestController
@RequestMapping("/upload")
@Slf4j
public class DriveUploadController {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private AppendFileStorageClient appendFileStorageClient;

    @Autowired
    private BaseMongoService<FileMetaData> fileMetaDataService;


    @Value("${fdfs.nginxIp}")
    private String fileOutputIp;

    @Value("${fdfs.group}")
    private String groupName;

    @Value("${transition.request}")
    private String transitonRequestUrl;


    @RequestMapping(value = "/drive/v1/files", method = RequestMethod.POST)
    public String uploadFIle(@RequestParam("file") MultipartFile file, @RequestParam("fileId") String fileId, @RequestParam("uploadType") String uploadType,@RequestParam("path") String path) {
        String name = file.getName();
        String result;
        StorePath storePath = null;
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!file.isEmpty()) {
            try {
                if (uploadType.equals(UploadType.media.name()))
                    storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),extension, null);

                else if(uploadType.equals(UploadType.multipart.name())){
                    if(StringUtils.isNotEmpty(path))
                        storePath = appendFileStorageClient.uploadAppenderFile(groupName, file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()));
                    else
                        appendFileStorageClient.appendFile(groupName,path,file.getInputStream(),file.getSize());
                }
                if(storePath !=null){
                    String md5Checksum = DigestUtils.md5Hex(file.getInputStream());
                    Bson updateBson = combine(
                            set("webContentLink",fileOutputIp + storePath.getFullPath()),
                            set("size",file.getSize()),
                            set("quotaBytesUsed",file.getSize()),
                            set("fullFileExtension",extension),
                            set("fileExtension",extension),
                            set("md5Checksum",md5Checksum));
                    fileMetaDataService.updateOne(new Document("id",fileId),updateBson);

                    String uploadFileUrl = fileOutputIp+storePath.getFullPath();
                    HttpClient.httpPostRequest(transitonRequestUrl,JsonFormat.toJson(TransRequest.getHttpRequest(file.getOriginalFilename(),uploadFileUrl,fileId)));
                    return uploadFileUrl;
                }
                else
                    return "uploadType method not support .";
            } catch (Exception e) {
                result = "You failed to upload " + name + " => " + e.getMessage();
            }
        } else
            result = "You failed to upload " + name + " because the file was empty.";

        return result;
    }
}