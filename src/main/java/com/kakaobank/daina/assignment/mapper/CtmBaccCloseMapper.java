package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.Account;
import com.kakaobank.daina.assignment.domain.CtmBaccClose;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CtmBaccCloseMapper {
    void insert(CtmBaccClose ctmBaccClose);

}
