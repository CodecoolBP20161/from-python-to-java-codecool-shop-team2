$(document).ready(function() {
    $(".addbutton").hover(function(){
        $(this).css({"margin-left": "53px", "margin-top": "3px"});
    });

    $(".addbutton").mouseout(function() {
        $(this).css({"margin-left": "50px", "margin-top": "0px"})
    })

    $('html, body').animate({ scrollTop: 0 }, 'fast');
});