$(document).ready(function () {

    var status = $("#loginstatus").text();
    if (status == "Login Success"){
        $("#regbutt").hide();
        $("#loginbutt").hide();
        $("#logoutbutt").show();
        $("#loginstatus").show().delay(5000).fadeOut();
    }else{
        $("#regbutt").show();
        $("#loginbutt").show();
        $("#logoutbutt").hide();


    }





});