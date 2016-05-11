$(function () {
    $("#upload").ajaxForm({
        success: function(data){
            if (data.code == 0) {

                $("#tableNameNew").val(data.data.tableName);
                $("#rowsNew").val(data.data.rows);
                $("#columnsNew").val(data.data.columns);
                $("#outPath").val(data.data.outPath);

                $("#IMPBefore").val(data.data.IMPBefore);
                $("#IMPAfter").val(data.data.IMPAfter);
                $("#TotalSize").val(data.data.rows * data.data.columns);
                $("#TotalTime").val(data.data.totalTime);

                layer.msg("数据处理完毕！", {icon: 11});
            } else {
                $("#tableNameNew").val(null);
                $("#rowsNew").val(null);
                $("#columnsNew").val(null);
                $("#outPath").val(null);
                layer.msg(data.msg, {icon: 11})
            }
        }
    });
});
