package com.softvision.demo.controller;

import com.softvision.demo.service.DemoMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value="/messages")
public class MessageController {

    @Autowired
    private DemoMessageService demoMessageService;

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);


    @RequestMapping(value="/test",method = RequestMethod.GET)
    public String getMessage( ) {
        logger.info("MessageController:getMessage");

        String test = "test string";
        demoMessageService.sendMessage(test);
        return test;
    }


    @RequestMapping(value="/test",method = RequestMethod.PUT)
    public void sendMessage( @RequestBody String  testStr) {
        logger.info("MessageController:sendMessage {}" , testStr);
        demoMessageService.sendMessage(testStr);
    }


    @RequestMapping(value="/test/custom",method = RequestMethod.PUT)
    public void sendMessageCustomOutput( @RequestBody String  testStr) {
        logger.info("MessageController:sendMessageCustomOutput {}" , testStr);
        demoMessageService.sendMessageCustomOutput(testStr);
    }



}
