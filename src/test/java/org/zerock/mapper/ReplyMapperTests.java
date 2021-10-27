package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/*/*/*.xml")

@Log4j2
public class ReplyMapperTests {

    // 테스트 전에 해당 번호 게시물 존재하는지 반드시 확인할 것!
    private Long[] bnoArr = {82l, 81l, 69l, 68l, 67l, 66l};

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;


    @Test
    public void testMapper(){
        log.debug("testMapper() invoked.");

        log.info("mapper : {}", mapper);
    } //testMapper


    @Test
    public void testCreate(){
        log.debug("testCreate() invoked.");

        IntStream.rangeClosed(1,10).forEach(
                i -> {
                    ReplyVO vo = new ReplyVO();

                    // 게시물 번호
                    vo.setBno(bnoArr[i%5]);
                    vo.setReply("댓글 테스트 " + i);
                    vo.setReplyer("replyer " + i);

                    mapper.insert(vo);
                }); //forEach
    } //testCreate


    @Test
    public void testRead(){
        log.debug("testRead() invoked.");

        Long targetRno = 5l;

        ReplyVO vo = mapper.read(targetRno);

        log.info("\t + vo : {}, vo");
    } //testRead


    @Test
    public void testDelete(){
        log.debug("testDelete() invoked.");

        Long targetRno = 1l;

        mapper.delete(targetRno);

        log.info(targetRno + " deleted...");
    } //tesetDelete


    @Test
    public void testUpdate(){
        log.debug("testUpdate() invoked.");

        Long targetRno = 10l;

        ReplyVO vo = mapper.read(targetRno);

        vo.setReplyer("Update Reply!");

        int count = mapper.update(vo);

        log.info("UPDATE COUNT : " + count);
    } //testUpdate


    @Test
    public void testList(){
        log.debug("testList() invoked.");

        Criteria cri = new Criteria();

        List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);

        replies.forEach(reply -> log.info(reply));
    } //testList

} //end class
