package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.ReceiHis;
import com.kakaobank.daina.assignment.mapper.SendMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendService {
    private final SendMapper sendMapper;

    public SendService(SendMapper sendMapper) {

        this.sendMapper = sendMapper;
    }

    @Transactional
    public void editreceihis(String re_kko_uid, String t_date, String t_time, String r_name, String r_nick) {

        ReceiHis byId = sendMapper.findById(re_kko_uid);
        if(byId == null) {
            //없으면 insert
            sendMapper.insert(ReceiHis.createNew(re_kko_uid, t_date, t_time, r_name, r_nick));
        }
        else {
            //있으면 update
            byId.edit(t_date, t_time);
            sendMapper.update(byId);
        }
    }


}
