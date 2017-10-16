 $(function(){
	var ctx = $('#ctx').val(); 
	
  });
 
 $(document).ready(function(){
 	 var tbodylength = $('#board_list_tbody tr').length;
	 	var tbody = $('#board_list_tbody');
		
		/*if(tbodylength < 10){
				for(var i=0; i<10-tbodylength; i++){
 				tbodyContent='<tr style="height: 35.5px;"><td scope="row" style="width:10%"></td>'
						+'<td style="width:10%;"></td>'
						+'<td style="width:40%;"></td>'
						+'<td style="width:10%;"></td>'
						+'<td style="width:20%;"></td>'
						+'<td style="width:10%;"></td></tr>';
				tbody.append(tbodyContent);
			}		
		}*/
 })
 
//13자리 날짜 변환 함수
 function dateFormat(timestamp) {

 	var date = new Date(timestamp);
 	var year = date.getFullYear();
 	var month = date.getMonth() + 1;
 	var day = date.getDate();
 	var hour = date.getHours();
 	var min = date.getMinutes();
 	var sec = date.getSeconds();

 	var retVal = year + "-" + (month < 10 ? "0" + month : month) + "-"
 			+ (day < 10 ? "0" + day : day) + " "+ (hour==0 ? "00" : hour )+":"+(min==0 ? "00" : min );

 	return retVal
 }
 
 
 
 //파일 추가.
 function fileupload(){
 	     var formObj = $("form[role='form1']");
		 formObj.attr("action", "/file_insert");
		 formObj.attr("method", "post");
		 formObj.submit();  
	}
 
 //보드 상세보기.
 function boardDetail(a){
 var no = a;
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
 	     
 	 /* $("form[name='form_modify']").attr("action", "${ctx}/board/board_read?BOARD_NO=?").submit();  */
	 
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
 	 location.href="/boardInsert?BOARD_MNG_NO="+BOARD_MNG_NO;

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
function boardPaging(boardPageNum, a) {
 	var keyword = $("#keyword").val();
	var ctx = $("#ctx").val();
    var BOARD_MNG_NO = $("#BOARD_MNG_NO").val();
 	var tbody = $('#board_list_tbody');
	var tbodyContent = "";
  	var boardData = { "boardPageNum": boardPageNum, "BOARD_MNG_NO" : BOARD_MNG_NO,"keyword" : keyword 
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
 	         		    +'<td style="width:20%;">'+dateFormat(data.boardList[i].created)+'</td>'
 	        	        +'<td style="width:10%;">'+data.boardList[i].view_CNT+'</td></tr>' ; 
 				    }
 				    else{
 				    	tbodyContent +='<tr><td scope="row" style="width:10%; text-align:center"><input type="checkbox" id="del_code" class="call_chek" name="del_code" value="'+data.boardList[i].board_NO+'"></td>'
 	 				    +'<td style=width:10%">' + data.boardList[i].board_NO + '</td>'
 	 				    +"<td style=width:40%;><a href='#' onclick=boardDetail('"+data.boardList[i].board_NO+"'); style='cursor: pointer;' class='callClick'>" + data.boardList[i].title +"</a> <i class='file icon'></i></td>" 	
 	         			+'<td style="width:10%;">' + data.boardList[i].created_BY +'</td>'
 	         		    +'<td style="width:20%;">'+dateFormat(data.boardList[i].created)+'</td>'
 	        	        +'<td style="width:10%;">'+data.boardList[i].view_CNT+'</td></tr>' ; 
 				    }
        		}
 			   tbody.append(tbodyContent);
 			}
			
			/*if(data.boardList.length < 10){
 				for(var i=0; i<10-data.boardListSize; i++){
					tbodyContent='<tr style="height: 35.5px;"><td scope="row" style="width:10%"></td>'
 						+'<td style="width:10%;"></td>'
						+'<td style="width:40%;"></td>'
						+'<td style="width:10%;"></td>'
						+'<td style="width:20%;"></td>'
 						+'<td style="width:10%;"></td></tr>';
					tbody.append(tbodyContent);
				}		
			}*/
			
			var pageContent = "";

			// 시작

			$("#pageSpace").children().remove();
			var ccPageNum = data.boardPageNum;
			var startPageNum = data.page.startPageNum;
			var endPageNum = data.page.endPageNum;
			var firstPageCount = data.page.firstPageCount;
			var totalPageCount = data.page.totalPageCount;
			var prevPageNum = data.page.prevPageNum;
			var nextPageNum = data.page.nextPageNum;
			var prevStepPage = data.page.prevStepPage;
			var nextStepPage = data.page.nextStepPage;
			paging(ccPageNum, startPageNum, endPageNum, firstPageCount,
					totalPageCount, prevPageNum, nextPageNum,
					prevStepPage, nextStepPage);
		},
			
		 
		error : function() {
			alert("오류!");
		}
	});
} 
  

//페이징
function paging(ccPageNum, startPageNum, endPageNum, firstPageCount, totalPageCount, prevPageNum, nextPageNum, prevStepPage, nextStepPage){
	var endPageNo = $("<input>");
	endPageNo.attr({"type":"hidden","id":"endPageNum","value":endPageNum});
	var ccPageeNo = $("<input>");
	ccPageeNo.attr({"type":"hidden","id":"ccPageNum","value":ccPageNum});
	$("#pageSpace").append(endPageNo).append(ccPageeNo);
	
	var prevPage = $("<a>");
	prevPage.addClass("icon item");
	var prevI = $("<i>");
	prevI.addClass("left chevron icon");
	console.log(prevPageNum);
	console.log(firstPageCount);
	if(ccPageNum != firstPageCount){
		prevPage.attr("href","javascript:boardPaging("+prevPageNum+")");
	}
	prevPage.append(prevI);
	$("#pageSpace").append(prevPage);
	for(var i = startPageNum; i <= endPageNum; i++){
		var ccPage = $("<a>");
		ccPage.addClass("item");
		ccPage.attr("href","javascript:boardPaging("+i+")");
		ccPage.html(i);
		if(i == ccPageNum){
			var b = $("<b>");
			ccPage.attr("id","pNum");
			b.append(ccPage);
			$("#pageSpace").append(b);
		}else{
			$("#pageSpace").append(ccPage);
		}
	}
	var nextPage = $("<a>");
	nextPage.addClass("icon item");
	var nextI = $("<i>");
	nextI.addClass("right chevron icon");
	if(ccPageNum != totalPageCount){
		nextPage.attr("href","javascript:boardPaging("+nextPageNum+")");
	}
	nextPage.append(nextI);
	$("#pageSpace").append(nextPage);
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


//ppt 다운로드 팝업
function pptPopup(FILE_CD){  
	alert(FILE_CD);
	$("#file_cd").val(FILE_CD);
	var windowW = 400; // 창의 가로 길이
    var windowH = 400; // 창의 세로 길이
    var left = Math.ceil((window.screen.width - windowW)/2);
    var top = Math.ceil((window.screen.height - windowH)/2);
	//var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(optoin)
	window.open('standardDetailPop?FILE_CD=' + FILE_CD,"","top="+top+", left="+left+", height="+windowH+", width="+windowW);
}
 