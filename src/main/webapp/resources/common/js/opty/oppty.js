/**
 * 
 * opptyDetail(oppty_no)			:: Detail 화면으로 이동.
 * opptySingleAddForm()				:: 단건등록 페이지 이동.
 * opptySchList()					:: 검색조건
 * 
 */

var ctx = $("#ctx").val();
var count = 0;

function ocustList(custPageNum)
{
	location.href = ctx + '/cust?custPageNum=' + custPageNum;
}

function ocustDetail(custNo)
{
	location.href = ctx + '/custForm?cust_no=' + custNo;
}

// 검색 조건 초기화
function opptySchReset()
{
	$("#oppty_no_srch").val("");
	$("#oppty_name_srch").val("");
	$("#cust_name_srch").val("");
	$("#emp_name_srch").val("");
	$("#oppty_status_cd_srch option:eq(0)").prop("selected", "selected");
	$("#oppty_stage_cd_srch option:eq(0)").prop("selected", "selected");
	$("#exp_close_dt_srch").val("");
	$("#dtype_cd_srch option:eq(0)").prop("selected", "selected");
	$("#purchase_type_srch option:eq(0)").prop("selected", "selected");
}

//검색 엔터키 기능
function opptyEnterSearch(event) {
	var keycode = (event.keyCode ? event.keyCode : event.which);
	
 	if (keycode == '13') {
		if ($("#oppty_no_srch").val() == '' && $("#oppty_name_srch").val() == '' && $("#cust_name_srch").val() == '' && $("#emp_name_srch").val() == ''  && $("#exp_close_dt_srch").val() == '' ) {
			alert("검색어를 입력하세요.")
			$("#cust_no").focus();
		} else {
			opptySchList(1);
		}
	}
	event.stopPropagation();
}

function opptyDetail(oppty_no, opptyPageNum)
{
	var hoppty_status_cd = $("#hoppty_status_cd").val();
	var cust_opty_no = $("#cust_opty_no").val();
	var page_type = $("#page_type").val();
	
	console.log(page_type);
	console.log(cust_opty_no);
	
	if(cust_opty_no == null || cust_opty_no == '')		// 영업기회
	{
		if(page_type == 0)
		{
//			console.log("A");
			location.href = ctx + "/oppty_detail?oppty_no=" + oppty_no + "&opptyPageNum=" + opptyPageNum + "&hoppty_status_cd=" + hoppty_status_cd + "&page_type=" + page_type;
		}
		else
		{
//			console.log("AB");
			location.href = ctx + "/oppty_detail?oppty_no=" + oppty_no + "&opptyPageNum=" + opptyPageNum + "&hoppty_status_cd=" + hoppty_status_cd + "&page_type=" + page_type;
		}
	}
	if(cust_opty_no != null && cust_opty_no != '')		// 고객에서 타고 들어오는 영업기회
		location.href = ctx + "/oppty_detail?oppty_no=" + oppty_no + "&opptyPageNum=" + opptyPageNum + "&cust_opty_no=" + cust_opty_no;
//		console.log("B");
	
}

// 단건 등록
function opptySingleAddForm()
{
	location.href = ctx + "/oppty_detail";
}

//Popup 닫기
function popupClose()
{
	$.unblockUI();
}

function opptySchList(opptyPageNum)
{
	var oppty_no_srch 	= $("#oppty_no_srch").val();
	var oppty_name_srch = $("#oppty_name_srch").val();
	var cust_name_srch  = $("#cust_name_srch").val();
	var emp_name_srch   = $("#emp_name_srch").val();
	var oppty_status_cd_srch = $("#oppty_status_cd_srch").val();
	var oppty_stage_cd_srch	 = $("#oppty_stage_cd_srch").val();
	var exp_close_dt_srch    = $("#exp_close_dt_srch").val();
	var dtype_cd_srch		 = $("#dtype_cd_srch").val();
	var purchase_type_srch   = $("#purchase_type_srch").val();
	var hoppty_status_cd 	 = $("#hoppty_status_cd").val();
	var session 			 = $("#session").val();
	var page_type 			 = $("#page_type").val();
	var cust_opty_no 		 = $("#cust_opty_no").val();
	var opptyData			 = null;
	
	console.log(page_type);
	console.log(hoppty_status_cd);

	if(page_type == 0)		// 전체페이지
	{
		opptyData = {opptyPageNum		 : opptyPageNum,
					 	 oppty_no_srch 		 : oppty_no_srch,
					 	 oppty_name_srch  	 : oppty_name_srch,
					 	 cust_name_srch		 : cust_name_srch,
					 	 emp_name_srch		 : emp_name_srch,
					 	 oppty_status_cd_srch : oppty_status_cd_srch,
					 	 oppty_stage_cd_srch  : oppty_stage_cd_srch,
					 	 exp_close_dt_srch 	 : exp_close_dt_srch,
					 	 dtype_cd_srch		 : dtype_cd_srch,
					 	 purchase_type_srch	 : purchase_type_srch,
					 	 hoppty_status_cd	 : hoppty_status_cd,
					 	 cust_opty_no		 : cust_opty_no
						};
		
	}
	else if(page_type == 1)		// my page
	{
		opptyData = {opptyPageNum		 : opptyPageNum,
			 	 oppty_no_srch 		 : oppty_no_srch,
			 	 oppty_name_srch  	 : oppty_name_srch,
			 	 cust_name_srch		 : cust_name_srch,
			 	 emp_name_srch		 : emp_name_srch,
			 	 oppty_status_cd_srch : oppty_status_cd_srch,
			 	 oppty_stage_cd_srch  : oppty_stage_cd_srch,
			 	 exp_close_dt_srch 	 : exp_close_dt_srch,
			 	 dtype_cd_srch		 : dtype_cd_srch,
			 	 purchase_type_srch	 : purchase_type_srch,
			 	 hoppty_status_cd	 : hoppty_status_cd,
			 	 session			 : session
				};
	}
	else if(page_type == 2)
	{
		opptyData = {opptyPageNum		 : opptyPageNum,
			 	 	 oppty_no_srch 		 : oppty_no_srch,
			 	 	 oppty_name_srch  	 : oppty_name_srch,
			 	 	 cust_name_srch		 : cust_name_srch,
			 	 	 emp_name_srch		 : emp_name_srch,
			 	 	 oppty_status_cd_srch : oppty_status_cd_srch,
			 	 	 oppty_stage_cd_srch  : oppty_stage_cd_srch,
			 	 	 exp_close_dt_srch 	 : exp_close_dt_srch,
			 	 	 dtype_cd_srch		 : dtype_cd_srch,
			 	 	 purchase_type_srch	 : purchase_type_srch,
			 	 	 hoppty_status_cd	 : hoppty_status_cd,
			 	 	 cust_opty_no		 : cust_opty_no
					};
	}
	
	var tbody = $('#oppty_list_tbody');
	var tbodyContent = "";
	
	$.ajax({
		url:ctx + '/oppty_sch',
		type: 'POST',
		data: opptyData,
		dataType:'json',
		success: function(data){
			tbody.children().remove();
			
			var size = data.srcList.length;
			
			if(size == 0) {
				tbodyContent = "<tr style='height: 75px;'><td colspan='13' style='width: 1320px; text-align: center;  vertical-align: middle;'>검색 결과가 없습니다.</td></tr>";
	    		tbody.append(tbodyContent);
			}
			else
			{
				for(var i=0; i<size; i++)
				{
					tbodyContent = "<tr>" +
					"<td style='text-align: left;' >" +data.srcList[i].oppty_no +"</td>" +
					"<td style='text-align: left;'>" +
					"<a onclick=opptyDetail('"+data.srcList[i].oppty_no+"','"+data.opptyPageNum+"'); id='"+data.srcList[i].oppty_no+"'>" + data.srcList[i].oppty_name+"</a></td>" +
					"<td style='text-align: left;'>" + data.srcList[i].cust_no +"</td>" +
					"<td style='text-align: left;'>" + data.srcList[i].cust_name +"</td>" +
					"<td style='text-align: left;'>" + data.srcList[i].cust_phone + "</td>" +
					"<td style='text-align: left;'>" + data.srcList[i].emp_name + "</td>" +
					"<td style='text-align: left;'>" + data.srcList[i].oppty_status_cd + "</td>" +
					"<td style='text-align: left;'>" + data.srcList[i].oppty_stage_cd + "</td>" +
					"<td style='text-align: left;'>" + data.srcList[i].exp_close_day + "</td>" +
					"<td style='text-align: left;'>" + data.srcList[i].dtype_cd + "</td>" +
					"<td style='text-align: left;'>" + data.srcList[i].purchase_type + "</td>" +
					"<td style='text-align: left;'>" + data.srcList[i].rec_per_cd + "</td>" +
					"<td style='text-align: left;'>" + data.srcList[i].create_date + "</td>" +
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
			} else if(data.opptyPageNum == data.page.startPageNum){
				pageContent = "<input type='hidden' id='opptyPageNum' value='"+data.opptyPageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"◀ <input type='text' id='pageInput' value='"+data.page.startPageNum+"' onkeypress=\"opptyPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>" 
				+"<a onclick=\"opptySchList("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
				+"<a onclick=\"opptySchList("+(data.opptyPageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
			} else if(data.opptyPageNum == data.page.endPageNum){
				pageContent = "<input type='hidden' id='opptyPageNum' value='"+data.opptyPageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"<a onclick=\"opptySchList("+(data.opptyPageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
				+"<input type='text' id='pageInput' value='"+data.page.endPageNum+"' onkeypress=\"opptyPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
				+"<a> / "+data.page.endPageNum+"</a> ▶";
			} else {
				pageContent = "<input type='hidden' id='opptyPageNum' value='"+data.opptyPageNum+"'/><input type='hidden' id='opptyEndPageNum' value='"+data.page.endPageNum+"'/>"
				+"<a onclick=\"opptySchList("+(data.opptyPageNum-1)+",2);\" id='pNum' style='cursor: pointer;'> ◀ </a>"
				+"<input type='text' id='pageInput' value='"+data.opptyPageNum+"' onkeypress=\"opptyPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
				+"<a onclick=\"opptySchList("+data.page.opptyPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
				+"<a onclick=\"opptySchList("+(data.opptyPageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
			}
			$(".pagingDiv").append(pageContent);
			
		},
		error: function(){
			alert("error");
		}
	});
}

//페이징 엔터키
function opptyPageNumInputEnter(event) {
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
			opptySchList(pageNum);
		}
	}
	event.stopPropagation();
}

//엑셀 Import 팝업	
//function opptyExcelImportOpen() 
//{
//	var popWidth  = '520'; // 파업사이즈 너비
//	var popHeight = '160'; // 팝업사이즈 높이
//	var winHeight = document.body.clientHeight;	// 현재창의 높이
//	var winWidth = document.body.clientWidth;	// 현재창의 너비
//	var winX = window.screenLeft;	// 현재창의 x좌표
//	var winY = window.screenTop;	// 현재창의 y좌표
//
//	var popX = winX + (winWidth - popWidth)/2;
//	var popY = winY + (winHeight - popHeight)/2;
//	var popUrl = "opptyExcelImportTab";
//	var popOption = "width=520, height=160, resize=no, scrollbars=no, status=no, location=no, directories=no; ,top=pop,left=popX";
//	window.open(popUrl, "_blank","width="+popWidth+"px,height="+popHeight+"px,top="+popY+",left="+popX);
//}

//popup
function opptyExcelImportOpen()
{
	// 팝업창 표시
	$.blockUI({ message: $('#opptyMultiInsertModalDiv'),
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
function opptyExcelCheck()
{
    var excelFile = $("#excelFile").val();
    
    if (excelFile == "" || excelFile == null) 
    {
        alert("파일을 선택해주세요.");
        return false;
    } 
    else if (!opptyCheckFileType(excelFile)) 
    {
        alert("엑셀 파일만 업로드 가능합니다.");
        return false;
    }
    if (confirm("업로드 하시겠습니까?")) 
    {
    	var options = {
    		type	: 	'POST',
    		cache	: 	false,
    		url		: 	ctx + "/opptyExcelUpload",
    		success	:	function(data) {
    			popupClose();
    			opptySchList(1);
    			alert(data + " 건이 등록되었습니다.");
    		},
    		error	: function(data) {
    			alert("엑셀 업로드 중 에러가 발생했습니다.");
    			return;
    		}
    	};
    	$("#excelUploadForm").ajaxSubmit(options);
//    	$("#excelUploadForm").append(excelFile);
//    	$("#excelUploadForm").submit();
	}
	
}

//엑셀 파일 추가 fucntion
function opptyCheckFileType(filePath) 
{
	var fileFormat = filePath.split(".");
	
	if (fileFormat.indexOf("xlsx") > -1) {
		return true;
	} 
//	if (fileFormat.indexOf("xls") > -1) {
//		return true;
//	} 
	else {
		return false;
	}

}

//엑셀 출력 적용 함수
function download_list_Excel(formID, flg) 
{
	var t = flg;
	var ctx = $("#ctx").val();
	var hoppty_status_cd = $("#hoppty_status_cd").val();
	var cust_opty_no = $("#cust_opty_no").val();
	var page = $("#page_type").val();
	
	var form = $("#"+formID);
	var excel = $('<input type="hidden" value="true" name="excel">');
	var flg = $("<input type='hidden' value='"+ flg +"' name='flg'>");
	var page_type = page_type = $("<input type='hidden' value='"+ page +"' name='page_type'>");
	
	console.log(page);
	
//	if(count == 0)
//	{
//		page_type = $("<input type='hidden' value='"+ page +"' name='page_type'>");
//	}
//	else
//	{
//		page_type = null;
//		page_type = $("<input type='hidden' value='"+ page +"' name='page_type'>");
//	}
	
	if(hoppty_status_cd != null && hoppty_status_cd != '')
	{
		var oppty_status_cd = $("<input type='hidden' value='"+ hoppty_status_cd +"' name='hoppty_status_cd'>");
		form.append(oppty_status_cd);
		
		console.log("A");
		console.log(oppty_status_cd);
		console.log(hoppty_status_cd);
	}
	if(cust_opty_no != null && cust_opty_no != '')
	{
		var cust_opty_no = $("<input type='hidden' value='"+ cust_opty_no +"' name='cust_opty_no'>");
		form.append(cust_opty_no);
		
		console.log("BA");
	}
	
	if(t == 0)
	{
		if(confirm("엑셀로 출력하시겠습니까? 대량의 경우 대기시간이 필요합니다.")) 
		{
			form.append(excel);
			form.append(flg);
			form.append(page_type);
			form.attr("action", "/toOpptyExcel");
			form.submit();
		} 
		$("input[name=excel]").val("");
	}
	else if(t == 1)
	{
		form.append(excel);
		form.append(flg);
		form.attr("action", "/toOpptyExcel");
		form.submit();
		
		$("input[name=excel]").val("");
	}
}


