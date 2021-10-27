package org.zerock.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import java.util.List;

@Service
@Log4j2
public class ReplyServiceImpl implements ReplyService{

    @Setter(onMethod_=@Autowired)
    private ReplyMapper mapper;

    @Override
    public int register(ReplyVO vo){
        log.debug("register({}) invoked.", vo);

        return mapper.insert(vo);
    } //register


    @Override
    public ReplyVO get(Long rno){
        log.debug("get({}) invoked.", rno);

        return mapper.read(rno);
    } //get


    @Override
    public int modify(ReplyVO vo){
        log.debug("modify({}) invoked.", vo);

        return mapper.update(vo);
    } //modify


    @Override
    public int remove(Long rno){
        log.debug("remove({}) invoked.", rno);

        return mapper.delete(rno);
    } //remove


    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno){
        log.debug("getList({},{}) invoked.", cri, bno);

        return mapper.getListWithPaging(cri, bno);
    } //getList


} //end class
