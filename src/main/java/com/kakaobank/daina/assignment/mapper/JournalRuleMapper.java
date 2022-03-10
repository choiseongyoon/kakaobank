package com.kakaobank.daina.assignment.mapper;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.JournalRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JournalRuleMapper {
    List<JournalRule> findAll(String caseNum);

}
