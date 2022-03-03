package com.kakaobank.daina.assignment.controller;

import com.kakaobank.daina.assignment.domain.ReHisTrans;
import com.kakaobank.daina.assignment.dto.CreateTransferIn;
import com.kakaobank.daina.assignment.dto.EditTransferIn;
import com.kakaobank.daina.assignment.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {

        this.transferService = transferService;
    }

    @GetMapping("/trans/view")
    public String retrieveTransfer(Model model) {
        List<ReHisTrans> transactions = transferService.findTransactions();
        model.addAttribute("transactions", transactions);

        return "history";
    }

    @PostMapping("/trans/add")
    public String createTransfer(CreateTransferIn createTransferIn) {
        transferService.createTransfer(createTransferIn);

        return "redirect:/trans/view";
    }

    @PostMapping("/trans/edit")
    public String editAccount(@Valid EditTransferIn editTransferIn) {
        transferService.editTransfer(editTransferIn);

        return "redirect:/trans/view";
    }
}
