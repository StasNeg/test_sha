package com.example.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map getUserByEmail() throws JsonProcessingException {
        Map map = new HashMap();
        String date = "2016-02-05T21:18";
        map.put(date, LocalDateTime.now());
        String auditorium = "Yellow hall";
        map.put(auditorium, auditorium);
        String event = "The revenant";
        map.put(event, event);

        return map;
    }
}
