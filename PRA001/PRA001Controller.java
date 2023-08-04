package com.krtc.practice.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.krtc.core.web.base.BaseController;
import com.krtc.de.util.DEJsonObject;
import com.krtc.practice.bo.HAD02BO;
import com.krtc.practice.service.PRA001Service;
import com.krtc.practice.vo.HAD02VO;

@Controller
@RequestMapping(value = "/pr/pra001")
public class PRA001Controller extends BaseController {

	@Autowired
	PRA001Service pra001Service;

	@RequestMapping(value = "/index")
	public String init() throws Exception {
		System.out.println("-------------init start--------------");
		return "practice.pra001";
	}

	@RequestMapping(value = "/query")
	@ResponseBody
	public HashMap<String, Object> query() {
		System.out.println("------------------query-------------------");
		HashMap<String, Object> rtnData = Maps.newHashMap();
		List<HAD02VO> had02VOList = pra001Service.selecthad20();
		System.out.println(had02VOList);
		if (had02VOList.size() > 0) {

			rtnData.put("isSuccess", true);
			rtnData.put("dataList", had02VOList);
		} else {
			rtnData.put("isSuccess", false);
			rtnData.put("dataList", null);
		}
		return rtnData;
	}

	/**
	 * @title 查詢多筆(含分頁功能)
	 * 
	 * 
	 * @param in0024VO 申請單表頭檔清單值物件
	 * @return HashMap<String, Object>
	 */
	@RequestMapping(value = "/listByKey")
	@ResponseBody
	public HashMap<String, Object> listByKey(@RequestBody HAD02BO had02BO) {
		List<HAD02VO> lstData = null;
		HashMap<String, Object> mData = Maps.newHashMap();
		Long total = null;

		Integer offset = had02BO.getOffset();
		Integer limit = had02BO.getLimit();
		lstData = pra001Service.listByKey(had02BO, offset, limit);
		total = pra001Service.countByKey(had02BO);

		mData.put("total", total);
		mData.put("dataTable", lstData);

		return mData;
	}

	// 新增 作業類別[TBIN0020]
	@RequestMapping(value = "/insert")
	@ResponseBody
	public HashMap<String, Object> insert20(@RequestBody HAD02VO had02VO) {
		HashMap<String, Object> rtnData = Maps.newHashMap();

		try {
			had02VO.setStatus("Y");

			int count = pra001Service.insert(had02VO);
			if (count > 0) {
				rtnData.put("isSuccess", true);
				rtnData.put("msg", "新增成功!");
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			rtnData.put("isSuccess", false);
			rtnData.put("msg", "新增失敗!" + ExceptionUtils.getRootCauseMessage(e));
		}
		return rtnData;
	}

	@RequestMapping(value = "/remove")
	@ResponseBody
	public HashMap<String, Object> remove(@RequestBody HAD02VO had02VO) {
		HashMap<String, Object> rtnData = Maps.newHashMap();

		try {
			int count = pra001Service.del(had02VO.getLocNo(), had02VO.getLocDesc());
			if (count > 0) {
				rtnData.put("isSuccess", true);
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return rtnData;
	}

	/**
	 * @title 修改
	 * 
	 * @param in0024VO 申請單表頭檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@RequestMapping("/update")
	@ResponseBody
	public DEJsonObject<HAD02VO> update(@RequestBody HAD02VO had02VO) {
		log.info("update() Start...");

		try {
			pra001Service.update(had02VO);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return DEJsonObject.fail("修改失敗", ExceptionUtils.getRootCauseMessage(e), had02VO);
		}
		return DEJsonObject.success("修改成功", "", had02VO);
	}

	/**
	 * @title  取得簽核編號
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@RequestMapping("/findSignIdAndHandleUser")
	@ResponseBody
	public HashMap<String, Object> findSignIdAndHandleUser(HAD02BO had02BO) {
		log.info("findSignIdAndHandleUser() Start...");
		HashMap<String, Object> mData = null;
		
		try {
			mData = pra001Service.findSignIdAndHandleUser(had02BO);			
		} catch (Exception e) {
			mData = new HashMap<String, Object>();
			mData.put("error", true);
			
			log.error(ExceptionUtils.getStackTrace(e));
			return mData;
		}
		return mData;
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
	public HAD02VO findByKey(HAD02VO had02VO) {
		System.out.println("findByKey start...");
		
		return pra001Service.findByKey(had02VO);
	}
	

}