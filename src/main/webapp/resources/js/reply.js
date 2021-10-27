console.log("Reply js started....");

var replyService = (function(){

    function add(reply, callback, error) {
        console.log("add reply.....");

        $.ajax({
            method: 'post',
            url: '/replies/new',
            data: JSON.stringify(reply),
            contentType: "application/json; charset=urf-8",
            success: function (result, status, xhr) {
                if (callback) {
                    callback(result);
                } //if
            }, //success for ajax

            error: function (xhr, status, er) {
                if (error) {
                    error(er);
                }
            } //error for ajax
        }); //ajax
    } //add

    function getList(param, callback, error){
        var bno = param.bno;
        var page = param.page || 1;

        $.getJSON("/replies/pages/" + bno + "/" + page + ".json",
            function(data){
                if(callback){
                    callback(data);
                } //if
            }).fail(function(xhr, status, err){
                if(error){
                    error();
                } //if
        }); //function
    } //getList

    return {
        add:add,
        getList : getList
    }; //return

})(); //IIFE