package com.kakaobank.daina.assignment.controller;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.dto.ErrorMessage;
import com.kakaobank.daina.assignment.dto.SendMoneyIn;
import com.kakaobank.daina.assignment.dto.VerifyPasswordIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.exception.PasswordCountException;
import com.kakaobank.daina.assignment.service.TransInfoService;
import com.kakaobank.daina.assignment.service.VerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TransInfoController {

    private final TransInfoService transInfoService;
    private final VerificationService verificationService;
    private final Logger logger = LoggerFactory.getLogger(TransInfoController.class);

    public TransInfoController(TransInfoService transInfoService, VerificationService verificationService) {

        this.transInfoService = transInfoService;
        this.verificationService = verificationService;
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
    public String sendMoney(@Valid SendMoneyIn sendMoneyIn, Model model) {
        transInfoService.sendMoney(sendMoneyIn);

        List<SimTransDetail> infomation = transInfoService.findTransactions2(sendMoneyIn.gettId());
        model.addAttribute("infomation", infomation);

        return "successtrans";
    }
    //json형태로 데이터를 전달하는
    @PostMapping(value = "/send/verifyPassword")
    public ResponseEntity doTest(@Valid @RequestBody VerifyPasswordIn verifyPasswordIn, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(
                    DefaultMessageSourceResolvable::getDefaultMessage
            ).collect(Collectors.toList());

            ErrorMessage errorMessage = new ErrorMessage(errors);

            return ResponseEntity
                    .badRequest()
                    .body(errorMessage);
        }

        // TODO: 2022-03-08 비밀번호 실패시 오류 횟수 출력
        boolean check;

        check = verificationService.verifyPassword(verifyPasswordIn.getBaccPass(), verifyPasswordIn.getBaccId());

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

        logger.error("BizException", exception);
        return "redirect:/send/view";
    }
    @ExceptionHandler(PasswordCountException.class)
    public String error(PasswordCountException exception, RedirectAttributes redirectAttributes) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        logger.error("PasswordCountException", exception);
        return "redirect:/send/view";
    }
    @ExceptionHandler(BindException.class)
    public String error(BindException exception, RedirectAttributes redirectAttributes) {
        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(
                DefaultMessageSourceResolvable::getDefaultMessage
        ).collect(Collectors.toList());

        ErrorMessage errorMessage = new ErrorMessage(errors);
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        logger.error("BindException", exception);

        return "redirect:/send/view";
    }
}
