package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.Account;
import com.kakaobank.daina.assignment.domain.State;
import com.kakaobank.daina.assignment.dto.CreateAccountIn;
import com.kakaobank.daina.assignment.dto.EditAccountIn;
import com.kakaobank.daina.assignment.dto.RemoveAccountIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.mapper.AccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountMapper accountMapper;

    public AccountService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public List<Account> findUsers() {
        List<Account> users = accountMapper.findAll();
        return users;
    }

    @Transactional
    public void createAccount(CreateAccountIn createAccountIn) {
        Account account = accountMapper.findByNickname(createAccountIn.getNickname());
        if(account != null) {
            throw new BizException("이미 존재하는 닉네임입니다.");
        }

        accountMapper.insert(Account.createNew(
                createAccountIn.getNickname(),
                createAccountIn.getKorName(),
                createAccountIn.getTelNum()));
    }

    @Transactional
    public void removeAccount(RemoveAccountIn removeAccountIn) {
        Account account = accountMapper.findById(removeAccountIn.getAccountId());
        if(account == null) {
            throw new BizException("존재하지 않는 사용자입니다.");
        }

        account.remove();
        accountMapper.update(account);
    }

    @Transactional
    public void editAccount(EditAccountIn editAccountIn) {
//        if(!StringUtils.hasText(editAccountIn.getKorName())) {
//            throw new BizException("한글이름을 입력하세요");
//        }
//
//        if(!StringUtils.hasText(editAccountIn.getState())) {
//            throw new BizException("상태를 입력해주세요.");
//        }
//
//        if(!List.of("NORMAL", "DELETE").contains(editAccountIn.getState())) {
//            throw new BizException("유효한 상태값이 아닙니다.");
//        }

        Account byId = accountMapper.findById(editAccountIn.getAccountId());
        if(byId == null) {
            throw new BizException("존재하지 않는 사용자입니다.");
        }

        byId.edit(editAccountIn.getKorName(), editAccountIn.getTelNum(), editAccountIn.getState());
        accountMapper.update(byId);
    }
}
