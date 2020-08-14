function selectOperation(opp, response) {
    if (opp === "delete") {
        $("#delSocModalBody").html(response);
    } else if (opp === "update") {
        $("#updSocModalBody").html(response);
    } else if (opp === "add") {
        $("#addSocModalBody").html(response);
    }
    window.reload();
}

function init(id, opp, obj) {
    const data = {
        id: id,
        opp: opp,
    };
    $.ajax({
        url: "/admin/" + obj,
        type: "GET",
        dataType: "html",
        data: data,
        success: function (response) {
            selectOperation(opp, response);
        },
        error: function () {
            alert("error");
            window.reload();
        }
    });
}