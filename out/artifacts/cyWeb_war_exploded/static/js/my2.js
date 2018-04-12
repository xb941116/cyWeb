$(document).ready(function(){

    var conl = $('.conl li');
    var content = $('.content');

    tab(conl,content);

    function tab(conl,content){
        conl.on('touchstart click',function(){
            var _index = $(this).index();
            conl.children().removeClass('bgactive');
            $(this).children().addClass('bgactive');
            content.hide();
            $('.main-tab-'+_index).show();
        })
    }



    // 关闭弹出框
    var  btnClose = $('.close');
    var  alertBox = $('.alert-box');

    btnClose.bind('touchstart click',function(){

        alertBox.hide();
    })

    var goback = $('a.back');
    goback.on('click', function () {
        /*window.history.back();
        location.reload();*/
        window.history.go(-1);
    })
});


