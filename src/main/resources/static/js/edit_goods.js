/**
 * Created by zhang on 2016/3/14.
 */


$("#save-btn").on('click', function () {
    var d = gatherData();
    if (d.id != undefined && d.id != "") {
        $.ajax({
            method: "PUT",
            url: "/api/goods",
            data: d,
            success: function (data) {
                if (data.code == 0) {
                    layer.alert('更新成功', {
                        icon: 9, offset: '150px', end: function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        }
                    });
                } else {
                    layer.alert(data.msg, {icon: 11})
                }
            }
        });
    } else {
        $.ajax({
            method: "POST",
            url: "/api/goods",
            data: d,
            success: function (data) {
                if (data.code == 0) {
                    layer.alert('创建成功', {
                        icon: 9, offset: '150px', end: function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        }
                    });
                } else {
                    layer.alert(data.msg, {icon: 11})
                }
            }
        });
    }
});

function gatherData() {
    var id = $("#id").val();
    var title = $("#title").val();
    var questionType = $("#questionType").val();
    var options = $("#options").val().trim();
    var answers = $("#answers").val().trim();
    var difficulty = $("#difficulty").val();
    var priority = $("#priority").val();
    var status = $("#status").val();
    var tag_array = new Array();
    $('input[name="tag"]:checked').each(function () {
        tag_array.push($(this).val());
    });
    var tagList = tag_array.join();

    var d = {
        "id": id,
        "title": title,
        "questionType": questionType,
        "options": options,
        "answers": answers,
        "difficulty": difficulty,
        "priority": priority,
        "status": status,
        "tagList": tagList
    };

    return d;
}

