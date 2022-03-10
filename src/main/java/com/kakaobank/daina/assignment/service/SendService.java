package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.ReceiHis;
import com.kakaobank.daina.assignment.mapper.ReceiHisMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendService {
    private final ReceiHisMapper receiHisMapper;

    public SendService(ReceiHisMapper receiHisMapper) {

        this.receiHisMapper = receiHisMapper;
    }

    @Transactional
    public void editreceihis(String re_kko_uid, String t_date, String t_time, String r_name, String r_nick) {

        ReceiHis byId = receiHisMapper.findById(re_kko_uid);
        if(byId == null) {
            //없으면 insert
            receiHisMapper.insert(ReceiHis.createNew(re_kko_uid, t_date, t_time, r_name, r_nick));
        }
        else {
            //있으면 update
            byId.edit(t_date, t_time);
            receiHisMapper.update(byId);
        }
    }


}
