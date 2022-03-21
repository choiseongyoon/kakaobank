package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.CtmBaccClose;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CtmBaccCloseMapper {
    void insert(CtmBaccClose ctmBaccClose);

}
