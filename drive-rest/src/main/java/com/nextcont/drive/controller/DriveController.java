package com.nextcont.drive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/4
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DriveController {

    @RequestMapping("/home")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
