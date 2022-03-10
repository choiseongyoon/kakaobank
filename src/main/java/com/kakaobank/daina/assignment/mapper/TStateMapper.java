package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.domain.TState;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TStateMapper {
    String getTstateId();

    void insert(TState tState);
}
