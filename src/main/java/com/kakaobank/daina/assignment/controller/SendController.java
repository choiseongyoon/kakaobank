package com.kakaobank.daina.assignment.controller;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.dto.EditAmountIn;
import com.kakaobank.daina.assignment.service.SendService;
import com.kakaobank.daina.assignment.service.TransInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

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
                               @RequestParam(required = false) String t_time
                               ) {

        int var = sendService.editreceihis(re_kko_uid, t_date, t_time);

        return "successtrans";
    }
}
