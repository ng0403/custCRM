/**
* 함수 목록
* taskSchList(taskPageNum)              : 상담 조회
* taskCancelList()						: 검색 input text 값 삭제(빈칸)
* task_add_save()                   	: 상담 단건 추가
* taskDetail(a)                     	: 상담 상세정보
* enterSearch(event)                	: 엔터키 기능
* taskPageNumInputEnter(event)          : 페이징 엔터키 기능
* taskPaging(pageNum)              	 	: 페이징 함수
* download_list_Excel(formID)			: 엑셀 출력 함수
* download_Excel_form(a)				: 엑셀 양식 출력
* taskExcelImportOpen() 				: 엑셀 Import 팝업
* taskExcelCheck()						: 엑셀 Insert
* taskCheckFileType(filePath) 			: 엑셀 파일 추가
*/

$(document).ready(function(){
	
	var ctx 	= $("#ctx").val();
	var flg 	= $("#flg").val();
	var session = $("#session").val();

});

//상담조회
function taskSchList(pageNum) {
	
   var cust_task_no = $("#cust_task_no").val();
   var task_no_srch    = $("#task_no_srch").val();
   var subject_srch    = $("#subject_srch").val();
   var cust_name_srch  = $("#cust_name_srch").val();
   var emp_name_srch   = $("#emp_name_srch").val();
   var next_day_srch   = $("#next_day_srch").val();
   var dtype_cd_srch   = $("#dtype_cd_srch").val();
   var session 		   = $("#session").val();
   
   var tbody = $('#task_list_tbody');
   var tbodyContent = "";
   
   $.ajax({
      url:ctx + '/task_sch',
      type: 'POST',
      data: {
         taskPageNum       : pageNum,
         task_no_srch      : task_no_srch,
         subject_srch      : subject_srch,
         cust_name_srch    : cust_name_srch,
         emp_name_srch     : emp_name_srch,
         next_day_srch     : next_day_srch,
         dtype_cd_srch     : dtype_cd_srch,
         session 		   : session,
         cust_task_no 	   : cust_task_no
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
		             "<td style='text-align: left;' >" +data.srcList[i].task_no +"</td>" +
		             "<td style='text-align: left;'>" +
		                "<a onclick=taskDetail('"+data.srcList[i].task_no+"','"+data.taskPageNum+"'); id='"+data.srcList[i].task_no+"'>" + data.srcList[i].subject+"</a></td>" +
		             "<td style='text-align: left;'>" + data.srcList[i].cust_no +"</td>" +
		             "<td style='text-align: left;'>" + data.srcList[i].cust_name +"</td>" +
		             "<td style='text-align: left;'>" + data.srcList[i].phone_area_no +"-"+ data.srcList[i].phone_no + "</td>" +
		             "<td style='text-align: left;'>" + data.srcList[i].emp_no + "</td>" +
		             "<td style='text-align: left;'>" + data.srcList[i].next_day + "</td>" +
		             "<td style='text-align: left;'>" + data.srcList[i].dtype_cd + "</td>" +
		             "<td style='text-align: left;'>" + data.srcList[i].update_date + "</td>" +
		             "</tr>"

		            tbody.append(tbodyContent);
		         }
			}

    	  	// 페이징
	         $(".pagingDiv").empty();
	         var pageContent = "";
	
	         if(data.page.endPageNum == 0 || data.page.endPageNum == 1){
	            pageContent = "◀ <input type='text' id='taskPageNum' readonly='readonly' value='1' style='width: 25px; text-align: center;'/> / 1 ▶";
	         } else if(data.taskPageNum == data.page.startPageNum){
	            pageContent = "<input type='hidden' id='taskPageNum' value='"+data.taskPageNum+"'/><input type='hidden' id='taskEndPageNum' value='"+data.page.endPageNum+"'/>"
	            +"◀ <input type='text' id='pageInput' value='"+data.page.startPageNum+"' onkeypress=\"taskPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>" 
	            +"<a onclick=\"taskSchList("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
	            +"<a onclick=\"taskSchList("+(data.taskPageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
	         } else if(data.taskPageNum == data.page.endPageNum){
	            pageContent = "<input type='hidden' id='taskPageNum' value='"+data.taskPageNum+"'/><input type='hidden' id='taskEndPageNum' value='"+data.page.endPageNum+"'/>"
	            +"<a onclick=\"taskSchList("+(data.taskPageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
	            +"<input type='text' id='pageInput' value='"+data.page.endPageNum+"' onkeypress=\"taskPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
	            +"<a> / "+data.page.endPageNum+"</a> ▶";
	         } else {
	            pageContent = "<input type='hidden' id='taskPageNum' value='"+data.taskPageNum+"'/><input type='hidden' id='taskEndPageNum' value='"+data.page.endPageNum+"'/>"
	            +"<a onclick=\"taskSchList("+(data.taskPageNum-1)+",2);\" id='pNum' style='cursor: pointer;'> ◀ </a>"
	            +"<input type='text' id='pageInput' value='"+data.taskPageNum+"' onkeypress=\"taskPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
	            +"<a onclick=\"taskSchList("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
	            +"<a onclick=\"taskSchList("+(data.taskPageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
	         }
	         $(".pagingDiv").append(pageContent);
	      },
	      error: function(){
	         alert("error");
	      }
	   });
}


//검색 취소버튼
function taskCancelList() {
	$('#task_no_srch').val('');
	$('#subject_srch').val('');
	$('#cust_name_srch').val('');
	$('#emp_name_srch').val('');
	$('#next_day_srch').val('');
	$('#dtype_cd_srch').val('');
}

//상담 상세 초기화 버튼
function task_reset() {
	
	if(confirm("입력된 정보를 지우겠습니까?"))
	{
	$("#subject").val("");
	$("#cust_no").val("");
	$("#cust_name").val("");
	$("#emp_no").val("");
	$("#emp_name").val("");
	$("#lead_no").val("");
	$("#lead_name").val("");
	$("#oppty_no").val("");
	$("#oppty_name").val("");
	$("#next_day").val(""); 
	$("#location").val(""); 
	$("#remark_cn").val("");
	
	$("#dtype_cd option:eq(0)").prop("selected", "selected");
	$("#score_cd option:eq(0)").prop("selected", "selected");
	}
	else{
		return false;
	}
}

//상담 단건 추가
function task_add(){
	//focus, css, readonly, disabled false 상태로 변경
	//값 초기화
	$("#subject").focus();
	$("#task_form_tbl input[type='text'], textarea, input[type='date']").attr({
		readonly:false,
		style:'background-color:white'
	}).val('');
	$("#task_form_tbl select").attr({
		display:false,
		style:'background-color:white'
	});
	var flg = $("#flg");
	var cust_task_no = $("#cust_task_no").val();
	var lead_no = $("#lead_no").val();
	var cust_no = $("#cust_no").val();
	var lead_code = $("#lead_code").val();
	var page_type = $("#page_type").val();
	var task_code = $("#task_code").val();
	
	if(task_code =="undefined"){
		task_code = null;
	}
	if(lead_code =="undefined"){
		lead_code = null;
	}
	if(lead_no =="undefined")
	{
		lead_no = null;
	}
	if(cust_no =="undefined")
	{
		cust_no = null;
	}
	if(cust_task_no =="undefined")
	{
		cust_task_no = null;
	} 
  
	if(cust_task_no != null && cust_task_no != "")
	{
		if(lead_no != null && lead_no != "")
		{	 
 			location.href="/task_detail?lead_no=" + lead_no + "&cust_no=" + cust_no +"&cust_task_no=1"+"&lead_code="+lead_code+ "&page_type=2";
		}else{
 			location.href="/task_detail?task_no=" +"&cust_task_no=" + cust_task_no + "&page_type=" + page_type;

		}
	}else{
		location.href="/task_detail?task_code="+task_code + "&page_type=" + page_type; 
	}
}

//상담 상세정보
function taskDetail(a, PageNum, lead_no, cust_no) {
 	var no = a;
 	var task_code = $("#task_code").val();
 	var page_type = $("#page_type").val();
	var cust_task_no = $("#cust_task_no").val();
  	var lead_no = $("#lead_no").val();
  	var lead_code = $("#lead_code").val();
  	 
  	if (cust_task_no == 'undefined') {
  		cust_task_no = null;
  	}
  	if (page_type == 'undefined') {
  		page_type = null;
  	}
	if(cust_task_no != null && cust_task_no != '' )
	{
		  if(lead_code !=null && lead_code !=""){
			  location.href="/task_detail?task_no=" + no +"&PageNum=" + PageNum + "&cust_task_no=" + cust_task_no + "&page_type=" + page_type + "&lead_code=" + lead_code + "&lead_no=" + lead_no; 
		  }else{
			  location.href="/task_detail?task_no=" + no +"&PageNum=" + PageNum + "&cust_task_no=" + cust_task_no + "&page_type=" + page_type;	  
		  } 
	}
	
	else if (lead_no != null && lead_no != '' )
	{
		location.href="/task_detail?task_no=" + no +"&PageNum=" + PageNum + "&lead_no=" + lead_no + "&cust_no=" + cust_no + "&page_type=" + page_type; 
	}
	else
	{
		location.href="/task_detail?task_no=" + no +"&PageNum=" + PageNum + "&page_type=" + page_type + "&task_code=" + task_code; 
	}
}

//고객 상담 이력 돌아가기.
function lead_counsel_read(cust_no, lead_code, PageNum){
    var lead_no = $("#lead_no").val();
 	location.href="/task?cust_task_no=" + cust_no + "&lead_no=" + lead_no + "&PageNum="+PageNum+"&lead_code="+lead_code;
}

//엔터키 기능
function taskenterSearch(event) 
{
   var keycode = (event.keyCode ? event.keyCode : event.which);
   if (keycode == '13') {
      /*searchKeyword()(1, 1);*/
	   taskSchList();
   }
   event.stopPropagation();
}

//페이징 엔터키
function taskPageNumInputEnter(event) {
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
         /*taskPaging(pageNum);*/
         taskSchList(pageNum);
      }
   }
   event.stopPropagation();
}

//페이징 함수
function taskPaging(pageNum) {
   $(document).ready(function() {
      // 컨트롤러로 전송
      var ctx = $("#ctx").val();
      var taskListForm = $("#taskListPagingForm");
        
       var pageNumInput = $('<input type="hidden" value="'+pageNum+'" name="pageNum">');
    
       taskListForm.append(pageNumInput);
       viewLoadingShow();
       taskListForm.submit();
   });
}


//엑셀 출력 적용 함수
function download_list_Excel(formID, flg) 
{	
	var session = $("#session").val(); 
   	var cust_no = $("#cust_no").val();
 	var t = flg;
	var ctx = $("#ctx").val();
	var cust_task_no = $("#cust_task_no").val();
	var form = $("#"+formID);
	var excel = $('<input type="hidden" value="true" name="excel">');
	var flg = $('<input type="hidden" value="'+flg+'" name="flg">');
    var cst_num = $('<input type="hidden" value="'+cust_no+'" name="cst_num">');
    var my_user_id = $('<input type="hidden" value="'+session+'" name="my_user_id">');
	
    if(cust_task_no != null && cust_task_no != '')
	{
		var cust_no = $('<input type="hidden" value="'+cust_task_no+'" name="cust_task_no">');
		form.append(cust_no);
	}
    
    if(t == 0)
	{
		if(confirm("리스트를 출력하시겠습니까? 대량의 경우 대기시간이 필요합니다.")) 
		{
			form.append(excel);
			form.append(flg);
			if(cst_num != null && cst_num !='' && cst_num !='undefined'){
				form.append(cst_num);	
			}  
			if(typeof my_user_id != 'undefined')
				{
				form.append(my_user_id)
				}
			form.attr("action", "/toExcel");
			form.submit();
		} 
		 
		$("input[name=excel]").val("");
	}
	else if(t == 1)
	{
		form.append(excel);
		form.append(flg);
		form.attr("action", "/toExcel");
		form.submit();
		
		$("input[name=excel]").val("");
	}
	 
}

//엑셀 양식 다운로드 
function download_Excel_form(a) { 
	var ctx = $("#ctx").val();
	var form = $("#"+formID);
    var flg = a;
	var excel = $('<input type="hidden" value="true" name="excel">');
	
	if(confirm("엑셀 양식을 다운로드 받으시겠습니까?")) 
	{
			form.append(flg);
			form.append(excel); 
			if(flg == 1) 
			{
				form.attr("action", "/toLeadExcel");
				form.submit(); 
			}  
	} 
	$("input[name=excel]").val("");
}

function exportToExcel(){
   document.taskExcelForm.action="/toExcel";
   document.taskExcelForm.method="POST"
   document.taskExcelForm.submit();
}

//popup
function taskExcelImportOpen()
{
	// 팝업창 표시
	$.blockUI({ message: $('#multiInsertModalDiv'),
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
}

//Popup 닫기
function popupClose()
{
	$.unblockUI();
}

//엑셀파일 insert
function taskExcelCheck()
{
	var excelFile = $("#excelFile").val();
	
	if (excelFile == "" || excelFile == null) 
	{
	    alert("파일을 선택해주세요.");
	    return false;
	} 
	else if (!taskCheckFileType(excelFile)) 
	{
	    alert("엑셀 파일만 업로드 가능합니다.");
	    return false;
	}
	if (confirm("업로드 하시겠습니까?")) 
	{
		var options = {
    		type	: 	'POST',
    		cache	: 	false,
    		url		: 	ctx + "/taskExcelUpload",
    		success	:	function(data) {
			popupClose();
			taskSchList(1);
			alert(data + " 건이 등록되었습니다.");
    		},
    		error	: function(data) {
    			alert("엑셀 업로드 중 에러가 발생했습니다.");
    			return;
    		}
		};
		$("#excelUploadForm").ajaxSubmit(options);
		}
}

//엑셀 파일 추가 fucntion
function taskCheckFileType(filePath) 
{
	var fileFormat = filePath.split(".");
	
	if (fileFormat.indexOf("xlsx") > -1) {
		return true;
	} 
	else {
		return false;
	}
}

//가망고객 리스트 이동.
function leadlist(url){
	 
	 location.href=url;
} 

//고객리드 상세정보
//준석 추가
function tleadDetail(lead_no, PageNum, lead_code)
{
	 location.href="/lead_detail?lead_no=" + lead_no + "&pageNum=" + PageNum + "&lead_code="+lead_code; 
}

//lead 고객 상담조회
function custtaskSchList(cust_no,pageNum) {
	 
	   var task_no_srch    = $("#task_no_srch").val();
	   var subject_srch    = $("#subject_srch").val();
	   var cust_name_srch  = $("#cust_name_srch").val();
	   var emp_name_srch   = $("#emp_name_srch").val();
	   var next_day_srch   = $("#next_day_srch").val();
	   var dtype_cd_srch   = $("#dtype_cd_srch").val();

	   var tbody = $('#task_list_tbody');
	   var tbodyContent = "";
	   
	   $.ajax({
	      url:ctx + '/cust_task_sch',
	      type: 'POST',
	      data: {
	         taskPageNum       : pageNum,
	         task_no_srch      : task_no_srch,
	         subject_srch      : subject_srch,
	         cust_name_srch    : cust_name_srch,
	         emp_name_srch     : emp_name_srch,
	         next_day_srch     : next_day_srch,
	         dtype_cd_srch     : dtype_cd_srch,
	         cust_no  	       : cust_no
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
			             "<td style='text-align: left;' >" +data.srcList[i].task_no +"</td>" +
			             "<td style='text-align: left;'>" +
			                "<a onclick=taskDetail('"+data.srcList[i].task_no+"','"+data.taskPageNum+"'); id='"+data.srcList[i].task_no+"'>" + data.srcList[i].subject+"</a></td>" +
			             "<td style='text-align: left;'>" + data.srcList[i].cust_no +"</td>" +
			             "<td style='text-align: left;'>" + data.srcList[i].cust_name +"</td>" +
			             "<td style='text-align: left;'>" + data.srcList[i].phone_no + "</td>" +
			             "<td style='text-align: left;'>" + data.srcList[i].emp_no + "</td>" +
			             "<td style='text-align: left;'>" + data.srcList[i].next_day + "</td>" +
			             "<td style='text-align: left;'>" + data.srcList[i].dtype_cd + "</td>" +
			             "<td style='text-align: left;'>" + data.srcList[i].create_date + "</td>" +
			             "</tr>"

			            tbody.append(tbodyContent);
			         }
				}

	    	  	// 페이징
		         $(".pagingDiv").empty();
		         var pageContent = "";
		
		         if(data.page.endPageNum == 0 || data.page.endPageNum == 1){
		            pageContent = "◀ <input type='text' id='taskPageNum' readonly='readonly' value='1' style='width: 25px; text-align: center;'/> / 1 ▶";
		         } else if(data.taskPageNum == data.page.startPageNum){
		            pageContent = "<input type='hidden' id='taskPageNum' value='"+data.taskPageNum+"'/><input type='hidden' id='taskEndPageNum' value='"+data.page.endPageNum+"'/>"
		            +"◀ <input type='text' id='pageInput' value='"+data.page.startPageNum+"' onkeypress=\"taskPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>" 
		            +"<a onclick=\"custtaskSchList('"+cust_no+"' , "+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
		            +"<a onclick=\"custtaskSchList('"+cust_no+"' , "+(data.taskPageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
		         } else if(data.taskPageNum == data.page.endPageNum){
		            pageContent = "<input type='hidden' id='taskPageNum' value='"+data.taskPageNum+"'/><input type='hidden' id='taskEndPageNum' value='"+data.page.endPageNum+"'/>"
		            +"<a onclick=\"custtaskSchList('"+cust_no+"' , "+(data.taskPageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
		            +"<input type='text' id='pageInput' value='"+data.page.endPageNum+"' onkeypress=\"taskPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
		            +"<a> / "+data.page.endPageNum+"</a> ▶";
		         } else {
		            pageContent = "<input type='hidden' id='taskPageNum' value='"+data.taskPageNum+"'/><input type='hidden' id='taskEndPageNum' value='"+data.page.endPageNum+"'/>"
		            +"<a onclick=\"custtaskSchList('"+cust_no+"' , "+(data.taskPageNum-1)+",2);\" id='pNum' style='cursor: pointer;'> ◀ </a>"
		            +"<input type='text' id='pageInput' value='"+data.taskPageNum+"' onkeypress=\"taskPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
		            +"<a onclick=\"custtaskSchList('"+cust_no+"' , "+data.page.taskPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
		            +"<a onclick=\"custtaskSchList('"+cust_no+"' , "+(data.taskPageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
		         }
		         $(".pagingDiv").append(pageContent);
		      },
		      error: function(){
		         alert("error");
		      }
		   });
	}

//고객관리 리스트
function tcustList(pageNum)
{
	location.href = ctx + '/cust?custPageNum=' + pageNum;
}

// 고객상세정보
function tcustDetail(cust_no)
{
	console.log(cust_no);
	location.href = ctx + '/custForm?cust_no=' + cust_no + "&page_type=" + 0;
}

function custTaskList(cust_no)
{
	console.log(cust_no);
	location.href = ctx + "/task?cust_task_no=" + cust_no;
}
