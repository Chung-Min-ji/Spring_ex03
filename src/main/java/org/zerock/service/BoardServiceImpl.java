package org.zerock.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import java.util.List;


@Service

@Log4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

    // 스프링 4.3 이후부터는, 주입받으려는 필드가 하나일 때 어노테이션 안붙여도 자동주입 됨.
    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;


    // 게시물 등록
    @Override
    public void register(BoardVO board){
        log.debug("register(board) invoked");

        mapper.insertSelectKey(board);
    } //register


    // 특정 게시물 조회
    @Override
    public BoardVO get(Long bno) {
        log.debug("get(bno) invoked.");

        log.info("bno : " + bno);

        return mapper.read(bno);
    } //get


    // 게시물 수정
    @Override
    public boolean modify(BoardVO board) {
        log.debug("modify(board) invoked.");

        log.info("modify : " + board);

        return mapper.update(board) == 1;
    } //modify


    // 게시물 삭제
    @Override
    public boolean remove(Long bno) {
        log.debug("remove(bno) invoked.");

        log.info("bno : " + bno);

        return mapper.delete(bno) == 1;
    } //remove


    // 전체 게시물 목록 조회
//    @Override
//    public List<BoardVO> getList() {
//        log.debug("getList() invoked");
//
//        return mapper.getList();
//    } //getList


    // 전체 게시물 조회 with 페이징
    @Override
    public List<BoardVO> getList(Criteria cri){
        log.debug("getList(cri) invoked. cri : " + cri);

        return mapper.getListWithPaging(cri);
    } //getList


    @Override
    public int getTotal(Criteria cri){
        log.debug("getTotal(cri) invoked.");

        return mapper.getTotalCount(cri);
    } //getTotal

} //BoardServiceImpl

