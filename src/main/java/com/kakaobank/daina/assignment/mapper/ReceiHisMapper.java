package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.ReceiHis;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReceiHisMapper {
    List<ReceiHis> findAll();

    ReceiHis findById(String re_kko_uid);

    void update(ReceiHis receiHis);

    void insert(ReceiHis receiHis);

}
