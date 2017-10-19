  $(document).ready(function(){
  	 replyPaging(1); 
 })
 
 
//댓글 리스트 그냥 페이징
function replyPaging(PageNum) {
      var ctx = $("#ctx").val();
  	var BOARD_NO = $("#BOARD_NO").val();
 	var tbody = $('#reply_list_tbody');
	var tbodyContent = "";
  	var boardData = { "PageNum": PageNum, "BOARD_NO" : BOARD_NO
 			        };
	
	$.ajax({
		url : '/search_replyInqr',
		type : 'POST',
		data : boardData,
		success : function(data) {
    		 tbody.children().remove();
 			 for (var i = 0; i < data.reply_list.length; i++) { 
 				tbodyContent +='<tr><th>' + data.reply_list[i].created_BY + '</th>'
 				+ '<td colspan="5">' 
 				+ data.reply_list[i].reply_CONTENT+
 				'<a href style="float:right" id='+data.reply_list[i].reply_NO+' onclick="modify_reply(this.id);">편집</a>'+  
 				'<a href style="float:right" id='+data.reply_list[i].reply_NO+' onclick="remove_reply(this.id);">삭제</a></td></tr>';
        		}
 			   tbody.append(tbodyContent);
  			 
			//시작
			// 페이징
			$(".pagingDiv").empty();
			var pageContent = "";
				if(data.page.endPageNum == 0 || data.page.endPageNum == 1){
				pageContent = "◀ <input type='text' id='pageInput' readonly='readonly' value='1' style='width: 25px; text-align: center;'/> / 1 ▶";
			} else if(data.PageNum == data.page.startPageNum){
					pageContent = "<input type='hidden' id='PageNum' value='"+data.PageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"◀ <input type='text' id='pageInput' value='"+data.page.startPageNum+"' onkeypress=\"leadPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>" 
				+"<a onclick=\"replyPaging("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
				+"<a onclick=\"replyPaging("+(data.PageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
			} else if(data.PageNum == data.page.endPageNum){
					pageContent = "<input type='hidden' id='PageNum' value='"+data.PageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"<a onclick=\"replyPaging("+(data.PageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
				+"<input type='text' id='pageInput' value='"+data.page.endPageNum+"' onkeypress=\"leadPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
				+"<a> / "+data.page.endPageNum+"</a> ▶";
			} else {
					pageContent = "<input type='hidden' id='PageNum' value='"+data.PageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"<a onclick=\"replyPaging("+(data.PageNum-1)+" );\" id='pNum' style='cursor: pointer;'> ◀ </a>"
				+"<input type='text' id='pageInput' value='"+data.PageNum+"' onkeypress=\"leadPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
				+"<a onclick=\"replyPaging("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
				+"<a onclick=\"replyPaging("+(data.PageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
			}
			$(".pagingDiv").append(pageContent);
			 
		},
		error : function() {
			alert("오류!");
		}
	});
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
		
	 //댓글 삭제 기능.
function remove_reply(REPLY_NO){ 
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

//댓글 편집 버튼
function modify_reply(){
	
}
		
//댓글 편집 저장.
function modify_reply_save(REPLY_NO){ 
  
 var BOARD_MNG_NO = $("#BOARD_MNG_NO").val();
 var BOARD_NO = $("#BOARD_NO").val(); 
 var REPLY_CONTENT = $("#reply_content").val();
  
 if(confirm("정보를 수정 하시겠습니까?")){
			
    $.ajax({
	  url : '/reply_modify/',
	  headers : {
			      "Content-Type" : "application/json",
			       "X-HTTP-Method-Override" : "POST"
			    },
	    data : JSON.stringify({"board_NO":BOARD_NO, "reply_NO":REPLY_NO ,"board_MNG_NO":BOARD_MNG_NO, "REPLY_CONTENT":REPLY_CONTENT}),
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
		
	 
