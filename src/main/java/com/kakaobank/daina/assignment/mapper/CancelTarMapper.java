package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.CancelTar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CancelTarMapper {
    CancelTar findById(Long tId);
    void updateRcode(CancelTar cancelTar);
}
