package com.instabot.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lezalekss
 */
@RestController
public class BotApiController {
    @GetMapping("/")
    public String hello(){
        return "<html>  <head></head>  <body>" +
                "      <h1>This is the body of the sample view</h1> " +
                "   </body>" +
                "</html>";
    }

}
