package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    List<Account> findAll();

    Account findByNickname(String nickname);

    void insert(Account account);

    Account findById(Long accountId);

    void update(Account account);
}
