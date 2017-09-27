/*校验参数*/

$().ready(function() {
 $("#roomInfomation").validate({
        submitHandler:function(form){
            alert("提交事件!");   
            form.submit();
        }    
    });
});

$(function() {
	$("#roomInfomation").validate({
		rules:{
			name: "required"
		},
		messages:{
			name: "请输入房间类型，例如豪华房"
		}
	});
})