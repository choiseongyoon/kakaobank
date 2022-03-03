package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.ReHisTrans;
import com.kakaobank.daina.assignment.domain.ReceiHis;
import com.kakaobank.daina.assignment.domain.SimTransDetail;
import com.kakaobank.daina.assignment.dto.CreateRnameIn;
import com.kakaobank.daina.assignment.dto.CreateTransferIn;
import com.kakaobank.daina.assignment.mapper.ReHisTransMapper;
import com.kakaobank.daina.assignment.mapper.ReceiverMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReceiverService {

    private final ReceiverMapper receiverMapper;

    public ReceiverService(ReceiverMapper receiverMapper) {

        this.receiverMapper = receiverMapper;
    }

    public List<ReceiHis> findTransactions() {
        List<ReceiHis> receiHis = receiverMapper.findAll();
        return receiHis;
    }

    @Transactional
    public void createReceiver(CreateRnameIn createRnameIn) {
        receiverMapper.insert(SimTransDetail.createNew(
                createRnameIn.getrNick(),
                createRnameIn.getReKkoUid(),
                createRnameIn.getrName()
        ));
    }
}
