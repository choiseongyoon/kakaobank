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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SendController {

    SendService sendService;

    private String tId;

    public SendController(SendService sendService) {
        this.sendService = sendService;
    }

//    //과거 이체 이력이 있는 친구 정보 확인
//    @PostMapping("/send/editreceihis")
//    public String editTcode() {
//        transInfoService.editTcode(tId);
//
//        return "successtrans";
//    }
}
