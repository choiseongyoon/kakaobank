package com.kakaobank.daina.assignment.controller;

import com.kakaobank.daina.assignment.domain.Account;
import com.kakaobank.daina.assignment.dto.CreateAccountIn;
import com.kakaobank.daina.assignment.dto.EditAccountIn;
import com.kakaobank.daina.assignment.dto.ErrorMessage;
import com.kakaobank.daina.assignment.dto.RemoveAccountIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.service.AccountService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/user/view")
    public String retrieveAccounts(Model model) {
        List<Account> users = accountService.findUsers();
        model.addAttribute("users", users);

        return "account";
    }

    @PostMapping("/user/add")
    public String createAccount(CreateAccountIn createAccountIn) {
        accountService.createAccount(createAccountIn);

        return "redirect:/user/view";
    }

    @PostMapping("/user/remove")
    public String removeAccount(RemoveAccountIn removeAccountIn) {
        accountService.removeAccount(removeAccountIn);

        return "redirect:/user/view";
    }

    @PostMapping("/user/edit")
    public String editAccount(@Valid EditAccountIn editAccountIn) {
        accountService.editAccount(editAccountIn);

        return "redirect:/user/view";
    }

    @ExceptionHandler(BizException.class)
    public String error(BizException exception, RedirectAttributes redirectAttributes) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        return "redirect:/user/view";
    }

    @ExceptionHandler(BindException.class)
    public String error(BindException exception, RedirectAttributes redirectAttributes) {
        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(
                DefaultMessageSourceResolvable::getDefaultMessage
        ).collect(Collectors.toList());

        ErrorMessage errorMessage = new ErrorMessage(errors);
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        return "redirect:/user/view";
    }
}
