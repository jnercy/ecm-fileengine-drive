package com.nextcont.drive.controller;

import com.nextcont.file.request.file.FileCopyRequest;
import com.nextcont.file.request.file.FileRequestbody;
import com.nextcont.file.request.permission.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/10
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/drive/v1/files")
@Slf4j
public class PermissionsController {


    @RequestMapping(value = "/{fileId}/permissions", method = RequestMethod.POST)
    public String create(@PathVariable("fileId") String fileId, PermissionCreateRequest reuqest, @RequestBody PermissionCreateRequestbody bodyData){
        return null;
    }


    @RequestMapping(value = "/{fileId}/permissions/{permissionId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("fileId") String fileId,@PathVariable String permissionId, @RequestParam boolean supportsTeamDrives){
        return null;
    }

    @RequestMapping(value = "/{fileId}/permissions/{permissionId}", method = RequestMethod.GET)
    public String get(@PathVariable("fileId") String fileId,@PathVariable String permissionId, @RequestParam boolean supportsTeamDrives){
        return null;
    }

    @RequestMapping(value = "/{fileId}/permissions", method = RequestMethod.GET)
    public String list(@PathVariable("fileId") String fileId, PermissionListRequest request){
        return null;
    }


    @RequestMapping(value = "/{fileId}/permissions/{permissionId}", method = RequestMethod.PATCH)
    public String update(@PathVariable("fileId") String fileId, @PathVariable String permissionId, PermissionUpdateRequest request, @RequestBody PermissionUpdateRequestbody bodyData){
        return null;
    }








}
