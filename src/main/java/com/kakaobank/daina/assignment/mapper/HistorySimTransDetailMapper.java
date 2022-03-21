package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.HistorySimTransDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistorySimTransDetailMapper {
    void insert(HistorySimTransDetail historySimTransDetail);
}
