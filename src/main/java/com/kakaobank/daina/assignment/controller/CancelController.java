package com.kakaobank.daina.assignment.controller;

import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.dto.CancelIn;
import com.kakaobank.daina.assignment.dto.ErrorMessage;
import com.kakaobank.daina.assignment.dto.ReceiveMoneyIn;
import com.kakaobank.daina.assignment.dto.VerifyNameIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.service.CancelService;
import com.kakaobank.daina.assignment.service.ReceiverMoneyService;
import com.kakaobank.daina.assignment.service.VerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CancelController {
    CancelService cancelService;

    Logger logger = LoggerFactory.getLogger(CancelController.class);
    public CancelController(CancelService cancelService) {
        this.cancelService = cancelService;
    }

    @GetMapping("/cancel/view")
    public String retrieveAmount(Model model) {
        List<SimTransDetail> transactions = cancelService.findTransactions("USER1");
        model.addAttribute("transactions", transactions);

        return "cancel";
    }
    @PostMapping("/cancel/money")
    public String cancelMoney(@Valid CancelIn cancelIn, Model model) {
        logger.debug("야옹");
        cancelService.cancelMoney(cancelIn.gettId(), "C2");

        SimTransDetail infomation = cancelService.findInformation(cancelIn.gettId());
        model.addAttribute("infomation", infomation);


        return "successcancel";
    }
    @ExceptionHandler(BizException.class)
    public String error(BizException exception, RedirectAttributes redirectAttributes) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        return "redirect:/cancel/view";
    }
    @ExceptionHandler(BindException.class)
    public String error(BindException exception, RedirectAttributes redirectAttributes) {
        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(
                DefaultMessageSourceResolvable::getDefaultMessage
        ).collect(Collectors.toList());

        ErrorMessage errorMessage = new ErrorMessage(errors);
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        return "redirect:/cancel/view";
    }
}
