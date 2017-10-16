 $(function(){
	var ctx = $('#ctx').val();
	$("#button_div1").hide(); 
	fn_lock();
  });
 
//목록가기
function go_list() {
	if(confirm("목록으로 가시겠습니까?")){
		location.href = "/boardmngInqr"; 
	}else
		{
		return false;
		}
}

//수정 save
function modify_save() {
	var formObj = $("form[role='form']");

	formObj.attr("action", "/board_mng_modify");
	formObj.attr("method", "post");
	formObj.submit(); 
}


//편집버튼 눌렀을 때
function modify_fbtn() {
	$("#BOARD_NM").attr("readonly", false);
	$("#BOARD_MNG_CD").attr("disabled", false);
	$("#sel1").attr("disabled", false);
	$(".radio_class").prop('disabled', false);

	$("#baseBtnDiv").hide();
	$("#baseBtnDiv2").show();

}

//편집 취소
function modify_cancel(){ 
/*	 fn_lock();
	 $("#baseBtnDiv2").hide();
	 $("#baseBtnDiv").show(); */
	$("#BOARD_NM").val("");
	$("#BOARD_MNG_CD").val("");
	
	$("#BOARD_NM").attr("readonly", true);
	$("#BOARD_MNG_CD").attr("disabled", true);
	$("#sel1").attr("disabled", true);
	$(".radio_class").prop('disabled', true);
	
	$('#BOARD_NM').css("background-color", "white");
	$('#BOARD_MNG_CD').css("background-color", "white"); 
	
	$("#baseBtnDiv").css("display", "block");
	$("#baseBtnDiv2").css("display", "none");
	
	

}


//편집 취소시 act
function fn_lock() {
 	var CODE_TXT = $("#CODE_TXT").val(); 
	var ACTIVE_FLG = $("#ACTIVE_FLG").val(); 
	var REPLY_FLG = $("#REPLY_FLG").val();
	var FILE_ATTACH_FLG = $("#FILE_ATTACH_FLG").val();
	var NOTICE_FLG = $("#NOTICE_FLG").val();
 
	if(ACTIVE_FLG == 'Y')
	{
 	 $('input:radio[id="active_flg_y"]').attr("checked", true);
	}
	else{
	 $('input:radio[id="active_flg_n"]').attr("checked", true); 
	}
	
	if(REPLY_FLG =='Y')
	{
	  $('input:radio[id="reply_flg_y"]').attr("checked", true);

	}
	else{
	  $('input:radio[id="reply_flg_n"]').attr("checked", true); 

	}
	
	if(FILE_ATTACH_FLG =='Y')
	{
	  $('input:radio[id="file_attach_flg_y"]').attr("checked", true);

	}
	else{
	  $('input:radio[id="file_attach_flg_n"]').attr("checked", true); 

	}
	
	if(NOTICE_FLG =='Y')
	{
	  $('input:radio[id="notice_flg_y"]').attr("checked", true);

	}
	else{
	  $('input:radio[id="notice_flg_n"]').attr("checked", true); 

	}
	
	$("#BOARD_NM").prop('readonly', true);
 	$(".radio_class").prop('disabled', 'disabled');
 	$("#sel1").prop('disabled', 'disabled');

	
}