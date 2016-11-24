$(document).ready(function () {
   console.log("jquery working");

    $('#subbutton').click(function () {
        var userData = {
            'userName': $("input[name=username]").val(),
            'userEmail': $("input[name=email]").val(),
            'userPassword': $("input[name=password]").val()
        };

        $.ajax({
            url: "/registration",
            data: userData
        });
    });
});