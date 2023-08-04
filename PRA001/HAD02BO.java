package com.krtc.practice.bo;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import com.krtc.practice.vo.HAD02VO;

public class HAD02BO extends HAD02VO{

	private List<HAD02VO> dataTable;
	private List<HAD02VO> lsthad02VO;
	
	private int limit;
	private int offset;
	
	private String label;
	private String matrlNo;
	private String signId;

	private String issueTallyNoStart;
	private String issueTallyNoEnd;
	
	private String locNo;
	private String childLocNo;
	private String layer;
	private String locationName;
	private String acctCodeDesc;
	private String locName;
	private String showName;
	
	private String tranTallyNoBegin;
	private String tranTallyNoEnd;
	private String planTranDateBegin;
	private String planTranDateEnd;
	private String creDateBegin;
	private String creDateEnd;
	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMatrlNo() {
		return matrlNo;
	}

	public void setMatrlNo(String matrlNo) {
		this.matrlNo = matrlNo;
	}
	
	public String getSignId() {
		return signId;
	}

	public void setSignId(String signId) {
		this.signId = signId;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getIssueTallyNoStart() {
		return issueTallyNoStart;
	}

	public void setIssueTallyNoStart(String issueTallyNoStart) {
		this.issueTallyNoStart = issueTallyNoStart;
	}

	public String getIssueTallyNoEnd() {
		return issueTallyNoEnd;
	}

	public void setIssueTallyNoEnd(String issueTallyNoEnd) {
		this.issueTallyNoEnd = issueTallyNoEnd;
	}

	public List<HAD02VO> getDataTable() {
		return dataTable;
	}

	public void setDataTable(List<HAD02VO> dataTable) {
		this.dataTable = dataTable;
	}

	public List<HAD02VO> getLsthad02VO() {
		return lsthad02VO;
	}

	public void setLsthad02VO(List<HAD02VO> lsthad02VO) {
		this.lsthad02VO = lsthad02VO;
	}

	public List<HAD02VO> getLstIn0025VO() {
		return lsthad02VO;
	}

	public String getLocNo() {
		return locNo;
	}

	public void setLocNo(String locNo) {
		this.locNo = locNo;
	}

	public String getChildLocNo() {
		return childLocNo;
	}

	public void setChildLocNo(String childLocNo) {
		this.childLocNo = childLocNo;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAcctCodeDesc() {
		return acctCodeDesc;
	}

	public void setAcctCodeDesc(String acctCodeDesc) {
		this.acctCodeDesc = acctCodeDesc;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}


	public String getTranTallyNoBegin() {
		return tranTallyNoBegin;
	}

	public void setTranTallyNoBegin(String tranTallyNoBegin) {
		this.tranTallyNoBegin = tranTallyNoBegin;
	}

	public String getTranTallyNoEnd() {
		return tranTallyNoEnd;
	}

	public void setTranTallyNoEnd(String tranTallyNoEnd) {
		this.tranTallyNoEnd = tranTallyNoEnd;
	}

	public String getPlanTranDateBegin() {
		return planTranDateBegin;
	}

	public void setPlanTranDateBegin(String planTranDateBegin) {
		this.planTranDateBegin = planTranDateBegin;
	}

	public String getPlanTranDateEnd() {
		return planTranDateEnd;
	}

	public void setPlanTranDateEnd(String planTranDateEnd) {
		this.planTranDateEnd = planTranDateEnd;
	}

	public String getCreDateBegin() {
		return creDateBegin;
	}

	public void setCreDateBegin(String creDateBegin) {
		this.creDateBegin = creDateBegin;
	}

	public String getCreDateEnd() {
		return creDateEnd;
	}

	public void setCreDateEnd(String creDateEnd) {
		this.creDateEnd = creDateEnd;
	}


	// 複製VO的值至BO
	public void copyFromValueObject(HAD02VO vo) {
		try {
			BeanUtils.copyProperties(this, vo);
		} catch (Exception ex) {

		}
	}	
	
	
	
}
