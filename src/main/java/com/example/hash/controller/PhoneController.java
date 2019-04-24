package com.example.hash.controller;

import com.example.hash.model.tele.Telephone;
import com.example.hash.service.TelephoneService;
import com.example.hash.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/test")
public class PhoneController {
    @Autowired
    UserService userService;
    @Autowired
    TelephoneService telephoneService;




    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/telephone/hash/{hash}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Telephone getNumberByHash(@PathVariable String hash)  {
        return telephoneService.getTelephoneByHash(hash);
    }

    @RequestMapping(value = "/telephone/number/{number}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Telephone getNumberByHashl(@PathVariable String number)  {
        return telephoneService.getHashByPhoneNumber(number);
    }
}
