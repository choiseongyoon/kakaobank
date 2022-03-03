package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.Account;
import com.kakaobank.daina.assignment.domain.ReceiHis;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.SimpleTimeZone;

@Mapper
public interface ReceiverMapper {
    List<ReceiHis> findAll();

    void insert(SimTransDetail simTransDetail);
}
