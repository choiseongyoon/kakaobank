package com.kakaobank.daina.assignment.controller;

import com.kakaobank.daina.assignment.domain.ReHisTrans;
import com.kakaobank.daina.assignment.domain.ReceiHis;
import com.kakaobank.daina.assignment.dto.CreateRnameIn;
import com.kakaobank.daina.assignment.dto.CreateTransferIn;
import com.kakaobank.daina.assignment.dto.EditTransferIn;
import com.kakaobank.daina.assignment.service.ReceiverService;
import com.kakaobank.daina.assignment.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReceiverController {

    private final ReceiverService receiverService;

    public ReceiverController(ReceiverService receiverService) {

        this.receiverService = receiverService;
    }

    @GetMapping("/send/view")
    public String retrieveReceiverHis(Model model) {
        List<ReceiHis> transactions = receiverService.findTransactions();
        model.addAttribute("transactions", transactions);

        return "receiver";
    }

    @PostMapping("/send/re-choice")
    public String createRname(CreateRnameIn createRnameIn) {
        receiverService.createReceiver(createRnameIn);

        //다음 페이지로 변경하기
        return "redirect:/send/view";
    }

}
