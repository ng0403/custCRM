/**
* codeSchList(pageNum)							:: 코드 조회
* codeDetail(code_no, code)						:: 코드 상세정보
* code_add_save()								:: 코드 추가
* code_modify_save()							:: 코드 편집
* code_del_save()								:: 코드 삭제
* codeCancelList()								:: 검색 input text 값 삭제(빈칸)
* enterSearch(event)                			:: 엔터키 기능
* codePageNumInputEnter(event)          		:: 페이징 엔터키 기능
* codePaging(pageNum)              	 			:: 페이징 함수
*/ 

$(document).ready(function(){
	
	$("#code_btn_div").show();
	$("#code_add_btn_div").hide();
	$("#code_update_btn_div").hide();
	
	//상세보기 추가버튼 클릭 시
	$("#code_add").click(function(){
		if($("#code_no").val() == '' || $("#code_no").val() == null){
			$("#code_btn_div").hide();
			$("#code_update_btn_div").hide();
			
			$("#code_add_btn_div").show();
			
			$("#code_no").val('').attr("readonly", false);
			$("#code_no").css('background-color', 'white');
			$("#code").val('').attr("readonly", false);
			$("#code").css('background-color', 'white');
			$("#code_name").val('').attr("readonly", false);
			$("#par_code_no").val('').attr("disabled", false);
			$("#code_form_tbl input[type=radio]").prop("disabled", false);
			$("#code_no").focus();
			
		} else{
			alert("새로운 값을 추가합니다.");
			
			$("#code_btn_div").hide();
			$("#code_update_btn_div").hide();
			$("#code_add_btn_div").show();
			
			$("#code_no").val('').attr("readonly", false);
			$("#code").val('').attr("readonly", false);
			$("#code_name").val('').attr("readonly", false);
			$("#par_code_no").val('').attr("disabled", false);
			$("#code_form_tbl input[type=radio]").prop("disabled", false);
			
			$("#code_no").focus();
			
		}
	});
	
	//상세보기 편집버튼 클릭 시
	$("#code_mdfy").click(function(){
		if($("#code_no").val() == '' || $("#code_no").val() == null){
			alert("편집할 항목을 선택해 주십시오.");
		} else {
			$("#code_btn_div").hide();
			$("#code_update_btn_div").show();
			$("#code_add_btn_div").hide();
			
//			$("#code_no").attr("readonly", false);
//			$("#code").attr("readonly", false);
			$("#code_name").attr("readonly", false);
			$("#par_code_no").attr("readonly", false);
			$("#code_form_tbl input[type=radio]").prop("disabled", false);
			$("#code_name").focus();
		}
	});
	
	//상세보기 추가에서 취소버튼 클릭 시
	$("#code_cancel" ).click(function(){
		alert("추가가 취소되었습니다.");
		$("#code_btn_div").show();
		$("#code_add_btn_div").hide();
		$("#code_update_btn_div").hide();
		
		$("#code_no").val('').attr("readonly", true);
		$("#code").val('').attr("readonly", true);
		$("#code_name").val('').attr("readonly", true);
		$("#par_code_no").val('').attr("readonly", true);
		$("#code_form_tbl input[type=radio]").prop("disabled", true);
	});
	
	//상세보기 편집에서 취소버튼 클릭 시
	$("#code_update_cancel").click(function(){
		alert("편집이 취소되었습니다.");
		$("#code_btn_div").show();
		$("#code_add_btn_div").hide();
		$("#code_update_btn_div").hide();
		
		$("#code_no").val('').attr("readonly", true);
		$("#code").val('').attr("readonly", true);
		$("#code_name").val('').attr("readonly", true);
		$("#par_code_no").val('').attr("readonly", true);
		$("#code_form_tbl input[type=radio]").prop("disabled", true);
	});
	
	
});

var ctx 	= $("#ctx").val();
var flg		= $("#flg").val();
var pageNum	= $("#pageNum").val();
var code_no = $("#code_no").val();
var code 	= $("#code").val();

//코드조회
function codeSchList(pageNum) {
   
   var code_no_srch   = $("#code_no_srch").val();
   var code_srch      = $("#code_srch").val();
   var code_name_srch = $("#code_name_srch").val();

   var tbody = $('#code_list_tbody');
   var tbodyContent = "";
   
   $.ajax({
      url:ctx + '/code_sch',
      type: 'POST',
      data: {
         codePageNum    : pageNum,
         code_no_srch   : code_no_srch,
         code_srch      : code_srch,
         code_name_srch : code_name_srch,
      },
      dataType:'json',
      success: function(data){
    	  tbody.children().remove();
    	  if(data.srcList == 0){
    		  tbodyContent = "<tr style='height: 75px;'><td colspan='9' style='width: 1320px; text-align: center;  vertical-align: middle;'>검색 결과가 없습니다.</td></tr>";
    		  tbody.append(tbodyContent);
			}else{
				
		         var size = data.srcList.length;
		         for(var i=0; i<size; i++)
		         {
		            tbodyContent = "<tr>" +
		             "<td style='text-align: left;' >" +data.srcList[i].code_no +"</td>" +
		             "<td style='text-align: left;'>" + data.srcList[i].code +"</td>" +
		             "<td style='text-align: left;'>" +
		                "<a onclick=codeDetail('"+data.srcList[i].code_no+"','"+data.codePageNum+"'); id='"+data.srcList[i].code_no+"'>" + data.srcList[i].code_name+"</a></td>" +
		             "<td style='text-align: left;'>" + data.srcList[i].create_date + "</td>" +
		             "</tr>"

		            tbody.append(tbodyContent);
		         }
			}

    	  	// 페이징
	         $(".pagingDiv").empty();
	         var pageContent = "";
	
	         if(data.page.endPageNum == 0 || data.page.endPageNum == 1){
	            pageContent = "◀ <input type='text' id='codePageNum' readonly='readonly' value='1' style='width: 25px; text-align: center;'/> / 1 ▶";
	         } else if(data.codePageNum == data.page.startPageNum){
	            pageContent = "<input type='hidden' id='codePageNum' value='"+data.codePageNum+"'/><input type='hidden' id='codeEndPageNum' value='"+data.page.endPageNum+"'/>"
	            +"◀ <input type='text' id='pageInput' value='"+data.page.startPageNum+"' onkeypress=\"codePageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>" 
	            +"<a onclick=\"codeSchList("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
	            +"<a onclick=\"codeSchList("+(data.codePageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
	         } else if(data.codePageNum == data.page.endPageNum){
	            pageContent = "<input type='hidden' id='codePageNum' value='"+data.codePageNum+"'/><input type='hidden' id='codeEndPageNum' value='"+data.page.endPageNum+"'/>"
	            +"<a onclick=\"codeSchList("+(data.codePageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
	            +"<input type='text' id='pageInput' value='"+data.page.endPageNum+"' onkeypress=\"codePageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
	            +"<a> / "+data.page.endPageNum+"</a> ▶";
	         } else {
	            pageContent = "<input type='hidden' id='codePageNum' value='"+data.codePageNum+"'/><input type='hidden' id='codeEndPageNum' value='"+data.page.endPageNum+"'/>"
	            +"<a onclick=\"codeSchList("+(data.codePageNum-1)+",2);\" id='pNum' style='cursor: pointer;'> ◀ </a>"
	            +"<input type='text' id='pageInput' value='"+data.codePageNum+"' onkeypress=\"codePageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
	            +"<a onclick=\"codeSchList("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
	            +"<a onclick=\"codeSchList("+(data.codePageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
	         }
	         $(".pagingDiv").append(pageContent);
	      },
	      error: function(){
	         alert("error");
	      }
	   });
}


//상세정보
function codeDetail(code_no, code) {
	$(document).ready(function() {
	 	
		$.ajax({  
			url:  ctx+'/codeDetailAjax',   
			type: "POST",
			data: 
				{
					code_no 	: code_no,
					code    	: code
				},
				
			dataType: "json",
			success: function(data) {
				
				$("#code_no").val(data.codeDetail[0].code_no);
				$("#code").val(data.codeDetail[0].code);
				$("#code_name").val(data.codeDetail[0].code_name);
				$("#display_yn[value="+data.codeDetail[0].display_yn+"]").prop("checked", true);
				$("#par_code_no").val(data.codeDetail[0].par_code_no);
			},
			beforeSend: function(){
	        	viewLoadingShow();			
	        },
	        complete:function(){
	        	viewLoadingHide();	
	        },
			error: function(data) { 
				alert("정보를 불러 오는 중 에러가 발생하였습니다.");
				return false;
			},		
		});		
	});
}


//코드 저장
function code_add_save() {
	
	$(document).ready(function() {
		
	 	var code_no   	 = $("#code_no").val();
	 	var code   		 = $("#code").val();
	 	var code_name    = $("#code_name").val();
	 	var display_yn   = $("#display_yn").val();
	 	var par_code_no  = $("#par_code_no").val();
	 	
	 	if($("#code_no").val() == 0 || $("#code_no").val() == null || $("#code_no").val() == "") {
			alert("code_no 입력하세요.");
			$("#code_no").focus();
			return false;
		} 
	 	
		var ynChk = confirm("해당 코드를 저장하시겠습니까?");
		if(ynChk){
			$.ajax({
				type : 'POST',
				url : ctx + '/code_add',
				data : {
					code_no 	: code_no,
					code 		: code,
					code_name	: code_name,
					display_yn	: display_yn,
					par_code_no	: par_code_no
				},
				dataType : "json",
				success : function(data) {
					
					alert("코드가 저장되었습니다.");
					window.location.reload(true);
					
				}, error : function(request,status,error) {
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		} else {
			alert("취소되었습니다.");
		}
	});
}

//코드 수정 
function code_modify_save() {
	
	 $(document).ready(function() {
		 
			var code_no   	 = $("#code_no").val();
		 	var code   		 = $("#code").val();
		 	var code_name    = $("#code_name").val();
		 	var display_yn   = $("#display_yn").val();
		 	var par_code_no  = $("#par_code_no").val();
		 	
		 	if($("#code_no").val() == 0 || $("#code_no").val() == null || $("#code_no").val() == "") {
				alert("code_no 입력하세요.");
				$("#code_no").focus();
				return false;
			} 
	 
		var ynChk = confirm("해당 코드를 수정하시겠습니까?");
		if(ynChk){
			$.ajax({
				type : 'POST',
				url : ctx + '/code_edit',
				data : {
					code_no 	: code_no,
					code 		: code,
					code_name	: code_name,
					display_yn	: display_yn,
					par_code_no	: par_code_no
				},
				dataType : "json",
				success : function(data) {
					
					alert("코드가 수정되었습니다.");
					window.location.reload(true);
					
				}, error : function(request,status,error) {
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		} else {
			alert("취소되었습니다.");
		}
	});
}

//코드 삭제
function code_del_save() {   
	$(document).ready(function() {
		if($("#code_no").val() == '' || $("#code_no").val() == null){
			alert("삭제할 항목을 선택해 주십시오.");
		} else {
			var ynChk = confirm("해당 코드를 삭제하시겠습니까?");
			if(ynChk)
			{
				$.ajax({
					type : 'POST',
					url : ctx + '/code_delete',
					data : {
						code_no 	: $("#code_no").val(),
						code	 	: $("#code").val()
					},
					dataType : "json",
					success : function(data) {
						
						alert("코드가 삭제되었습니다.");
						window.location.reload(true);
						
					},
					error : function(request,status,error) {
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
			}
			else
			{
				alert("취소되었습니다.");
			}
		}
		
	});
}


//검색 취소버튼
function codeCancelList() {
	$('#code_no_srch').val('');
	$('#code_srch').val('');
	$('#code_name_srch').val('');
}

//상위코드 서치 팝업 오픈
function codeSchPopupOpen()
{
	// 팝업창 표시
	$.blockUI({ message: $('#codeListModalDiv'),
    	css: { 
    	'left': '65%',
    	'top': '50%',
    	'margin-left': '-400px',
    	'margin-top': '-250px',
    	'width': '400px',
    	'height': '500px',
    	'cursor': 'default'
    	}
		,onOverlayClick : $.unblockUI
	});
	
	// list 불러오는 함수.
	viewCodeList(pageNum);
}


//상위코드 Popup
function viewCodeList(pageNum) {
	
	var ctx = $("#ctx").val();
 	var s_code_no   = $("#s_code_no").val();
    var s_code      = $("#s_code").val();
    var s_code_name = $("#s_code_name").val();
 	
 	$.ajax({
 	      url:ctx + '/codeUpListAjax',
 	      type: 'POST',
 	      data: {
 	    	 codePopupPageNum     : pageNum,
 	    	 s_code_no   : s_code_no,
 	    	 s_code      : s_code,
 	         s_code_name : s_code_name,
 	      },
 	      dataType:'json',
 	      success: function(data){
 	    	  
 	    	 $("#codeListTbody").empty();
  			 $("#s_code_no").bind("keypress", function(event) {
  				enterSearch(event);
  			});
  			 
  			if (data.codePopupList.length == 0) {
 				var trElement = $("#codeListTableHeader").clone().removeClass().empty();
 				$("#codeListTbody").append(trElement);
 				$("#codeListTbody tr:last").append("<td colspan='3' style='width:100%; height: 260px; cursor: default; background-color: white;' align='center'>검색 결과가 없습니다</td>");
 			} else {
 				console.log(data);
 				$.each(data.codePopupList, function(i) {
 					var trElement = $("#codeListTableHeader").clone().removeClass().empty();
 					var code_no = this.code_no;
 					var code = this.code;
 					var code_name = this.code_name;

 					trElement.bind("click", function(e) {
 						setTimeout($.unblockUI, 0);
 						$("#par_code_no").val(code_no);
 						
 					});
 					
 					addMouseEvent(trElement);
 					trElement.css("cursor", "pointer");
 					
 					$("#codeListTbody").append(trElement);
 					$("#codeListTbody tr:last").append("<td width='30%'>" + code_no + "</td>"+ "<td width='30%'>" + code + "</td>"+ "<td width='60%'>" + code_name + "</td>");
 				});
 			} 
 	    	  	// 페이징
  			$("#codePopupPagingDiv").empty();
			var pageContent = "";
 		
			if(data.page.endPageNum == 0 || data.page.endPageNum == 1){
				pageContent = "◀ <input type='text' id='codePopupInput' readonly='readonly' value='1' style='width: 25px; text-align: center;'/> / 1 ▶";
			} else if(data.pageNum == data.page.startPageNum){
				pageContent = "<input type='hidden' id='codePageNum' value='"+data.pageNum+"'/><input type='hidden' id='codeEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"◀ <input type='text' id='codePopupInput' value='"+data.page.startPageNum+"' onkeypress=\"codePageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>" 
				+"<a onclick=\"viewCodeList("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
				+"<a onclick=\"viewCodeList("+(data.pageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
			} else if(data.pageNum == data.page.endPageNum){
				pageContent = "<input type='hidden' id='codePageNum' value='"+data.pageNum+"'/><input type='hidden' id='codeEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"<a onclick=\"viewCustList("+(data.pageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
				+"<input type='text' id='codePopupInput' value='"+data.page.endPageNum+"' onkeypress=\"codePageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
				+"<a> / "+data.page.endPageNum+"</a> ▶";
			} else {
				pageContent = "<input type='hidden' id='codePageNum' value='"+data.pageNum+"'/><input type='hidden' id='codeEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"<a onclick=\"viewCodeList("+(data.pageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
				+"<input type='text' id='codePopupInput' value='"+data.pageNum+"' onkeypress=\"codePageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
				+"<a onclick=\"viewCodeList("+data.pageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
				+"<a onclick=\"viewCodeList("+(data.pageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
			}
			$("#codePopupPagingDiv").append(pageContent);
			
 			
 		},
 		beforeSend: function(){
         	viewLoadingShow();			
         },
         complete:function(){
         	viewLoadingHide();	
         },
 		error: function(data) { 
 			alert("코드 목록을 취득하지 못했습니다.");
 			return false;
 		}
 	});
	
}

//Popup 닫기
function popupClose()
{
	$.unblockUI();
}

//엔터키 기능
function codeenterSearch(event) 
{
   var keycode = (event.keyCode ? event.keyCode : event.which);
   if (keycode == '13') {
	   codeSchList();
   }
   event.stopPropagation();
}

//페이징 엔터키
function codePageNumInputEnter(event) {
   var keycode = (event.keyCode ? event.keyCode : event.which);
   if (keycode == '13') {
      var pageNum = parseInt($("#pageInput").val());
      if ($("#pageInput").val() == '') {
         alert("페이지 번호를 입력하세요.")
         $("#pageInput").val($("#pageNum").val());
         $("#pageInput").focus();
      } else if(pageNum > parseInt($("#endPageNum").val())) {
         alert("페이지 번호가 너무 큽니다.");
         $("#pageInput").val($("#pageNum").val());
         $("#pageInput").focus();
      } else if (1 > pageNum) {
         alert("페이지 번호가 너무 작습니다.");
         $("#pageInput").val($("#pageNum").val());
         $("#pageInput").focus();
      } else {
         codeSchList(pageNum);
      }
   }
   event.stopPropagation();
}

//페이징 함수
function codePaging(pageNum) {
   $(document).ready(function() {
      // 컨트롤러로 전송
      var ctx = $("#ctx").val();
      var codeListForm = $("#codeListPagingForm");
        
       var pageNumInput = $('<input type="hidden" value="'+pageNum+'" name="pageNum">');
    
       codeListForm.append(pageNumInput);
       viewLoadingShow();
       codeListForm.submit();
   });
}