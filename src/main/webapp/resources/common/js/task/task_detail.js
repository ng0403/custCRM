/** 
* task_add_save()						: 상담 저장
* task_modify_btn() 					: 상담 편집버튼 클릭 시 disabled  해제
* task_modify_save()					: 상담 수정 
* task_del_save()						: 상담 삭제
* task_cancel()							: 상담 리스트로 이동
*/

var ctx = $("#ctx").val();
 

//상담 저장
function task_add_save() {
	
	$(document).ready(function() {
		
	 	var task_no   = $("#task_no").val();
	 	var subject   = $("#subject").val();
	 	var emp_no    = $("#emp_no").val();
	 	var cust_no   = $("#cust_no").val();
	 	var next_day  = $("#next_day").val();
	 	var dtype_cd  = $("#dtype_cd").val();
	 	var lead_no   = $("#lead_no").val();
	 	var score_cd  = $("#score_cd").val();
	 	var ttype_cd  = $("#ttype_cd").val();
	 	var divis_cd  = $("#divis_cd").val();
	 	var oppty_no  = $("#oppty_no").val();
	 	var location  = $("#location").val();
	 	var remark_cn = $("#remark_cn").val();
	 	
	 	if($("#subject").val() == 0 || $("#subject").val() == null || $("#subject").val() == "") {
			alert("제목을 입력하세요.");
			$("#subject").focus();
			return false;
		} else if ($("#cust_no").val() == 0 || $("#cust_no").val() == null || $("#cust_no").val() == "") {
			alert("고객을 입력하세요.");
			$("#cust_no").focus();
			return false;
		} else if ($("#emp_no").val() == 0 || $("#emp_no").val() == null || $("#emp_no").val() == "") {
			alert("담당자를 입력하세요.");
			$("#emp_no").focus();
			return false;
		} else if ($("#next_day").val() == 0 || $("#next_day").val() == null || $("#next_day").val() == "") {
			alert("다음일자를 입력하세요.");
			$("#next_day").focus();
			return false;
		} else if ($("#dtype_cd").val() == 0 || $("#dtype_cd").val() == null || $("#dtype_cd").val() == "") {
			alert("분류를 입력하세요.");
			$("#dtype_cd").focus();
			return false;
		} else if ($("#ttype_cd").val() == 0 || $("#ttype_cd").val() == null || $("#ttype_cd").val() == "") {
			alert("상담유형을 입력하세요.");
			$("#ttype_cd").focus();
			return false;
		} else if ($("#score_cd").val() == 0 || $("#score_cd").val() == null || $("#score_cd").val() == "") {
			alert("가능성을 입력하세요.");
			$("#score_cd").focus();
			return false;
		} else if ($("#divis_cd").val() == 0 || $("#divis_cd").val() == null || $("#divis_cd").val() == "") {
			alert("상담구분을 입력하세요.");
			$("#divis_cd").focus();
			return false;
		} else if ($("#location").val() == 0 || $("#location").val() == null || $("#location").val() == "") {
			alert("진행장소를 입력하세요.");
			$("#location").focus();
			return false;
		} 
	 	
		var ynChk = confirm("해당 상담을 저장하시겠습니까?");
		if(ynChk){
			$.ajax({
				type : 'POST',
				url : ctx + '/task_single_add',
				data : {
					task_no 	: task_no,
					subject 	: subject,
					cust_no		: cust_no,
					emp_no		: emp_no,
					next_day	: next_day,
					dtype_cd	: dtype_cd,
					lead_no 	: lead_no,
					oppty_no	: oppty_no,
					score_cd	: score_cd,
					ttype_cd	: ttype_cd,
					divis_cd	: divis_cd,
					location	: location,
					remark_cn	: remark_cn
				},
				dataType : "json",
				success : function(data) {
					
					alert("상담이 저장되었습니다.");
					alert("상담 리스트로 이동합니다.");
					//location.href= ctx + '/task';
					taskList();
					
				}, error : function(request,status,error) {
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		} else {
			alert("취소되었습니다.");
		}
	});
}

//상담 편집버튼 클릭 시 disabled  해제
function task_modify_btn() {
	
	var lead_no = $("#lead_no").val();
	console.log(lead_no);
	if($("#task_single_modify").val() == "편집") {
	  
		if(lead_no != null && lead_no != '' )
		{
			console.log('a');
			document.getElementById('task_form_title').innerHTML ="고객상담 수정";
		}else{
			console.log('v');
			document.getElementById('task_form_title').innerHTML ="상담 수정";
		}
		
		$("#task_single_modify").val("저장");
		$("#task_single_modify").removeClass("func_btn");
		$("#task_single_modify").addClass("tr_btn");
		
		$('#subject').removeAttr("readonly"); //제목
		$('#cust_no').removeAttr("readonly");
		$('#emp_no').removeAttr("readonly"); //담당자 
		$('#next_day').removeAttr("disabled");
		$('#dtype_cd').removeAttr("disabled");
		$('#lead_no').removeAttr("readonly");
		$('#oppty_no').removeAttr("readonly");
		$('#score_cd').removeAttr("disabled"); 
		$('#ttype_cd').removeAttr("disabled"); 
		$('#divis_cd').removeAttr("disabled"); 
		$('#location').removeAttr("readonly");
		$('#remark_cn').removeAttr("readonly");
		$('#custSchBtn').removeAttr("disabled");
		$('#empSchBtn').removeAttr("disabled");
		$('#leadSchBtn').removeAttr("disabled");
		$('#opptySchBtn').removeAttr("disabled");
			
		/*$("#task_form_tbl input[type='text'], textarea, input[type='date'], select").attr({
			style:'background-color:white'
		});*/
		return false;
		
	} if($("#task_single_modify").val() == "저장") {
		task_modify_save();
	}
}

//상담 수정 
function task_modify_save() {
	
	 $(document).ready(function() {
		var lead_no = $("#lead_no").val();
		var cust_no = $("#cust_no").val();
		var taskPageNum = $("#taskPageNum").val();
 	 	var task_no   = $("#task_no").val();
	 	var subject   = $("#subject").val();
	 	var emp_no    = $("#emp_no").val();
	 	var cust_no   = $("#cust_no").val();
	 	var next_day  = $("#next_day").val();
	 	var dtype_cd  = $("#dtype_cd").val();
	 	var lead_no   = $("#lead_no").val();
	 	var score_cd  = $("#score_cd").val();
	 	var ttype_cd  = $("#ttype_cd").val();
	 	var divis_cd  = $("#divis_cd").val();
	 	var oppty_no  = $("#oppty_no").val();
	 	var location  = $("#location").val();
	 	var remark_cn = $("#remark_cn").val();
	 	
	 	if($("#subject").val() == 0 || $("#subject").val() == null || $("#subject").val() == "") {
			alert("제목을 입력하세요.");
			$("#subject").focus();
			return false;
		} else if ($("#cust_no").val() == 0 || $("#cust_no").val() == null || $("#cust_no").val() == "") {
			alert("고객을 입력하세요.");
			$("#cust_no").focus();
			return false;
		} else if ($("#emp_no").val() == 0 || $("#emp_no").val() == null || $("#emp_no").val() == "") {
			alert("담당자를 입력하세요.");
			$("#emp_no").focus();
			return false;
		} else if ($("#next_day").val() == 0 || $("#next_day").val() == null || $("#next_day").val() == "") {
			alert("다음일자를 입력하세요.");
			$("#next_day").focus();
			return false;
		} else if ($("#dtype_cd").val() == 0 || $("#dtype_cd").val() == null || $("#dtype_cd").val() == "") {
			alert("분류를 입력하세요.");
			$("#dtype_cd").focus();
			return false;
		} else if ($("#ttype_cd").val() == 0 || $("#ttype_cd").val() == null || $("#ttype_cd").val() == "") {
			alert("상담유형을 입력하세요.");
			$("#ttype_cd").focus();
			return false;
		} else if ($("#score_cd").val() == 0 || $("#score_cd").val() == null || $("#score_cd").val() == "") {
			alert("가능성을 입력하세요.");
			$("#score_cd").focus();
			return false;
		} else if ($("#divis_cd").val() == 0 || $("#divis_cd").val() == null || $("#divis_cd").val() == "") {
			alert("상담구분을 입력하세요.");
			$("#divis_cd").focus();
			return false;
		} else if ($("#location").val() == 0 || $("#location").val() == null || $("#location").val() == "") {
			alert("진행장소를 입력하세요.");
			$("#location").focus();
			return false;
		}
	 
		var ynChk = confirm("해당 상담을 수정하시겠습니까?");
		if(ynChk){
			$.ajax({
				type : 'POST',
				url : ctx + '/task_edit',
				async:false,
				data : {
					
					task_no 	: task_no,
					subject 	: subject,
					cust_no		: cust_no,
					emp_no		: emp_no,
					next_day	: next_day,
					dtype_cd	: dtype_cd,
					lead_no 	: lead_no,
					oppty_no	: oppty_no,
					score_cd	: score_cd,
					ttype_cd	: ttype_cd,
					divis_cd	: divis_cd,
					location	: location,
					remark_cn	: remark_cn
					
				},
				dataType : "json",
				success : function(data) {
					
					
					//location.href= ctx + '/task';
					if(lead_no != null && lead_no != '' )
					{
						alert("상담이 수정되었습니다.");
						alert("고객 상담 리스트로 이동합니다.");
					 
						window.location.href="/cust_task?pageNum=" + taskPageNum + "&cust_no=" + cust_no + "&lead_no="+lead_no;
					}
					else{
						alert("상담이 수정되었습니다.");
						alert("상담 리스트로 이동합니다.");
						taskList();	
					}
					
					
				}, error : function(request,status,error) {
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		} else {
			alert("취소되었습니다.");
		}
	});
}

//상담 삭제
function task_del_save() {
	$(document).ready(function() {
		var ynChk = confirm("해당 상담을 삭제하시겠습니까?");
		if(ynChk)
		{
			$.ajax({
				type : 'POST',
				url : ctx + '/task_delete',
				data : {
					task_no 	: $("#task_no").val()
				},
				dataType : "json",
				success : function(data) {
					
					alert("상담이 삭제되었습니다.");
					alert("상담 리스트로 이동합니다.");
					
					taskList();
					
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

// 취소버튼
function task_cancel(taskPageNum) {
	if(confirm("리스트 페이지로 이동하시겠습니까?"))
	location.href="/task?taskPageNum=" + taskPageNum;
}

//가망 고객 취소 페이지 이동
function lead_cancel(a, b){
var cust_no = $("#cust_no").val();
var lead_no = b 
 if(confirm("리스트 페이지로 이동하시겠습니까?")){
		 alert("가망고객 리스트로 이동합니다.");
		 location.href="/cust_task?pageNum=" + a + "&cust_no=" + cust_no + "&lead_no="+lead_no;
	 }
	 else{
		 return false;
	 }
}

//리스트 이동 클릭함수
function taskList() {
	location.href="/task";
}


//리드 상세정보
function leadDetail(a,b) {
   var no = a; 

  console.log(no);
  location.href="/lead_detail?lead_no=" + no + "&pageNum=" + b; 
	 
}