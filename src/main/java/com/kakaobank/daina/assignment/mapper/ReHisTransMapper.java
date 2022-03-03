package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.ReHisTrans;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReHisTransMapper {
    List<ReHisTrans> findAll();

    void insert(ReHisTrans reHisTrans);

    ReHisTrans findById(Long tId);

    void update(ReHisTrans reHisTrans);
}
