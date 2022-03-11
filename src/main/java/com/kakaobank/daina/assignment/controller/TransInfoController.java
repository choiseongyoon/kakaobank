package com.kakaobank.daina.assignment.controller;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.HistorySimTransDetail;
import com.kakaobank.daina.assignment.domain.JournalRule;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.dto.SendMoneyIn;
import com.kakaobank.daina.assignment.dto.ErrorMessage;
import com.kakaobank.daina.assignment.dto.VerifyPasswordIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.service.AccountingService;
import com.kakaobank.daina.assignment.service.LoginService;
import com.kakaobank.daina.assignment.service.TransInfoService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TransInfoController {

    private final TransInfoService transInfoService;
    private final LoginService loginService;

    public TransInfoController(TransInfoService transInfoService, LoginService loginService) {

        this.transInfoService = transInfoService;
        this.loginService = loginService;
    }

    //계좌 조회, 간편이체거래내역 조회
    @GetMapping("/send/viewamount")
    public String retrieveAmount(@RequestParam Long t_id, @RequestParam String bacc_id,
                                  Model model) {

        AccInfo transactions = transInfoService.findTransactions(bacc_id);
        List<SimTransDetail> transactions2 = transInfoService.findTransactions2(t_id);

        model.addAttribute("transactions", transactions);
        model.addAttribute("transactions2", transactions2);

        return "transinfo";
    }
    //송금프로세스
    @PostMapping("/send/money")
    public String sendMoney(@Valid SendMoneyIn sendMoneyIn) {
        transInfoService.sendMoney(sendMoneyIn);


        return "successtrans";
    }
    //json형태로 데이터를 전달하는
    @PostMapping(value = "/send/verifyPassword")
    public ResponseEntity doTest(@Valid @RequestBody VerifyPasswordIn verifyPasswordIn) {
        // TODO: 2022-03-08 비밀번호 실패시 오류 횟수 출력
        boolean check;
        try {
            check = loginService.verifyPassword(verifyPasswordIn.getBaccPass(), verifyPasswordIn.getBaccId());
        } catch (BizException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorMessage(e.getMessage()));
        }
        if(check == true)   return ResponseEntity.ok().build();
        //화면이 아닌 결과값을 데이터로 리턴한다.
        else return ResponseEntity
                .badRequest()
                .body(new ErrorMessage("비밀번호가 일치하지 않습니다."));

    }

    @ExceptionHandler(BizException.class)
    public String error(BizException exception, RedirectAttributes redirectAttributes) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        return "redirect:/send/viewamount";
    }
    @ExceptionHandler(BindException.class)
    public String error(BindException exception, RedirectAttributes redirectAttributes) {
        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(
                DefaultMessageSourceResolvable::getDefaultMessage
        ).collect(Collectors.toList());

        ErrorMessage errorMessage = new ErrorMessage(errors);
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        return "redirect:/send/viewamount";
    }
}
