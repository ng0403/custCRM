  $(document).ready(function(){
 	 replyPaging(1); 
 })
 
 
//댓글 리스트 그냥 페이징
function replyPaging(replyPageNum) {
    	var ctx = $("#ctx").val();
  	var BOARD_NO = $("#BOARD_NO").val();
 	var tbody = $('#reply_list_tbody');
	var tbodyContent = "";
  	var boardData = { "replyPageNum": replyPageNum, "BOARD_NO" : BOARD_NO
 			        };
	
	$.ajax({
		url : '/search_replyInqr',
		type : 'POST',
		data : boardData,
		success : function(data) {
   				tbody.children().remove();
 			 for (var i = 0; i < data.reply_list.length; i++) { 
 				tbodyContent +='<tr><th style="width:165px">' + data.reply_list[i].created_BY + '</th>'
 				+ '<td  style="text-align:left">' + data.reply_list[i].reply_CONTENT+ '<i style="float:right" class="large trash icon" id = '+data.reply_list[i].reply_NO+' onclick="remove_reply(this.id);"></i></td></tr>';
        		}
 			   tbody.append(tbodyContent);
  			/*x*/
			var pageContent = ""; 
			
			//시작
			
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
 			paging(ccPageNum, startPageNum, endPageNum, firstPageCount, totalPageCount, prevPageNum, nextPageNum, prevStepPage, nextStepPage);
		 
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
  		prevPage.attr("href","javascript:replyPaging("+prevPageNum+")");
  	}
  	prevPage.append(prevI);
  	$("#pageSpace").append(prevPage);
  	for(var i = startPageNum; i <= endPageNum; i++){
  		var ccPage = $("<a>");
  		ccPage.addClass("item");
  		ccPage.attr("href","javascript:replyPaging("+i+")");
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
  		nextPage.attr("href","javascript:replyPaging("+nextPageNum+")");
  	}
  	nextPage.append(nextI);
  	$("#pageSpace").append(nextPage);
  }

//댓글 추가
		function reply_add(){  	
		 var BOARD_MNG_NO = $("#BOARD_MNG_NO").val();
 		 var BOARD_NO = $("#BOARD_NO").val(); 
		 var REPLY_CONTENT_OBJ = $("#reply_content");
		 var REPLY_CONTENT = REPLY_CONTENT_OBJ.val();
 	  	 var CREATED_BY = "${SessionID}" ;
	  	$.ajax({
			type:'POST',
			url:'/reply_add',
			headers: { 
			      "Content-Type": "application/json",
			      "X-HTTP-Method-Override": "POST" },
			dataType:'text',
			processData: false,
			contentType: false,
			data:  JSON.stringify({"board_NO":BOARD_NO, "reply_CONTENT":REPLY_CONTENT, "created_BY":CREATED_BY, "board_MNG_NO":BOARD_MNG_NO}),
			success:function(result){
				$("#reply_content").val(""); 
				replyPaging(1);
	   			 $("#reply_content").blur(); 

				} 
		          
			      });
		} 
	 
		function remove_reply(e){ 
			 var REPLY_NO = e;
			 var BOARD_MNG_NO = $("#BOARD_MNG_NO").val();
	 		 var BOARD_NO = $("#BOARD_NO").val(); 
	 	 
			if(confirm("정보를 삭제 하시겠습니까?")){
			 $.ajax({
					url : '/reply_remove/',
					headers : {
			            "Content-Type" : "application/json",
			            "X-HTTP-Method-Override" : "POST"
			         },
					data : JSON.stringify({"board_NO":BOARD_NO, "reply_NO":REPLY_NO ,"board_MNG_NO":BOARD_MNG_NO}),
					dataType : 'text',
					processData: false,
					contentType: false,
					type: 'POST',
					success : function(result) {
						 
						if(result=="success"){
							replyPaging(1); 

	 					}
						  
					} 
			         
					}) 
			}
		} 
	 
