package com.krtc.practice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.krtc.core.service.BaseService;
import com.krtc.dt.api.DTR001API;
import com.krtc.dt.vo.DTR001VO;
import com.krtc.du.bo.DUU001BO;
import com.krtc.du.vo.DUU001VO;
import com.krtc.practice.bo.HAD02BO;
import com.krtc.practice.dao.PRA001DAO;
import com.krtc.practice.vo.HAD02VO;

@Service
public class PRA001Service extends BaseService {

	@Autowired
	private PRA001DAO pra001DAO;
	
	@Autowired
	private DTR001API dtr001API;
	
	
	public List<HAD02VO> selecthad20() {
		HAD02VO had02VO = new HAD02VO();
		return pra001DAO.listByKey(had02VO, 5, 10);
	}
	
	/**
	 * @title  查詢多筆
	 *
	 * @param  had02VO 申請單明細檔清單值物件
	 * @return List<HAD02VO> 申請單明細檔值物件列表
	 * @throws Exception
	 */
	public List<HAD02VO> listByKey(HAD02BO had02BO, Integer offset, Integer limit) {

		return pra001DAO.listByKey(had02BO, offset, limit);
	}
	
	/**
	 * @title  查詢多筆
	 *
	 * @param  had02VO 申請單明細檔清單值物件
	 * @return List<HAD02VO> 申請單明細檔值物件列表
	 * @throws Exception
	 */
	public List<HAD02VO> listByKey(String sLocNo) {
		HAD02VO had02VO = new HAD02VO();
		had02VO.setLocNo(sLocNo);
		return pra001DAO.listByKey(had02VO, 0, Integer.MAX_VALUE);
	}

	/**
	 * @title  查詢單筆
	 *
	 * @param  HAD02VO 申請單表頭檔清單值物件
	 * @return HAD02VO 申請單表頭檔清單值物件
	 * @throws Exception
	 */
	public HAD02VO findByKey(HAD02VO had02VO) {
		return pra001DAO.findByKey(had02VO);
	}
	
	public Long countByKey(HAD02BO had02BO) {
		return pra001DAO.countList(had02BO);
	}
	
	public int del(String locNo, String locDesc) {
		System.out.println("---del---");
		HAD02VO had02VO = new HAD02VO();
		had02VO.setLocNo(locNo);
		had02VO.setLocDesc(locDesc);
		
		return pra001DAO.delete(had02VO);
	}
	
	public int insert(HAD02VO had02VO) {
		return pra001DAO.insert(had02VO);
	}
	/**
	 * @title  修改
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@Transactional(transactionManager="oltp.DataSourceTransactionManager", rollbackFor={Exception.class})
	public int update(HAD02VO had02VO) throws Exception {
		int iCount = 0;
		HAD02VO had02VOCheckVO = this.findByKey(had02VO);
		
		had02VOCheckVO.setLocNo(had02VO.getLocNo());
		had02VOCheckVO.setLocDesc(had02VO.getLocDesc());
		had02VOCheckVO.setStatus(had02VO.getStatus());
		had02VOCheckVO.setCrtEmpNo(had02VO.getCrtEmpNo());
		had02VOCheckVO.setCrtDate(had02VO.getCrtDate());
		had02VOCheckVO.setUptEmpNo(had02VO.getUptEmpNo());
		had02VOCheckVO.setUptDate(had02VO.getUptDate());
		pra001DAO.update(had02VOCheckVO);	
		
		return iCount;
	}
	
	
	
	/**
	 * @title  取得簽核編號
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return Map<String, Object> 簽核編號
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> findSignIdAndHandleUser(HAD02BO had02BO) throws Exception{
		
		boolean bApprove = false;
		String sLocNo = "";
		
		String sIssueTranEmpoBoss = "";
		String sSignId = "";
		String sStageId = "";
		String sHandleUser = "";
		String sApproveUser = "";
		String sLevelNo = "";
		
		String btnManual = "";
		String btnVerify = "";
		String btnApprove = "";
		
		HashMap<String, Object> mData = new HashMap<String, Object>();
		Map<String, Object> mButtonAuth = null;
		Map<String, Object> mTemp = null;
		DTR001VO dtr001CurrentVO = null;
		DTR001VO dtr001TempVO = null;
		HAD02VO had02VO =  new HAD02VO();
		
		List<HAD02VO> lsthad02VO = null;
		List<DUU001VO> lstDuu001VO = null;
		List<DUU001BO> lstDuu001BO = null;
		List<DTR001VO> lstFormRecord = null;
		List<DTR001VO> lstDtr001TempVO = new ArrayList<DTR001VO>();
		Date approveDate = null;
		
		if (had02VO != null) {
			sLocNo = had02VO.getLocNo();
		}
	
		had02VO.setLocNo(sLocNo);
		had02VO = this.findByKey(had02VO);
		
		dtr001CurrentVO = dtr001API.findLastlySigningRecord("PAR001", sLocNo, sSignId);
		
		if(dtr001CurrentVO != null) {
			mButtonAuth = dtr001API.buttonAuthIsApproveAndVerify(dtr001CurrentVO, this.userInfo.userNo());
			bApprove = mButtonAuth.get("approve") == null ? false : (Boolean)mButtonAuth.get("approve");
			
			sStageId = dtr001CurrentVO.getStageId();
			sApproveUser = dtr001CurrentVO.getApproveUser();
			approveDate = dtr001CurrentVO.getApproveDate();			
		}
		
		return mData;
	}
}
		
		
	

