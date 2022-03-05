package com.kakaobank.daina.assignment.controller;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.dto.EditAccountIn;
import com.kakaobank.daina.assignment.dto.EditAmountIn;
import com.kakaobank.daina.assignment.service.TransInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TransInfoController {

    TransInfoService transInfoService;

    private String tId;
    private String baccId;

    public TransInfoController(TransInfoService transInfoService) {
        this.transInfoService = transInfoService;
    }

    //여기부터해애라아~
    //이체금액조회 <acc_im> 테이블
    @GetMapping("/send/viewamount")
    public String retrieveAmount(@RequestParam String t_id,
                                  @RequestParam String bacc_id,
                                  Model model) {
        tId = t_id;
        baccId = bacc_id;

        List<AccInfo> transactions = transInfoService.findTransactions(tId, baccId);
        model.addAttribute("transactions", transactions);

        System.out.println(tId);
        System.out.println(baccId);

        return "transinfo";
    }
    //송금금액 입력
    @PostMapping("/send/editamount")
    public String editAmount(@Valid EditAmountIn editAmountIn) {
        transInfoService.editAmount(editAmountIn, tId);

        //update 후 최종 계좌 정보 확인하는 select 문
        return "redirect:/send/viewinfo";
    }
    //이체 정보 확인
    @GetMapping("/send/viewinfo")
    public String retrieveInformation(Model model) {
        List<SimTransDetail> transactions2 = transInfoService.findSimTrans(tId);
        model.addAttribute("transactions2", transactions2);


        return "transinfo";
    }
    //이체 실행 (이체구분코드 update)
    @PostMapping("/send/edittcode")
    public String editTcode() {
        transInfoService.editTcode(tId);

        return "successtrans";
    }
}
