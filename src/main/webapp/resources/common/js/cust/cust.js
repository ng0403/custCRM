/**
* 함수 목록
* searchKeyword()                       : 고객 조회
* cust_add() 							: 고객 단건 추가
* custDetail(a)							: 고객 상세정보
* cust_add_save()						: 고객 저장
* cust_modify_save()					: 고객 수정 
* cust_cancel()							: 고객 취소버튼(리스트로 이동)
* cust_phone_add(cust_no)				: 테이블 행 추가
* cust_remove() 						: 테이블 행 삭제
* cust_delete()							: 고객 삭제
* 
*/

var ctx = $("#ctx").val();
var count = 0;

$(document).ready(function(){
	$(document).on( 'click','.phone_primary_yn', function(event) {
		$(".phone_primary_yn").prop("checked", false);
		$(this).prop("checked", true);
		
	});
	
	$(document).on( 'click','.addr_primary_yn',function(event) {
		$(".addr_primary_yn").prop("checked", false);
		$(this).prop("checked", true);
	});	
	
	// 주민번호 숫자만 입력
//	$("#resident_no").keyup(function(event) {
//		
//		if(event.keyCode < 48 || event.keyCode > 57) {
//			alert("숫자만 입력 가능합니다.");
//			this.value = this.value.replace(/[^0-9]/g,'');
//			return false;
//		}
//	});
});

//체크박스 전체 선택.
function custPhoneAllChk()
{
	var checkbox1=$('#custP_form_tbl tbody').find('input[type=checkbox]');
	
	if($('#custPhoneChk').is(":checked")){
		$(checkbox1).prop("checked", true);
	}else{
		$(checkbox1).prop("checked", false);
	}
}

//체크박스 전체 선택.
function custAddrAllChk()
{
	var checkbox2=$('#custA_form_tbl tbody').find('input[type=checkbox]');
	
	if($('#custAddrChk').is(":checked")){
		$(checkbox2).prop("checked", true);
	}else{
		$(checkbox2).prop("checked", false);
	}
}

//취소버튼 
function cust_cancel(custPageNum)
{
	if(confirm("취소하시겠습니까?"))
	{
		alert("고객 리스트 화면으로 이동합니다.");
		location.href="/cust?custPageNum=" + custPageNum;
	}
	else
		return false;
	
} 

// 특수문자 예외처리
function wordch(thisword)
{
	console.log(thisword);
	
	var flag = true;
	var specialChars="~`!@#$%^&*-=+\|[](){};:'<.,>/?_";

	wordadded = thisword;

	for(i=0; i<wordadded.length; i++) 
	{
		for (j = 0; j < specialChars.length; j++) 
		{         
			if (wordadded.charAt(i) == specialChars.charAt(j))
			{
				flag = false;
				break;
	         }
	     }
	  }
	return flag;
}

function custEmpSchPopupOpen()
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
	
	$("#pageNum").val("1");
	
	// list 불러오는 함수.
	viewEmpList(1);
}

//Popup 닫기
function popupClose()
{
	$.unblockUI();
}

// 검색 초기화 버튼
function custSchReset()
{
	$("#cust_no").val("");
	$("#cust_name").val("");
	$("#chart_no").val("");
	$("#visit_cd option:eq(0)").prop("selected", "selected");
	$("#rec_per").val("");
	$("#phone_no").val("");
}

//고객 상세 초기화 버튼
function cust_reset() 
{
	if(confirm("입력한 정보를 지우겠습니까?"))
	{
		$("#cust_name").val("");
		$("#resident_no").val("");
		$("#chart_no").val("");
		$("#cust_id").val("");
		$("#rec_per").val("");
		$("#reason_cd").val("");
		$("#remark_cn").val("");
		$("#email_id").val("");
		$("#email_dm").val("");
		
		$("#discount_cost option:eq(0)").prop("selected", "selected");
		$("#visit_cd option:eq(0)").prop("selected", "selected");
		$("#visit_dtl_cd option:eq(0)").prop("selected", "selected");
		$("#purchase_type_sel option:eq(0)").prop("selected", "selected");
		$("#payment_cd_sel option:eq(0)").prop("selected", "selected");
		$("#rec_per_cd_sel option:eq(0)").prop("selected", "selected");
	}
	else
		return false;
		
}

//검색 엔터키 기능
function custEnterSearch(event) {
	var keycode = (event.keyCode ? event.keyCode : event.which);
	
 	if (keycode == '13') {
		if ($("#cust_no").val() == '' && $("#cust_name").val() == '' && $("#chart_no").val() == '' && $("#rec_per").val() == ''  && $("#phone_no").val() == '' ) {
			alert("검색어를 입력하세요.")
			$("#cust_no").focus();
		} else {
			searchKeyword(1);
		}
	}
	event.stopPropagation();
}


function custList(custPageNum)
{
	var page_type = $("#page_type").val();
	if(page_type == 0)
	{
		location.href = ctx + '/cust?custPageNum=' + custPageNum;
	}
	else if(page_type == 1)
	{
		location.href = ctx + '/my_cust?custPageNum=' + custPageNum;
	}
}

// 고객 삭제
function custDelete()
{
	 console.log($("#cust_no").val());
	 
	$(document).ready(function() {
		var ynChk = confirm("해당 고객을 삭제하시겠습니까?");
		if(ynChk)
		{
			$.ajax({
				type : 'POST',
				url : ctx + '/cust_delete',
				data : {
					cust_no 		: $("#cust_no").val()
				},
				dataType : "json",
				success : function(data) {
					alert("고객이 삭제되었습니다.");
					alert("고객 리스트로 이동합니다.");
						
					location.href = ctx + '/cust';
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
	});
	 
}

// 고객 조회
function searchKeyword(pageNum)
{
	var cust_no  = $("#cust_no").val();
	var cust_name = $("#cust_name").val();
 	var chart_no = $("#chart_no").val();
	var visit_cd = $("#visit_cd").val();
	var rec_per  = $("#rec_per").val();
	var phone_no = $("#phone_no").val();
	var user_id  = $("#session").val();
	
	console.log(user_id);
	
	var custData = { 
			 		 "custPageNum" : pageNum,
					 "cust_no": cust_no, 
					 "cust_name": cust_name,
					 "chart_no": chart_no, 
					 "visit_cd":visit_cd, 
					 "rec_per":rec_per,
					 "phone_no" : phone_no,
					 "session"	: user_id
					};
 
	
	var tbody = $('#cust_list_tbody');
	var tbodyContent = "";
		
	$.ajax({
				url: ctx + '/custAjax',
				type: 'POST',
				data: custData,
				dataType:'json',
				success: function(data){
					tbody.children().remove(); 
					
					if(data.custList.length == 0) {
						tbodyContent = "<tr style='height: 75px;'><td colspan=9' style='width: 1320px; text-align: center;  vertical-align: middle;'>검색 결과가 없습니다.</td></tr>";
			    		tbody.append(tbodyContent);
					}
					else {
						for(var i=0; i<data.custList.length; i++)
						{
							var cust_no   = data.custList[i].cust_no;
							var cust_name = data.custList[i].cust_name;
							var chart_no  = data.custList[i].chart_no;
							var cust_id	  = data.custList[i].cust_id;
							var rec_per   = data.custList[i].rec_per;
							var phone_no  = data.custList[i].phone_no;
							var main_address = data.custList[i].main_address;
							var visit_dtl_cd = data.custList[i].visit_dtl_cd;
							var create_date  = data.custList[i].create_date;
							
							var vititCdList_contents = '';
							
							for(var j=0;j < vititCdList.length; j++)
							{
								if(vititCdList[j] == data.custList[i].visit_cd){
									vititCdList_contents = vititCdList[++j];
								}
							}
							
							var vititDtlCdList_contents = '';
							for(var j=0;j < vititDtlCdList.length; j++)
							{
								if(vititDtlCdList[j] == data.custList[i].visit_dtl_cd){
									vititDtlCdList_contents = vititDtlCdList[++j];
								}
							}
							
							tbodyContent = "<tr>" +
							"<td style='text-align: left;' >" + data.custList[i].cust_no +"</td>" +
							"<td style='text-align: left;'>" +
							"<a href='#' onclick=custDetail('"+data.custList[i].cust_no+"','"+data.pageNum+"'); id='"+data.custList[i].cust_no+"'>" + data.custList[i].cust_name+"</a></td>" +
							"<td style='text-align: left;'>" + data.custList[i].chart_no +"</td>" +
							"<td style='text-align: left;' > " + vititCdList_contents +
							"</td>" +
							"<td style='text-align: left;' > " + vititDtlCdList_contents +
							"</td>" +
							"<td style='text-align: left;'>" + data.custList[i].rec_per + "</td>" +
							"<td style='text-align: left;'>" + data.custList[i].phone_area_no + data.custList[i].phone_no + "</td>" +
							"<td style='text-align: left;'>" + data.custList[i].main_address + "</td>" +
							"<td style='text-align: left;'>" + data.custList[i].create_date + "</td>" +
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
 					} else if(data.pageNum == data.page.startPageNum){
 						pageContent = "<input type='hidden' id='custPageNum' value='"+data.pageNum+"'/><input type='hidden' id='custEndPageNum' value='"+data.page.endPageNum+"'/>"
 						+"◀ <input type='text' id='pageInput' value='"+data.page.startPageNum+"' onkeypress=\"custPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>" 
 						+"<a onclick=\"searchKeyword("+data.page.endPageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
 						+"<a onclick=\"searchKeyword("+(data.pageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
 					} else if(data.pageNum == data.page.endPageNum){
 						pageContent = "<input type='hidden' id='custPageNum' value='"+data.pageNum+"'/><input type='hidden' id='custEndPageNum' value='"+data.page.endPageNum+"'/>"
 						+"<a onclick=\"searchKeyword("+(data.pageNum-1)+");\" id='pNum' style='cursor: pointer;'> ◀ </a>"
 						+"<input type='text' id='pageInput' value='"+data.page.endPageNum+"' onkeypress=\"custPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
 						+"<a> / "+data.page.endPageNum+"</a> ▶";
 					} else {
 						pageContent = "<input type='hidden' id='custPageNum' value='"+data.pageNum+"'/><input type='hidden' id='custEndPageNum' value='"+data.page.endPageNum+"'/>"
 						+"<a onclick=\"searchKeyword("+(data.pageNum-1)+",2);\" id='pNum' style='cursor: pointer;'> ◀ </a>"
 						+"<input type='text' id='pageInput' value='"+data.pageNum+"' onkeypress=\"custPageNumInputEnter(event);\" style='width: 25px; text-align: center;'/>"
 						+"<a onclick=\"searchKeyword("+data.page.pageNum+");\" id='pNum' style='cursor: pointer;'> / "+data.page.endPageNum+"</a>"
 						+"<a onclick=\"searchKeyword("+(data.pageNum+1)+");\" id='pNum' style='cursor: pointer;'> ▶ </a>";
 					}
 					$(".pagingDiv").append(pageContent);
				},
				error: function(){
					alert("error");
				}
			});
}

//페이징 엔터키
function custPageNumInputEnter(event) {
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

 // 고객 단건 추가
function cust_add(){
	location.href="/custForm?cust_no=";
}
 
// 고객 상세정보
 function custDetail(a, custPageNum) {
   var no = a;
   var page_type = $("#page_type").val();
   
   console.log(page_type);
   
   if(page_type == 0)
	   location.href=ctx + "/custForm?cust_no=" + no + "&custPageNum=" + custPageNum + "&page_type=" + page_type;
   else if(page_type == 1)
	   location.href=ctx + "/custForm?cust_no=" + no + "&custPageNum=" + custPageNum + "&page_type=" + page_type;
	   
   $("#cust_phone").css("display", "block");
 }
 
// 고객 저장
function cust_add_save() 
{
	if($("#cust_name").val() == null || $("#cust_name").val() == "")
	{
		alert("고객명을 입력하세요.");
		return false;
	}
	if($("#visit_cd").val() == null || $("#visit_cd").val() == "")
	{
		alert("내원경로를 선택해주세요.");
		return false;
	}
	if($("#visit_dtl_cd").val() == null || $("#visit_dtl_cd").val() == "")
	{
		alert("내원경로상세를 선택해주세요.");
		return false;
	}
	
	console.log($("#email_id").val());
	 
	$(document).ready(function() {
		var cust_no = $("#cust_no").val();
		var ynChk = confirm("해당 고객을 저장하시겠습니까?");
		if(ynChk){
			$.ajax({
				type : 'POST',
				url : ctx + '/custSave',
				data : {
					cust_no 		: cust_no,
					cust_name 		: $("#cust_name").val(),
					resident_no		: $("#resident_no").val(),
					chart_no		: $("#chart_no").val(),
					emp_no			: $("#emp_no").val(),
					cust_id			: $("#cust_id").val(),
					visit_cd		: $("#visit_cd").val(),
					visit_dtl_cd 	: $("#visit_dtl_cd ").val(),
					visit_cn		: $("#visit_cn").val(),
					rec_per			: $("#rec_per").val(),
					remark_cn		: $("#remark_cn").val(),
					cust_type		: $("#cust_type").val(),
					cust_rank		: $("#cust_rank").val(),
					email_id		: $("#email_id").val(),
					email_dm		: $("#email_dm").val()
				},
				dataType : "json",
				success : function(data) {
					console.log(data);
					$("#cust_no").val(data.cust_no);
					alert("고객이 저장되었습니다.");
					alert("고객 리스트로 이동합니다.");
					location.href = ctx + '/cust';
				}, error : function(request,status,error) {
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		} else {
			alert("취소되었습니다.");
		}
	});
 }
 
// 편집 눌렀을 때
 function cust_modify()
 {
	 if($("#cust_single_modify").val() == "편집")
	{
		$("#cust_single_modify").val("저장");
		$("#cust_single_modify").removeClass("func_btn");
		$("#cust_single_modify").addClass("tr_btn");

		$("#cust_name").prop("readonly", false);
		$("#chart_no").prop("readonly", false);
		$("#cust_id").prop("readonly", false);
		$("#remark_cn").prop("readonly", false);
		$("#visit_cn").prop("readonly", false);
		$("#rec_per").prop("readonly", false);
		$("#email_id").prop("readonly", false);
		$("#email_dm").prop("readonly", false);

		$("#empSchBtn").prop("disabled", false);
		$("#cust_type").prop("disabled", false);
		$("#cust_rank").prop("disabled", false);
		$("#visit_cd").prop("disabled", false);
		$("#visit_dtl_cd").prop("disabled", false);
		
		return false;
	}
	if($("#cust_single_modify").val() == "저장")
	{
		cust_modify_save();
	}
	 
 }
 
// 고객 수정
function cust_modify_save() 
{
	if($("#cust_name").val() == null || $("#cust_name").val() == "")
	{
		alert("고객명을 입력하세요.");
		return false;
	}
	if($("#visit_cd").val() == null || $("#visit_cd").val() == "")
	{
		alert("내원경로를 선택해주세요.");
		return false;
	}
	if($("#visit_dtl_cd").val() == null || $("#visit_dtl_cd").val() == "")
	{
		alert("내원경로상세를 선택해주세요.");
		return false;
	}
	
	console.log($("#email_id").val());
	console.log($("#email_dm").val());
	
	$(document).ready(function() {
		var cust_no = $("#cust_no").val();
		var ynChk = confirm("해당 고객을 수정하시겠습니까?");
		if(ynChk){
			$.ajax({
				type : 'POST',
				url : ctx + '/custSave',
				data : {
					cust_no 		: cust_no,
					cust_name 		: $("#cust_name").val(),
//					resident_no		: $("#resident_no").val(),
					chart_no		: $("#chart_no").val(),
					cust_id			: $("#cust_id").val(),
					cust_type       : $("#cust_type").val(),
					cust_rank		: $("#cust_rank").val(),
					visit_cd		: $("#visit_cd").val(),
					visit_dtl_cd 	: $("#visit_dtl_cd ").val(),
					rec_per			: $("#rec_per").val(),
					emp_no			: $("#emp_no").val(),	// 여기
					visit_cn		: $("#visit_cn").val(),
					remark_cn		: $("#remark_cn").val(),
					email_id		: $("#email_id").val(),
					email_dm		: $("#email_dm").val()
				},
				dataType : "json",
				success : function(data) {
					alert("고객이 수정되었습니다.");
					alert("고객 리스트로 이동합니다.");
					
					location.href = ctx + "/cust";
				}, error : function(request,status,error) {
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		} else {
			alert("취소되었습니다.");
		}
	});
 }
 
 // 테이블 행 추가
function cust_phone_add() {
	var cust_no = $("#cust_no").val();
	
	console.log(cust_no);
	
    var tbody = $('#table_tbody');
    var phoneTypeCdList_contents = '';
    var phoneCountryCdList_contents = '';

    for(var i=0;i < phoneTypeCdList.length; i++){
    	phoneTypeCdList_contents += "<option value='"+phoneTypeCdList[i]+"'>"+phoneTypeCdList[++i]+"</option>";
	}
    
    for(var i=0;i < phoneCountryCdList.length; i++){
    	phoneCountryCdList_contents += "<option value='"+phoneCountryCdList[i]+"'>"+phoneCountryCdList[++i]+"</option>";
	}
	var tbodyContent = "<tr>"+
							"<td style='width: 2%; text-align: center;'><input type='checkbox' class='del_chk' name='del_chk'></td>" +
							"<td>" +
								"<select id='phone_type_cd' name='phone_type_cd'" +
									"style='margin-left: 0; width: 90%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;'>" +
									"<option value=''>선택</option>"+ 
									phoneTypeCdList_contents + 
								"</select>"+
							"</td>"+
							"<td>"+
								"<select id='phone_country_cd' name='phone_country_cd' " + 
										"style='margin-left: 0; width: 90%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;'>" +
									"<option value=''>선택</option>" +
									phoneCountryCdList_contents +
								"</select>" +
							"</td>"+
							
							"<td>"+
								"<input type='text' id='phone_area_no' name='phone_area_no' style='width: 90%;'> " +
							"</td>" +
							"<td>"+
								"<input type='text' id='phone_no' name='phone_no' style='width: 90%;'>" + 
							"</td>" +
							"<td style='width: 2%; text-align: center;'>" +
								"<input id='custPhoneChk' class='phone_primary_yn' type='checkbox' />" +
							"</td>" +

						"</tr>";
	
	// 새로 그려준다.
	tbody.append(tbodyContent);
    
}

// 테이블 행 삭제
function cust_remove()
{
	var checkbox=$('#custP_form_tbl tbody').find('input[type=checkbox]:checked');	// 체크된 체크박스를 담는다.
	var delTr = checkbox.parent().parent();											// 체크된 체크박스의 tr을 담는다.
	
	console.log(delTr);
	delTr.remove();
	
	var checkbox1=$('#custP_form_tbl thead').find('input[type=checkbox]');
	
	if($('#custPhoneChk').is(":checked")){
		$(checkbox1).prop("checked", false);
	}
	
//	var table_tbody = document.getElementById('table_tbody');
}

// 전화번호 등록
function cust_phone_save()
{
	var cust_no = $("#cust_no").val();
	var phone_type_cd = [];
	var country_cd	  = [];
	var phone_area_no = [];
	var phone_no      = [];
	var primary_yn;
	var custPlist     = [];
	
	var tbody = $("#table_tbody");
	var tbodyContent = "";
	
	$("#table_tbody tr").each(function() {
		console.log($(this).children().eq(5).children().eq(0).prop('checked'));
		var tmp = $(this).children().eq(5).children().eq(0).prop('checked');
		
		if(tmp == true)
			primary_yn ="Y";
		else
			primary_yn= "N";
			
		console.log($(this).children().eq(1).children().eq(0).val());
		phone_type_cd.push($(this).children().eq(1).children().eq(0).val());
		country_cd.push($(this).children().eq(2).children().eq(0).val());
		phone_area_no.push($(this).children().eq(3).children().eq(0).val());
		phone_no.push($(this).children().eq(4).children().eq(0).val());
		
		custPlist.push(phone_type_cd.pop());
		custPlist.push(country_cd.pop());
		custPlist.push(phone_area_no.pop());
		custPlist.push(phone_no.pop());
		custPlist.push(primary_yn);
		
	});
	console.log(custPlist);
	$.ajax({
		url : ctx + '/custPhoneSave',
		type : 'POST',
		dataType : 'json',
		data : {
			cust_no : cust_no,
			custPlist : custPlist
		},
		success:function(data){
			console.log(data);
			tbody.children().remove();
			alert("전화번호가 등록되었습니다.");
			
			var size = data.length;
			
			for(var i=0; i<size; i++)
			{
				var phoneTypeCdList_contents = '';
			    var phoneCountryCdList_contents = '';
			    
				for(var j=0;j < phoneTypeCdList.length; j++)
				{
					if(phoneTypeCdList[j] == data[i].phone_type_cd)
						phoneTypeCdList_contents += "<option value='"+phoneTypeCdList[j]+"' selected='selected'>"+phoneTypeCdList[++j]+"</option>";
					else
						phoneTypeCdList_contents += "<option value='"+phoneTypeCdList[j]+"'>"+phoneTypeCdList[++j]+"</option>";
				}
			    
			    for(var k=0;k < phoneCountryCdList.length; k++)
			    {
			    	if(phoneCountryCdList[k] == data[i].phone_country_cd)
			    		phoneCountryCdList_contents += "<option value='"+phoneCountryCdList[k]+"' selected='selected'>"+phoneCountryCdList[++k]+"</option>";
			    	else
			    		phoneCountryCdList_contents += "<option value='"+phoneCountryCdList[k]+"'>"+phoneCountryCdList[++k]+"</option>";
				}
			    
				tbodyContent = "<tr>"+
					"<td style='width: 2%; text-align: center;'><input type='checkbox' class='del_chk' name='del_chk'></td>" +
					"<td>" +
						"<select id='phone_type_cd' name='phone_type_cd'" +
							"style='margin-left: 0; width: 90%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;'>" +
							"<option value=''>선택</option>"+ 
							phoneTypeCdList_contents + 
							"</select>"+
					"</td>"+
					"<td>"+
						"<select id='phone_country_cd' name='phone_country_cd' " + 
							"style='margin-left: 0; width: 90%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;'>" +
							"<option value=''>선택</option>" +
							phoneCountryCdList_contents +
							"</select>" +
					"</td>"+
					"<td>"+
						"<input type='text' id='phone_area_no' name='phone_area_no' style='width: 90%;' value='"+data[i].phone_area_no+"'> " +
					"</td>" +
					"<td>"+
						"<input type='text' id='phone_no' name='phone_no' style='width: 90%;' value='"+data[i].phone_no+"'> " + 
					"</td>";
				
					if(data[i].primary_yn == "Y")
					{
						tbodyContent += "<td style='width: 2%; text-align: center;'>" +
						"<input id='custPhoneChk' class='phone_primary_yn' type='checkbox' checked='checked'/>" +
						"</td>" +
						"</tr>";
					}
					else
					{
						tbodyContent += "<td style='width: 2%; text-align: center;'>" +
						"<input id='custPhoneChk' class='phone_primary_yn' type='checkbox'" +
						"</td>" +
						"</tr>";
					}
					
				tbody.append(tbodyContent);
			}
		},
		error:function(request){
			alert("error : " + request.status)
		}
	});
}

// 우편 테이블 행 추가
function cust_address_add()
{
	var cust_no = $("#cust_no").val();
	console.log(cust_no);

	var tbody = $('#tableAddr_tbody');
	var addrTypeCdList_contents = '';

	for(var i=0;i < addrTypeCdList.length; i++)
	{
		addrTypeCdList_contents += "<option value='"+addrTypeCdList[i]+"'>"+phoneTypeCdList[++i]+"</option>";
	}

	var tbodyContent = "<tr>"+
							"<td style='width: 2%; text-align: center;'><input type='checkbox' class='del_chk' name='del_chk'></td>" +
							"<td>" +
								"<select id='addr_type_cd' name='addr_type_cd'" +
									"style='margin-left: 0; width: 90%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;'>" +
									"<option value=''>선택</option>"+ 
									addrTypeCdList_contents + 
								"</select>"+
							"</td>"+
							"<td>"+
								"<input type='text' id='postcodify_search' class='postcodify postcodify_postcode5 postcodify_search_button' name='zip_no' style='width: 90%;'> " +
							"</td>" +
							"<td>"+
								"<input type='text' id='main_address"+count+"' class='postcodify postcodify_address' name='main_address"+count+"' style='width: 90%;'> " + 
							"</td>" +
							"<td>"+
							"<input type='text' id='detail_address' class='postcodify postcodify_details' name='detail_address' style='width: 90%;'> " + 
							"</td>" +
							"<td style='width: 2%; text-align: center;'>" +
								"<input id='custAddrChk' class='addr_primary_yn' type='checkbox' />" +
							"</td>" +
						"</tr>";
	count++;
	// 새로 그려준다.
	tbody.append(tbodyContent);
	
	
	$(".postcodify_search_button").postcodifyPopUp();
}

//테이블 행 삭제
function custAddr_remove() {
	
	var checkbox=$('#custA_form_tbl tbody').find('input[type=checkbox]:checked');	// 체크된 체크박스를 담는다.
	var delTr = checkbox.parent().parent();											// 체크된 체크박스의 tr을 담는다.
	
	delTr.remove();
	
	var checkbox2=$('#custA_form_tbl thead').find('input[type=checkbox]');
	
	if($('#custAddrChk').is(":checked")){
		$(checkbox2).prop("checked", false);
	}
	
//	var table_tbody = document.getElementById('tableAddr_tbody');
}

//function cust_addr_save(cust_no)
function cust_addr_save()
{
	var cust_no = $("#cust_no").val();
	var addr_type_cd   = [];
	var zip_no	  	   = [];
	var main_address   = [];
	var detail_address = [];
	var primary_yn;
	var custAlist      = [];
	
	var checkbox=$('#custP_form_tbl tbody').find('input[type=checkbox]:checked');	// 체크된 체크박스를 담는다.
	var delTr = checkbox.parent().parent();
	
	var tbody = $("#tableAddr_tbody");
	var tbodyContent = "";
	
	$("#tableAddr_tbody tr").each(function() {
		var tmp = $(this).children().eq(5).children().eq(0).prop('checked');
		
		if(tmp == true)
			primary_yn ="Y";
		else
			primary_yn= "N";
		
		addr_type_cd.push($(this).children().eq(1).children().eq(0).val());
		zip_no.push($(this).children().eq(2).children().eq(0).val());
		main_address.push($(this).children().eq(3).children().eq(0).val());
		detail_address.push($(this).children().eq(4).children().eq(0).val());
		
		custAlist.push(addr_type_cd.pop());
		custAlist.push(zip_no.pop());
		custAlist.push(main_address.pop());
		custAlist.push(detail_address.pop());
		custAlist.push(primary_yn);
	});
	
	console.log(cust_no);
	console.log(custAlist);
	
	$.ajax({
		url : ctx + '/custAddrSave',
		type : 'POST',
		dataType : 'json',
		data : {
			cust_no : cust_no,
			custAlist : custAlist
		},
		success:function(data){
			console.log(data);
			tbody.children().remove();
			
			alert("우편번호가 등록되었습니다.");
			
			var size = data.length;
			
			for(var i=0; i<size; i++)
			{
				var addrTypeCdList_contents = '';

				for(var j=0; j < addrTypeCdList.length; j++)
				{
					if(addrTypeCdList[j] == data[i].addr_type_cd)
						addrTypeCdList_contents += "<option value='"+addrTypeCdList[j]+"' selected='selected'>"+phoneTypeCdList[++j]+"</option>";
					else
						addrTypeCdList_contents += "<option value='"+addrTypeCdList[j]+"'>"+phoneTypeCdList[++j]+"</option>";
					
				}
			    
				tbodyContent = "<tr>"+
					"<td style='width: 2%; text-align: center;'><input type='checkbox' class='del_chk' name='del_chk'></td>" +
					"<td>" +
						"<select id='addr_type_cd' name='addr_type_cd'" +
							"style='margin-left: 0; width: 90%; text-align: center; font-size: 10.5px; padding: 0.3em 0.3em;'>" +
							"<option value=''>선택</option>"+ 
							addrTypeCdList_contents + 
							"</select>"+
					"</td>"+
					"<td>"+
						"<input type='text' id='zip_no' name='zip_no' style='width: 90%;' value='"+data[i].zip_no+"'> " +
					"</td>" +
					"<td>"+
						"<input type='text' id='main_address' name='main_address' style='width: 90%;' value='"+data[i].main_address+"'> " + 
					"</td>" +
					"<td>"+
						"<input type='text' id='detail_address' name='detail_address' style='width: 90%;' value='"+data[i].detail_address+"'> " + 
					"</td>";
				
					if(data[i].primary_yn == "Y")
					{
						tbodyContent += "<td style='width: 2%; text-align: center;'>" +
						"<input id='custAddrChk' class='addr_primary_yn' type='checkbox' checked='checked'/>" +
						"</td>" +
						"</tr>";
					}
					else
					{
						tbodyContent += "<td style='width: 2%; text-align: center;'>" +
						"<input id='custAddrChk' class='addr_primary_yn' type='checkbox'" +
						"</td>" +
						"</tr>";
					}
				tbody.append(tbodyContent);
			}
		},
		error:function(request){
			alert("error : " + request.status)
		}
		
	});
}

/*  Excel  */
//엑셀 Import 팝업	
function excelImportOpen() 
{
	// 팝업창 표시
	$.blockUI({ message: $('#custMultiInsertModalDiv'),
    	css: { 
    	'left': '65%',
    	'top': '50%',
    	'margin-left': '-400px',
    	'margin-top': '-250px',
    	'width': '400px',
    	'height': '250px',
    	'cursor': 'default'
    	}
		,onOverlayClick : $.unblockUI
	});
}

//엑셀파일 insert
function check()
{
    var excelFile = $("#excelFile").val();
    
    if (excelFile == "" || excelFile == null) 
    {
        alert("파일을 선택해주세요.");
        
        return false;
    } 
    else if (!checkFileType(excelFile)) 
    {
        alert("엑셀 파일만 업로드 가능합니다.");
        
        return false;
    }
    if (confirm("업로드 하시겠습니까?")) 
    {
    	var options = {
        		type	: 	'POST',
        		cache	: 	false,
        		url		: 	ctx + "/custExcelUpload",
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
	}
	
}

//엑셀 파일 추가 fucntion
function checkFileType(filePath) 
{
	var fileFormat = filePath.split(".");
	
	if (fileFormat.indexOf("xlsx") > -1) {
		return true;
	} 
	else {
		return false;
	}

}


//엑셀 출력 적용 함수
function download_list_Excel(formID, flg) 
{
	var t = flg;
	var ctx = $("#ctx").val();
	var page = $("#page_type").val();
	
	var form = $("#"+formID);
	var excel = $('<input type="hidden" value="true" name="excel">');
	var flg = $("<input type='hidden' value='"+ flg +"' name='flg'>");
	var page_type = $("<input type='hidden' value='"+ page +"' name='page_type'>");
	
	console.log(t);
	
	if(t == 0)
	{
		if(confirm("엑셀로 출력하시겠습니까? 대량의 경우 대기시간이 필요합니다.")) 
		{
			form.append(excel);
			form.append(flg);
			form.append(page_type);
			form.attr("action", "/toCustExcel");
			form.submit();

		} 
		$("input[name=excel]").val("");
	}
	else if(t == 1)
	{

		form.append(excel);
		form.append(flg);
		form.attr("action", "/toCustExcel");
		form.submit();

		$("input[name=excel]").val("");
	}
	
}

// 재욱
function cust_opty_btn(cust_no)
{
	console.log(cust_no);
	
	location.href = "/oppty?cust_opty_no=" + cust_no;
}

function cust_lead_btn(cust_no)
{
	console.log(cust_no);
	
	location.href = "/lead?cust_lead_no=" + cust_no;
}

function cust_task_btn(cust_no)
{
	console.log(cust_no);
	
	location.href = "/task?cust_task_no=" + cust_no;
}

//담당자 Popup
function viewEmpList(empPopupPageNum) {
	var ctx = $("#ctx").val();
	
	var s_emp_name = $("#s_emp_name").val();
	
	console.log(s_emp_name);
	
	$.ajax({
		url: ctx + "/custEmpListAjax", 
		type: "POST",  
		data: { 
			empPopupPageNum : empPopupPageNum,
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
				$.each(data.empPopupList, function(i) {
					console.log(this.user_no);
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
			console.log(data);
			
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





