 
   $(document).ready(function(){
		var file_div = $('#file_div');
 

   })


//보드 목록
 function board_list() {
	var BOARD_MNG_NO = $("#BOARD_MNG_NO").val();
	if(confirm("목록으로 가시겠습니까?")){
	location.href = "/boardInqr?BOARD_MNG_NO=" + BOARD_MNG_NO;
	}else
		{
		return false;
		}
	}
  
function board_save(){ 
 	   var formObj = $("form[role='form']");
 	   	if(confirm("수정 하시겠습니까?")){
 	   		alert("게시판이 수정되었습니다.");
 	   		alert("게시판 페이지로 이동합니다.");
	 	$("form[name='modifyForm']").attr("action", "/board_modify").submit();
 	   	}else{
 	   		return false;
 	   	}
 }

function deleteFile(FILE_CD){
	
	var file_div = $('#file_div'); 
	var divContent = "";
if(confirm("파일을 삭제 하시겠습니까?")){	
	$.ajax({
        url : '/file_remove',
        headers : {
              "Content-Type" : "application/json",
              "X-HTTP-Method-Override" : "POST"
           },
        data : FILE_CD,
        dataType : 'text',
        processData: false,
        contentType: false,
        type: 'POST',
        success : function(result) {
        	file_div.children().remove();
             if(result =="success")
              {
            	alert("파일이 삭제되었습니다.");
             	divContent += "<input type='file' multiple='multiple' name='filedata' id='filedata'> ";
             	file_div.append(divContent);
                }
           else{
              alert("오류!");
           }
        
        }
        })  
}
}
	 