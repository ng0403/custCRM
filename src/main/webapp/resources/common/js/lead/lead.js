 
$(function(){
 	  var flg = $("#flg").val();
 
	  // 상세보기일 때 text readonly
	  if(flg == '0'){
		  	$("#lead_no").attr("readonly", true);
		  	$("#lead_name").attr("readonly", true);
		  	$("#cust_no").attr("readonly", true);
		    $("#emp_name").attr("readonly", true);
 			$("#rank_cd").attr("readonly", true); 
			$("#reason_cd").attr("readonly", true);
			$("#remark_cn").attr("readonly", true);  
			$("#emp_list_pop").attr("disabled", true);
			$("#cust_list_pop").attr("disabled", true);
			$("#contact_day").attr("disabled", true);
			$("#possibility_cd_sel").attr("disabled", true);
			$("#lead_status_cd_sel").attr("disabled", true);
			$("#contact_day").css('background-color', 'white');
	  } 
 
	  // lead update button div hide
	  $("#lead_update_div").css("display", "none");
	  
	  //lead update title div hide
	  $("#lead_update_title").css("display", "none");
}); 

//검색 조건
function searchKeyword(lead_code,a){
 	var cust_lead_no = $("#cust_lead_no").val();
 	
 	var lead_no_srch = $("#lead_no_srch").val();
	var lead_name_srch = $("#lead_name_srch").val();
	var cust_name = $("#cust_name").val();
	var emp_name = $("#emp_name").val();
	var contact_day_srch = $("#contact_day_srch").val();
	var rank_cd = $("#rank_cd").val();
	var session = $("#session").val();
	
	if(lead_no_srch == null)
  {
		lead_no_srch = "";
  }
	if(lead_name_srch == null)
  {
		lead_name_srch = "";
  }
	if(cust_name == null)
  {
		cust_name = "";
  }
	if(emp_name == null)
  {
		emp_name = "";
  }
	if(contact_day_srch == null)
  {
		contact_day_srch = "";
  }
	if(rank_cd == null)
  {
		rank_cd = "";
  }
	
	var leadData = { "lead_no_srch": lead_no_srch, 
				"lead_name_srch": lead_name_srch,
		        "cust_name": cust_name, 
		        "emp_name":emp_name, 
		        "contact_day_srch":contact_day_srch,
		        "rank_cd" : rank_cd , "PageNum" : a,
		        "cust_lead_no" : cust_lead_no,
		        "lead_code" : lead_code,
		        "session" : session
		        };
				

			var tbody = $('#lead_list_tbody');
			var tbodyContent = "";
	  
			$.ajax({
				url:'/searchKeyword',
				type: 'POST',
				data: leadData,
				dataType:'json',
				success: function(data){
					console.log(data);
					
					tbody.children().remove(); 
			  
					if(data.leadList.length == 0) {
						tbodyContent = "<tr style='height: 75px;'><td colspan=9' style='width: 1320px; text-align: center;  vertical-align: middle;'>검색 결과가 없습니다.</td></tr>";
			    		tbody.append(tbodyContent);
					}
					else{
					for(var i=0; i<data.leadList.length; i++){ 
					tbodyContent = "<tr>" +
	 	 			"<td style='text-align: left;' >" +data.leadList[i].lead_no +"</td>" +
	 	 			"<td style='text-align: left;'>" +
	 	 			"<a href='#' onclick=leadDetail('"+data.leadList[i].lead_no+"','"+data.PageNum+"','"+data.leadList[i].lead_status_cd+"'); id='"+data.leadList[i].lead_no+"'>" + data.leadList[i].lead_name+"</a></td>" +
	 	 			"<td style='text-align: left;'>" + data.leadList[i].cust_no +"</td>" +
	 	 			"<td style='text-align: left;'>" +data.leadList[i].cust_name +"</td>" +
	 	 			"<td style='text-align: left;'>" + data.leadList[i].phone_area_no +"-"+ data.leadList[i].phone_no + "</td>" +
	 	 			"<td style='text-align: left;'>" + data.leadList[i].user_nm + "</td>" +
	 	 			"<td style='text-align: left;'>" + data.leadList[i].contact_day + "</td>" +
	 	 			"<td style='text-align: left;'>" + data.leadList[i].rank_cd + "</td>" +
	 	 			"<td style='text-align: left;'>" + data.leadList[i].update_date + "</td>" +
	 	 			"</tr>";
					tbody.append(tbodyContent);
					}
				 }

					// 페이징
					$(".pagingDiv").empty();
					var pageContent = "";

					console.log(data);
					
					if(data.page.endPageNum == 0 || data.page.endPageNum == 1){
						pageContent = "◀ <input type='text' id='pageInput' readonly='readonly' value='1' style='width: 25px; text-align: center;'/> / 1 ▶";
					} else if(data.PageNum == data.page.startPageNum){
				 
						pageContent = "<input type='hidden' id='PageNum' value='"+data.PageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
						+"◀ <input type='text' id='pageInput' value='"+data.page.startPageNum+"' onkeypress=\"leadPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>" 
						+"<a onclick=\"searchKeyword('"+lead_code+"',"+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
						+"<a onclick=\"searchKeyword('"+lead_code+"',"+(data.PageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
					} else if(data.PageNum == data.page.endPageNum){
					 
						pageContent = "<input type='hidden' id='PageNum' value='"+data.PageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
						+"<a onclick=\"searchKeyword('"+lead_code+"',"+(data.PageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
						+"<input type='text' id='pageInput' value='"+data.page.endPageNum+"' onkeypress=\"leadPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
						+"<a> / "+data.page.endPageNum+"</a> ▶";
					} else {
	 
						pageContent = "<input type='hidden' id='PageNum' value='"+data.PageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
						+"<a onclick=\"searchKeyword('"+lead_code+"',"+(data.PageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
						+"<input type='text' id='pageInput' value='"+data.PageNum+"' onkeypress=\"leadPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
						+"<a onclick=\"searchKeyword('"+lead_code+"',"+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
						+"<a onclick=\"searchKeyword('"+lead_code+"',"+(data.PageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
					}
					$(".pagingDiv").append(pageContent);
					
					
					
				},
				error: function(){
					alert("error");
				}
			});
	 
}
 
//가망 고객 검색 초기화
function srch_reset() { 
	
		$("#lead_no_srch").val("");
		$("#lead_name_srch").val("");
		$("#cust_name").val("");
		$("#emp_name").val("");
		$("#contact_day_srch").val("");
		$("#rank_cd").val("");  
}
  

//검색 엔터키 기능
function leadEnterSearch(event) {
	var lead_code = $("#lead_code").val();
	var keycode = (event.keyCode ? event.keyCode : event.which);
	
 	if (keycode == '13') {
		if ($("#lead_no_srch").val() == '' && $("#lead_name_srch").val() == '' && $("#cust_name").val() == '' && $("#emp_name").val() == ''  && $("#rank_cd").val() == '' ) {
			alert("검색어를 입력하세요.")
			$("#lead_no_srch").focus();
		} else {
			searchKeyword(lead_code);
		}
	}
	event.stopPropagation();
} 

//페이징 엔터키
function leadPageNumInputEnter(event) {
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
			searchKeyword(pageNum);
		}
	}
	event.stopPropagation();
}
 
 
// 리드 상세정보
 function leadDetail(a,b,lead_code) {
   var no = a;
   var cust_no = $("#cust_lead_no").val();
   
//   console.log(no);
   console.log(cust_no);
   
   if(cust_no == null || cust_no == '')
   {
	   console.log("A");
	   location.href="/lead_detail?lead_no=" + no + "&pageNum=" + b + "&lead_code="+lead_code; 
   }
   if(cust_no != null && cust_no != '')
   {
	   console.log("B");
	   location.href="/lead_detail?lead_no=" + no + "&pageNum=" + b + "&cust_lead_no=" + cust_no; 
   }
	 
 }
  
 //가망고객 리스트 이동.
 function leadlist(url){
	 
	 location.href=url;
 }
 
 //가망 고객 취소 페이지 이동
 function lead_cancel(a){
 
	 if(confirm("리스트 페이지로 이동하시겠습니까?")){
		 alert("가망고객 리스트로 이동합니다.");
		 location.href="/lead?pageNum=" + a;
	 }
	 else{
		 return false;
	 }
 }
  
 // 가망고객 단건 추가
 function lead_add(){
 
	 location.href="/lead_single_add"
	 
 } 
 
 //가망고객 단건 추가 저장
function lead_single_save(){
	var formObj =  $("form[role='form']"); 
	
    if($("#lead_name").val() == null || $("#lead_name").val()==""){
        	alert("리드명을 입력해 주세요.");
        	return false;
    } 
    
    if($("#cust_no").val() == null || $("#cust_no").val()==""){
    	alert("고객명을 입력해 주세요.");
    	return false;
    }	  
    	
    if($("#contact_day").val() == null || $("#contact_day").val()==""){
    	alert("접촉할 일자를 입력해 주세요.");
    	return false;
    }  
    
    if($("#lead_status_cd_sel").val() == null || $("#lead_status_cd_sel").val()==""){
    	alert("상태 코드를 입력해 주세요.");
    	return false;
    } 
    
	 if(confirm("저장 하시겠습니까?")){ 
	alert("가망고객이 저장되었습니다.");
	alert("가망고객 리스트로 이동합니다.");

	 formObj.attr("action", "/lead_single_add");
	 formObj.attr("method", "post");
	 formObj.submit();          
	  }else{
   	 return false;
    }
}
 
//가망 고객 수정
function lead_modify(){  
	
	/*$("#emp_name").attr("readonly", false);*/
	$("#lead_name").attr("readonly", false);
	$("#contact_day").attr("readonly", false);
	$("#rank_cd").attr("readonly", false); 
	$("#reason_cd").attr("readonly", false);
	$("#remark_cn").attr("readonly", false);  
	$("#emp_list_pop").attr("disabled", false);
	$("#cust_list_pop").attr("disabled", false);
	$("#contact_day").attr("disabled", false);
	$("#lead_status_cd_sel").attr("disabled", false);
	$("#possibility_cd_sel").attr("disabled",false); 
	
	$("#lead_detail_div").css("display", "none");
	$("#lead_update_div").css("display", "block"); 
	
	$("#lead_detail_title").css("display", "none");
	$("#lead_update_title").css("display", "block");
 
} 

//가망 고객 수정 저장
function lead_modify_save() {
	
	var formObj = $("form[role='form']");

    	
    	if($("#cust_no").val() == null || $("#cust_no").val()==""){
    	    alert("고객명을 입력해 주세요.");
    	    return false;
    	}  	
    	    	
    	if($("#contact_day").val() == null || $("#contact_day").val()==""){
    	    alert("접촉할 일자를 입력해 주세요.");
    	    return false;
        }  
    	
     if(confirm("수정 하시겠습니까?")){ 
     alert("가망고객이 수정되었습니다.");
	 alert("가망고객 리스트로 이동합니다.");

 	 formObj.attr("action", "/lead_update");
	 formObj.attr("method", "post");
	 formObj.submit();  
    }else{
   	 return false;
    }
	
}

//가망 고객 상세 초기화 버튼
function lead_reset() {
	if(confirm("초기화 하시겠습니까")){
	$("#lead_name").val("");
	$("#cust_name").val("");
	$("#emp_name").val("");
	$("#contact_day").val("");
	$("#rank_cd").val("");
	$("#reason_cd").val("");
	$("#remark_cn").val(""); 
	} 
}

//가망 고객 삭제
function lead_remove() {
	
	var formObj = $("form[role='form']");
    if(confirm("삭제 하시겠습니까?")){
    	alert("삭제되었습니다.");
    	alert("가망고객 리스트로 이동합니다.");
 	 formObj.attr("action", "/lead_delete");
	 formObj.attr("method", "post");
	 formObj.submit();  
    }else{
     alert("취소되었습니다.");
   	 return false;
    }
   
}  
//고객생담 이력 
function lead_counsel_read(cust_no, PageNum, lead_code){
    var lead_no = $("#lead_no").val();
	location.href="/task?cust_task_no=" + cust_no + "&lead_no=" + lead_no + "&lead_code="+lead_code + "&PageNum="+PageNum;
}


// 재욱
function lcustList(pageNum)
{
	var ctx = $("#ctx").val();
	location.href = ctx + '/cust?custPageNum=' + pageNum;
}

function lcustDetail(custNo)
{
	var ctx = $("#ctx").val();
	location.href = ctx + '/custForm?cust_no=' + custNo + "&page_type=" + 0;
}

function leadCustList(custNo)
{
	var ctx = $("#ctx").val();
	location.href = "/lead?cust_lead_no=" + custNo;
}



//엑셀 출력 
function download_list_Excel(formID, flgNum) {
 	var url = $("#url").val();
	
	var code = $("#flg").val();
	var cust_lead_no = $("#cust_lead_no").val();
	var ctx = $("#ctx").val();
	var form = $("#"+formID);
	var excel = $('<input type="hidden" value="true" name="excel">');
	var flg = $('<input type="hidden" value="'+flgNum+'" name="flg">');
	var code_flg = $('<input type="hidden" value="'+code+'" name="code_flg">');
	var session = $("#session").val();
	var user_id = $('<input type="hidden" value="'+session+'" name="user_id">');
	var path = $('<input type="hidden" value="'+url+'" name="path">')
	 
	
	if(cust_lead_no != null && cust_lead_no != '')
	{
		var cust_no = $('<input type="hidden" value="'+cust_lead_no+'" name="cust_lead_no">');
		form.append(cust_no);
		
		console.log(cust_lead_no);
		console.log(cust_no);
	}
	
	if(flgNum == '0'){
	if(confirm("리스트를 출력하시겠습니까? 대량의 경우 대기시간이 필요합니다.")) 
	{
		alert("flg ? " + flg);
		form.append(excel);
		form.append(flg);
	    form.append(code_flg);
		form.append(user_id);
		form.append(path);
		form.attr("action", "/toLeadExcel?flg=" + flgNum);
		form.submit();
		flg="";
		alert("flg ??? " + flg);
/*		if(flg == 0) 
		{
		
		} 
		else(flg == 1) 
		{
			form.attr("action", "/task_sch");
			form.submit();
		}*/
	} 
/*	$("input[name=excel]").val("");
	$("input[name=flg]").val("");*/
	else{
		return false;
	} 
	$("input[name=excel]").val("");
}
	
	if(flgNum == '1'){
 			form.append(excel);
			form.append(flg);
			form.append(code);
			
			/*form.attr("action", "/toLeadExcel?flg=" + flgNum);*/
			form.attr("action", "/toLeadExcel");
			form.submit();
			flg = "";
			alert("flg ? " + form.toSource());
	/*		if(flg == 0) 
			{
			
			} 
			else(flg == 1) 
			{
				form.attr("action", "/task_sch");
				form.submit();
			}*/
		} 
	/*	$("input[name=excel]").val("");
		$("input[name=flg]").val("");*/
		else{
			return false;
		} 
		
  		/*form[0].reset(); */
	
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
			$("form").each(function() {  
		        this.reset();  
		     });   
	} 
	
  	/*$("input[name=excel]").val("");*/
}


//엑셀 Import 팝업	
/*function leadExcelImportOpen() 
{
	var popWidth  = '520'; // 파업사이즈 너비
	var popHeight = '160'; // 팝업사이즈 높이
	var winHeight = document.body.clientHeight;	// 현재창의 높이
	var winWidth = document.body.clientWidth;	// 현재창의 너비
	var winX = window.screenLeft;	// 현재창의 x좌표
	var winY = window.screenTop;	// 현재창의 y좌표

	var popX = winX + (winWidth - popWidth)/2;
	var popY = winY + (winHeight - popHeight)/2;
	var popUrl = "leadExcelImportTab";
	var popOption = "width=520, height=160, resize=no, scrollbars=no, status=no, location=no, directories=no; ,top=pop,left=popX";
	window.open(popUrl, "_blank","width="+popWidth+"px,height="+popHeight+"px,top="+popY+",left="+popX);
}*/


//excel 다중 import popup
function leadExcelImportOpen()
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


//엑셀파일 insert
function leadExcelCheck()
{
  var excelFile = $("#excelFile").val();
  
  if (excelFile == "" || excelFile == null) 
  {
      alert("파일을 선택해주세요.");
      
      return false;
  } 
  else if (!leadCheckFileType(excelFile)) 
  {
      alert("엑셀 파일만 업로드 가능합니다.");
      
      return false;
  }
  if (confirm("업로드 하시겠습니까?")) 
  {
  	
  	var options = {
      		type	: 	'POST',
      		cache	: 	false,
      		url		: "/leadExcelUpload",
      		success	:	function(data) {
      			popupClose();
      			searchKeyword(1);
      			alert(data + " 건이 등록되었습니다.");
      		},
      		error	: function(data) {
      			alert("엑셀 업로드 중 에러가 발생했습니다.");
      			return;
      		}
      	};
      	$("#excelUploadForm").ajaxSubmit(options);
  	
  	/* 
  	$("#excelUploadForm").append(excelFile);
  	$("#excelUploadForm").submit();*/
	}
	
//	opener.parent.location.reload();
}

//엑셀 파일 추가 fucntion
function leadCheckFileType(filePath) 
{
	var fileFormat = filePath.split(".");
	
	if (fileFormat.indexOf("xlsx") > -1) {
		return true;
	} 
	else {
		return false;
	}

} 

//고객 Popup
function viewCustList(pageNum) {
	var ctx = $("#ctx").val();
	
	var s_cust_name = $("#s_cust_name").val();

	
	$.ajax({
		url: ctx + "/custPopListAjax", 
		type: "POST",  
		data: {
			custPopupPageNum     : pageNum,
			s_cust_name : s_cust_name 
		},
		dataType: "json",
		success: function(data) { 
			
			$("#custListTbody").empty();
			$("#s_cust_name").bind("keypress", function(event) {
				enterSearch(event);
			});
			
			if (data.custPopupList.length == 0) {
				var trElement = $("#custListTableHeader").clone().removeClass().empty();
				$("#custListTbody").append(trElement);
				$("#custListTbody tr:last").append("<td colspan='3' style='width:100%; height: 260px; cursor: default; background-color: white;' align='center'>검색 결과가 없습니다</td>");
			} else {
				console.log(data);
				$.each(data.custPopupList, function(i) {
					var trElement = $("#custListTableHeader").clone().removeClass().empty();
					var cust_no = this.cust_no;
					var cust_name = this.cust_name;

					trElement.bind("click", function(e) {
						setTimeout($.unblockUI, 0);
						$("#cust_no").val(cust_no);
						$("#cust_name").val(cust_name);
					});
					
					addMouseEvent(trElement);
					trElement.css("cursor", "pointer");
					
					$("#custListTbody").append(trElement);
					$("#custListTbody tr:last").append("<td width='60%'>" + cust_no + "</td>"
							+ "<td width='30%'>" + cust_name + "</td>");
				});
			} 
		// 페이징 그리기
			$("#custPopupPagingDiv").empty();
			var pageContent = "";
	
			console.log(data.pageNum);
			
			if(data.page.endPageNum == 0 || data.page.endPageNum == 1){
				pageContent = "◀ <input type='text' id='custPopupInput' readonly='readonly' value='1' style='width: 25px; text-align: center;'/> / 1 ▶";
			} else if(data.pageNum == data.page.startPageNum){
				pageContent = "<input type='hidden' id='custPageNum' value='"+data.pageNum+"'/><input type='hidden' id='custEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"◀ <input type='text' id='custPopupInput' value='"+data.page.startPageNum+"' onkeypress=\"custPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>" 
				+"<a onclick=\"viewCustList("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
				+"<a onclick=\"viewCustList("+(data.pageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
			} else if(data.pageNum == data.page.endPageNum){
				pageContent = "<input type='hidden' id='custPageNum' value='"+data.pageNum+"'/><input type='hidden' id='custEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"<a onclick=\"viewCustList("+(data.pageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
				+"<input type='text' id='custPopupInput' value='"+data.page.endPageNum+"' onkeypress=\"custPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
				+"<a> / "+data.page.endPageNum+"</a> ▶";
			} else {
				pageContent = "<input type='hidden' id='custPageNum' value='"+data.pageNum+"'/><input type='hidden' id='custEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"<a onclick=\"viewCustList("+(data.pageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
				+"<input type='text' id='custPopupInput' value='"+data.pageNum+"' onkeypress=\"custPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
				+"<a onclick=\"viewCustList("+data.pageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
				+"<a onclick=\"viewCustList("+(data.pageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
			}
			$("#custPopupPagingDiv").append(pageContent);
			
			
		},
		beforeSend: function(){
       	viewLoadingShow();			
       },
       complete:function(){
       	viewLoadingHide();	
       },
		error: function(data) { 
			alert("고객 목록을 취득하지 못했습니다.");
			return false;
		}
	});
}


//담당자 Popup
function viewEmpList(pageNum) {
	var ctx = $("#ctx").val();
	
	var s_emp_name = $("#s_emp_name").val();
	
	console.log(s_emp_name);
	
	$.ajax({
		url: ctx + "/empPopListAjax", 
		type: "POST",  
		data: { 
			pageNum     : pageNum,
			s_emp_name : s_emp_name 
		},
		dataType: "json",
		success: function(data) { 
			
			$("#empListTbody").empty();
			$("#s_emp_name").bind("keypress", function(event) {
				enterSearch(event);
			});
			
			if (data.empPopupList.length == 0) {
				var trElement = $("#empListTableHeader").clone().removeClass().empty();
				$("#empListTbody").append(trElement);
				$("#empListTbody tr:last").append("<td colspan='3' style='width:100%; height: 260px; cursor: default; background-color: white;' align='center'>검색 결과가 없습니다</td>");
			} else {
				console.log(data);
				$.each(data.empPopupList, function(i) {
					var trElement = $("#empListTableHeader").clone().removeClass().empty();
					var emp_no = this.user_no;
					var emp_name = this.user_nm;

					trElement.bind("click", function(e) {
						setTimeout($.unblockUI, 0);
						$("#emp_no").val(emp_no);
						$("#emp_name").val(emp_name);
					});
					
					addMouseEvent(trElement);
					trElement.css("cursor", "pointer");
					
					$("#empListTbody").append(trElement);
					$("#empListTbody tr:last").append("<td width='60%'>" + emp_no + "</td>"
							+ "<td width='30%'>" + emp_name + "</td>");
				});
			}
			
		// 페이징 그리기
			$("#empPopupPagingDiv").empty();
			var pageContent = "";
			
			if(data.page.endPageNum == 0 || data.page.endPageNum == 1){
				pageContent = "◀ <input type='text' id='empPopupInput' readonly='readonly' value='1' style='width: 25px; text-align: center;'/> / 1 ▶";
			} else if(data.pageNum == data.page.startPageNum){
				pageContent = "<input type='hidden' id='empPageNum' value='"+data.pageNum+"'/><input type='hidden' id='empEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"◀ <input type='text' id='empPopupInput' value='"+data.page.startPageNum+"' onkeypress=\"custPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>" 
				+"<a onclick=\"viewEmpList("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
				+"<a onclick=\"viewEmpList("+(data.pageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
			} else if(data.pageNum == data.page.endPageNum){
				pageContent = "<input type='hidden' id='empPageNum' value='"+data.pageNum+"'/><input type='hidden' id='empEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"<a onclick=\"viewEmpList("+(data.pageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
				+"<input type='text' id='empPopupInput' value='"+data.page.endPageNum+"' onkeypress=\"custPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
				+"<a> / "+data.page.endPageNum+"</a> ▶";
			} else {
				pageContent = "<input type='hidden' id='empPageNum' value='"+data.pageNum+"'/><input type='hidden' id='empEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"<a onclick=\"viewCustList("+(data.pageNum-1)+",2);\" id='pNum' style='cursor: pointer;'> ◀ </a>"
				+"<input type='text' id='empPopupInput' value='"+data.pageNum+"' onkeypress=\"custPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
				+"<a onclick=\"viewEmpList("+data.page.pageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
				+"<a onclick=\"viewEmpList("+(data.pageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
			}
			$("#empPopupPagingDiv").append(pageContent);
			
		},
		beforeSend: function(){
       	viewLoadingShow();			
       },
       complete:function(){
       	viewLoadingHide();	
       },
		error: function(data) { 
			alert("담당자목록을 취득하지 못했습니다.");
			return false;
		}
	});
}

//고객 서치 팝업 오픈
function custSchPopupOpen()
{
	// 팝업창 표시
	$.blockUI({ message: $('#custListModalDiv'),
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
	viewCustList('1');
}

//담당자 서치 팝
function empSchPopupOpen()
{
	// 팝업창 표시
	$.blockUI({ message: $('#empListModalDiv'),
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
	viewEmpList(1);
} 

//Popup 닫기
function popClose()
{
	$.unblockUI();
}
 


 