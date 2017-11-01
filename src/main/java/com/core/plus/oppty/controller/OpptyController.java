package com.core.plus.oppty.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.core.plus.common.PagerVO;
import com.core.plus.contact.cust.service.CustService;
import com.core.plus.contact.cust.vo.CustVO;
import com.core.plus.emp.vo.EmpVO;
import com.core.plus.info.menu.controller.MenuController;
import com.core.plus.info.menu.service.MenuService;
import com.core.plus.info.menu.vo.MenuVo;
import com.core.plus.login.dao.LoginDAO;
import com.core.plus.oppty.service.OpptyService;
import com.core.plus.oppty.vo.OpptyItemVO;
import com.core.plus.oppty.vo.OpptyVO;

@Controller
public class OpptyController {
	
	@Resource
	OpptyService opptyService;
	
	@Resource
	MenuService menuService;
	
	@Resource
	CustService custService;
	
	@Resource
	LoginDAO loginDao;
	
	//메뉴를 위한 추가
	@Resource
	MenuController menuControlleri;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 영업기회 리스트(시작)
	 * 
	 * oppty_code : 내 담당 리스트 코드
	 * */
	@RequestMapping(value="/oppty")
	public ModelAndView opptyList(HttpSession session,
			@RequestParam(value = "opptyPageNum", defaultValue = "1") int opptyPageNum, String oppty_status_cd, String cust_opty_no, String page_type, String oppty_code)
	{
		//session 값 체크 후 null값이면 로그인 페이지 이동
		if (session.getAttribute("user") == null) {
			return new ModelAndView("redirect:/");
		}
		
		Map<String, Object> opptyMap = new HashMap<String, Object>();
		String user_id = null;
		
		if(oppty_code != null) {
			user_id = session.getAttribute("user").toString();
			opptyMap.put("user_id", user_id);
		}
				
		opptyMap.put("opptyPageNum", opptyPageNum);
		opptyMap.put("oppty_status_cd", oppty_status_cd);
		opptyMap.put("cust_opty_no", cust_opty_no);
		
		// paging
		PagerVO page = opptyService.getOpptyListRow(opptyMap);
		opptyMap.put("page", page);
		
		List<OpptyVO> vo = opptyService.opptyList(opptyMap);
		List<OpptyVO> status = opptyService.opptyStatusCD();
		List<OpptyVO> stage = opptyService.opptyStageCD();
		List<OpptyVO> dtype = opptyService.opptyDtypeCD();
		List<OpptyVO> purchase = opptyService.opptyPerchaseType();
		
		ModelAndView mov = new ModelAndView("oppty_list");
		
		mov.addObject("page", page);
		mov.addObject("opptyPageNum", opptyPageNum);
		mov.addObject("cust_opty_no", cust_opty_no);
		mov.addObject("opptyList", vo);
		mov.addObject("opptyStatusCd", status);
		mov.addObject("opptyStageCd", stage);
		mov.addObject("dtypeCd", dtype);
		mov.addObject("purchaseType", purchase);
		mov.addObject("hoppty_status_cd", oppty_status_cd);
		mov.addObject("pageType", "0");		// my page 구분해주기 위한 flg (0: 기본 페이지 1: my page)

		if(cust_opty_no == null)	// 영업기회
		{
			if(oppty_status_cd==null)
			{
				if(oppty_code == null)	// 전체리스트
				{
					mov.addObject("main_menu_url", "oppty");
					mov.addObject("sub_menu_url", "oppty");
					
					menuControlleri.menuImport(mov, "oppty");
				}
				else					// my_list					
				{
					mov.addObject("session", user_id);
					mov.addObject("pageType", "1");
					mov.addObject("oppty_code", oppty_code);
					mov.addObject("main_menu_url", "oppty");
					mov.addObject("sub_menu_url", "oppty?oppty_code=000");
					
					menuControlleri.menuImport(mov, "oppty?oppty_code=000");
				}
			}
			else if(oppty_status_cd.equals("001"))
			{
				mov.addObject("pageType", "2");
				mov.addObject("main_menu_url", "oppty");
				mov.addObject("sub_menu_url", "oppty?oppty_status_cd=001");
				
				menuControlleri.menuImport(mov, "oppty?oppty_status_cd=001");
			}
			else if(oppty_status_cd.equals("002"))
			{
				mov.addObject("pageType", "2");
				mov.addObject("main_menu_url", "oppty");
				mov.addObject("sub_menu_url", "oppty?oppty_status_cd=002");
				
				menuControlleri.menuImport(mov, "oppty?oppty_status_cd=002");
			}
			else if(oppty_status_cd.equals("003"))
			{
				mov.addObject("pageType", "2");
				mov.addObject("main_menu_url", "oppty");
				mov.addObject("sub_menu_url", "oppty?oppty_status_cd=003");
				
				menuControlleri.menuImport(mov, "oppty?oppty_status_cd=003");
			}
			else if(oppty_status_cd.equals("004"))
			{
				mov.addObject("pageType", "2");
				mov.addObject("main_menu_url", "oppty");
				mov.addObject("sub_menu_url", "oppty?oppty_status_cd=004");
				
				menuControlleri.menuImport(mov, "oppty?oppty_status_cd=004");
			}
		}
		if(cust_opty_no != null)	// 고객에서 넘어올 때
		{
			if(cust_opty_no.equals("undefined") || cust_opty_no.equals(" "))
			{
				mov.addObject("main_menu_url", "oppty");
				mov.addObject("sub_menu_url", "oppty");
				
				menuControlleri.menuImport(mov, "oppty");
			}
			else if(cust_opty_no.equals(null))
			{
				mov.addObject("pageType", "1");
				mov.addObject("main_menu_url", "oppty");
				mov.addObject("sub_menu_url", "oppty");
				
				menuControlleri.menuImport(mov, "oppty");
			}
			else
			{
				mov.addObject("pageType", "2");
				mov.addObject("cust_opty_no", cust_opty_no);
				mov.addObject("main_menu_url", "cust");
				mov.addObject("sub_menu_url", "cust");
				
				menuControlleri.menuImport(mov, "cust");
			}
		}
		
		return mov;
	}
	
	/**
	 * 영업기회 Ajax
	 * 검색, 페이징했을 시
	 * */
	@RequestMapping(value="oppty_sch", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> opptSchList(
												  @RequestParam(value = "opptyPageNum", defaultValue = "1") int opptyPageNum,
												  String oppty_no_srch, String oppty_name_srch, 
												  String cust_name_srch, String emp_name_srch, String cust_opty_no, String oppty_code,
												  String oppty_status_cd_srch, String oppty_stage_cd_srch, String hoppty_status_cd,
												  String exp_close_dt_srch, String dtype_cd_srch, String purchase_type_srch, String session)
	{
		Map<String, Object> kMap = new HashMap<String, Object>();
		kMap.put("opptyPageNum", opptyPageNum);
		kMap.put("oppty_no_srch", oppty_no_srch);
		kMap.put("cust_opty_no", cust_opty_no);
		kMap.put("oppty_code", oppty_code);
		kMap.put("oppty_name_srch", oppty_name_srch);
		kMap.put("cust_name_srch", cust_name_srch);
		kMap.put("emp_name_srch", emp_name_srch);
		kMap.put("oppty_status_cd_srch", oppty_status_cd_srch);
		kMap.put("oppty_stage_cd_srch", oppty_stage_cd_srch);
		kMap.put("exp_close_dt_srch", exp_close_dt_srch);
		kMap.put("dtype_cd_srch", dtype_cd_srch);
		kMap.put("purchase_type_srch", purchase_type_srch);
		kMap.put("oppty_status_cd", hoppty_status_cd);
		kMap.put("user_id", session);
		
		System.out.println("oppty_code : " + oppty_code);
		System.out.println("user_id(session) : " + session);
		
		// paging
		PagerVO page = opptyService.getOpptyListRow(kMap);
		kMap.put("page", page);
				
		List<OpptyVO> srcList = opptyService.opptySchList(kMap);
		
		kMap.put("srcList", srcList);
		
		return kMap;
	}
	
	/**
	 * 상세보기 및 단건등록화면
	 * 기회에 해당하는 상품의 목록 조회
	 * 총금액(청구액)과 미수금액을 조회 
	 * */
	@RequestMapping(value="oppty_detail")
	public ModelAndView opptyDetail(@RequestParam(value = "opptyPageNum", defaultValue = "1") int opptyPageNum, 
									String oppty_no, String hoppty_status_cd, String cust_opty_no, String page_type)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oppty_no", oppty_no);
		
		List<OpptyVO> status = opptyService.opptyStatusCD();
		List<OpptyVO> stage = opptyService.opptyStageCD();
		List<OpptyVO> dtype = opptyService.opptyDtypeCD();
		List<OpptyVO> purchase = opptyService.opptyPerchaseType();
		List<OpptyVO> payment = opptyService.opptyPaymentCD();
		List<OpptyVO> recper = opptyService.opptyRecPerCD();
		
		System.out.println("page_type : " + page_type);
		
		ModelAndView mov = new ModelAndView("oppty_detail");
		
		if(oppty_no == null || oppty_no == "")	// 단건등록 시
		{
			OpptyVO opptyNo = opptyService.opptyNoIndex();
			
			mov.addObject("opptyNoIndex", opptyNo);
			mov.addObject("opptyPageNum", opptyPageNum);
			mov.addObject("main_menu_url", "oppty");
			
			menuControlleri.menuImport(mov, "oppty");
		}
		else	// 상세보기	OpptyItem도 조회해야함.
		{
			OpptyVO optyVo = opptyService.opptyDetail(oppty_no);
			CustVO optyItemAmount = custService.optyItemAmount(map); 
			List<OpptyItemVO> itemList 	= opptyService.opptyItemList(oppty_no);	// 매출상품 리스트 조회
			
			int tmp=0, list_price=0, stotal_price=0, dc_price=0, offer_price=0;
			String listPirce="", totalPrice="", dcPrice="", offerPrice="";
			
			// 조회한 상품정보에 숫자에 콤마를 붙여준다.
			for(int i=0; i<itemList.size(); i++)
			{
				tmp = tmp + itemList.get(i).getTotal_price();
				
				list_price = itemList.get(i).getList_price();
				stotal_price = itemList.get(i).getTotal_price();
				dc_price = itemList.get(i).getDc_price();
				offer_price = itemList.get(i).getOffer_price();
				
				listPirce = Comma_won(Integer.toString(list_price));
				totalPrice = Comma_won(Integer.toString(stotal_price));
				dcPrice = Comma_won(Integer.toString(dc_price));
				offerPrice = Comma_won(Integer.toString(offer_price));
				
				itemList.get(i).setListPirce(listPirce);
				itemList.get(i).setDcPirce(dcPrice);
				itemList.get(i).setOfferPirce(offerPrice);
				itemList.get(i).setTotalPirce(totalPrice);
				
			}
			String total_price = Comma_won(Integer.toString(tmp));
			
			if(optyVo.getOppty_stage_cd().equals("004") && optyVo.getOppty_status_cd().equals("003"))
			{
				String outstanding_amount = Comma_won(Integer.toString(optyItemAmount.getOutstanding_amount()));;
				mov.addObject("item_flg",  "complete");
				mov.addObject("outstanding_amount", outstanding_amount);
			}
			else
				mov.addObject("outstanding_amount", "0");
			
			mov.addObject("opptyDetail",  optyVo);
			mov.addObject("itemList", itemList);
			mov.addObject("opptyPageNum", opptyPageNum);
			mov.addObject("hoppty_status_cd", hoppty_status_cd);
			mov.addObject("page_type", page_type);
			mov.addObject("total_price", total_price);
			
			// 네비를 지정해주는 부분 (뺄 수 있으면 메소드 생성 후 따로 관리할 예정)
			if(cust_opty_no != null && cust_opty_no == " ")
			{
				System.out.println("A");
				
				mov.addObject("cust_opty_no", cust_opty_no);
				mov.addObject("main_menu_url", "cust");
				mov.addObject("sub_menu_url", "cust");
					
				menuControlleri.menuImport(mov, "cust");
			}
			else if(cust_opty_no != null)
			{
				System.out.println("A");
				
				mov.addObject("cust_opty_no", cust_opty_no);
				mov.addObject("main_menu_url", "cust");
				mov.addObject("sub_menu_url", "cust");
					
				menuControlleri.menuImport(mov, "cust");
			}
			else
			{
				if(!page_type.equals("1"))
				{
					System.out.println("B");
					
					mov.addObject("main_menu_url", "oppty");
					mov.addObject("sub_menu_url", "oppty");
					
					menuControlleri.menuImport(mov, "oppty");
				}
				else
				{
					System.out.println("C");
					
					mov.addObject("main_menu_url", "oppty");
					mov.addObject("sub_menu_url", "oppty?oppty_code=000");
					
					menuControlleri.menuImport(mov, "oppty?oppty_code=000");
				}
				
			}
		}
		
		mov.addObject("opptyStatusCd", status);
		mov.addObject("opptyStageCd", stage);
		mov.addObject("dtypeCd", dtype);
		mov.addObject("purchaseType", purchase);
		mov.addObject("paymentCd", payment);
		mov.addObject("recperCd", recper);
		
		return mov;
	}

	/* CUD */
	/**
	 * 영업기회 단건등록
	 * */
	@RequestMapping(value="oppty_single_add", method=RequestMethod.POST)
	public @ResponseBody int opptySingleInsert(OpptyVO opptyVo, HttpSession session, HttpServletRequest request)
	{
		int result = 0;
		System.out.println("insert : " + opptyVo);
		
		result = opptyService.opptyInsert(opptyVo);
		
		System.out.println("insert : " + result);
		
		return 0;
	}
	
	/**
	 * 영업기회 수정
	 * 수정한 뒤에 기회상태와 기회단계를 비교해 미수금액을 표시
	 * */
	@RequestMapping(value="oppty_edit", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> opptyEdit(OpptyVO opptyVo, HttpSession session)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oppty_no", opptyVo.getOppty_no());

		int result = 0;
		result = opptyService.opptyEdit(opptyVo);
		
		if(result == 1)
		{
			CustVO optyItemAmount = custService.optyItemAmount(map);
			System.out.println(optyItemAmount);
			map.put("optyItemAmount", optyItemAmount);
			
			if(optyItemAmount != null)
			{
				String outstanding_amount = Comma_won(Integer.toString(optyItemAmount.getOutstanding_amount()));;
				map.put("outstanding_amount", outstanding_amount);
			}
			
		}

		return map;
	}
	
	/**
	 * 영업기회 삭제
	 * */
	@RequestMapping(value="oppty_delete", method=RequestMethod.POST)
	public @ResponseBody int opptyDelete(OpptyVO opptyVo, HttpSession session)
	{
		int result = 0;
		
		result = opptyService.opptyDelete(opptyVo);
		
		return result;
	}
	
	/* Item CUD */
	/**
	 * 영업기회 상품추가
	 * 상품을 추가한 뒤에 화면 이동을 하지 않고상세보기 화면을 유지
	 * */
	@RequestMapping(value="opptyItemInsert", method=RequestMethod.POST)
	public @ResponseBody List<OpptyItemVO> opptItemInsert(@RequestParam(value="opptyItemList[]", required=false) List<String> opptyItemList, String oppty_no, OpptyVO opptyVo)
	{
		System.out.println("Item Insert : " + opptyItemList);
		System.out.println("Item Insert : " + oppty_no);
		System.out.println("opptyVo : " + opptyVo);
		
		List<OpptyItemVO> itemList = new ArrayList<OpptyItemVO>();
		List<OpptyItemVO> ditemList = opptyService.opptyItemList(oppty_no);		// 매출상품 조회
		
		int result = 0;
		
		if(ditemList == null)
			System.out.println("list 없음.");
		else		// 리스트가 존재하면 전부 삭제한다.
			result = opptyService.opptyItemDelete(oppty_no);
		
		if(opptyItemList != null)
		{
			for(int i=0; i<opptyItemList.size(); i++)
			{
				OpptyItemVO ovo = new OpptyItemVO();
				
				ovo.setOppty_no(oppty_no);
				ovo.setMain_cate_cd(opptyItemList.get(i));
				ovo.setMid_cate_cd(opptyItemList.get(++i));
				ovo.setSmall_cate_cd(opptyItemList.get(++i));
				ovo.setQty(Integer.parseInt(opptyItemList.get(++i)));
				ovo.setList_price(Integer.parseInt(opptyItemList.get(++i)));
				ovo.setDc_price(Integer.parseInt(opptyItemList.get(++i)));
				ovo.setPayment_day(opptyItemList.get(++i));
				itemList.add(ovo);
			}
			
			// opptyItem Insert
			int oResult = opptyService.opptyItemInsert(itemList);	// 매출상품 추가
		}
		
		// 바로 detail 화면으로 뿌려준다.
		List<OpptyItemVO> optyItemList 	= opptyService.opptyItemList(oppty_no);	// 조회 후 상세보기에 출력
		
		return optyItemList;
	}
	
	/* Popup*/
	/**
	 * 고객리스트 팝업
	 * */
	@RequestMapping(value="custListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> custListPopup(@RequestParam(value = "custPopupPageNum", defaultValue = "1") int custPopupPageNum, String s_cust_name)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custPopupPageNum", custPopupPageNum);
		
		if(s_cust_name != null)
			map.put("s_cust_name", s_cust_name);
		
		// paging
		PagerVO page = opptyService.getCustPopupRow(map);
		map.put("page", page);
		map.put("pageNum", custPopupPageNum);
		
		// 고객리스트 불러오는 서비스/다오/맵퍼 작성
		if(s_cust_name == null || s_cust_name == "")
		{
			List<CustVO> custPopupList = opptyService.custPopupList(map);
			map.put("custPopupList", custPopupList);
			
			return map;
		}
		else
		{
			map.put("s_cust_name", s_cust_name);
			List<CustVO> schCustPopupList = opptyService.custPopupList(map);
			map.put("custPopupList", schCustPopupList);
			
			return map;
		}
	}
	
	/**
	 * 담당자리스트 팝업
	 * */
	@RequestMapping(value="empListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> empListPopup(@RequestParam(value = "empPopupPageNum", defaultValue = "1") int empPopupPageNum, String s_emp_name)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empPopupPageNum", empPopupPageNum);
		
		// paging
		PagerVO page = opptyService.getEmpPopupRow(map);
		map.put("page", page);
		map.put("pageNum", empPopupPageNum);
		
		// 담당자리스트 불러오는 서비스/다오/맵퍼 작성
		if(s_emp_name == null || s_emp_name == "")
		{
			List<EmpVO> empPopupList = opptyService.empPopupList(map);
			map.put("empPopupList", empPopupList);
			
			return map;
		}
		else
		{
			map.put("s_emp_name", s_emp_name);
			List<EmpVO> schEmpPopupList = opptyService.empPopupList(map);
			map.put("empPopupList", schEmpPopupList);
			
			return map;
		}
	}
	
	// 대분류
	/**
	 * 상품 대분류리스트 팝업
	 * */
	@RequestMapping(value="mainCateListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> mainCatList(@RequestParam(value = "mainCatePopupPageNum", defaultValue = "1") int mainCatePopupPageNum, String s_main_cate_name)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mainCatePopupPageNum", mainCatePopupPageNum);
		
		// paging
		PagerVO page = opptyService.getMainCatePopupRow(map);
		map.put("page", page);
		map.put("pageNum", mainCatePopupPageNum);
		
		if(s_main_cate_name == null || s_main_cate_name == "")
		{
			List<OpptyItemVO> mainCatePopupList = opptyService.mainCatPopupList(map);
			map.put("mainCatePopupList", mainCatePopupList);
			
			return map;
		}
		else
		{
			map.put("s_main_cate_name", s_main_cate_name);
			
			List<OpptyItemVO> schMainCatePopupList = opptyService.mainCatPopupList(map);
			map.put("mainCatePopupList", schMainCatePopupList);
			
			return map;
		}
	}
	
	/**
	 * 상품 중분류리스트 팝업
	 * */
	@RequestMapping(value="midCateListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> midCatList(@RequestParam(value = "midCatePopupPageNum", defaultValue = "1") int midCatePopupPageNum, String main_cate_cd, String s_mid_cate_name)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("midCatePopupPageNum", midCatePopupPageNum);
		
		// paging
		PagerVO page = opptyService.getMidCatePopupRow(map);
		map.put("page", page);
		map.put("pageNum", midCatePopupPageNum);
		
		if(s_mid_cate_name == null || s_mid_cate_name == "")
		{
			map.put("main_cate_cd", main_cate_cd);
			
			List<OpptyItemVO> midCatePopupList = opptyService.midCatPopupList(map);
			map.put("midCatePopupList", midCatePopupList);
			
			return map;
		}
		else
		{
			map.put("main_cate_cd", main_cate_cd);
			map.put("s_mid_cate_name", s_mid_cate_name);
			
			List<OpptyItemVO> schMidCatePopupList = opptyService.midCatPopupList(map);
			map.put("midCatePopupList", schMidCatePopupList);
			
			return map;
		}
	}
	
	/**
	 * 상품 소분류리스트 팝업
	 * */
	@RequestMapping(value="smallCateListAjax", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> smallCatList(@RequestParam(value = "smallCatePopupPageNum", defaultValue = "1") int smallCatePopupPageNum, String main_cate_cd, String mid_cate_cd, String s_small_cate_name)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smallCatePopupPageNum", smallCatePopupPageNum);
		
		// paging
		PagerVO page = opptyService.getSmallCatePopupRow(map);
		map.put("page", page);
		map.put("pageNum", smallCatePopupPageNum);		
		
		if(s_small_cate_name == null || s_small_cate_name == "")
		{
			map.put("main_cate_cd", main_cate_cd);
			map.put("mid_cate_cd", mid_cate_cd);
			
			List<OpptyItemVO> smallCatPopupList = opptyService.smallCatPopupList(map);
			map.put("smallCatePopupList", smallCatPopupList);
			
			return map;
		}
		else
		{
			map.put("main_cate_cd", main_cate_cd);
			map.put("mid_cate_cd", mid_cate_cd);
			map.put("s_small_cate_name", s_small_cate_name);
			
			List<OpptyItemVO> schSmallCatPopupList = opptyService.smallCatPopupList(map);
			map.put("smallCatePopupList", schSmallCatPopupList);
			
			return map;
		}
	}
	
	/**
	 * 영업기회 리스트 Excel을 이용한 다건등록
	 * */
    @RequestMapping(value="/opptyExcelUpload", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody int opptyExcelForm(@RequestParam("excelFile") MultipartFile file) throws Exception 
    {
		int result = opptyService.excelUpload(file);
		
		return result;
	}
    
    /**
     * 영업기회 리스트 Excel 출력
     * */
  	@RequestMapping(value = "/toOpptyExcel",  method=RequestMethod.POST)
  	public ModelAndView toExcel(HttpServletRequest req, HttpSession session, 
  			 String oppty_no_srch, String oppty_name_srch, 
  			  String cust_name_srch, String emp_name_srch,
  			  String oppty_status_cd_srch, String oppty_stage_cd_srch,
  			  String exp_close_dt_srch, String dtype_cd_srch, String purchase_type_srch, String flg,
  			  String hoppty_status_cd, String cust_opty_no, String page_type) 
  	{
  		ModelAndView result = new ModelAndView();
  		Map<String, Object> opptykMap = new HashMap<String, Object> ();
  		
  		char temp = flg.charAt(flg.length()-1);
  		char temp1;
  		
  		if(page_type == null)
  			page_type = "";
  		
  		if(page_type.equals("1"))
  		{
  			temp1 = page_type.charAt(page_type.length()-1);
  			String user_id = session.getAttribute("user").toString();
  			System.out.println("user_id : " + user_id);
  			opptykMap.put("user_id", user_id);
  		}
  		
  		if(temp == '0')
  		{
  			opptykMap.put("oppty_no_srch", oppty_no_srch);
  			opptykMap.put("oppty_name_srch", oppty_name_srch);
  			opptykMap.put("cust_name_srch", cust_name_srch);
  			opptykMap.put("emp_name_srch", emp_name_srch);
  			opptykMap.put("oppty_status_cd_srch", oppty_status_cd_srch);
  			opptykMap.put("oppty_stage_cd_srch", oppty_stage_cd_srch);
  			opptykMap.put("exp_close_dt_srch", exp_close_dt_srch);
  			opptykMap.put("dtype_cd_srch", dtype_cd_srch);
  			opptykMap.put("purchase_type_srch", purchase_type_srch);
  			
  			if(hoppty_status_cd != null)
  			{
  				char temp2 = hoppty_status_cd.charAt(hoppty_status_cd.length()-1);
//  				String opptyStatusCd = "00" + temp2;	//	hoppty_status_cd 코드값을 위한 변수
  				String[] tmp = hoppty_status_cd.split(",");
  				String opptyStatusCd = tmp[0];
  				
  				opptykMap.put("oppty_status_cd", opptyStatusCd);
  			}
  			if(cust_opty_no != null)
  			{
  				String[] tmp = cust_opty_no.split(",");
  				String custOptyNo = tmp[0];
  				
  				opptykMap.put("cust_opty_no", custOptyNo);
  			}
  			//taskMap.put("some",req.getParameter("some"));    				// where에 들어갈 조건??
  			List<OpptyVO> list = opptyService.opptyExcelExport(opptykMap);	// 쿼리
  			
  			result.addObject("opptyExcelExport", list); 					// 쿼리 결과를 model에 담아줌
  			result.setViewName("/oppty/opptyList_excel");					// 엑셀로 출력하기 위한 jsp 페이지
  			
  			return result;
  		}
  		else
  		{
  			result.setViewName("/oppty/opptyList_excel");					// 엑셀로 출력하기 위한 jsp 페이지
  			
  			return result;
  		}
  	}
  	
  	/**
  	 * 숫자타입에 콤마를 붙여주는 메소드
  	 * */
  	public static String Comma_won(String junsu) 
  	{
  		int inValues = Integer.parseInt(junsu);
  		DecimalFormat Commas = new DecimalFormat("#,###");
  		String result_int = (String)Commas.format(inValues);
  		
  		return result_int;
  	 }
}
