package com.krtc.in.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.krtc.core.web.base.BaseController;
import com.krtc.de.util.DEJsonObject;
import com.krtc.in.bo.IN0024BO;
import com.krtc.in.bo.IN0025BO;
import com.krtc.in.service.INU004Service;
import com.krtc.in.vo.IN0024VO;
import com.krtc.in.vo.IN0025VO;


@Controller
@RequestMapping(value = "/in/inu004")
public class INU004Controller extends BaseController {

    @Autowired
    INU004Service inu004Service;


    /**
     * 初始化頁面
     *
     * @return String 頁面檔名
     */
    @RequestMapping(value = "/index")
    public String init() throws Exception {
        System.out.println("init start...");

        return "in.inu004";
    }
    
    @RequestMapping(value = "/add")
    public String add() throws Exception {
        System.out.println("add start...");

        return "in.inu004Add";
    }

    /**
	 * @title  查詢單筆
	 * IssueType [B:領料]
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return in0024VO 申請單表頭檔值物件
	 */
	@RequestMapping(value = "/findByKey")
	@ResponseBody
	public IN0024VO findByKey(IN0024VO in0024VO) {
		System.out.println("findByKey start...");
		
		return inu004Service.findByKey(in0024VO);
	}
	
	/**
	 * @title  查詢多筆(含分頁功能)
	 * IssueType [B:領料]
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return HashMap<String, Object>
	 */
	@RequestMapping(value = "/listByKey")
	@ResponseBody
	public HashMap<String, Object> listByKey(@RequestBody IN0024BO in0024BO) {
		List<IN0024VO> lstData = null;
		HashMap<String, Object> mData = Maps.newHashMap();
		Long total = null;
		
		Integer offset = in0024BO.getOffset();
		Integer limit = in0024BO.getLimit();
		lstData = inu004Service.listByKey(in0024BO, offset, limit);
		total = inu004Service.countByKey(in0024BO);
		
		mData.put("total", total);
		mData.put("dataTable", lstData);
		
		return mData;
	}
	
	/**
	 * @title  確定(新增)
	 * IssueType [B:領料]
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@RequestMapping("/confirm")
	@ResponseBody
	public DEJsonObject<IN0024VO> confirm(@RequestBody IN0024BO in0024BO) {
		log.info("confirm() Start...");
		// String sMessage = "";
		
		try {	
			// in0024BO.setIssueType("B"); 
			
			inu004Service.validate(in0024BO);
			inu004Service.create(in0024BO);
			
			/*
			if (!"52".equals(in0024BO.getAcctCode())) {
				// 非工單處理
				sMessage = "確定成功，且已送出簽核至上層主管！";
			} else {
				sMessage = "確定成功！";
			}
			*/
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return DEJsonObject.fail("確定失敗", ExceptionUtils.getRootCauseMessage(e), in0024BO);
		}
		//return DEJsonObject.success(sMessage, "", in0024BO);
		return DEJsonObject.success("確定成功，請至下方操作，並按下[送出按鈕]簽核至上層主管", "", in0024BO);
	}
	
	/**
	 * @title  修改
	 * IssueType [B:領料]
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return DEJsonObject Json物件
	 */
//	@RequestMapping("/update")
//	@ResponseBody
//	public DEJsonObject<IN0024VO> update(@RequestBody IN0024VO in0024VO) {
//		log.info("update() Start...");
//		
//		try {	
//			in0024VO.setIssueType("B");
//			
//			inu004Service.validate(in0024VO);
//			inu004Service.update(in0024VO);
//		} catch (Exception e) {
//			log.error(ExceptionUtils.getStackTrace(e));
//			return DEJsonObject.fail("修改失敗", ExceptionUtils.getRootCauseMessage(e), in0024VO);
//		}
//		return DEJsonObject.success("修改成功", "", in0024VO);
//	}
	
	/**
	 * @title  刪除
	 * IssueType [B:領料]
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public DEJsonObject<IN0024VO> delete(@RequestBody IN0024BO in0024BO) {
		log.info("delete() Start...");
		//IN0024VO in0024VO = null;
		try {	
			// inu004Service.validate(in0024VO);
			inu004Service.delete(in0024BO);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return DEJsonObject.fail("刪除失敗", ExceptionUtils.getRootCauseMessage(e), in0024BO);
		}
		return DEJsonObject.success("刪除成功", "", in0024BO);
	}
	
	/**
	 * @title  送出
	 * IssueType [B:領料]
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@RequestMapping("/send")
	@ResponseBody
	public DEJsonObject<IN0024VO> send(@RequestBody IN0024BO in0024BO) {
		log.info("send() Start...");
		
		try {	
			inu004Service.send(in0024BO, in0024BO.getIssueTallyNo());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return DEJsonObject.fail("送出失敗", ExceptionUtils.getRootCauseMessage(e), in0024BO);
		}
		return DEJsonObject.success("送出成功", "", in0024BO);
	}
	

    // ============以下為申請明細檔[IN0025VO]操作API====================================

	/**
	 * @title  查詢(明細檔)多筆
	 * IssueType [B:領料]
	 * 
	 * @param  in0025VO 申請單表身檔清單值物件
	 * @return HashMap<String, Object>
	 */
	@RequestMapping(value = "/listDetailByKey")
	@ResponseBody
	public HashMap<String, Object> listDetailByKey(IN0025VO in0025VO) {
		List<IN0025VO> lstData = null;
		HashMap<String, Object> mData = Maps.newHashMap();
		Long total = null;
		
		try {
			Integer offset = Integer.valueOf(request.request().getParameter("offset"));
			Integer limit = Integer.valueOf(request.request().getParameter("limit"));
			lstData = inu004Service.listByKey(in0025VO, offset, limit);
			total = inu004Service.countByKey(in0025VO);
			
			mData.put("total", total);
			mData.put("dataTable", lstData);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return mData;
	}
	
	/**
	 * @title  查詢多筆(含物料明細)
	 * 
	 * @param  in0025VO 申請單表身檔清單值物件
	 * @return HashMap<String, Object>
	 */
	@RequestMapping(value = "/listDetailByKeyWithMaterialDetail")
	@ResponseBody
	public HashMap<String, Object> listDetailByKeyWithMaterialDetail(IN0025VO in0025VO) {
		List<IN0025BO> lstData = null;
		HashMap<String, Object> mData = Maps.newHashMap();
		Long total = null;
		
		try {
			Integer offset = Integer.valueOf(request.request().getParameter("offset") == null ? "0" : request.request().getParameter("offset"));
			Integer limit = Integer.valueOf(request.request().getParameter("limit") == null ? "5" : request.request().getParameter("limit"));
			lstData = inu004Service.listByKeyWithMaterialDetail(in0025VO, offset, limit);
			total = new Long(lstData.size());
			
			mData.put("total", total);
			mData.put("dataTable", lstData);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return mData;
	}
	
    /**
	 * @title  PDF 報表
	 * 
	 */
	@RequestMapping("/print")
	@ResponseBody
	public void print(String issueTallyNo, HttpServletResponse response) {
		try {			
			inu004Service.print(issueTallyNo, response);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
	}

	/**
	 * @title  確認(明細檔)
	 * 
	 * @param  in0025VO 申請單表身檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@RequestMapping("/confirmDetail")
	@ResponseBody
	public DEJsonObject<IN0025VO> confirmDetail(@RequestBody IN0025VO in0025VO) {
		log.info("confirmDetail() Start...");
		
		try {
			// in0025VO.setIssueType("B"); // B:領料
			//inu004Service.validate(in0025VO, assignLocNo);
			
			if (inu004Service.checkSigning(in0025VO)) {
				inu004Service.confirmDetail(in0025VO);	
			} else {
				return DEJsonObject.fail("確認失敗，無法編輯領料明細！", "", in0025VO);
			}
			
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return DEJsonObject.fail("確認失敗", ExceptionUtils.getRootCauseMessage(e), in0025VO);
		}
		return DEJsonObject.success("確認成功", "", in0025VO);
	}
	
	/**
	 * @title  修改(明細檔)
	 * 
	 * @param  in0025VO 申請單表身檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	/*
	@RequestMapping("/updateDetail")
	@ResponseBody
	//public DEJsonObject<IN0025VO> updateDetail(@RequestBody IN0025VO in0025VO) {
	public DEJsonObject<IN0025VO> updateDetail(@RequestBody IN0025VO in0025VO) {
		log.info("updateDetail() Start...");
		
		try {
			// in0025VO.setIssueType("B"); // B:領料
			//inu004Service.validate(in0025VO, assignLocNo);
			
			if (inu004Service.checkSigning(in0025VO)) {
				inu004Service.update(in0025VO);
			} else {
				return DEJsonObject.fail("確認失敗，無法編輯領料明細！", "", in0025VO);
			}
			
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return DEJsonObject.fail("修改失敗", ExceptionUtils.getRootCauseMessage(e), in0025VO);
		}
		return DEJsonObject.success("修改成功", "", in0025VO);
	}
	*/
	/**
	 * @title  刪除(單筆明細檔)
	 * 
	 * @param  in0025VO 申請單表身檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@RequestMapping("/deleteDetail")
	@ResponseBody
	public DEJsonObject<IN0025VO> deleteDetail(@RequestBody IN0025BO in0025VO) {
		log.info("deleteDetail() Start...");
		
		try {
			// in0025VO.setIssueType("B"); // B:領料
			
			inu004Service.delete(in0025VO);	
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return DEJsonObject.fail("刪除失敗", ExceptionUtils.getRootCauseMessage(e), in0025VO);
		}
		return DEJsonObject.success("刪除成功", "", in0025VO);
	}
	
	/**
	 * @title  刪除(多筆明細檔)
	 * 
	 * @param  in0025VO 申請單表身檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@RequestMapping("/deleteMultiDetail")
	@ResponseBody
	public DEJsonObject<IN0025VO> deleteMultiDetail(@RequestBody IN0025BO in0025BO) {
		log.info("deleteDetail() Start...");
		
		try {
			// in0025BO.setIssueType("B"); // B:領料
			
			inu004Service.deleteMulti(in0025BO);	
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return DEJsonObject.fail("刪除失敗", ExceptionUtils.getRootCauseMessage(e), in0025BO);
		}
		return DEJsonObject.success("刪除成功", "", in0025BO);
	}
	
	/**
	 * @title  第一階段主管批示完修改領料單狀態
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return 
	 */
	@RequestMapping("/changeStatus")
	@ResponseBody
	public void changeStatus(@RequestBody IN0024BO in0024BO) {
		log.info("changeStatus() Start...");
		
		try {
			inu004Service.changeStatus(in0024BO);	
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		
//		return DEJsonObject.success("變更為已結案成功", "", "");
	}
	
	/**
	 * @title  取得簽核編號
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@RequestMapping("/findSignIdAndHandleUser")
	@ResponseBody
	public HashMap<String, Object> findSignIdAndHandleUser(IN0024BO in0024BO) {
		log.info("findSignIdAndHandleUser() Start...");
		HashMap<String, Object> mData = null;
		
		try {
			mData = inu004Service.findSignIdAndHandleUser(in0024BO);			
		} catch (Exception e) {
			mData = new HashMap<String, Object>();
			mData.put("error", true);
			
			log.error(ExceptionUtils.getStackTrace(e));
			return mData;
		}
		return mData;
	}


}