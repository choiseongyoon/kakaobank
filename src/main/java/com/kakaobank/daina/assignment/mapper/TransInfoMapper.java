package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.ReceiHis;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransInfoMapper {
    List<AccInfo> findAll(@Param("bacc_id") String bacc_id);

//    void insert(SimTransDetail simTransDetail);
}
