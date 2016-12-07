$(document).ready(function () {

    var status = $("#loginstatus").text();
    if (status == "Login Success"){
        $("#regbutt").hide();
        $("#loginbutt").hide();
        $("#logoutbutt").show();
    }else{
        $("#regbutt").show();
        $("#loginbutt").show();
        $("#logoutbutt").hide();


    }



});