package com.nextcont.drive.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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


    @RequestMapping(value = "/drive/files" , method = RequestMethod.POST)
    public String uploadFIle( @RequestParam("file") MultipartFile file){
        String name = file.getName();
        if (!file.isEmpty()) {
            try {
                StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
                return "http://192.168.15.205:9200/"+storePath.getFullPath();
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }



}
