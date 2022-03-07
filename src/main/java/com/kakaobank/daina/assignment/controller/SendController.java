package com.kakaobank.daina.assignment.controller;

import com.kakaobank.daina.assignment.service.SendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SendController {

    SendService sendService;

    //private String tId;

    public SendController(SendService sendService) {
        this.sendService = sendService;
    }

    //과거 이체 이력이 있는 친구 정보 확인
    @RequestMapping("/send/editreceihis")
    public String editreceihis(@RequestParam(required = false) String re_kko_uid,
                               @RequestParam(required = false) String t_date,
                               @RequestParam(required = false) String t_time,
                               @RequestParam(required = false) String r_name,
                               @RequestParam(required = false) String r_nick
                               ) {

        sendService.editreceihis(re_kko_uid, t_date, t_time, r_name, r_nick);

        return "successtrans";
    }
    //회계처리하는 것 구현
}
