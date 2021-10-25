package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import javax.print.attribute.standard.Media;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/sample")

@Log4j
public class SampleController {

    @GetMapping(value="/getText", produces="text/plain; charset=utf-8")
    public String getText(){
        log.debug("getText() invoked.");

        log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);

        return "안녕하세요";
    } //getText


    @GetMapping(value="/getSample",
            produces={ MediaType.APPLICATION_JSON_VALUE,
                       MediaType.APPLICATION_XML_VALUE })
    public SampleVO getSample(){
        log.debug("getSample() invoked.");

        return new SampleVO(112, "스타", "로드");
    } //getSample


    @GetMapping(value = "/getSample2")
    public SampleVO getSample2(){
        log.debug("getSample2() invoked.");

        return new SampleVO(113, "로켓", "라쿤");
    } //getSample2


    @GetMapping("/getList")
    public List<SampleVO> getList(){
        log.debug("getList() invoked.");

        return IntStream.range(1, 10)
                .mapToObj(
                        i -> new SampleVO(i, i+"First", i+"Last")
                ).collect(Collectors.toList());
    } //getList


    @GetMapping("/getMap")
    public Map<String, SampleVO> getMap(){
        log.debug("getMap() invoked.");

        Map<String, SampleVO> map = new HashMap<>();
        map.put("First", new SampleVO(111, "그루트", "주니어"));

        return map;
    } //getMap


    @GetMapping(value="/check", params={"height", "weight"})
    public ResponseEntity<SampleVO> check(Double height, Double weight){
        log.debug("check(height, weight) invoekd.");

        SampleVO vo = new SampleVO(0, "" + height, "" + weight);

        ResponseEntity<SampleVO> result = null;

        if(height < 150){
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
        } else {
            result = ResponseEntity.status(HttpStatus.OK).body(vo);
        } // if~else

        return result;
    } //check


    @GetMapping("/product/{cat}/{pid}")
    public String[] getPath(
            @PathVariable("cat") String cat,
            @PathVariable("pid") Integer pid){
        log.debug("getPath(cat, pid) invoked.");

        return new String[]{"category: " + cat, "productid: " + pid};
    } //getPath


    @PostMapping("/ticket")
    public Ticket convert(@RequestBody Ticket ticket){
        log.debug("convert(ticket) invoked.... ticket : " + ticket);

        return ticket;
    } //convert

} //end class
