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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
public class ReceiverController {

    private final ReceiverService receiverService;

    public ReceiverController(ReceiverService receiverService) {

        this.receiverService = receiverService;
    }
    //과거이체이력이 있는 친구 리스트 select
    @GetMapping("/send/view")
    public String retrieveReceiverHis(Model model) {
        List<ReceiHis> transactions = receiverService.findTransactions();
        model.addAttribute("transactions", transactions);

        return "receiver";
    }
    //실명입력받고 거래내역 insert
    @PostMapping("/send/re-choice")
    public String createRname(CreateRnameIn createRnameIn, RedirectAttributes redirectAttributes) {
        HashMap ob = receiverService.createReceiver(createRnameIn);

        //다음 페이지로 변경하기
        redirectAttributes.addAttribute("t_id", ob.get("t_id"));
        redirectAttributes.addAttribute("bacc_id", ob.get("bacc_id"));

        return "redirect:/send/viewamount";
    }

}
