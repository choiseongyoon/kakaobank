package com.kakaobank.daina.assignment.service;

import com.kakaobank.daina.assignment.domain.Account;
import com.kakaobank.daina.assignment.domain.ReHisTrans;
import com.kakaobank.daina.assignment.dto.*;
import com.kakaobank.daina.assignment.exception.BizException;
import com.kakaobank.daina.assignment.mapper.AccountMapper;
import com.kakaobank.daina.assignment.mapper.ReHisTransMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferService {
    private final ReHisTransMapper reHisTransMapper;

    public TransferService(ReHisTransMapper reHisTransMapper) {

        this.reHisTransMapper = reHisTransMapper;
    }
    //과거 이체 이력이 있는 친구 리스트를 호출하는 함수
    public List<ReHisTrans> findTransactions() {
        List<ReHisTrans> reHisTrans = reHisTransMapper.findAll();
        return reHisTrans;
    }

    @Transactional
    public void createTransfer(CreateTransferIn createTransferIn) {
        reHisTransMapper.insert(ReHisTrans.createNew(
                createTransferIn.getBookmark(),
                createTransferIn.getrName(),
                createTransferIn.getrNick()
                ));
    }

    @Transactional
    public void editTransfer(EditTransferIn editTransferIn) {
//        if(!StringUtils.hasText(editAccountIn.getKorName())) {
//            throw new BizException("한글이름을 입력하세요");
//        }
//
//        if(!StringUtils.hasText(editAccountIn.getState())) {
//            throw new BizException("상태를 입력해주세요.");
//        }
//
//        if(!List.of("NORMAL", "DELETE").contains(editAccountIn.getState())) {
//            throw new BizException("유효한 상태값이 아닙니다.");
//        }
        ReHisTrans byId = reHisTransMapper.findById(editTransferIn.gettId());

        if(byId == null) {
            throw new BizException("존재하지 않는 사용자입니다.");
        }

        byId.edit(editTransferIn.getrName(), editTransferIn.getrNick(), editTransferIn.getBookmark() );
        reHisTransMapper.update(byId);
    }
}
