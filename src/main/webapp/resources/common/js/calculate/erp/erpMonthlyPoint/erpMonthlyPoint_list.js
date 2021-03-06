/**
업 무 명 : ERP 가맹점별 월포인트 정산 화면
작 성 자 : 정은지 (wjdmwi@coreplus.co.kr)
작 성 일 : 2015/09/14
수 정 자 : 정은지 (wjdmwi@coreplus.co.kr)
수 정 일 : 2015/09/14
내 용 : ERP 가맹점별 월포인트 정산에 대한 javascript 코드이다.
*참고사항 : 
*/

//ERP 가맹점별 월포인트 정산 관리 페이징
function EPPaging(pageNum) {
	$(document).ready(function() {
		var ctx = $("#ctx").val();
		var $form = $('#erpMonthlyPointPagingForm');
	    
	    var pageNum_input = $('<input type="hidden" value="'+pageNum+'" name="pageNum">');
	    
	    $form.append(pageNum_input);
	    viewLoadingShow();
	    $form.submit();
	});
}

//ERP가맹점별 월포인트 정산 검색
function erpPoint_goSearch(){
	var date = $("#cond_dt").val();
	var year = $("#year_dt").val();
	var month = $("#month_dt").val();
	
	var store_wid = $("#store_wid").val();
	var store_name = $("#store_name").val();
	
	//조회날짜 설정
	$("#cond_dt").val($("#cbo_year").children('option:selected').val()+$("#cbo_month").children('option:selected').val());
	$("#year_dt").val($("#cbo_year").children('option:selected').val());
	$("#month_dt").val($("#cbo_month").children('option:selected').val());

	$("#store_name").val($("#store_name").val());
	viewLoadingShow();
	$("#monthlyPointForm").submit();
}

//페이지 엔터키 기능
function erppointpageNumEnter(event) {
	$(document).ready(function() {
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if (keycode == '13') {
			var pageNum = parseInt($("#pageInput").val());
			if ($("#pageInput").val() == '') {
				alert("페이지 번호를 입력하세요.")
				$("#pageInput").val($("#erpPointPageNum").val());
				$("#pageInput").focus();
			} else if(pageNum > parseInt($("#endPageNum").val())) {
				alert("페이지 번호가 너무 큽니다.");
				$("#pageInput").val($("#erpPointPageNum").val());
				$("#pageInput").focus();
			} else if (1 > pageNum) {
				alert("페이지 번호가 너무 작습니다.");
				$("#pageInput").val($("#erpPointPageNum").val());
				$("#pageInput").focus();
			} else {
				EPPaging(pageNum);
			}
		}
		event.stopPropagation();
	});
}

//가맹점 구분 Text 설정
function setText(){
	var sVal = "";
	$("#brand_wid option:selected").each(function(){
		sVal = $(this).val();
	});

	$("#up_id").attr("value",sVal);
	
	//가맹점 정보 초기화
	resetStore();
}

//가맹점 검색조건 초기화
function resetStore() {
	$('#store_name').val('');
	$('#store_wid').val('');
}

//ERP 가맹점별 월포인트 리스트로 이동 버튼
function go_erpPoint_list() {
	var ctx = $("#ctx").val();
	
	// 동적 폼생성 get 전송
	var $form = $('<form></form>');
	$form.attr('action', ctx+'/erpMonthlyPoint');
	$form.attr('method', 'post');
	$form.appendTo('body');
	viewLoadingShow();
	$form.submit();
}

//ERP 가맹점별 월포인트 전송
function transErp(){
	var ctx = $("#ctx").val();
	
	var tran_yy = $('#cbo_year').val();
	var tran_mm = $('#cbo_month').val();
	
	var brand = $('#brand_wid').val();
	var store = $('#store_wid').val();
	
	var tran_dt = tran_yy+ tran_mm;
	
	var param = {
			std_ym : tran_dt,
			brand_wid : brand,
			store_wid : store
	}
	
	if(!confirm("전송 하시겠습니까?"))
		return;
	
	$.ajax({  
		url:  ctx+'/erpMonthlyTransPoint',   
		type: "POST",
		data: param,
		success: function(data) {
        	if(data=="error"){
        		alert("ERP 전송중 에러가 발생하였습니다.");
        		return;
        	}
        	alert("ERP 전송이 완료되었습니다.");
		},
		beforeSend: function(){
	        	viewLoadingShow();			
	        },
	        complete:function(){
	        	viewLoadingHide();	
	        },
		error: function(data) { 
			alert("ERP 전송중 에러가 발생하였습니다.");
			return false;
		}	
	});		
	
}

//월마감
function CloseErpPointMonthly(){
	var ctx = $('#ctx').val();
	
	var thisDate = new Date();
	var now_year = thisDate.getFullYear();  //현재 년도
	var now_month = thisDate.getMonth()+1; //현재 월 
	
//	var now_date = thisDate.getDate();
	var sel_date = new Date($("#cbo_year").children('option:selected').val()+$("#cbo_month").children('option:selected').val());
	
//	sel_date.setMonth(sel_date.getMonth()+1);
	
	if($("#cbo_year").children('option:selected').val() > now_year && $("#cbo_month").children('option:selected').val() > now_month){
		alert("선택한 날짜의 마감은 그 다음달1일 부터 가능합니다.");
		return;
	}
	if(!confirm("마감 처리하시겠습니까?"))
		return;
	var param = "cond_dt="+$("#cbo_year").children('option:selected').val()+"-"+$("#cbo_month").children('option:selected').val()+"-"+"01";
//	alert(param);
	$.ajax({  
		url:  ctx+'/closeErpPointMonthlyProcedure',   
		type: "POST",
		data: param,
		success: function(data) {
        	if(data=="error"){
        		alert("마감 처리중 에러가 발생하였습니다.");
        		return;
        	}
        	alert("마감 처리가 완료되었습니다.");
		},
		beforeSend: function(){
	        	viewLoadingShow();			
	        },
	        complete:function(){
	        	viewLoadingHide();	
	        },
		error: function(data) { 
			alert("마감 처리중 에러가 발생하였습니다.");
			return false;
		}	
	});		
}
