package com.kakaobank.daina.assignment.controller;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.dto.SendMoneyIn;
import com.kakaobank.daina.assignment.dto.ErrorMessage;
import com.kakaobank.daina.assignment.dto.VerifyPasswordIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.service.LoginService;
import com.kakaobank.daina.assignment.service.TransInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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


        //update 후 최종 계좌 정보 확인하는 select 문
        return "successtrans";
    }
    //json형태로 데이터를 전달하는
    @PostMapping(value = "/send/verifyPassword")
    public ResponseEntity doTest(@Valid @RequestBody VerifyPasswordIn verifyPasswordIn) {
        // TODO: 2022-03-08 비밀번호 실패시 오류 횟수 출력
        try {
            loginService.verifyPassword(verifyPasswordIn.getBaccPass(), verifyPasswordIn.getBaccId());
        } catch (BizException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorMessage(e.getMessage()));
        }
        //화면이 아닌 결과값을 데이터로 리턴한다.
        return ResponseEntity.ok().build();

    }
    //이체 정보 확인
//    @GetMapping("/send/ivewinfo")
//    public String retrieveInformation(Model model) {
//        List<SimTransDetail> transactions2 = transInfoService.findSimTrans(tId);
//        model.addAttribute("transactions2", transactions2);
//
//
//        return "transinfo";
//    }
//    //이체 실행 (이체구분코드 update)
//    @PostMapping("/send/edittcode")
//    public String editTcode(RedirectAttributes redirectAttributes) {
//        HashMap ob =transInfoService.editTcode(tId);
//
//        redirectAttributes.addAttribute("re_kko_uid", ob.get("re_kko_uid"));
//        redirectAttributes.addAttribute("r_name", ob.get("r_name"));
//        redirectAttributes.addAttribute("r_nick", ob.get("r_nick"));
//        redirectAttributes.addAttribute("t_date", ob.get("t_date"));
//        redirectAttributes.addAttribute("t_time", ob.get("t_time"));
//
//        return "redirect:/send/editreceihis";
//    }
    @ExceptionHandler(BizException.class)
    public String error(BizException exception, RedirectAttributes redirectAttributes) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        return "redirect:/send/view";
    }
}
