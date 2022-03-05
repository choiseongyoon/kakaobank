package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.ReHisTrans;
import com.kakaobank.daina.assignment.domain.ReceiHis;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.dto.EditAmountIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.mapper.SendMapper;
import com.kakaobank.daina.assignment.mapper.TransInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SendService {
    private final SendMapper sendMapper;

    public SendService(SendMapper sendMapper) {

        this.sendMapper = sendMapper;
    }

    @Transactional
    public int editreceihis(String re_kko_uid, String t_date, String t_time) {

        ReceiHis byId = sendMapper.findById(re_kko_uid);
        if(byId == null) {
            return 0;
        }

        byId.edit(t_date, t_time);
        sendMapper.update(byId);

        return 1;
    }


}
