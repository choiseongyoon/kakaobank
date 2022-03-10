package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccInfoMapper {
    AccInfo findBaccAll(@Param("bacc_id") String bacc_id);

    void update(AccInfo accInfo);
    void updateCnt(AccInfo accInfo);

}
