 $(function(){
	var ctx = $('#ctx').val();
 
	
   });
 
 
//목록가기
function go_list() {
	location.href = "/boardmngInqr";
}

//게시판 관리 리스트에서 추가기능
function board_add(){
	location.href="/board_mng_add";	
}


//게시판 추가에서 SELECT_BOX 클릭시.
function fn_SelectBox(CODE){
    $("[id$='BOARD_MNG_CD']").val(CODE); 
	  
}

function board_mng_add_save(){
 var BOARD_MNG_NM =  $("#BOARD_MNG_NM option:selected").val();
 var formObj = $("form[role='form']");

	 formObj.attr("action", "/board_mng_add");
	 formObj.attr("method", "post");
	 formObj.submit();	
}


function checkAll(){
 	if ($("#checkall").prop("checked")) {

		$("input[name=del_code]").prop("checked", true);
	} else {
		$("input[name=del_code]").prop("checked", false);
	}

}


/* 삭제(체크박스된 것 전부) */
function deleteAction() {
	var del_code = "";
	$("input[name='del_code']:checked").each(function() {
		del_code = del_code + $(this).val() + ",";
		});  

	if (del_code == '') {
		alert("삭제할 대상을 선택하세요.");
		return false;
	}

	if (confirm("정보를 삭제 하시겠습니까?")) {

			$.ajax({
				url : '/board_mng_remove',
				headers : {
		            "Content-Type" : "application/json",
		            "X-HTTP-Method-Override" : "POST"
		         },
				data : del_code,
				dataType : 'text',
				processData: false,
				contentType: false,
				type: 'POST',
				success : function(result) {
					
					 					
					if(result =="success")
					{
	                	 var delsize = $("input[name=del_code]:checked").length;
	                 	alert(delsize + "개의 게시판이 삭제 되었습니다.");

	                 	boardMngPaging();
 					}
				else{
					alert("오류!");
				}
					 

				} ,  error:function(request,status,error){
	             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	          } 
				}) 
		
		} 
	}

  
//보드 리스트 그냥 페이징
function boardMngPaging(PageNum) {
     var ctx = $("#ctx").val();
  	var tbody = $('#board_list_tbody');
	var tbodyContent = "";
  	var boardData = { "PageNum": PageNum };
	
	$.ajax({
		url : '/boardmngPaging',
		type : 'POST',
		data : boardData,
		success : function(data) {
   			if(data.boardLitstSize == 0){
				alert("검색결과가 없습니다.");
				location.href = ctx+'/contact';
			}else{
				tbody.children().remove(); 
				tbody.children().remove();
			
			for (var i = 0; i < data.boardList.length; i++) { 
 				tbodyContent +='<tr><td scope="row" style="text-align:center"><input type="checkbox" id="del_code" class="call_chek" name="del_code" value="'+data.boardList[i].BOARD_MNG_NO+'"></td>'
 				    +'<td>' + data.boardList[i].BOARD_MNG_NO + '</td>' 
 				    +'<td>' + data.boardList[i].BOARD_MNG_CD + '</td>'
					+"<td><a href='#' onclick=\"boardmngDetailClick('" + data.boardList[i].BOARD_MNG_NO +"' );\" style='color: black; cursor: pointer;' class='callClick'>"+ data.boardList[i].BOARD_NM+"</a></td>"
          		    +'<td>'+data.boardList[i].UPDATED+'</td>'  
          		    +'<td>' + data.boardList[i].ACTIVE_FLGSTRING + '</td></tr>';
 				}
        	 
 			   tbody.append(tbodyContent);
 			}
			
			 
			 // 페이징
			$(".pagingDiv").empty();
			var pageContent = "";
				if(data.page.endPageNum == 0 || data.page.endPageNum == 1){
				pageContent = "◀ <input type='text' id='pageInput' readonly='readonly' value='1' style='width: 25px; text-align: center;'/> / 1 ▶";
			} else if(data.PageNum == data.page.startPageNum){
 				 pageContent = "<input type='hidden' id='PageNum' value='"+data.PageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"◀ <input type='text' id='pageInput' value='"+data.page.startPageNum+"' onkeypress=\"leadPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>" 
				+"<a onclick=\"boardMngPaging("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
				+"<a onclick=\"boardMngPaging("+(data.PageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
			} else if(data.PageNum == data.page.endPageNum){
 					pageContent = "<input type='hidden' id='PageNum' value='"+data.PageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"<a onclick=\"boardMngPaging("+(data.PageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
				+"<input type='text' id='pageInput' value='"+data.page.endPageNum+"' onkeypress=\"leadPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
				+"<a> / "+data.page.endPageNum+"</a> ▶";
			} else {
 					pageContent = "<input type='hidden' id='PageNum' value='"+data.PageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"<a onclick=\"boardMngPaging("+(data.PageNum-1)+" );\" id='pNum' style='cursor: pointer;'> ◀ </a>"
				+"<input type='text' id='pageInput' value='"+data.PageNum+"' onkeypress=\"leadPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
				+"<a onclick=\"boardMngPaging("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
				+"<a onclick=\"boardMngPaging("+(data.PageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
			}
			$(".pagingDiv").append(pageContent);
			 
		},
		error : function() {
			alert("제가 문제입니다 페이징");
		}
	});
} 
  

//게시판관리 상세보기 ajax
function boardmngDetailClick(BOARD_MNG_NO) {
 		event.preventDefault();
  		var board_cate = $("#board_cate");
 		var board_content = "";
 		
 		$("#baseBtnDiv3").css("display", "none");
 		$("#baseBtnDiv2").css("display", "none");
 		$("#baseBtnDiv").css("display", "block");
 		
		$.ajax({
			url : '/boardMngDetail',
			data : BOARD_MNG_NO, //보낼 데이터값
			dataType : 'json',
			type : "POST", //
			contentType : "application/json",
			success : function(data) {
   				$('#BOARD_NM').val(data.boardMngvo.board_NM);
    			$('#BOARD_MNG_NO').val(data.boardMngvo.board_MNG_NO);
    			
    			
    			if(data.boardMngvo.active_FLG == 'Y')
    			 {
       			    $('#active_flg_y').prop('checked',true);
      			 }
    			else{
       			    $('#active_flg_n').prop('checked',true);
      			}	
    			
  			if(data.boardMngvo.file_ATTACH_FLG == 'Y')
 			 {
    			    $('#file_attach_flg_y').prop('checked',true);
   			 }
 			else{
    			    $('#file_attach_flg_n').prop('checked',true);
   			}
  			
  			if(data.boardMngvo.reply_FLG == 'Y')
 				{
    			    $('#reply_flg_y').prop('checked',true);
   				}
 			else{
   	  			 $('#reply_flg_n').prop('checked',true);
   			}
  			
  			 
  			$("#sel1").val(data.boardMngvo.board_MNG_CD);
   			
  			  $('#BOARD_MNG_CD').val(data.boardMngvo.board_MNG_CD);  
				 
 				 
 			}, 
			error : function(e) {
				alert("오류발생");
			}
		});
	 	/*$('#board_list_tbody').delegate('a', 'click', function(event) {

	});*/
}



//게시판 관리 수정
function updateBoardMng() {
	     var BOARD_MNG_NO = $("#BOARD_MNG_NO").val();
  		 var BOARD_NM = $("#BOARD_NM").val();
 		 var BOARD_MNG_CD = $("#sel1").val();
  		 var FILE_ATTACH_FLG;
 		 var REPLY_FLG;
 		 var ACTIVE_FLG;
 		 
 		 if($('#active_flg_y').is(':checked'))
			 {
			 ACTIVE_FLG = 'Y';
			 }
		 else{
			 ACTIVE_FLG = 'N';
		 } 		 
 		 if($('#active_flg_y').is(':checked'))
 			 {
 			 FILE_ATTACH_FLG = 'Y';
 			 }
 		 else{
 			 FILE_ATTACH_FLG = 'N';
 		 }
 		 if($('#reply_flg_y').is(':checked'))
 			 {
 			 REPLY_FLG = 'Y';
 			 }
 		 else{
 			 REPLY_FLG = 'N';
 		 }
 		 var boardMngjsonData = {
 				"BOARD_MNG_NO":BOARD_MNG_NO, "ACTIVE_FLG":ACTIVE_FLG, "BOARD_NM" : BOARD_NM, "BOARD_MNG_CD" : BOARD_MNG_CD, "FILE_ATTACH_FLG" : FILE_ATTACH_FLG, "REPLY_FLG" : REPLY_FLG
 		 }
 		 
 		 if(BOARD_NM == null || BOARD_NM == "")
 			 {
 			 alert("게시판 이름을 입력해 주세요.");
 			 return false;
 			 }
 		 if(BOARD_MNG_CD == null || BOARD_MNG_CD == "")
 			 {
 			 alert("게시판 코드를 입력해 주세요.");
 			 return false;
 			 }
 		 
		 
		 $.ajax({
				url : 'boardmngupdate',
				data : boardMngjsonData, //보낼 데이터값
				dataType : 'json',
				type : "POST", //
 				success : function(data) { 
					alert("게시판이 수정 되었습니다.");
					
					$('#BOARD_NM').val("");  
					$('#BOARD_MNG_CD').val("");
					
					$("#BOARD_NM").attr("readonly", true);
					$("#BOARD_MNG_CD").attr("readonly", true);
					$("#sel1").attr("disabled", true);
					$(".radio-inline").attr("disabled", true);
					$('#sel1').val("default");
					boardMngPaging();
					
					$("#baseBtnDiv").css("display", "block");
					$("#baseBtnDiv2").css("display", "none");
					
	 			},
	 			
				error : function(e) {
					alert("오류발생");
				}
			});	 
		 
}
 

//연락처 추가 버튼
function boardMngAddp(){
 	
	$("#BOARD_NM").attr("readonly", false);
	$("#BOARD_MNG_CD").attr("disabled", false);
	$("#sel1").attr("disabled", false);
	$(".radio_class").prop('disabled', false);
  
	$('#BOARD_NM').css("background-color", "white");
	$('#BOARD_MNG_CD').css("background-color", "white"); 
 	
	$("#baseBtnDiv3").css("display", "block");
	$("#baseBtnDiv").css("display", "none");
	$("#baseBtnDiv2").css("display", "none");

	 
	$('#BOARD_NM').val("");
	$('#BOARD_MNG_CD').val("");
	$('#sel1').val("default");
	
}


//게시판 관리 취소 버튼
function cancelBtn() {
	
	$("#BOARD_NM").val("");
	$("#BOARD_MNG_CD").val("");
	
	$("#BOARD_NM").attr("readonly", true);
	$("#BOARD_MNG_CD").attr("disabled", true);
	$("#sel1").attr("disabled", true);
	$(".radio_class").prop('disabled', true);
	
	$('#BOARD_NM').css("background-color", "white");
	$('#BOARD_MNG_CD').css("background-color", "white"); 
	
	$("#btn_1").css("display", "block");
	$("#btn_2").css("display", "none");
	
	$("#baseBtnDiv3").css("display", "none");
	$("#baseBtnDiv2").css("display", "none");
	$("#baseBtnDiv").css("display", "block");
	
	
}


//게시판 관리 추가 저장.
function boardmngInsert() { 
  	 var BOARD_NM = $("#BOARD_NM").val();
	 var BOARD_MNG_CD = $("#sel1").val();
	 var FILE_ATTACH_FLG;
	 var REPLY_FLG;
	 
	 if($('#file_attach_flg_y').is(':checked'))
		 {
		 FILE_ATTACH_FLG = 'Y';
		 }
	 else{
		 FILE_ATTACH_FLG = 'N';
	 }
	 if($('#reply_flg_y').is(':checked'))
		 {
		 REPLY_FLG = 'Y';
		 }
	 else{
		 REPLY_FLG = 'N';
	 }
	 
	 if(BOARD_NM == null || BOARD_NM == "")
		 {
		 alert("게시판 이름을 입력해 주세요.");
		 return false;
		 }
	 if(BOARD_MNG_CD == null || BOARD_MNG_CD == "")
		 {
		 alert("게시판 코드를 입력해 주세요.");
		 return false;
		 }
		 
		 
	
	 var boardMngjsonData = {
				 "BOARD_NM" : BOARD_NM, "BOARD_MNG_CD" : BOARD_MNG_CD, "FILE_ATTACH_FLG" : FILE_ATTACH_FLG, "REPLY_FLG" : REPLY_FLG
		 }
		 
		 $.ajax({
				url : 'boardMngInsert',
				data : boardMngjsonData, //보낼 데이터값
				dataType : 'json',
				type : "POST", //
 				success : function(data) { 
 					alert("게시판이 추가 되었습니다.");
 					
 	  			    $('#BOARD_NM').val("");  
					$('#BOARD_MNG_CD').val("");
					
					$("#BOARD_NM").attr("readonly", true);
					$("#BOARD_MNG_CD").attr("readonly", true);
					$("#sel1").attr("disabled", true);
					$(".radio-inline").attr("disabled", true);
					
					
 					$("#baseBtnDiv3").css("display", "none")
					$("#baseBtnDiv").css("display", "block");
					 
					
					
					
					boardMngPaging();
					
					
					
	 			},
	 			
				error : function(e) {
					alert("오류발생");
				}
			});	  
}
 