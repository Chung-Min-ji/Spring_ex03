<%@ page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file ="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header"> Board Read </h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">

        <div class="panel panel-default">

            <div class="panel-heading">Board Read Page</div>
            <!-- /.pannel-heading -->
            <div class="panel-body">

                <div class="form-group">
                    <label>Bno</label>
                    <input class="form-control" name="bno" value="<c:out value="${board.bno}"/>"
                           readonly="readonly">
                </div>

                <div class="form-group">
                    <label>Title</label>
                    <input class="form-control" name="title" value="<c:out value="${board.title}"/>"
                           readonly="readonly">
                </div>

                <div class="form-group">
                    <label>Text area</label>
                    <textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out value="${board.content}"/></textarea>
                </div>

                <div class="form-group">
                    <label>Writer</label>
                    <input class="form-control" name="writer" value="<c:out value="${board.writer}" />"
                           readonly="readonly">
                </div>

                <button data-oper="modify"
                        class="btn btn-default"
                        onclick="location.href='/board/modify?bno=<c:out value="${board.bno}" />'">
                    Modify
                </button>

                <button data-oper="list"
                        class="btn btn-info"
                        onclick="location.href='/board/list'">
                    List
                </button>

                <form id="operForm" action="/board/modify" method="get">
                    <input type="hidden" id="bno" name="bno" value='<c:out value="${board.bno}"/>'>
                    <input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}'/>">
                    <input type="hidden" name="amount" value="<c:out value='${cri.amount}'/>">
                    <input type="hidden" name="keyword" value="<c:out value='${cri.keyword}'/>">
                    <input type="hidden" name="type" value="<c:out value='${cri.type}'/>">
                </form>

            </div>
            <!-- end panel-body -->

        </div>
        <!-- end panel-heading -->
    </div>
    <!-- end panel -->
</div>
<!-- /.row -->

<div class="row">

    <div class="col-lg-12">

        <!-- /.panel -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"></i>
                Reply
            </div>
        </div>

        <!-- /.panel-heading -->
        <div class="panel-body">

            <ul class="chat">
                <!-- start reply -->
                <li class="left clearfix" data-rno="12">
                    <div>
                        <div class="header">
                            <strong class="primary-font">user00</strong>
                            <small class="pull-right text-muted">2018-01-01 13:13</small>
                        </div>
                        <p>Good job!</p>
                    </div>
                </li>
                <!-- end reply -->
            </ul>
            <!-- /.end ul-->
        </div>
        <!-- /.panel .chat-panel -->
    </div>
</div>
<!-- ./end row -->

<%@include file="../includes/footer.jsp" %>

<script type="text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">
    $(function(){
        console.log("get.js started.....");
        console.log("replyService : {}" , replyService);

        var operForm = $("#operForm");

        var bnoValue = '<c:out value="${board.bno}"/>';
        var replyUL = $(".chat");


        $("button[data-oper='modify']").on("click", function(e){
            operForm.attr("action", "/board/modify").submit();

        }); //on click for modify


        $("button[data-oper='list']").on("click", function(e){
            operForm.find("#bno").remove();
            operForm.attr("action", "/board/list");
            operForm.submit();

        }); //on click for list


        showList(1);

        function showList(page){
            replyService.getList({bno:bnoValue, page:page||1}, function(list){

                var str="";

                if(list==null || list.length == 0){
                    replyUL.html("");

                    return;
                } //if : 작성된 댓글이 없으면..

                for (var i=0, len=list.length||0; i<len; i++){
                    str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>";
                    str += " <div><div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>";
                    str += "   <small class='pull-right text-muted'>"
                        + replyService.displayTime(list[i].replyDate)
                        + "</small></div>";
                    str += "      <p>"+list[i].reply+"</p></div></li>";
                } //for

                replyUL.html(str);
            }); //function
        }// showList
    }); //.jq


    console.log("----------reply.js TEST");

    var bnoValue = '<c:out value="${board.bno}"/>';

    //-- for replyService List test
    replyService.getList({bno:bnoValue, page:1}, function(list){

        for(var i=0, len=list.length||0; i<len; i++){
            console.log(list[i]);
        } //for
    }); //getList

    //-- for replyService add test
    replyService.add({reply:"js Test", replyer:"tester", bno:bnoValue}, function(result){
            alert("RESULT : " + result);
    }); //add

    //-- 4번 댓글 삭제 테스트
    replyService.remove(4,function(count){
      console.log(count);

      if(count === "success"){
          alert("REMOVED");
      } //if
    }, function(err){
        alert("REMOVE ERROR...");
    }); //remove

    //-- 9번 댓글 수정 테스트
    replyService.update({
        rno: 9,
        bno: bnoValue,
        reply: "Modified Reply..."
    }, function(result){
        alert("수정완료...");
    }); //update

    //-- 9번 댓글 보기 테스트
    replyService.get(10, function(data){
        console.log(data);
    }); //get

</script>