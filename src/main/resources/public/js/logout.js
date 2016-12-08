$(document).ready(function () {
    $("#logoutbutt").click(function () {
        $.ajax({
            method: "POST",
            url: "/",
            data: {logout: true}
        })
            .done(function () {
                console.log("AJAX: done")
            })
            .fail(function () {
                console.log("AJAX: error")
            });
        location.reload();
    })
});