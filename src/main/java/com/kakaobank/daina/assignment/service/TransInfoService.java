package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.AccInfo;
import com.kakaobank.daina.assignment.domain.Account;
import com.kakaobank.daina.assignment.domain.ReceiHis;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.dto.CreateRnameIn;
import com.kakaobank.daina.assignment.dto.EditAccountIn;
import com.kakaobank.daina.assignment.dto.EditAmountIn;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.mapper.ReceiverMapper;
import com.kakaobank.daina.assignment.mapper.TransInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class TransInfoService {

    private final TransInfoMapper transInfoMapper;

    public TransInfoService(TransInfoMapper transInfoMapper) {

        this.transInfoMapper = transInfoMapper;
    }

    public List<AccInfo> findTransactions(String t_id, String bacc_id) {
        List<AccInfo> accInfo = transInfoMapper.findAll(bacc_id);

        return accInfo;
    }
    @Transactional
    public void editAmount(EditAmountIn editAmountIn, String t_id) {

        SimTransDetail byId = transInfoMapper.findById(Long.parseLong(t_id));
        if(byId == null) {
            throw new BizException("존재하지 않는 사용자입니다.");
        }

        byId.edit(editAmountIn.gettAmount());
        transInfoMapper.update(byId);
    }
    public List<SimTransDetail> findSimTrans(String t_id) {
        List<SimTransDetail> simInfo = transInfoMapper.findDetail(Long.parseLong(t_id));

        return simInfo;
    }

    @Transactional
    public HashMap editTcode(String t_id) {

        SimTransDetail byId = transInfoMapper.findById(Long.parseLong(t_id));
        if(byId == null) {
            throw new BizException("존재하지 않는 사용자입니다.");
        }

        byId.editTcode();

        transInfoMapper.updatetCode(byId);

        HashMap<String, String> ob = new HashMap<String, String>();
        ob.put("re_kko_uid", byId.getReKkoUid());
        ob.put("r_name", byId.getrName());
        ob.put("r_nick", byId.getrNick());
        ob.put("t_date", String.valueOf(byId.gettDate()));
        ob.put("t_time", String.valueOf(byId.gettTime()));

        System.out.println(byId.getReKkoUid());

        return ob;
    }

}
