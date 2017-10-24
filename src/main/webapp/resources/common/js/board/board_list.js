 $(function(){
	var ctx = $('#ctx').val(); 
    });
 
 $(document).ready(function(){
 	
	 //상세, 추가, 편집 구분 flg
	 var crud_flg = $("#crud_flg").val();
	 
 })
 
 
 
 //파일 추가.
 function fileupload(){
 	     var formObj = $("form[role='form1']");
		 formObj.attr("action", "/file_insert");
		 formObj.attr("method", "post");
		 formObj.submit();  
	}
 
 //보드 상세보기.
 function boardDetail(no){
     location.href="/boardDetail?BOARD_NO=" + no;  
 } 
 
 //보드 목록가기. 
 function goboardList() {
	 	var BOARD_MNG_NO = $("#BOARD_MNG_NO").val();
	 	if(confirm("목록으로 가시겠습니까?")){
 	      	location.href = "/boardInqr?BOARD_MNG_NO=" + BOARD_MNG_NO; 
	 	}
	 	else{
	 		return false;
	 	}
}
 
 
//보드 편집 버튼.  
function board_modify(){
   
	var formObj = $("form[role='form']"); 
    formObj.attr("action", "/boardModify");
	formObj.attr("method", "get");		
	formObj.submit(); 
 	     
 } 


//게시판 수정 저장
function board_modify_save() {
	
	var formObj = $("form[role='form']");

    	
    	if($("#TITLE").val() == null || $("#TITLE").val()==""){
    	    alert("제목을 입력해 주세요.");
    	    return false;
    	}  	
    	    	
    	if($("#boardcontent").val() == null || $("#boardcontent").val()==""){
    	    alert("내용을 입력해 주세요.");
    	    return false;
        } 
    	
     
    	
     if(confirm("수정 하시겠습니까?")){ 
     alert("내용이 수정되었습니다.");
	 alert("게시판 리스트로 이동합니다.");

	   var formObj = $("form[role='form']"); 
		formObj.attr("action", "/board_modify");
		formObj.attr("method", "post");		
		formObj.submit();  
		
    }else{
   	 return false;
    }
	
}


//보드 상세 삭제
function board_detail_remove() {
	 var formObj1 = $("form[role='form1']");
	 if(confirm("삭제 하시겠습니까?")){
	 alert("게시판이 삭제 되었습니다.");
	 alert("게시판 페이지로 이동합니다.");
	 formObj1.attr("action", "/detail_remove");
	 formObj1.attr("method", "post");
	 formObj1.submit();
	 }
	 else{
		 
	 }
} 
 
  //보드 추가버튼. 
 function board_add() {
	 var BOARD_MNG_NO = $("#BOARD_MNG_NO").val();
 	location.href="/boardInsertForm?BOARD_MNG_NO="+BOARD_MNG_NO;
  
 }
 
 //보드 추가 저장
 function board_add_save(){
	 
	 if($("#TITLE").val() == "" || $("#TITLE").val() == null)
	{
		 alert("제목을 입력해 주세요.");
		 return false;
	}
	 if($("#CONTENT").val() == "" || $("#CONTENT").val() == null)
		 {
		 alert("내용을 입력해 주세요.")
		 return false;	
		 }
	  
	 var formObj = $("form[role='form']");
     if(confirm("저장 하시겠습니까?")){
     alert("게시판이 저장 되었습니다.");
     alert("게시판 페이지로 이동합니다.");
	 formObj.attr("action", "/boardInsert");
	 formObj.attr("method", "post");
	 formObj.submit();  
     }else{
    	 return false;
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

   if (confirm("삭제 하시겠습니까?")) { 
 
          $.ajax({
             url : '/board_remove',
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
                	/*alert(delsize + "개의 게시판이 삭제 되었습니다.");*/
                	 alert("게시판이 삭제 되었습니다.");
                	 boardPaging();
                   }
                else{
                   alert("오류!");
                }
             
             }
             })  
        }else{
       
       }  
  
 }
  
//체크박스 모두 선택
 function checkAll(){
    if ($("#checkall").prop("checked")) {

      $("input[name=del_code]").prop("checked", true);
   } else {
      $("input[name=del_code]").prop("checked", false);
   }
 }
  

//보드 리스트 그냥 페이징
function boardPaging(boardPageNum) {
 	var keyword = $("#keyword").val();
	var ctx = $("#ctx").val();
    var BOARD_MNG_NO = $("#BOARD_MNG_NO").val();
 	var tbody = $('#board_list_tbody');
	var tbodyContent = "";
  	var boardData = { "PageNum": boardPageNum, "BOARD_MNG_NO" : BOARD_MNG_NO,"keyword" : keyword 
 			        };
	
	$.ajax({
		url : '/boardPaging',
		type : 'POST',
		data : boardData,
		success : function(data) {
 			if(data.boardLitstSize == 0){
				alert("검색결과가 없습니다.");
				location.href = "/boardInqr?BOARD_MNG_NO=" + BOARD_MNG_NO;
			}else{
				tbody.children().remove(); 
				tbody.children().remove();
			
			for (var i = 0; i < data.boardList.length; i++) { 
 				
 				    if(data.boardList[i].file_CD == null){
 				    	tbodyContent +='<tr><td scope="row" style="width:10%; text-align:center"><input type="checkbox" id="del_code" class="call_chek" name="del_code" value="'+data.boardList[i].board_NO+'"></td>'
 	 				    +'<td style=width:10%">' + data.boardList[i].board_NO + '</td>'
 	         			+"<td style=width:40%;><a href='#' onclick=boardDetail('"+data.boardList[i].board_NO+"'); style='cursor: pointer;' class='callClick'>" + data.boardList[i].title +"</a></td>"
 	         			+'<td style="width:10%;">' + data.boardList[i].created_BY +'</td>'
 	         		    +'<td style="width:20%;">'+data.boardList[i].created+'</td>'
 	        	        +'<td style="width:10%;">'+data.boardList[i].view_CNT+'</td></tr>' ; 
 				    }
 				    else{
 				    	tbodyContent +='<tr><td scope="row" style="width:10%; text-align:center"><input type="checkbox" id="del_code" class="call_chek" name="del_code" value="'+data.boardList[i].board_NO+'"></td>'
 	 				    +'<td style=width:10%">' + data.boardList[i].board_NO + '</td>'
 	 				    +"<td style=width:40%;><a href='#' onclick=boardDetail('"+data.boardList[i].board_NO+"'); style='cursor: pointer;' class='callClick'>" + data.boardList[i].title +"</a> <i class='file icon'></i></td>" 	
 	         			+'<td style="width:10%;">' + data.boardList[i].created_BY +'</td>'
 	         		    +'<td style="width:20%;">'+data.boardList[i].created+'</td>'
 	        	        +'<td style="width:10%;">'+data.boardList[i].view_CNT+'</td></tr>' ; 
 				    }
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
					+"<a onclick=\"boardPaging("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
					+"<a onclick=\"boardPaging("+(data.PageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
				} else if(data.PageNum == data.page.endPageNum){
  					pageContent = "<input type='hidden' id='PageNum' value='"+data.PageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
					+"<a onclick=\"boardPaging("+(data.PageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
					+"<input type='text' id='pageInput' value='"+data.page.endPageNum+"' onkeypress=\"leadPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
					+"<a> / "+data.page.endPageNum+"</a> ▶";
				} else {
 					pageContent = "<input type='hidden' id='PageNum' value='"+data.PageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
					+"<a onclick=\"boardPaging("+(data.PageNum-1)+" );\" id='pNum' style='cursor: pointer;'> ◀ </a>"
					+"<input type='text' id='pageInput' value='"+data.PageNum+"' onkeypress=\"leadPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
					+"<a onclick=\"boardPaging("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
					+"<a onclick=\"boardPaging("+(data.PageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
				}
				$(".pagingDiv").append(pageContent);
		},
			
		 
		error : function() {
			alert("오류!");
		}
	});
} 
  
 
//검색 엔터키 기능
function boardSearchEnter(event) {
	var keycode = (event.keyCode ? event.keyCode : event.which);
	
 	if (keycode == '13') {
		if ($("#keyword").val() == '' && $("#qna_answer").val() == '') {
			alert("검색어를 입력하세요.")
			$("#keyword").focus();
		} else {
			boardPaging(1,'');
		}
	}
	event.stopPropagation();
}

 