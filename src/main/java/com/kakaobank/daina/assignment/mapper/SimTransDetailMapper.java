package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.SimTransDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SimTransDetailMapper {

    List<SimTransDetail> findTransAll(@Param("t_id") String t_id);


    SimTransDetail findById(Long tId);

    void update(SimTransDetail simTransDetail);

    List<SimTransDetail> findDetail(@Param("t_id") Long tId);

    void updatetCode(SimTransDetail simTransDetail);
}
