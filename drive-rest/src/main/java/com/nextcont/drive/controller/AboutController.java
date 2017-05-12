package com.nextcont.drive.controller;

import com.nextcont.drive.aspect.AuthAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/5/11
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/about")
@Slf4j
public class AboutController {


    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<Object> aboutMe() {
        return new ResponseEntity<>(AuthAspect.getAuthTokenInfo(), HttpStatus.OK);
    }
}
