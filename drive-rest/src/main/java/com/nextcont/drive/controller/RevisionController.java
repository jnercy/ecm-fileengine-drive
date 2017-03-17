package com.nextcont.drive.controller;

import com.nextcont.file.request.revisions.RevisionUpdateReqeustbody;
import com.nextcont.file.request.revisions.RevisionListRequest;
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
public class RevisionController {



    @RequestMapping(value = "/{fileId}/revisions/{revisionId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("fileId") String fileId,@PathVariable("revisionId") String permissionId){

        return null;
    }

    @RequestMapping(value = "/{fileId}/revisions/{revisionId}", method = RequestMethod.GET)
    public String get(@PathVariable("fileId") String fileId,@PathVariable("revisionId") String permissionId,@RequestParam boolean acknowledgeAbuse){
        return null;
    }

    @RequestMapping(value = "/{fileId}/revisions", method = RequestMethod.GET)
    public String list(@PathVariable("fileId") String fileId, RevisionListRequest request){
        return null;
    }


    @RequestMapping(value = "/{fileId}/revisions/{revisionId}", method = RequestMethod.PATCH)
    public String update(@PathVariable("fileId") String fileId, @PathVariable("revisionId") String revisionId,@RequestBody RevisionUpdateReqeustbody bodyData){
        return null;
    }

}
