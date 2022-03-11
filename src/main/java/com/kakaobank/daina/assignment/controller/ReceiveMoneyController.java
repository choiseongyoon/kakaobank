package com.kakaobank.daina.assignment.controller;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.ReceiHis;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.dto.*;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.service.LoginService;
import com.kakaobank.daina.assignment.service.ReceiverMoneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ReceiveMoneyController {

    ReceiverMoneyService receiverMoneyService;
    LoginService loginService;
    Logger loggerFactory = LoggerFactory.getLogger(ReceiveMoneyController.class);

    public ReceiveMoneyController(ReceiverMoneyService receiverMoneyService, LoginService loginService) {
        this.receiverMoneyService = receiverMoneyService;
        this.loginService = loginService;
    }

    @GetMapping("/receive/view")
    public String retrieveAmount(Model model) {
        List<SimTransDetail> transactions = receiverMoneyService.findTransactions("USER1");
        model.addAttribute("transactions", transactions);

        return "receiveMoney";
    }
    @PostMapping("/receive/money")
    public String receiveMoney(@Valid ReceiveMoneyIn receiveMoneyIn) {
        receiverMoneyService.receiveMoney(receiveMoneyIn);


        return "successtrans";
    }
    // TODO: 2022-03-11 user이름이랑 고객이름 일치하지 않으면 취소 프로세스 
    @PostMapping(value = "/receive/verifyName")
    public ResponseEntity doTest(@Valid @RequestBody VerifyNameIn verifyNameIn) {
        try {
            loggerFactory.debug(verifyNameIn.getReceivename(), verifyNameIn.getUsername());
            loginService.verifyName(verifyNameIn.getReceivename(), verifyNameIn.getUsername());
        } catch (BizException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorMessage(e.getMessage()));
        }

        return ResponseEntity.ok().build();
    }
    @GetMapping("/receive/fail")
    public String retrieveFail() {

        return "fail";
    }
    @ExceptionHandler(BizException.class)
    public String error(BizException exception, RedirectAttributes redirectAttributes) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        return "redirect:/receive/view";
    }
    @ExceptionHandler(BindException.class)
    public String error(BindException exception, RedirectAttributes redirectAttributes) {
        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(
                DefaultMessageSourceResolvable::getDefaultMessage
        ).collect(Collectors.toList());

        ErrorMessage errorMessage = new ErrorMessage(errors);
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        return "redirect:/receive/view";
    }
}
