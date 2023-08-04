package com.krtc.in.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Maps;
import com.krtc.core.service.BaseService;
import com.krtc.de.api.DEL001API;
import com.krtc.de.api.DET001API;
import com.krtc.de.vo.DET001DVO;
import com.krtc.dr.jaspersoft.DRExportFormat;
import com.krtc.dr.jaspersoft.DRJaspersoftExport;
import com.krtc.dt.api.DTR001API;
import com.krtc.dt.po.DTR001PO;
import com.krtc.dt.vo.DTR001VO;
import com.krtc.du.api.DUP001API;
import com.krtc.du.api.DUR001API;
import com.krtc.du.api.DUU001API;
import com.krtc.du.bo.DUU001BO;
import com.krtc.du.vo.DUP001VO;
import com.krtc.du.vo.DUU001VO;
import com.krtc.in.api.IN0002API;
import com.krtc.in.api.IN0004API;
import com.krtc.in.api.IN0006API;
import com.krtc.in.bo.IN0024BO;
import com.krtc.in.bo.IN0025BO;
import com.krtc.in.dao.IN0024DAO;
import com.krtc.in.dao.IN0025DAO;
import com.krtc.in.enums.ING02FieldNameEnum;
import com.krtc.in.enums.ING02TableNameEnum;
import com.krtc.in.util.INBaseTool;
import com.krtc.in.util.INDateUtils;
import com.krtc.in.util.INKeyUtils;
import com.krtc.in.vo.IN0004VO;
import com.krtc.in.vo.IN0006VO;
import com.krtc.in.vo.IN0013VO;
import com.krtc.in.vo.IN0024VO;
import com.krtc.in.vo.IN0025VO;


@Service
public class INU004Service extends BaseService {
	private static final String FROMNO = "INU004";
	
	@Autowired
    private ING02Service ing02Service;
	
	@Autowired
    private IN0013Service in0013Service;
	
	@Autowired
	private DUU001API duu001API;
	
	@Autowired
	private DUR001API dur001API;
	
	@Autowired
	private IN0024DAO in0024DAO;
	
	@Autowired
	private IN0025DAO in0025DAO;
	
	@Autowired
	private DTR001API dtr001API;
	
	@Autowired
	private IN0006API in0006API;
	
	@Autowired
	private DRJaspersoftExport drJaspersoft;
	
	/**
	 * @title  查詢單筆
	 *
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return in0024VO 申請單表頭檔清單值物件
	 * @throws Exception
	 */
	public IN0024VO findByKey(IN0024VO in0024VO) {
		in0024VO.setCompId(this.userInfo.compId());
		in0024VO.setIssueType("B");
		
//		IN0004VO in0004VO = new IN0004VO();
//		in0004VO.setCompId(this.userInfo.compId());
//		in0004VO.setInventoryType("03");
//		in0004VO.setMatrlNo("1202020001");
//		
//		try {
//			IN0004VO a = in0004Service.findSumOfMatrlNo(in0004VO);
//			System.out.println(a.getMatrlInventory());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return in0024DAO.findByKey(in0024VO);
	}
	
	/**
	 * @title  查詢單筆
	 *
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return in0024VO 申請單表頭檔清單值物件
	 * @throws Exception
	 */
	public IN0024VO findByKey(String issueTallyNo, String issueType) {
		IN0024VO in0024VO = new IN0024VO();
		in0024VO.setCompId(this.userInfo.compId());
		in0024VO.setIssueTallyNo(issueTallyNo);
		in0024VO.setIssueType(issueType);
		
		return in0024DAO.findByKey(in0024VO);
	}
	
	/**
	 * @title  查詢多筆
	 *
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return List<IN0024VO> 申請單表頭檔(主檔)值物件列表
	 * @throws Exception
	 */
	public List<IN0024VO> listByKey(IN0024BO in0024BO) {
		in0024BO.setCompId(this.userInfo.compId());
		in0024BO.setIssueType("B");
		
		return in0024DAO.listByKey(in0024BO, 0, Integer.MAX_VALUE);
	}
	
	/**
	 * @title  查詢多筆(含分頁功能)
	 *
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return List<IN0024VO> 申請單表頭檔(主檔)值物件列表
	 * @throws Exception
	 */
	public List<IN0024VO> listByKey(IN0024BO in0024BO, Integer offset, Integer limit) {
		in0024BO.setCompId(this.userInfo.compId());
		in0024BO.setIssueType("B");
		
		return in0024DAO.listByKey(in0024BO, offset, limit);
	}
	
	/**
	 * @title  查詢多筆筆數(含分頁功能)
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return List<IN0024VO> 申請單表頭檔(主檔)值物件列表
	 * @throws Exception
	 */
	public Long countByKey(IN0024BO in0024BO) {
		in0024BO.setCompId(this.userInfo.compId());
		in0024BO.setIssueType("B");
		
		return in0024DAO.countList(in0024BO);
	}
	
	/**
	 * @title  新增
	 *
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return int 是否成功
	 * @throws Exception
	 */
	@Transactional(transactionManager="oltp.DataSourceTransactionManager", rollbackFor={Exception.class})
	public int create(IN0024BO in0024BO) throws Exception {
		boolean isRegister = false;
		int iCount = 0;
		String sIssueTallyNo = "";
		String sSeqNo = "";
		String sAssignLocNo = "";
		String sAcctCode = "";
		String sLocNo = "";
		String sEndQty = "";
		// String sTimeStamp = "";
		
		INDateUtils inDateUtils = new INDateUtils();
		IN0024VO in0024VO = new IN0024VO();
		IN0004VO in0004ParamVO = new IN0004VO();
		IN0004VO in0004VO = null;
		IN0006VO in0006VO = null;
		
		/** 執行註冊取號(沿用舊EIP TBING02序號檔的取號方式) */
        isRegister = ing02Service.registerMaster(ING02TableNameEnum.TBIN0024, ING02FieldNameEnum.issueTallyNo);
        if (isRegister) {
        	sIssueTallyNo = ing02Service.getSerNo(); // 取得申請單號
        	in0024BO.setIssueTallyNo(sIssueTallyNo); // 回傳給前端使用
        	 
			//in0024VO.setIssueTallyNo(this.getMaxIssueTallyNo());
        	// 換毓柱提供取號的寫法
        	in0024VO.setIssueTallyNo(sIssueTallyNo);
        	in0024VO.setIssueType(in0024BO.getIssueType());
        	in0024VO.setPlanTranDate(inDateUtils.getStringDate(in0024BO.getPlanTranDate()));
			
        	in0024VO.setIssueTranDeptNo(this.userInfo.deptNo());
			//in0024BO.setPhone(duu001API.findUserTelExtension(this.userInfo.userNo()) == null ? "" : duu001API.findUserTelExtension(this.userInfo.userNo()));
        	in0024VO.setPhone(in0024BO.getPhone());
        	in0024VO.setIssueTranEmpo(this.userInfo.userNo());
        	
        	in0024VO.setAcctCode(in0024BO.getAcctCode());
        	sAcctCode = in0024BO.getAcctCode();
        	
        	in0024VO.setApprvEmpNo(dur001API.findSupervisor(this.userInfo.userNo()));
        	in0024VO.setStus("A"); // A:申請中
        	in0024VO.setCostCenter(in0024BO.getCostCenter());
        	in0024VO.setEquipNo(in0024BO.getEquipNo());
        	in0024VO.setLocation(in0024BO.getLocation());
        	
        	in0024VO.setAssignLocNo(in0024BO.getAssignLocNo());
        	sAssignLocNo = in0024BO.getAssignLocNo();
        	
        	in0024VO.setRemark(in0024BO.getRemark());
        	in0024VO.setCiviNo(in0024BO.getCiviNo());
        	in0024VO.setConNo(in0024BO.getConNo());
			
			//in0024BO.setApprvStusTemp("");
			
			//in0024BO.setCostCenter(costCenter);
        	in0024VO.setPurposeId("01"); // 領料預設為01，此欄位會影響到後續會計拋帳問題
			
        	in0024VO.setCompId(this.userInfo.compId());
        	in0024VO.setCreEmpNo(this.userInfo.userNo());
        	in0024VO.setCreDate(inDateUtils.getToday());
			
        	in0024VO = (IN0024VO) INBaseTool.checkNullToEmptyValue(in0024VO);
        	in0024VO.setIssueType("B"); // B:領料
			in0024DAO.insert(in0024VO);
			
			in0024VO.setIssueType("C"); // C:收料
			// 2023.03.01 發現此欄位如果沒值時，會造成發料作業按下產生簽單時出現 Timestamp format must be yyyy-mm-dd hh:mm:ss.ffffff
			// 2023.03.09 改由changeStatus方法來更新ProcessId
        	// sTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date());
        	// in0024VO.setProcessId(sTimeStamp);
//        	if (in0024BO.getRemark() == null) {
//        		in0024VO.setRemark("from ERP created");
//        	} else {
//        		in0024VO.setRemark(in0024BO.getRemark() + " from ERP created");
//        	}
        	//del001API.info("INU004", "INU004Service", "create", "sTimeStamp: "+sTimeStamp + " in0024VO.getProcessId: " + in0024VO.getProcessId());
			// 2023.05.03 新增主檔時也作檢核
			this.checkStorageArea(sAssignLocNo, in0024BO.getLocNo(), in0024BO.getChildLocNo(), in0024BO.getLayer());
			in0024DAO.insert(in0024VO);
			
			for (IN0025VO in0025VO : in0024BO.getLstIn0025VO()) {
				
				isRegister = ing02Service.registerDetail(ING02TableNameEnum.TBIN0025, ING02FieldNameEnum.seqNo);
		        if (isRegister) {
		        	sSeqNo = ing02Service.getSerNo(); // 取得申請單號
		        	
		        	in0025VO.setCompId(this.userInfo.compId());
		        	in0025VO.setIssueTallyNo(sIssueTallyNo);
		        	
					//in0025VO.setSeqNo(this.getSeqNo(in0025VO));
					in0025VO.setSeqNo(sSeqNo);
					
					// 2022.12.02 新增時in0024BO.getLocNo()會是空值，故註解掉
					//in0025VO.setLocNo(in0024BO.getLocNo());
					// 2023.03.17 如果in0024BO.getLocNo()有值時則塞入明細檔LocNo
					/*
					if (StringUtils.isBlank(in0024BO.getLocNo())) {
						sLocNo = in0025VO.getLocNo(); // 存原先的物料明細儲區
					} else {
						in0025VO.setLocNo(in0024BO.getLocNo());
					}
					*/
					// 2023.04.18 改成當領料儲區為分存站T時，才指定六碼儲位
					if ("T".equals(sAssignLocNo)) {
						in0025VO.setLocNo(in0024BO.getLocNo());
						in0025VO.setChildLocNo(in0024BO.getChildLocNo());
						in0025VO.setLayer(in0024BO.getLayer());
					}
					
					in0025VO.setCreEmpNo(this.userInfo.userNo());
					in0025VO.setCreDate(inDateUtils.getToday());
					
					// 2023.04.25 改寫成一個方法，供其他方法呼叫
					this.checkInventory(in0025VO, sAssignLocNo);
					
					// 2023.03.17 配合耀仁需求檢查申請主檔領料儲區為T，但儲位代碼如果為空的話需告知使用者
					/*
					if (StringUtils.isNoneBlank(sAssignLocNo) && "T".equals(sAssignLocNo)) {
						if (StringUtils.isBlank(in0024BO.getLocNo())) {
							throw new Exception("儲位代碼第一碼未輸入，請檢查！");
						} else if (in0024BO.getChildLocNo() != null) {
							if (StringUtils.isBlank(in0024BO.getChildLocNo())) {
								throw new Exception("儲位代碼第二碼未輸入，請檢查！");
							}	
						} else if (in0024BO.getLayer() != null) {	
							if (StringUtils.isBlank(in0024BO.getLayer())) {
								throw new Exception("儲位代碼第三碼未輸入，請檢查！");
							}
						}
					
					}
					*/
					this.checkStorageArea(sAssignLocNo, in0024BO.getLocNo(), in0024BO.getChildLocNo(), in0024BO.getLayer());
					// 2022.11.14 配合耀仁需求檢查申請主檔領料儲區和申請明細儲區不同要檢核	
					/*
					else if (StringUtils.isNoneBlank(sAssignLocNo) && StringUtils.isNoneBlank(sLocNo)) {
						if (!sAssignLocNo.equals(sLocNo)) {
							throw new Exception("申請主檔領料儲區和申請明細儲區不同，請檢查！");
						}
					}
					*/
					// 2023.05.08 確認非工單領料不得領用專用性物料
//					if (StringUtils.isNoneBlank(in0025VO.getInventoryType())) {
//						if (!"52".equals(sAcctCode) && "03".equals(in0025VO.getInventoryType())) {
//							throw new Exception("非工單領料不得領用專用性物料！");
//						}
//					}
					this.checkTakeItem(sAcctCode, in0025VO.getInventoryType(), in0024BO.getConNo());
					
					in0025VO = (IN0025VO) INBaseTool.checkNullToEmptyValue(in0025VO);
					in0025VO.setIssueType("B"); // B:領料
					in0025DAO.insert(in0025VO);
					
					in0025VO.setIssueType("C"); // C:收料
					in0025DAO.insert(in0025VO);
					
		        }	
			}
			
			// 2022.12.02 配合需代理人機制，故使用簽核元件
			// if (!"52".equals(in0024BO.getAcctCode())) {
				// 新增完直接寄送至上層主管
				//this.send(in0024BO, sIssueTallyNo);
			// }
			
        } else {
            log.info("註冊失敗訊息: " + ing02Service.getErrMsg());
        }
		
		return iCount;
	}
	
	/**
	 * @title  修改
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	/*
	@Transactional(transactionManager="oltp.DataSourceTransactionManager", rollbackFor={Exception.class})
	public int update(IN0024VO in0024VO) throws Exception {
		int iCount = 0;
		INDateUtils inDateUtils = new INDateUtils();
		IN0024VO in0024CheckVO = this.findByKey(in0024VO);
		
		if (in0024CheckVO != null) {
			if ("A".equals(in0024CheckVO.getStus())) {
				in0024CheckVO.setAcctCode(in0024VO.getAcctCode());
				in0024CheckVO.setPlanTranDate(inDateUtils.getStringDate(in0024VO.getPlanTranDate()));
				in0024CheckVO.setRemark(in0024VO.getRemark());
				in0024CheckVO.setAssignLocNo(in0024VO.getAssignLocNo());
				
				in0024CheckVO.setUpdEmpNo(this.userInfo.userNo());
				in0024CheckVO.setUpdDate(inDateUtils.getToday());
				
				in0024CheckVO = (IN0024VO) INBaseTool.checkNullToEmptyValue(in0024CheckVO);
				in0024DAO.update(in0024CheckVO);
			} else {
				throw new Exception("修改錯誤：狀況目前已不是申請中，無法修改。");
			}
			
		}
		
		return iCount;
	}
	*/
	/**
	 * @title  刪除
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@Transactional(transactionManager="oltp.DataSourceTransactionManager", rollbackFor={Exception.class})
	public int delete(IN0024BO in0024BO) throws Exception {
		int iCount = 0;
		String sIssueTallyNo = "";
		String sIssueType = "";
		boolean bCheck = false;
		
		for (IN0024VO in0024VO : in0024BO.getDataTable()) {
			sIssueTallyNo = in0024VO.getIssueTallyNo();
			sIssueType = in0024VO.getIssueType();
			
			bCheck = this.checkUserAuth(sIssueTallyNo, sIssueType);
			IN0024VO in0024CheckVO = this.findByKey(in0024VO);
			
			if (bCheck) {
				if (in0024CheckVO != null) {
					if ("A".equals(in0024CheckVO.getStus())) {
						in0024VO.setCompId(this.userInfo.compId());
						iCount = in0024DAO.delete(in0024VO);
							
						IN0025VO in0025VO = new IN0025VO();
						in0025VO.setCompId(this.userInfo.compId());
						in0025VO.setIssueTallyNo(sIssueTallyNo);
						in0025VO.setIssueType("B"); //B:表示領料
						in0025DAO.deleteAll(in0025VO);
						
						in0025VO.setIssueType("C"); //C:表示發料
						in0025DAO.deleteAll(in0025VO);
					} else {
						throw new Exception("刪除錯誤：狀況目前已不是申請中，無法刪除。");
					}
				}
			} else {
				throw new Exception("您沒有權限刪除資料！");
			}
		}
		
		return iCount;
	}
	
	/**
	 * @title  送出
	 * 
	 * @param  IN0024BO, String
	 * @return 
	 * @throws Exception 
	 */
	public void send(IN0024BO in0024BO, String sIssueTallyNo) throws Exception {
		String sSignId = "";
		String sHandleUser = "";
		String sMsgUrl = "";
		
		DTR001PO dtr001PO = new DTR001PO();
		
		sHandleUser = dur001API.findSupervisor(this.userInfo.getUserNo());
		sMsgUrl = "/in/inu004/index?issueTallyNo=" + sIssueTallyNo;
		
		// T: 分存站
		if ("T".equals(in0024BO.getAssignLocNo())) {
			sSignId = "B";
		} else {
			sSignId = "A";
		}
		
		if (StringUtils.isNotBlank(sHandleUser)) {
			dtr001PO.setAppId("INU004");
			dtr001PO.setFormId(sIssueTallyNo);
			dtr001PO.setHandleUser(sHandleUser);
			dtr001PO.setSignId(sSignId);
			dtr001PO.setMsgUrl(sMsgUrl);
			
			dtr001API.send(dtr001PO);
		} else {
			throw new Exception("確定失敗，無法取得上層主管資訊，請確認主管層級設定！");
		}
	}
	
	/**
	 * @title  清空
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	public IN0024VO clearNull(IN0024VO in0024VO) {
		in0024VO.setAcctCode(in0024VO.getAcctCode() == null ? "" : in0024VO.getAcctCode());
		in0024VO.setApprvCom(in0024VO.getApprvCom() == null ? "" : in0024VO.getApprvCom());
		in0024VO.setApprvDate(in0024VO.getApprvDate() == null ? "" : in0024VO.getApprvDate());
		in0024VO.setApprvEmpNo(in0024VO.getApprvEmpNo() == null ? "" : in0024VO.getApprvEmpNo());
		in0024VO.setApprvId(in0024VO.getApprvId() == null ? "" : in0024VO.getApprvId());
		in0024VO.setApprvStus(in0024VO.getApprvStus() == null ? "" : in0024VO.getApprvStus());
		in0024VO.setAssignLocNo(in0024VO.getAssignLocNo() == null ? "" : in0024VO.getAssignLocNo());
		in0024VO.setCiviNo(in0024VO.getCiviNo() == null ? "" : in0024VO.getCiviNo());
		in0024VO.setConNo(in0024VO.getConNo() == null ? "" : in0024VO.getConNo());
		in0024VO.setCostCenter(in0024VO.getCostCenter() == null ? "" : in0024VO.getCostCenter());
		in0024VO.setCreDate(in0024VO.getCreDate() == null ? "" : in0024VO.getCreDate());
		in0024VO.setCreEmpNo(in0024VO.getCreEmpNo() == null ? "" : in0024VO.getCreEmpNo());
		in0024VO.setEquipNo(in0024VO.getEquipNo() == null ? "" : in0024VO.getEquipNo());
		in0024VO.setFinalDate(in0024VO.getFinalDate() == null ? "" : in0024VO.getFinalDate());
		in0024VO.setIssueTallyNo(in0024VO.getIssueTallyNo() == null ? "" : in0024VO.getIssueTallyNo());
		in0024VO.setIssueTranDeptNo(in0024VO.getIssueTranDeptNo() == null ? "" : in0024VO.getIssueTranDeptNo());
		in0024VO.setIssueTranEmpo(in0024VO.getIssueTranEmpo() == null ? "" : in0024VO.getIssueTranEmpo());
		in0024VO.setIssueType(in0024VO.getIssueType() == null ? "" : in0024VO.getIssueType());
		in0024VO.setLocation(in0024VO.getLocation() == null ? "" : in0024VO.getLocation());
		in0024VO.setLotId(in0024VO.getLotId() == null ? "" : in0024VO.getLotId());
		in0024VO.setMoId(in0024VO.getMoId() == null ? "" : in0024VO.getMoId());
		in0024VO.setMsgStamp(in0024VO.getMsgStamp() == null ? "" : in0024VO.getMsgStamp());
		in0024VO.setPhone(in0024VO.getPhone() == null ? "" : in0024VO.getPhone());
		in0024VO.setPlanTranDate(in0024VO.getPlanTranDate() == null ? "" : in0024VO.getPlanTranDate());
		in0024VO.setProcessId(in0024VO.getProcessId() == null ? "" : in0024VO.getProcessId());
		in0024VO.setProdStation(in0024VO.getProdStation() == null ? "" : in0024VO.getProdStation());
		in0024VO.setPurposeId(in0024VO.getPurposeId() == null ? "" : in0024VO.getPurposeId());
		in0024VO.setRemark(in0024VO.getRemark() == null ? "" : in0024VO.getRemark());
		in0024VO.setStus(in0024VO.getStus() == null ? "" : in0024VO.getStus());
		in0024VO.setTakeEmpNo(in0024VO.getTakeEmpNo() == null ? "" : in0024VO.getTakeEmpNo());
		in0024VO.setTranTallyNo(in0024VO.getTranTallyNo() == null ? "" : in0024VO.getTranTallyNo());
		in0024VO.setUpdDate(in0024VO.getUpdDate() == null ? "" : in0024VO.getUpdDate());
		in0024VO.setUpdEmpNo(in0024VO.getUpdEmpNo() == null ? "" : in0024VO.getUpdEmpNo());
		in0024VO.setVenderId(in0024VO.getVenderId() == null ? "" : in0024VO.getVenderId());
			
		return in0024VO;
	}
	
	/**
	 * @title  更改領料狀態
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @throws Exception 
	 */
	public void changeStatus(IN0024BO in0024BO) throws Exception {
		// int iCount = 0;
		INDateUtils inDateUtils = new INDateUtils();
		IN0024VO in0024DataVO = this.findByKey(in0024BO);
		String sTimeStamp = "";
		
		if (in0024DataVO != null) {
			in0024DataVO.setStus(in0024BO.getStus()); 
			
			in0024DataVO.setUpdEmpNo(this.userInfo.userNo());
			in0024DataVO.setUpdDate(inDateUtils.getToday());
			
			in0024DAO.update(in0024DataVO); // 此時issueType = 'B' 領料
			
			in0024DataVO.setIssueType("C"); 
			// 2023.03.09 發現此欄位如果沒值時，會造成發料作業按下產生簽單時出現 Timestamp format must be yyyy-mm-dd hh:mm:ss.ffffff
        	sTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date());
        	in0024DataVO.setProcessId(sTimeStamp);
			in0024DAO.update(in0024DataVO); // 此時issueType = 'C' 發料
		}
		
	}
	
	/**
	 * @title  更改結案後增加欄位核准日
	 * 
	 * @throws Exception 
	 */
	public void changeCloseCase(IN0024BO in0024BO) throws Exception {
		INDateUtils inDateUtils = new INDateUtils();
		IN0024VO in0024DataVO = this.findByKey(in0024BO);
		String sTimeStamp = "";
		
		if (in0024DataVO != null) {
			if (!"E".equals(in0024DataVO.getStus())) {
				sTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date());
				
				in0024DataVO.setIssueType("B");
				in0024DataVO.setApprvDate(inDateUtils.getToday());
				in0024DataVO.setApprvStus("Y");
				in0024DataVO.setApprvId(sTimeStamp);
				in0024DataVO.setFinalDate(inDateUtils.getToday());
				in0024DataVO.setUpdEmpNo(this.userInfo.userNo());
				in0024DataVO.setUpdDate(inDateUtils.getToday());
				in0024DAO.update(in0024DataVO); // 此時issueType = 'B' 領料
				
				in0024DataVO.setIssueType("C");
				in0024DAO.update(in0024DataVO); // 此時issueType = 'C' 發料
			}
		}
	}
	
	/**
	 * @title  檢查是否有權限更改
	 *
	 * @param  sIssueTallyNo 請購單編號
	 * @param  sUserNo 使用者編號
	 * @return boolean 是否有權限
	 * @throws Exception
	 */
	public boolean checkUserAuth(String sIssueTallyNo, String sIssueType) {
		boolean isAuth = false;
		String sUserNo = this.userInfo.userNo();
		String sDeptNo = this.userInfo.deptNo();
		
		IN0024VO in0024VO = new IN0024VO();
		in0024VO.setIssueTallyNo(sIssueTallyNo);
		in0024VO.setIssueType(sIssueType);
		
		IN0024VO in0024CheckVO = this.findByKey(in0024VO);
		
		// 檢查使用者是否為請購單編號的承辦人
		if (in0024CheckVO != null) {
			if (sUserNo.equals(in0024CheckVO.getCreEmpNo())) {
				isAuth = true;
				return isAuth;
			}
		}
		
		// M421 採購處工程勞務組倉儲課, 都能有權限
		if ("M421".equals(sDeptNo)) {
			isAuth = true;
		}
		
		return isAuth;
	}
	
	/**
	 * @title  取得新的一筆物料申請編號
	 *
	 * @return String 物料申請編號
	 * @throws Exception
	 */
	public String getMaxIssueTallyNo() throws Exception {
		INKeyUtils inKeyUtils = new INKeyUtils();
		
		DecimalFormat df = new DecimalFormat("00000");
		
		String sIssueTallyNo = inKeyUtils.getIssueTallyNo();
		String sSerialNo = "";
		String sMaxIssueTallyNo = null;
		
		int iSerialNo = 0;
		
		sMaxIssueTallyNo = in0024DAO.getMaxIssueTallyNo(sIssueTallyNo);
		
		if (sMaxIssueTallyNo == null) {
			sSerialNo = df.format(1);
		} else {
			iSerialNo = Integer.parseInt(sMaxIssueTallyNo);
			sSerialNo = df.format(++iSerialNo);
		}
		
		sIssueTallyNo = sIssueTallyNo + sSerialNo;
		
		return sIssueTallyNo;
	}
	
	/**
	 * @title  驗証
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return
	 */
	public void validate(IN0024BO in0024BO) throws Exception {
		if (StringUtils.isBlank(in0024BO.getAcctCode())) {
			throw new Exception("請輸入用途別！");
		} else if ("50".equals(in0024BO.getAcctCode()) || "52".equals(in0024BO.getAcctCode())) {
			if (StringUtils.isBlank(in0024BO.getCostCenter())) {
				throw new Exception("請輸入成本中心！");
			}
			if (StringUtils.isBlank(in0024BO.getEquipNo())) {
				throw new Exception("請選擇維修系統別！");
			}
		} 
		if (StringUtils.isBlank(in0024BO.getPhone())) {
			throw new Exception("請輸入領料人聯繫電話！");
		}
		if (StringUtils.isBlank(in0024BO.getAssignLocNo())) {
			throw new Exception("請輸入領料儲區！");
		}
//		if (StringUtils.isBlank(in0024VO.getLocation())) {
//			throw new Exception("請輸入配送地點！");
//		}
		
	}
	
	// =============================================================================================================================
	// 以下是申請單明細檔 IN0025VO
	// =============================================================================================================================
	
	/**
	 * @title  查詢單筆
	 *
	 * @param  in0025VO 申請單明細檔清單值物件
	 * @return in0025VO 申請單明細檔清單值物件
	 * @throws Exception
	 */
	public IN0025VO findByKey(IN0025VO in0025VO) {
		in0025VO.setCompId(this.userInfo.compId());
		in0025VO.setIssueType("B"); // 查詢B:領料
		
		return in0025DAO.findByKey(in0025VO);
	}
	
	/**
	 * @title  查詢多筆
	 *
	 * @param  in0025VO 申請單明細檔清單值物件
	 * @return List<IN0025VO> 申請單明細檔值物件列表
	 * @throws Exception
	 */
	public List<IN0025VO> listByKey(String sIssueTallyNo, String sIssueType) {
		IN0025VO in0025VO = new IN0025VO();
		in0025VO.setCompId(this.userInfo.compId());
		in0025VO.setIssueTallyNo(sIssueTallyNo);
		in0025VO.setIssueType(sIssueType);
		
		return in0025DAO.listByKey(in0025VO, 0, Integer.MAX_VALUE);
	}
	
	/**
	 * @title  查詢多筆
	 *
	 * @param  in0025VO 申請單明細檔清單值物件
	 * @return List<IN0025VO> 申請單明細檔值物件列表
	 * @throws Exception
	 */
	public List<IN0025VO> listByKey(IN0025VO in0025VO) {
		in0025VO.setCompId(this.userInfo.compId());
		in0025VO.setIssueType("B"); // 查詢B:領料
		
		return in0025DAO.listByKey(in0025VO, 0, Integer.MAX_VALUE);
	}
	
	/**
	 * @title  查詢多筆(含分頁功能)
	 *
	 * @param  in0025VO 申請單明細檔清單值物件
	 * @return List<IN0025VO> 申請單明細檔值物件列表
	 * @throws Exception
	 */
	public List<IN0025VO> listByKey(IN0025VO in0025VO, Integer offset, Integer limit) {
		in0025VO.setCompId(this.userInfo.compId());
		in0025VO.setIssueType("B"); // 查詢B:領料
		
		return in0025DAO.listByKey(in0025VO, offset, limit);
	}
	
	/**
	 * @title  查詢多筆(含物料明細)
	 *
	 * @param  in0025VO 申請單明細檔清單值物件
	 * @return List<IN0025VO> 申請單明細檔值物件列表
	 * @throws Exception
	 */
	public List<IN0025BO> listByKeyWithMaterialDetail(IN0025VO in0025VO, Integer offset, Integer limit) {
		in0025VO.setCompId(this.userInfo.compId());
		in0025VO.setIssueType("B"); // 查詢B:領料
		
		return in0025DAO.listByKeyWithMaterialDetail(in0025VO, offset, limit);
	}
	
	/**
	 * @title  查詢多筆筆數(含分頁功能)
	 * 
	 * @param  in0025VO 申請單明細檔清單值物件
	 * @return List<IN0025VO> 申請單明細檔值物件列表
	 * @throws Exception
	 */
	public Long countByKey(IN0025VO in0025VO) {
		in0025VO.setCompId(this.userInfo.compId());
		in0025VO.setIssueType("B"); // 查詢B:領料
		
		return in0025DAO.countList(in0025VO);
	}
	
	/**
	 * @title  確認(明細檔)
	 *
	 * @param  in0025VO 申請單明細檔清單值物件
	 * @return int 是否成功
	 * @throws Exception
	 */
	@Transactional(transactionManager="oltp.DataSourceTransactionManager", rollbackFor={Exception.class})
	public IN0025VO confirmDetail(IN0025VO in0025VO) throws Exception {
		boolean isRegister = false;
		// int iCount = 0;
		String sSeqNo = "";
		String sAssignLocNo = "";
		// String sLocNo = "";
		// String sChildLocNo = "";
		// String sLayer = "";
		
		in0025VO.setIssueType("B"); // B:領料
		
		INDateUtils inDateUtils = new INDateUtils();
		IN0025VO in0025CheckVO = this.findByKey(in0025VO);
		
		IN0024VO in0024CheckVO = this.findByKey(in0025VO.getIssueTallyNo(), in0025VO.getIssueType());
		boolean bCheck = this.checkUserAuth(in0025VO.getIssueTallyNo(), in0025VO.getIssueType());
		
		// 直接新增不用檢核
		if (in0025CheckVO == null) {
			// 註冊seqNo序號
	        isRegister = ing02Service.registerDetail(ING02TableNameEnum.TBIN0025, ING02FieldNameEnum.seqNo);
	        if (isRegister) {
	        	sSeqNo = ing02Service.getSerNo(); // 取得申請單號
	        	
	        	in0025VO.setCompId(this.userInfo.compId());
				//in0025VO.setSeqNo(this.getSeqNo(in0025VO));
				in0025VO.setSeqNo(sSeqNo);
				
				in0025VO.setCreEmpNo(this.userInfo.userNo());
				in0025VO.setCreDate(inDateUtils.getToday());
				
				in0025VO = (IN0025VO) INBaseTool.checkNullToEmptyValue(in0025VO);
				if (StringUtils.isNotBlank(in0025VO.getIssueTallyNo())) {
					// 2023.03.09 從工單傳進來的也要建立二筆明細資料一個領料一個收料
					in0025VO.setIssueType("B"); // B:領料
					
					// 2023.03.23由於領料主檔和明細檔是分開存值，如果是T分存站的話需再檢查locNo、childLocNo和layer
					// 2023.03.23 配合耀仁需求檢查申請主檔領料儲區為T，但儲位代碼如果為空的話需告知使用者
					sAssignLocNo = in0025VO.getLocNo();
					this.checkInventory(in0025VO, sAssignLocNo);
					this.checkStorageArea(sAssignLocNo, in0025VO.getLocNo(), in0025VO.getChildLocNo(), in0025VO.getLayer());
					/*
					if (StringUtils.isNoneBlank(sAssignLocNo) && "T".equals(sAssignLocNo)) {
						if (StringUtils.isBlank(in0025VO.getLocNo())) {
							throw new Exception("儲位代碼第一碼未輸入，請檢查！");
						} else if (in0025VO.getLocNo() != null) {
							if (in0025VO.getLocNo().length() != 2) {
								throw new Exception("儲位代碼第一碼需輸入二位代碼，請檢查！");
							}	
						} else if (in0025VO.getChildLocNo() != null) {
							if (StringUtils.isBlank(in0025VO.getChildLocNo())) {
								throw new Exception("儲位代碼第二碼未輸入，請檢查！");
							}	
						} else if (in0025VO.getLayer() != null) {	
							if (StringUtils.isBlank(in0025VO.getLayer())) {
								throw new Exception("儲位代碼第三碼未輸入，請檢查！");
							}
						}
					}
					*/
					// 2023.05.08 確認非工單領料不得領用專用性物料
					this.checkTakeItem(in0024CheckVO.getAcctCode(), in0025VO.getInventoryType(), in0024CheckVO.getConNo());
					
					if (bCheck) {
						if (in0024CheckVO != null) {
							if ("A".equals(in0024CheckVO.getStus())) {
								in0025VO.setIssueType("B"); // B:領料
								in0025DAO.insert(in0025VO);
								
								in0025VO.setIssueType("C"); // C:收料
								in0025DAO.insert(in0025VO);
							} else {
								throw new Exception("確定錯誤：狀態目前已不是申請中，無法確定。");
							}
						}
					} else {
						throw new Exception("您沒有權限維護資料！");
					}
				} else {
					throw new Exception("申請單據編號為空值，請確認是否已產生主檔申請單。");
				}
				
				
	        }	
		} else {
			in0025VO.setUpdEmpNo(this.userInfo.userNo());
			in0025VO.setUpdDate(inDateUtils.getToday());
			
			sAssignLocNo = in0025VO.getLocNo();
			this.checkInventory(in0025VO, sAssignLocNo);
			this.checkStorageArea(sAssignLocNo, in0025VO.getLocNo(), in0025VO.getChildLocNo(), in0025VO.getLayer());
			
			in0025VO = (IN0025VO) INBaseTool.checkNullToEmptyValue(in0025VO);
			
			if (bCheck) {
				if (in0024CheckVO != null) {
					if ("A".equals(in0024CheckVO.getStus())) {
						in0025VO.setIssueType("B"); // B:領料
						in0025DAO.update(in0025VO);
						
						in0025VO.setIssueType("C"); // C:收料
						in0025DAO.update(in0025VO);
					} else {
						throw new Exception("確定錯誤：狀態目前已不是申請中，無法確定。");
					}
				}
			} else {
				throw new Exception("您沒有權限維護資料！");
			}
		}
		
		return in0025VO;
	}
	
	/**
	 * @title  修改
	 *
	 * @param  in0025VO 申請單明細檔清單值物件
	 * @return int 是否成功
	 * @throws Exception
	 */
	/*
	@Transactional(transactionManager="oltp.DataSourceTransactionManager", rollbackFor={Exception.class})
	public IN0025VO update(IN0025VO in0025VO) throws Exception {
		// int iCount = 0;
		// String sLocNo = "";
		// String sChildLocNo = "";
		// String sLayer = "";
		
		in0025VO.setIssueType("B"); // B:領料
		
		INDateUtils inDateUtils = new INDateUtils();
		IN0024VO in0024CheckVO = this.findByKey(in0025VO.getIssueTallyNo(), in0025VO.getIssueType());
		IN0025VO in0025CheckVO = this.findByKey(in0025VO);
		
		if (in0025CheckVO != null) {
			if ("A".equals(in0024CheckVO.getStus())) {
//				if (!"".equals(in0025CheckVO.getLocNo())) {
//	        		sLocNo = in0025VO.getLocNo().substring(0, 2);
//	        		sChildLocNo = in0025VO.getLocNo().substring(2, 4);
//	        		sLayer = in0025VO.getLocNo().substring(4);
//	        		
//	        		in0025CheckVO.setLocNo(sLocNo);
//	        		in0025CheckVO.setChildLocNo(sChildLocNo);
//	        		in0025CheckVO.setLayer(sLayer);
					
//					in0025CheckVO.setLocNo(in0025VO.getLocNo());
//	        		in0025CheckVO.setChildLocNo(in0025VO.getChildLocNo());
//	        		in0025CheckVO.setLayer(in0025VO.getLayer());
//	        	}
				
				in0025CheckVO.setInventoryType(in0025VO.getInventoryType());
				in0025CheckVO.setMatrlNo(in0025VO.getMatrlNo());
				in0025CheckVO.setMatrlGrade(in0025VO.getMatrlGrade());
				in0025CheckVO.setQty(in0025VO.getQty());
				in0025CheckVO.setUsedYear(in0025VO.getUsedYear());
				in0025CheckVO.setRePairTime(in0025VO.getRePairTime());
				in0025CheckVO.setReceivePrice(in0025VO.getReceivePrice());
				
				in0025CheckVO.setUpdEmpNo(this.userInfo.userNo());
				in0025CheckVO.setUpdDate(inDateUtils.getToday());
				
				in0025CheckVO = (IN0025VO) INBaseTool.checkNullToEmptyValue(in0025CheckVO);
				in0025DAO.update(in0025CheckVO);
			} else {
				throw new Exception("修改錯誤：狀況目前已不是申請中，無法修改。");
			}
		}
		
		return in0025VO;
	}
	*/
	/**
	 * @title  刪除(單筆)
	 * 
	 * @param  in0025VO 申請單明細檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@Transactional(transactionManager="oltp.DataSourceTransactionManager", rollbackFor={Exception.class})
	public int delete(IN0025VO in0025VO) throws Exception {
		int iCount = 0;
		in0025VO.setIssueType("B"); // B:領料
		
		boolean bCheck = this.checkUserAuth(in0025VO.getIssueTallyNo(), in0025VO.getIssueType());
		IN0024VO in0024CheckVO = this.findByKey(in0025VO.getIssueTallyNo(), in0025VO.getIssueType());
		
		if (bCheck) {
			if (in0024CheckVO != null) {
				if ("A".equals(in0024CheckVO.getStus())) {
					in0025VO.setCompId(this.userInfo.compId());
					in0025VO.setIssueType("B"); // B:領料
					iCount = in0025DAO.delete(in0025VO);
					
					in0025VO.setIssueType("C"); // C:發料
					iCount = in0025DAO.delete(in0025VO);
				} else {
					throw new Exception("刪除錯誤：狀況目前已不是申請中，無法刪除。");
				}
			}
		} else {
			throw new Exception("您沒有權限刪除資料！");
		}
		
		return iCount;
	}
	
	/**
	 * @title  刪除(多筆)
	 * 
	 * @param  in0025VO 申請單明細檔清單值物件
	 * @return DEJsonObject Json物件
	 */
	@Transactional(transactionManager="oltp.DataSourceTransactionManager", rollbackFor={Exception.class})
	public int deleteMulti(IN0025BO in0025BO) throws Exception {
		int iCount = 0;
		in0025BO.setIssueType("B"); // B:領料
		
		boolean bCheck = this.checkUserAuth(in0025BO.getIssueTallyNo(), in0025BO.getIssueType());
		IN0024VO in0024CheckVO = this.findByKey(in0025BO.getIssueTallyNo(), in0025BO.getIssueType());
		
		if (bCheck) {
			if (in0024CheckVO != null) {
				if ("A".equals(in0024CheckVO.getStus())) {
					for(IN0025VO in0025VO: in0025BO.getDataTable()) {
						in0025VO.setCompId(this.userInfo.compId());
						in0025VO.setIssueType("B"); // B:領料
						iCount = in0025DAO.delete(in0025VO);
						
						in0025VO.setIssueType("C"); // C:發料
						iCount = in0025DAO.delete(in0025VO);
					}
				} else {
					throw new Exception("刪除錯誤：狀況目前已不是申請中，無法刪除。");
				}
			}
		} else {
			throw new Exception("您沒有權限刪除資料！");
		}
		
		return iCount;
	}
	
	public IN0025VO clearNull(IN0025VO in0025VO) {
		in0025VO.setAccuPreTranQty(in0025VO.getAccuPreTranQty() == null ? new BigDecimal("0") : in0025VO.getAccuPreTranQty());
		in0025VO.setAccuTranQty(in0025VO.getAccuTranQty() == null ? new BigDecimal("0") : in0025VO.getAccuTranQty());
		in0025VO.setAllotQty(in0025VO.getAllotQty() == null ? new BigDecimal("0") : in0025VO.getAllotQty());
		in0025VO.setAmt(in0025VO.getAmt() == null ? new BigDecimal("0") : in0025VO.getAmt());
		in0025VO.setAmtA(in0025VO.getAmtA() == null ? new BigDecimal("0") : in0025VO.getAmtA());
		in0025VO.setAmtB(in0025VO.getAmtB() == null ? new BigDecimal("0") : in0025VO.getAmtB());
		in0025VO.setAmtC(in0025VO.getAmtC() == null ? new BigDecimal("0") : in0025VO.getAmtC());
		in0025VO.setAmtD(in0025VO.getAmtD() == null ? new BigDecimal("0") : in0025VO.getAmtD());
		in0025VO.setAmtE(in0025VO.getAmtE() == null ? new BigDecimal("0") : in0025VO.getAmtE());
		in0025VO.setAmtF(in0025VO.getAmtF() == null ? new BigDecimal("0") : in0025VO.getAmtF());
		in0025VO.setCheckQty(in0025VO.getCheckQty() == null ?  new BigDecimal("0") : in0025VO.getCheckQty());
		in0025VO.setChildLocNo(in0025VO.getChildLocNo() == null ? "" : in0025VO.getChildLocNo());
		in0025VO.setCompId(in0025VO.getCompId() == null ? "" : in0025VO.getCompId());
		in0025VO.setCreDate(in0025VO.getCreDate() == null ? "" : in0025VO.getCreDate());
		in0025VO.setCreEmpNo(in0025VO.getCreEmpNo() == null ? "" : in0025VO.getCreEmpNo());
		in0025VO.setIdNo(in0025VO.getIdNo() == null ? "" : in0025VO.getIdNo());
		in0025VO.setImportChildLocNo(in0025VO.getImportChildLocNo() == null ? "" : in0025VO.getImportChildLocNo());
		in0025VO.setImportLayer(in0025VO.getImportLayer() == null ? "" : in0025VO.getImportLayer());
		in0025VO.setImportLocNo(in0025VO.getImportLocNo() == null ? "" : in0025VO.getImportLocNo());
		in0025VO.setImportLotNo(in0025VO.getImportLotNo() == null ? "" : in0025VO.getImportLotNo());
		in0025VO.setImportQty(in0025VO.getImportQty() == null ? new BigDecimal("0") : in0025VO.getImportQty());
		in0025VO.setInventoryQty(in0025VO.getInventoryQty() == null ? new BigDecimal("0") : in0025VO.getInventoryQty());
		in0025VO.setInventoryType(in0025VO.getInventoryType() == null ? "" : in0025VO.getInventoryType());
		in0025VO.setIsDeGrade(in0025VO.getIsDeGrade() == null ? "" : in0025VO.getIsDeGrade());
		in0025VO.setIsDeLocNo(in0025VO.getIsDeLocNo() == null ? "" : in0025VO.getIsDeLocNo());
		in0025VO.setIsDeLocNo1(in0025VO.getIsDeLocNo1() == null ? "" : in0025VO.getIsDeLocNo1());
		in0025VO.setIssueTallyNo(in0025VO.getIssueTallyNo() == null ? "" : in0025VO.getIssueTallyNo());
		in0025VO.setIssueType(in0025VO.getIssueType() == null ? "" : in0025VO.getIssueType());
		in0025VO.setLayer(in0025VO.getLayer() == null ? "" : in0025VO.getLayer());
		in0025VO.setLocNo(in0025VO.getLocNo() == null ? "" : in0025VO.getLocNo());
		in0025VO.setLotNo(in0025VO.getLotNo() == null ? "" : in0025VO.getLotNo());
		in0025VO.setMatrlGrade(in0025VO.getMatrlGrade() == null ? "" : in0025VO.getMatrlGrade());
		in0025VO.setMatrlNo(in0025VO.getMatrlNo() == null ? "" : in0025VO.getMatrlNo());
		in0025VO.setPreTranQty(in0025VO.getPreTranQty() == null ? new BigDecimal("0") : in0025VO.getPreTranQty());
		in0025VO.setQty(in0025VO.getQty() == null ? new BigDecimal("0") : in0025VO.getQty());
		in0025VO.setReceivePrice(in0025VO.getReceivePrice() == null ? new BigDecimal("0") : in0025VO.getReceivePrice());
		in0025VO.setRemark(in0025VO.getRemark() == null ? "" : in0025VO.getRemark());
		in0025VO.setRePairTime(in0025VO.getRePairTime() == null ? new BigDecimal("0") : in0025VO.getRePairTime());
		in0025VO.setSeqNo(in0025VO.getSeqNo() == null ? "" : in0025VO.getSeqNo());
		in0025VO.setSeqNo1(in0025VO.getSeqNo1() == null ? "" : in0025VO.getSeqNo1());
		in0025VO.setStus(in0025VO.getStus() == null ? "" : in0025VO.getStus());
		in0025VO.setSurplus(in0025VO.getSurplus() == null ? new BigDecimal("0") : in0025VO.getSurplus());
		in0025VO.setTranTallyNo(in0025VO.getTranTallyNo() == null ? "" : in0025VO.getTranTallyNo());
		in0025VO.setUnitInv(in0025VO.getUnitInv() == null ? "" : in0025VO.getUnitInv());
		in0025VO.setUnitPrice(in0025VO.getUnitPrice() == null ? new BigDecimal("0") : in0025VO.getUnitPrice());
		in0025VO.setUpdDate(in0025VO.getUpdDate() == null ? "" : in0025VO.getUpdDate());
		in0025VO.setUpdEmpNo(in0025VO.getUpdEmpNo() == null ? "" : in0025VO.getUpdEmpNo());
		in0025VO.setUsedYear(in0025VO.getUsedYear() == null ? new BigDecimal("0") : in0025VO.getUsedYear());
			
		return in0025VO;
	}
	
	/**
	 * @title  取得新的一筆物料序號
	 *
	 * @return String 物料序號
	 * @throws Exception
	 */
	public String getSeqNo(IN0025VO in0025VO) throws Exception {
		INKeyUtils inKeyUtils = new INKeyUtils();
		
		DecimalFormat df = new DecimalFormat("00000");
		
		String sSeqNo = inKeyUtils.getSeqNo();
		String sSerialNo = "";
		String sMaxSeqNo = null;
		
		int iSerialNo = 0;
		
		in0025VO.setSeqNo(sSeqNo);
		sMaxSeqNo = in0025DAO.getMaxSeqNo(in0025VO);
		
		if (sMaxSeqNo == null) {
			sSerialNo = df.format(1);
		} else {
			iSerialNo = Integer.parseInt(sMaxSeqNo);
			sSerialNo = df.format(++iSerialNo);
		}
		
		sSeqNo = sSeqNo + sSerialNo;
		
		return sSeqNo;
	}
	
	/**
	 * @title  驗証
	 * 
	 * @param  in0025VO 申請單明細檔清單值物件
	 * @return
	 */
	public void validate(IN0025VO in0025VO, String assignLocNo) throws Exception {
//		if (StringUtils.isBlank(in0025VO.getIssueTallyNo()))
//			throw new Exception("請輸入單號");
//		
//		if (StringUtils.isBlank(in0025VO.getInventoryType()))
//			throw new Exception("請擇選品別");
//		
//		if (StringUtils.isBlank(in0025VO.getMatrlNo()))
//			throw new Exception("請擇選物料編碼");
//		
//		if (StringUtils.isBlank(in0025VO.getLocNo()))
//			throw new Exception("請擇選庫別");
		
		if ("T".equals(assignLocNo)) {
			if (StringUtils.isNotBlank(in0025VO.getLocNo())) {
				if (in0025VO.getLocNo().length() != 2)
					throw new Exception("庫別的第一組字元長度為2！");
			}
			
			if (StringUtils.isNotBlank(in0025VO.getChildLocNo())) {
				if (in0025VO.getChildLocNo().length() != 2)
					throw new Exception("庫別的第二組字元長度為2！");
			}
			
			if (StringUtils.isNotBlank(in0025VO.getLayer())) {
				if (in0025VO.getLayer().length() != 2)
					throw new Exception("庫別的第三組字元長度為2！");
			}
		}
		
	}
	
	/**
	 * @title  列印表單
	 * 
	 * @param  sIssueTallyNo 申請單編號
	 * @param  response HttpServletResponse
	 * @throws Exception 
	 */
	public void print(String sIssueTallyNo, HttpServletResponse response) throws Exception {
		String jrmPath = "INU004Report.jrxml";
		
		HashMap<String, Object> parameters = Maps.newHashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		parameters.put("issueTallyNo", sIssueTallyNo);
		
		drJaspersoft.setExportFormat(DRExportFormat.PDF);
		drJaspersoft.exportWithConn("in", jrmPath, parameters, response);

	}
	
	/**
	 * @title  驗証庫存量
	 * 
	 * @throws Exception
	 */
	public void checkInventory(IN0025VO in0025VO, String assignLocNo) throws Exception {
		IN0006VO in0006VO = null;
		String sEndQty = "";
		
		// 2023.04.11 檢查物料編號
		if (StringUtils.isNoneBlank(in0025VO.getMatrlNo())) {
			if (in0025VO.getMatrlNo().length() > 10) {
				throw new Exception("物料編號需為十碼，請檢查！");
			}
		}
		
		if (in0025VO.getQty() != null) {
			if (in0025VO.getQty().compareTo(new BigDecimal("0")) == 0) {
				throw new Exception("申請數量不得為零，請檢查！");
			}
			
			if (in0025VO.getQty().compareTo(new BigDecimal("0")) < 1) {
				throw new Exception("申請數量不得為負值，請檢查！");
			}
			
			// 2023.04.17 當T分存站時利用儲區六碼來檢查該儲區物料數量，否則利用一碼儲區來檢查該機廠物料數量
			in0006VO = new IN0006VO();
			if ("T".equals(assignLocNo)) {
				in0006VO.setCompId(this.userInfo.compId());
				in0006VO.setInventoryType(in0025VO.getInventoryType());
				in0006VO.setMatrlNo(in0025VO.getMatrlNo());
				in0006VO.setMatrlGrade(in0025VO.getMatrlGrade());
				in0006VO.setLocNo(in0025VO.getLocNo());
				in0006VO.setChildLocNo(in0025VO.getChildLocNo());
				in0006VO.setLayer(in0025VO.getLayer());
				
				sEndQty = in0006API.findInventoryQtyByLocNoAndChildLocNoAndLayer(in0006VO);
			} else {
				in0006VO.setCompId(this.userInfo.compId());
				in0006VO.setInventoryType(in0025VO.getInventoryType());
				in0006VO.setMatrlNo(in0025VO.getMatrlNo());
				in0006VO.setMatrlGrade(in0025VO.getMatrlGrade());
				in0006VO.setLocNo(assignLocNo);
				
				sEndQty = in0006API.findInventoryQtyByLocNo(in0006VO);
			}
			
			if (sEndQty != null) {
				if (in0025VO.getQty().compareTo(new BigDecimal(sEndQty)) == 1) {
					throw new Exception("申請數量不得大於庫存量，請檢查！");
				} 
			} else {
				throw new Exception("料號："+in0025VO.getMatrlNo()+" 於儲區："+in0025VO.getLocNo()+" "+in0025VO.getChildLocNo()+" "+in0025VO.getLayer()+ " 找不到庫存量，請確認！");
			}
			
		} else {
			throw new Exception("請輸入申請數量！");
		}
	}
	
	/**
	 * @title  驗証庫非工單領料不得領用專用性物料
	 * 
	 * @throws Exception
	 */
	public void checkTakeItem(String acctCode, String inventoryType, String conNo) throws Exception {
		// 2023.05.08 確認非工單領料不得領用專用性物料, 和工令編號不得為空
		if (StringUtils.isNoneBlank(inventoryType)) {
			if ("03".equals(inventoryType)) {
				if (!"52".equals(acctCode)) {
					throw new Exception("非工單領料不得領用專用性物料！");
				}
				
				if ("52".equals(acctCode) && StringUtils.isBlank(conNo)) {
					throw new Exception("工單領料需填寫工令編號！");
				}
			}
		}
	}
	
	/**
	 * @title  驗証是否能編輯領料明細
	 * 
	 * @param  in0025VO 申請單明細檔清單值物件
	 * @return boolean
	 * @throws Exception
	 */
	public boolean checkSigning(IN0025VO in0025VO) throws Exception {
		Boolean isPass = false;
		
		String sAppId = "INU004";
		String sFormId = "";
		String sSignId = "";
		String sIssueTranEmpo = "";
		String sApproveUser = "";
		
		DTR001VO dtr001VO = null;
		IN0024VO in0024VO = null;
		
		if (in0025VO != null) {
			if (StringUtils.isNotBlank(in0025VO.getLocNo())) {
				if ("T".equals(in0025VO.getLocNo())) {
					sSignId = "B";
				} else {
					sSignId = "A";
				}
			}
			
			if (StringUtils.isNotBlank(in0025VO.getIssueTallyNo())) {
				sFormId = in0025VO.getIssueTallyNo();
			}
			
			in0024VO = this.findByKey(in0025VO.getIssueTallyNo(), "B");
			if (in0024VO != null) {
				sIssueTranEmpo = in0024VO.getIssueTranEmpo();
			}
		}
		
		dtr001VO = dtr001API.findLastlySigningRecord(sAppId, sFormId, sSignId);
		if (dtr001VO != null) {
			sApproveUser = dtr001VO.getApproveUser();
			
			if (StringUtils.isNotBlank(sApproveUser) && StringUtils.isNotBlank(sIssueTranEmpo)) {
				if (sApproveUser.equals(sIssueTranEmpo)) {
					if (sApproveUser.equals(this.userInfo.userNo())) {
						isPass = true;
					}
				}
			}
		} else {
			isPass = true;
		}
		
		return isPass;
	}
	
	/**
	 * @title  驗証儲區
	 * 
	 * @throws Exception
	 */
	public void checkStorageArea(String assignLocNo, String locNo, String childLocNo, String layer) throws Exception {
		// 2023.03.17 配合耀仁需求檢查申請主檔領料儲區為T，但儲位代碼如果為空的話需告知使用者
		if (StringUtils.isNoneBlank(assignLocNo)) {
			// 2023.04.27 工單領用申請於inu004.jsp新增明細時，傳進來的assignLocNo會是長度二碼
			if (assignLocNo.length() == 2) {
				assignLocNo = assignLocNo.substring(0, 1);
			}
			
			if ("T".equals(assignLocNo)) {
				if (StringUtils.isBlank(locNo)) {
					throw new Exception("儲位代碼第一碼未輸入，請檢查！");
				} else if (childLocNo != null) {
					if (StringUtils.isBlank(childLocNo)) {
						throw new Exception("儲位代碼第二碼未輸入，請檢查！");
					}	
				} else if (layer != null) {	
					if (StringUtils.isBlank(layer)) {
						throw new Exception("儲位代碼第三碼未輸入，請檢查！");
					}
				}
			}	
		}
	}
	
	/**
	 * @title  取得簽核編號
	 * 
	 * @param  in0024VO 申請單表頭檔清單值物件
	 * @return Map<String, Object> 簽核編號
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> findSignIdAndHandleUser(IN0024BO in0024BO) throws Exception {
		boolean bApprove = false;
		boolean bFlag01 = false;
		boolean bFlag02 = false;
		int iTotalAmt = 0;
		String sIssueTallyNo = "";
		String sIssueTranEmpo = "";
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
		IN0013VO in0013VO = null;
		IN0024VO in0024VO = new IN0024VO();
		// IN0025VO in0025VO = new IN0025VO();
		
		List<IN0025VO> lstIn0025VO = null;
		List<DUU001VO> lstDuu001VO = null;
		List<DUU001BO> lstDuu001BO = null;
		List<DTR001VO> lstFormRecord = null;
		List<DTR001VO> lstDtr001TempVO = new ArrayList<DTR001VO>();
		Date approveDate = null;
		
		if (in0024BO != null) {
			sIssueTallyNo = in0024BO.getIssueTallyNo();
		}
		
		in0024VO.setCompId(this.userInfo.compId());
		in0024VO.setIssueType("B");
		in0024VO.setIssueTallyNo(sIssueTallyNo);
		in0024VO = this.findByKey(in0024VO);
		
		if (in0024VO != null) {
			// T: 分存站，改由findByKey查詢完依查詢出的資料為主再判斷sSignId
			if ("T".equals(in0024VO.getAssignLocNo())) {
				sSignId = "B";
			} else {
				sSignId = "A";
			}
			// 申請人
			sIssueTranEmpo = in0024VO.getIssueTranEmpo();
			
			//mTemp = dtr001API.findLastSignRecord("INU004", sIssueTallyNo, sSignId);
			dtr001CurrentVO = dtr001API.findLastlySigningRecord("INU004", sIssueTallyNo, sSignId);
			
			if (dtr001CurrentVO != null) {
				//dtr001CurrentVO = mTemp.get("record") == null ? new DTR001VO() : (DTR001VO)mTemp.get("record");
				//mButtonAuth = mTemp.get("buttonAuth") == null ? new HashMap<String, Object>() : (Map<String, Object>)mTemp.get("buttonAuth");
				mButtonAuth = dtr001API.buttonAuthIsApproveAndVerify(dtr001CurrentVO, this.userInfo.userNo());
				bApprove = mButtonAuth.get("approve") == null ? false : (Boolean)mButtonAuth.get("approve");
				
				sStageId = dtr001CurrentVO.getStageId();
				sApproveUser = dtr001CurrentVO.getApproveUser();
				approveDate = dtr001CurrentVO.getApproveDate();
				
				// 如果第一階已有資料修改領料單狀態為[B審核中]
				if ("S1".equals(sStageId)) {
					in0024BO.setStus("B");
					this.changeStatus(in0024BO);
				}
				
				// 如果簽核在第一階且領料人和核准人都是自己的話狀態為[A申請中]
				if ("S1".equals(sStageId) && sIssueTranEmpo.equals(sApproveUser)) {
					in0024BO.setStus("A");
					this.changeStatus(in0024BO);
				}
				
				// 如果第一階段主管批示完到第二階段修改領料單狀態為[C待處理]
				if ("S2".equals(sStageId)) {
					in0024BO.setStus("C");
					this.changeStatus(in0024BO);
				}
				// 2023.03.09 主管簽核完更改狀態為[E已結案]
				if ("S4".equals(sStageId) && approveDate != null) {
					in0024BO.setStus("E");
					this.changeCloseCase(in0024BO);
					this.changeStatus(in0024BO);
				}
				
				if (bApprove) {
					if ("S1".equals(sStageId)) {
					} else if ("S2".equals(sStageId)) { 
						if ("A".equals(sSignId)) { // 第一階段結束，將簽核送給倉儲人員
							// mData.put("handleUser", in0024VO.getIssueTranEmpo());
						} else if ("B".equals(sSignId)) { // 交由分存站管理人員
							lstIn0025VO = this.listByKey(sIssueTallyNo, "C");
							if (lstIn0025VO != null && lstIn0025VO.size() > 0) {
								for (int i=0; i<1; i++) {
									in0013VO = new IN0013VO();
									in0013VO.setLocNo(lstIn0025VO.get(i) == null ? "" : lstIn0025VO.get(i).getLocNo());
									in0013VO.setChildLocNo(lstIn0025VO.get(i) == null ? "" : lstIn0025VO.get(i).getChildLocNo());
									in0013VO.setLayer(lstIn0025VO.get(i) == null ? "" : lstIn0025VO.get(i).getLayer());
									
									sHandleUser = in0013Service.findKeepEmpNo(in0013VO);
									
									if (StringUtil.isEmpty(sHandleUser)) {
										throw new Exception("無法取得分存站保管人，請檢查是否正確！");
									}
									mData.put("handleUser", sHandleUser);
								}
							} else {
								throw new Exception("無法取得分存站保管人，請檢查是否正確！");
							}
						}
					} else if ("S3".equals(sStageId)) {	
						/*
						if ("A".equals(sSignId)) { 
							if (!"G-IN-INU004-Undertaker-M42".equals(dtr001CurrentVO.getApproveUser())) {
								lstDuu001VO = duu001API.listUserByPostLevelNo("M421", "60");
								if (lstDuu001VO != null && lstDuu001VO.size() > 0) {
									mData.put("handleUser", lstDuu001VO.get(0).getUserNo());
								} else {
									throw new Exception("無法取得倉庫主管，請檢查是否正確！");
								}
							}
						} else if ("B".equals(sSignId)) { 
							
						}
						*/
						// 2023.04.14 改由取整個Level3的歷程，只要是前一個approveStatus為C(擬准)時，核准的鈕就要亮，反之都是擬准的按鈕亮
						lstFormRecord = dtr001API.listFormIdRecord("INU004", sIssueTallyNo, sSignId);
						for (int i=lstFormRecord.size(); i > lstFormRecord.size() - 2; i--) {
							dtr001TempVO = lstFormRecord.get(i-1);
							if (dtr001TempVO != null) {
								if ("A".equals(dtr001TempVO.getApproveStatus()) && i == lstFormRecord.size()) {
									// 最後一筆的簽核狀態是待處理的話
									bFlag01 = true;
								} else if ("B".equals(dtr001TempVO.getApproveStatus()) && i == lstFormRecord.size() - 1) {
									// 倒數第二筆的簽核狀態是擬准的話
									bFlag02 = true;
								}
							}
						}
					} else {
						mData.put("handleUser", "");
					}
				} else {
					// 第一階段
					if ("A".equals(sSignId)) {
						// 2023.01.18 如果直接是四級主管以上的職位來領取，則直接跳下一階段並設定領料人為承辦(條件增加為第一階段)
						// 2023.04.14 改為倉儲人員當承辦人
						if ("S1".equals(sStageId) && Integer.parseInt(this.userInfo.getLevelNo()) <= 70) {
							// mData.put("handleUser", in0024VO.getIssueTranEmpo());
							mData.put("handleUser", "G-IN-INU004-Undertaker-M42");
						}
					} else if ("B".equals(sSignId)) {
						lstFormRecord = dtr001API.listFormIdRecord("INU004", sIssueTallyNo, sSignId);
						for (int i=lstFormRecord.size(); i > lstFormRecord.size() - 2; i--) {
							dtr001TempVO = lstFormRecord.get(i-1);
							if (dtr001TempVO != null) {
								if ("S1".equals(dtr001TempVO.getStageId())) {
									// 最後二筆的簽核階段都是S1的話
									lstDtr001TempVO.add(dtr001TempVO);
								}	
							}
						}
						
						// 2023.04.25 利用簽核筆數來判斷，如大於等於2表示已簽過(申請單位)分存站管理人，否則反之並找出分存站管理人
						if (lstDtr001TempVO.size() >= 2) {
							sIssueTranEmpoBoss = dur001API.findSupervisor(sIssueTranEmpo);
							mData.put("handleUser", sIssueTranEmpoBoss);
						} else {
							// 2023.05.29 如果是四級以上主管申請時，則設定承辦人為G-IN-INU004-Undertaker-M42
							if (Integer.parseInt(this.userInfo.getLevelNo()) <= 70) {
								mData.put("handleUser", "G-IN-INU004-Undertaker-M42");
							} else {
								lstIn0025VO = this.listByKey(sIssueTallyNo, "C");
								if (lstIn0025VO != null && lstIn0025VO.size() > 0) {
									for (int i=0; i<1; i++) {
										in0013VO = new IN0013VO();
										in0013VO.setLocNo(lstIn0025VO.get(i) == null ? "" : lstIn0025VO.get(i).getLocNo());
										in0013VO.setChildLocNo(lstIn0025VO.get(i) == null ? "" : lstIn0025VO.get(i).getChildLocNo());
										in0013VO.setLayer(lstIn0025VO.get(i) == null ? "" : lstIn0025VO.get(i).getLayer());
										
										sHandleUser = in0013Service.findKeepEmpNo(in0013VO);
										
										if (StringUtil.isEmpty(sHandleUser)) {
											throw new Exception("無法取得分存站保管人，請檢查是否正確！");
										}
										mData.put("handleUser", sHandleUser);
									}
								} else {
									throw new Exception("無法取得分存站保管人，請檢查是否正確！");
								}
							}
						}
					}	
				}
			} else { 
				// 沒任何簽核的邏輯
				if ("A".equals(sSignId)) {
					// 2023.01.16 如果直接是四級主管以上的職位來領取，則直接跳下一階段並設定領料人為承辦
					// 2023.04.14 改為倉儲人員當承辦人
					if (Integer.parseInt(this.userInfo.getLevelNo()) <= 70) {
						//mData.put("handleUser", in0024VO.getIssueTranEmpo());
						mData.put("handleUser", "G-IN-INU004-Undertaker-M42");
					} else {
						mData.put("handleUser", "");
					}
				} else if ("B".equals(sSignId)) {
					lstIn0025VO = this.listByKey(sIssueTallyNo, "C");
					if (lstIn0025VO != null && lstIn0025VO.size() > 0) {
						// 2023.05.29 如果是四級以上主管申請時，則設定承辦人為G-IN-INU004-Undertaker-M42
						if (Integer.parseInt(this.userInfo.getLevelNo()) <= 70) {
							mData.put("handleUser", "G-IN-INU004-Undertaker-M42");
						} else {
							// 一般領料邏輯
							for (int i=0; i<1; i++) {
								in0013VO = new IN0013VO();
								in0013VO.setLocNo(lstIn0025VO.get(i) == null ? "" : lstIn0025VO.get(i).getLocNo());
								in0013VO.setChildLocNo(lstIn0025VO.get(i) == null ? "" : lstIn0025VO.get(i).getChildLocNo());
								in0013VO.setLayer(lstIn0025VO.get(i) == null ? "" : lstIn0025VO.get(i).getLayer());
								
								sHandleUser = in0013Service.findKeepEmpNo(in0013VO);
								
								if (StringUtil.isEmpty(sHandleUser)) {
									throw new Exception("無法取得分存站保管人，請檢查是否正確！");
								}
								mData.put("handleUser", sHandleUser);
							}
						}
					} else {
						// 2023.04.20 由於工單領用一開始不會選擇物料，所以無法取得分存站保管人
						// 由於從頁面inu004.jsp進來且如果沒有領料明細in0024BO.getLocNo()會是空值，故因此先判斷是否有值
						if (StringUtils.isNotBlank(in0024BO.getLocNo())) {
							in0013VO = new IN0013VO();
							in0013VO.setLocNo(in0024BO.getLocNo());
							in0013VO.setChildLocNo(in0024BO.getChildLocNo());
							in0013VO.setLayer(in0024BO.getLayer());
							
							sHandleUser = in0013Service.findKeepEmpNo(in0013VO);
							
							if (StringUtil.isEmpty(sHandleUser)) {
								throw new Exception("無法取得分存站保管人，請檢查是否正確！");
							}
							mData.put("handleUser", sHandleUser);
						}
					}
				}
			}
			
			mData.put("signId", sSignId);
			mData.put("stageId", sStageId);
			if (dtr001CurrentVO != null) {
				mData.put("approveUser", dtr001CurrentVO.getApproveUser());
				
				// 判斷按鈕前需判斷是否為ApproveUser或是群組G-IN-INU004-Undertaker-M42
				if (this.userInfo.userNo().equals(dtr001CurrentVO.getApproveUser()) || "G-IN-INU004-Undertaker-M42".equals(dtr001CurrentVO.getApproveUser())) {
					mData.put("btnManual", "true");
					if (bFlag01 && bFlag02) {
						// 符合條件則核准鈕要亮
						mData.put("btnVerify", "true");
						mData.put("btnApprove", "false");
						
						if ("A".equals(sSignId)) {
							lstDuu001VO = duu001API.listUserByPostLevelNo("M421", "60");
							if (lstDuu001VO != null && lstDuu001VO.size() > 0) {
								mData.put("handleUser", lstDuu001VO.get(0).getUserNo());
							} else {
								throw new Exception("無法取得倉庫主管，請檢查是否正確！");
							}
						} else if ("B".equals(sSignId)) {
							lstDuu001BO = dur001API.listSupervisorDetail(sIssueTranEmpo);
							for (DUU001BO duu001BO : lstDuu001BO) {
								if (duu001BO != null) {
									if ("60".equals(duu001BO.getPostLevelNo())) {
										mData.put("handleUser", duu001BO.getUserNo());
										break;
									}
								}
							}
							
						}
					} else {
						// 不符合條件則擬准鈕要亮
						mData.put("btnVerify", "false");
						mData.put("btnApprove", "true");
					}
				} else {
					mData.put("btnManual", "false");
				}
			}	
		}
		
		return mData;
	}
	
	
}
