package com.kakaobank.daina.assignment.controller;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.ReceiHis;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.dto.ReceiveMoneyIn;
import com.kakaobank.daina.assignment.dto.SendMoneyIn;
import com.kakaobank.daina.assignment.service.ReceiverMoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReceiveMoneyController {

    ReceiverMoneyService receiverMoneyService;

    public ReceiveMoneyController(ReceiverMoneyService receiverMoneyService) {
        this.receiverMoneyService = receiverMoneyService;
    }

    @GetMapping("/receive/view")
    public String retrieveAmount(Model model) {
        List<SimTransDetail> transactions = receiverMoneyService.findTransactions("USER1");
        model.addAttribute("transactions", transactions);

        return "receiveMoney";
    }
    @PostMapping("/receive/money")
    public String sendMoney(@Valid ReceiveMoneyIn receiveMoneyIn) {
        


        return "successtrans";
    }

}
