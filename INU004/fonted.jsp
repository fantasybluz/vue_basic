<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value="/static/in/js/in.js" />"></script>
<script src="<c:url value="/static/in/vue/location.vue" />"></script>
<script src="<c:url value="/static/in/vue/location-t.vue?v=1.2411122" />"></script>
<script src="<c:url value="/static/in/vue/level1-loc.vue?v=1.2411122" />"></script>
<script src="<c:url value="/static/in/vue/inventory-type.vue" />"></script>
<script src="<c:url value="/static/in/vue/matrl-grade.vue" />"></script>
<script src="<c:url value="/static/in/vue/material.vue?v=1.0.0.20230421" />"></script>

<style type="text/css">
	.el-dialog {
		width: 1400px !important;
 		min-width: 1400px !important;
 		margin-top: 20px !important;
 		padding: 0px 20px 0px 20px;
 		/* 下列語法是在內頁顯示捲軸，沒加的話捲軸則顯示在外層
 		max-height: 90vh;
 		overflow-y: auto;
 		overflow-x: hidden;
 		*/
	}
	.el-form-item__label {
		padding: 0 0 0px;
		line-height: 0px;
		font-size: 16px;
	}
	.el-form-item.is-error .el-input__inner {
		border-color: #f56c6c !important;
	}
	.el-table--striped .el-table__body tr.el-table__row--striped.current-row td, .el-table__body tr.current-row>td {
		color: fff;
		background-color: #e4c011 !important;
	}
    
</style>

<!-- Return to Top -->
<a href="javascript:" id="return-to-top"><i class="fas fa-chevron-up"></i></a>

<!-- start content-body -->
<div class="content-body" id="appInu004" v-cloak>
	<!-- start row -->
	<div class="row">
		
		<!-- start 主檔編輯 -->
		<div id="edit" class="col-xl-12 col-md-12 col-12">
			<div class="card tasks">
				<div class="card-content collapse show">
					<div class="card-content collapse show">
						<div class="card-body">
							<div class="row">
								<div class="col-12 mb-1">
									<div class="table2"> 
										<div class="row">
											<div class="c-1">狀態</div>
											<div class="c-3">
												<el-select v-model="searchForm.stus" clearable placeholder="">
													<el-option v-for="item in applyStatusOptions" :label="item.codeNo + '-' + item.showName" :value="item.codeNo"> 
													</el-option>
												</el-select>
											</div>
											<div class="c-1">申請單據編號</div>
											<div class="c-3">
												<el-input v-model="searchForm.issueTallyNoStart" maxlength="8" show-word-limit></el-input>~
												<el-input v-model="searchForm.issueTallyNoEnd" maxlength="8" show-word-limit></el-input>
											</div>
											<div class="c-1">功能</div>
											<div class="c-3">
												<el-button type="primary" @click="listByKey"> 查詢</el-button>
												<el-button type="info" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"> 進階條件</el-button>
												<el-button type="info" @click="clear"> 清除條件</el-button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-12">
									<div class="collapse" id="collapseExample">
										<div class="row">
											<div class="col-12 mb-1">
												<div class="table2"> 
													<div class="row">
														<div class="c-1">指定儲區</div>
														<div class="c-3">
															<location :location.sync="searchForm.assignLocNo" :readonly="false"></location>
														</div>
														<div class="c-1">計劃領料日</div>
														<div class="c-3">
															<el-date-picker v-model="searchForm.planTranDateArr" type="daterange" value-format="yyyy-MM-dd" range-separator="~" start-placeholder="開始日期" end-placeholder="結束日期"></el-date-picker>
														</div>
														<div class="c-1" >合約案號</div>
														<div class="c-3">
															<el-input v-model="searchForm.conNo" maxlength="12" show-word-limit></el-input>
														</div>
													</div>
													<div class="row">
														<div class="c-1">申請職工編號</div>
														<div class="c-3">
															<single-user :user_no.sync="searchForm.creEmpNo" :readonly="false"></single-user>
														</div>
														<div class="c-1">新增日期</div>
														<div class="c-3">
															<el-date-picker v-model="searchForm.creDateArr" type="daterange" value-format="yyyy-MM-dd" range-separator="~" start-placeholder="開始日期" end-placeholder="結束日期"></el-date-picker>
														</div>
														<div class="c-1">預算/計劃編號</div>
														<div class="c-3">
															<el-input v-model="searchForm.civiNo" maxlength="9" show-word-limit>
														</div>
													</div>
													<div class="row">
														<div class="c-1">成本中心</div>
														<div class="c-3">
															<el-input v-model="searchForm.costCenter" maxlength="6" show-word-limit>
														</div>
														<div class="c-1">用途別</div>
														<div class="c-3">
															<el-select v-model="searchForm.acctCode" clearable placeholder="">
																<el-option v-for="item in acctCodeOptions" :label="item.acctCode + '-' + item.acctCodeDesc" :value="item.acctCode"> 
																</el-option>
															</el-select>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
							</div>
							
							<el-tabs :tab-position="tabPosition" v-model="activeTabs" @tab-click="handleClick">
								<el-tab-pane label="申請單清單" name="tab1">
								
									<div class="row">
										<div class="col-12 mb-1">
											<div class="table2"> 
												<div class="row">
											    	<div class="c-1">功能</div>
													<div class="c-11">
														<!-- 
														<el-button type="primary" @click="create('editForm')">新增</el-button>
												    	<el-button type="warning" @click="update('editForm')" :disabled="isUpdate">修改</el-button>
												    	<el-button type="danger" @click="remove" :disabled="isDelete">刪除</el-button>
														 -->
														
												    	<el-button type="danger" @click="remove">刪除</el-button>
												    	<el-button type="info" @click="print">列印領料單</el-button>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<el-table ref="searchTable" v-loading="loading" :data="searchForm.dataTable" @current-change="handleSelectChange" @selection-change="selectionChange" border style="width: 100%" highlight-current-row>
									    <el-table-column type="selection" align="center" width="50">
						   	 			</el-table-column>
									    <el-table-column label="項次" type="index" align="center" width="60">
									    	<template slot-scope="scope">
									    		<span>{{ scope.$index + 1 }}</span>
									    	</template>	
									    </el-table-column>
									    <!-- 
									    <el-table-column prop="" label="功能" width="80" sortable>
									    	<template slot-scope="scope">
									    		<div>
									    			<el-button type="text" @click="openModal(-1)">檢視</el-button>
									    			
									    			<el-button type="text">修改</el-button>
	                                           		<el-button type="text">刪除</el-button>
									    			
	                                           	</div>
									    	</template>
									    </el-table-column>
									     -->
									    <el-table-column align="center" label="檢視" width="150">
									    	<template slot-scope="scope">
									    		<!-- 
									    		<a href="javascript:;" @click="viewClick"><i class="fas fa-edit"></i></a>
									    		 -->
									    		 
									    		<a href="javascript:;" @click="openModal(scope.$index)">[查詢]</a> 
									    		<a href="javascript:;" @click="viewClick(scope.$index)">[明細]</a>
									    		<a href="javascript:;" @click="eipLink(scope.$index)">[EIP]</a>
									    	</template>
									    </el-table-column>
									    <el-table-column label="狀態" width="150">
									    	<template slot-scope="scope">
									    		<div>
	                                           		<template v-if="scope.row.stus == 'E'"><el-tag type="danger">{{ scope.row.stus | status }}</el-tag></template>
										    		<template v-else-if="scope.row.stus == 'A'"><el-tag type="warning">{{ scope.row.stus | status }}</el-tag></template>
										    		<template v-else><el-tag>{{ scope.row.stus | status }}</el-tag></template>
	                                           	</div>
									    	</template>
									    </el-table-column> 
									    <el-table-column label="單號" width="130">
									    	<template slot-scope="scope">
									    		{{ scope.row.issueTallyNo }}
									    	</template>
									    </el-table-column>
									    <el-table-column label="用途別" width="180">
									    	<template slot-scope="scope">
									    		{{ scope.row.acctCode | acctCode }}
									    	</template>
									    </el-table-column>
									    <el-table-column label="預定領料日" width="150">
									    	<template slot-scope="scope">
									    		{{ scope.row.planTranDate | date }}
									    	</template>
									    </el-table-column>
									    <el-table-column label="領料人" width="150">
									    	<template slot-scope="scope">
									    		{{ scope.row.issueTranEmpo | user }}
									    	</template>
									    </el-table-column>
									    <el-table-column label="儲區" width="230">
									    	<template slot-scope="scope">
									    		{{ scope.row.assignLocNo | loc }}
									    	</template>
									    </el-table-column>
									    <el-table-column label="核准日" width="150">
									    	<template slot-scope="scope">
									    		{{ scope.row.apprvDate | date }}
									    	</template>
									    </el-table-column>
									    <el-table-column label="結案日" width="150">
									    	<template slot-scope="scope">
									    		{{ scope.row.finalDate | date }}
									    	</template>
									    </el-table-column>
									</el-table>
									
									<div class="d-flex">
										<div class="mr-auto">
										    <el-pagination
										    	:current-page.sync="searchForm.currentPage"
										    	layout="total"
										    	:total="searchForm.total">
										    </el-pagination>
										</div>
									    <div class="">
										    <el-pagination background
										    	@size-change="searchHandleSizeChange"
										    	@current-change="searchHandleCurrentChange"
										    	:current-page.sync="searchForm.currentPage"
										    	:page-sizes="[5, 10, 25, 50, 100]"
										    	:page-size="10"
										    	layout="sizes, prev, pager, next"
										    	:total="searchForm.total">
										    </el-pagination>
										</div>
									</div>
								</el-tab-pane>
								
						   	 	<el-tab-pane label="申請單明細" name="tab2">
						   	 		<div class="el-dialog-div">
						   	 			<template v-if='workOrderVisible == true'>
							   	 			<div class="row">
												<div class="col-12 mb-1">
													<div class="table2"> 
														<div class="row">
													    	<div class="c-1">功能</div>
															<div class="c-11">
														    	<el-button type="primary" @click="openDetailModal('create')"> 新增</el-button>
														    	<el-button type="warning" @click="openDetailModal('update')">修改</el-button>
										    					<el-button type="danger" @click="removeMultiRow"> 刪除</el-button>
															</div>
														</div>
													</div>
												</div>
											</div>
										</template>	
										
							   	 		<el-table ref="multipleTable" :data="editDetailForm.dataTable" @selection-change="selectionDetailChange" border style="width: 100%">
							   	 			<el-table-column type="selection" align="center" width="50">
							   	 			</el-table-column>
										    <el-table-column label="項次" type="index" align="center" width="60">
										    	<template slot-scope="scope">
										    		<span>{{ scope.$index + 1 }}</span>
										    	</template>
										    </el-table-column>
										    <el-table-column label="品別" width="150">
										    	<template slot-scope="scope">
										    		<template v-if="scope.row.inventoryType == '02'">
										    			<el-tag>{{ scope.row.inventoryType | inventoryType }}</el-tag>
										    		</template>	
										    		<template v-else-if="scope.row.inventoryType == '03'">
										    			<el-tag type="warning">{{ scope.row.inventoryType | inventoryType }}</el-tag>
										    		</template>
										    		<template v-else-if="scope.row.inventoryType == '05'">
										    			<el-tag type="warning">{{ scope.row.inventoryType | inventoryType }}</el-tag>
										    		</template>
										    		<template v-else-if="scope.row.inventoryType == '06'">
										    			<el-tag type="danger">{{ scope.row.inventoryType | inventoryType }}</el-tag>
										    		</template>
											    </template>
											</el-table-column>
										    <el-table-column label="物料編碼" width="120">
										    	<template slot-scope="scope">
										    		{{ scope.row.matrlNo }}
										    	</template>
											</el-table-column>
										    <el-table-column label="品級" width="90">
										    	<template slot-scope="scope">
										    		{{ scope.row.matrlGrade | matrlGrade }}
										    	</template>
										    </el-table-column>
										    <el-table-column label="品名 / 規範" width="660">
										    	<template slot-scope="scope">
										    		<el-tag>{{ scope.row.cNmDesc }}</el-tag> <el-tag>{{ scope.row.nmSpec }}</el-tag>
										    	</template>
										    </el-table-column>
										    <el-table-column label="儲區名稱" width="180">
										    	<template slot-scope="scope">
										    		{{ scope.row.locNo | loc }}
										    	</template>
										    </el-table-column>
										    <el-table-column label="儲區代碼" width="140">
										    	<template slot-scope="scope">
										    		{{ scope.row.locNo }}{{ scope.row.childLocNo }}{{ scope.row.layer }}
										    	</template>
										    </el-table-column>
										    <el-table-column label="數量" width="100">
										    	<template slot-scope="scope">
										    		<div align="right">{{ scope.row.qty }}</div>
											    </template>
										    </el-table-column>
										</el-table>
										
										<div class="d-flex">
											<div class="mr-auto mt-1">
												<!-- 
												<el-button type="primary" @click="addRow">增列</el-button>
											    <el-button type="danger" @click="removeRow">刪列</el-button>
												 -->
											    
											</div>
										    <div class="">
											    <el-pagination background
											    	layout="total"
											    	:total="editDetailForm.total">
											    </el-pagination>
											</div>
										</div>
						   	 		</div>
						   	 	</el-tab-pane>
						   	 	
						   	 	<!-- 
						   	 	<el-tab-pane label="基本資料" name="tab3">
						    		<el-form :model="editForm" :rules="editRules" ref="editForm" label-width="100px"  label-position="top" class="demo-ruleForm">
							    		<div class="row">
											<div class="col-12 mb-1">
												<div class="table2"> 
							  						<div class="row"> 
										  				<div class="c-2">申請單據編號</div>
										  				<div class="c-4">{{ editForm.issueTallyNo }}</div> 
										  				<div class="c-2">用途別</div>
										  				<div class="c-4">{{ editForm.acctCode | acctCode }}</div> 
										  			</div>
							  						<div class="row"> 
										  				<div class="c-2">預定領料日</div>
										  				<div class="c-4">{{ editForm.planTranDate | date }}</div> 
										  				<div class="c-2">領料部門</div>
										  				<div class="c-4">{{ editForm.issueTranDeptNo }}</div> 
										  			</div>
										  			<div class="row"> 
										  				<div class="c-2">領料人職工編號 </div>
										  				<div class="c-4">{{ editForm.issueTranEmpo | user }}</div> 
										  				<div class="c-2">領料人聯繫電話</div>
										  				<div class="c-4"><el-input v-model="editForm.phone" maxlength="5" show-word-limit></el-input></div> 
										  			</div>
										  			<div class="row"> 
										  				<div class="c-2">狀態</div>
										  				<div class="c-4">{{ editForm.stus | status }}</div> 
										  				<div class="c-2">主管職工編號</div>
										  				<div class="c-4">{{ editForm.apprvEmpNo | user }}</div> 
										  			</div>
										  			<div class="row"> 
										  				<div class="c-2">結案日</div>
										  				<div class="c-4">{{ editForm.finalDate | date }}</div> 
										  				<div class="c-2">領料儲區</div>
										  				<div class="c-4">{{ editForm.assignLocNo | loc }}</div> 
										  			</div>
										  			
										  			<div class="row"> 
										  				<div class="c-2">維修系統別</div>
										  				<div class="c-4">{{ editForm.equipNo | equip }}</div> 
										  				<div class="c-2">配送地點</div>
										  				<div class="c-4">{{ editForm.location | loc }}</div> 
										  			</div>
										  			<div class="row"> 
										  				<div class="c-2">申請人職工編號</div>
										  				<div class="c-4">{{ editForm.creEmpNo | user }}</div> 
										  				<div class="c-2">申請日期</div>
										  				<div class="c-4">{{ editForm.creDate | date }}</div> 
										  			</div>
										  			<div class="row"> 
										  				<div class="c-2">修改人職工編號</div>
										  				<div class="c-4">{{ editForm.updEmpNo }}</div> 
										  				<div class="c-2">修改日期</div>
										  				<div class="c-4">{{ editForm.updDate }}</div> 
										  			</div>
							  					</div>
							  				</div>
							  			</div>
									</<el-form>	
						    	</el-tab-pane>
						   	 	 -->
						   	 	
						    </el-tabs>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- end 主檔編輯 -->
		
	</div>
	<!-- end row -->
	
	<template v-if='signingVisible'>
		<electronic-signing 
			:app-Id="appIdParam" 
			:form-Id="editForm.issueTallyNo" 
			:sign-Id="signIdParam" 
			:msg-Url="msgUrlParam" 
			:msg-Title="msgTitleParam" 
			:handle-User="handleUserParam" 
			:readonly="false" :btn-Manual="btnManual" 
			:btn-Verify="btnVerify" 
			:btn-Approve="btnApprove" 
			@signing-result="getSigningResult">
			</electronic-signing>
	</template>	
	
	<el-dialog title="領料單主檔內容查詢" :visible.sync="dialogVisible" width="30%" :append-to-body="true">
  		<el-form :model="editForm" :rules="editRules" ref="editForm" label-width="100px"  label-position="top" class="demo-ruleForm">
    		<div class="row">
				<div class="col-12 mb-1">
					<div class="table2"> 
  						<div class="row"> 
			  				<div class="c-2">申請單據編號</div>
			  				<div class="c-4">{{ editForm.issueTallyNo }}</div> 
			  				<div class="c-2">用途別</div>
			  				<div class="c-4">
			  					{{ editForm.acctCode | acctCode }}
			  				</div> 
			  			</div>
  						<div class="row"> 
			  				<div class="c-2">預定領料日</div>
			  				<div class="c-4">{{ editForm.planTranDate | date }}</div> 
			  				<div class="c-2">領料部門</div>
			  				<div class="c-4">{{ editForm.issueTranDeptNo | dept }}</div> 
			  			</div>
			  			<div class="row"> 
			  				
			  				<div class="c-2">領料人職工編號 </div>
			  				<div class="c-4">{{ editForm.issueTranEmpo | user }}</div> 
			  				<div class="c-2">領料人聯繫電話</div>
			  				<div class="c-4">{{ editForm.phone }}</div> 
			  			</div>
			  			<div class="row"> 
			  				<div class="c-2">狀態</div>
			  				<div class="c-4">{{ editForm.stus | status }}</div> 
			  				<div class="c-2">主管職工編號</div>
			  				<div class="c-4">{{ editForm.apprvEmpNo | user }}</div> 
			  			</div>
			  			<div class="row"> 
			  				<div class="c-2">結案日</div>
			  				<div class="c-4">{{  }}</div> 
			  				<div class="c-2">領料儲區</div>
			  				<div class="c-4">{{ editForm.assignLocNo | loc }}</div> 
			  			</div>
			  			<div class="row"> 
			  				<div class="c-2">維修系統別</div>
			  				<div class="c-4">{{ editForm.equipNo | equip }}</div> 
			  				<div class="c-2">配送地點</div>
			  				<div class="c-4">
			  					<template v-if="editForm.location == 'A'">A 大寮機廠中心倉庫</template>
			  					<template v-else-if="editForm.location == 'B'">B 南機廠倉庫</template>
			  					<template v-else-if="editForm.location == 'C'">C O5/R10</template>
			  					<template v-else-if="editForm.location == 'D'">D R13</template>
			  					<template v-else-if="editForm.location == 'E'">E 輕軌機廠	</template>
			  				</div> 
			  			</div>
			  			<div class="row"> 
			  				<div class="c-2">申請人職工編號</div>
			  				<div class="c-4">{{ editForm.creEmpNo | user }}</div> 
			  				<div class="c-2">申請日期</div>
			  				<div class="c-4">{{ editForm.creDate | date }}</div> 
			  			</div>
			  			<div class="row"> 
			  				<div class="c-2">修改人職工編號</div>
			  				<div class="c-4">{{ editForm.creEmpNo | user }}</div> 
			  				<div class="c-2">修改日期</div>
			  				<div class="c-4">{{ editForm.creDate | date }}</div> 
			  			</div>
			  			<div class="row">
			  				<div class="c-2">檔案上傳</div>
				  			<div class="c-10"><file-comp :do-Action="doAction" :form-Ticket="formTicket" :creator="creator" :system-Id="systemId" :zip-Password="zipPassword" :show-Mode="showMode" @again-action="againAction"></file-comp></div>  
						</div>
  					</div>
  				</div>
  			</div>
		</<el-form>
	</el-dialog>
	
	<el-dialog title="領料單明細檔內容維護" :visible.sync="dialogDetailVisible" width="30%" :append-to-body="true">
  		<el-form :model="editDetailForm" :rules="editDetailRules" ref="editDetailForm" label-width="100px"  label-position="top" class="demo-ruleForm">
    		<div class="row">
				<div class="col-12 mb-1">
					<div class="table2"> 
						<div class="row">
					    	<div class="c-1">功能</div>
							<div class="c-11">
						    	<el-button type="primary" @click="confirmDetail('editDetailForm')"> 確定</el-button>
						    	<el-button type="primary" @click="cancel"> 返回</el-button>
							</div>
						</div>
					</div>
				</div>
			</div>
			
    		<div class="row">
				<div class="col-12 mb-1">
					<div class="table2"> 
  						<div class="row"> 
			  				<div class="c-2">品別</div>
			  				<div class="c-4">
			  					<inventory-type :inventory_type.sync="editDetailForm.inventoryType" :readonly="false"></inventory-type>
			  				</div> 
			  				<div class="c-2">物料編碼</div>
			  				<div class="c-4">
			  					<material :material_no.sync="editDetailForm.matrlNo" @material-result="getMaterialItemResult($event)"></material>
			  				</div> 
			  			</div>
  						<div class="row"> 
			  				<div class="c-2">品名</div>
			  				<div class="c-4"><el-tag>{{ editDetailForm.cNmDesc }}</el-tag></div> 
			  				<div class="c-2">材質</div>
			  				<div class="c-4"><el-tag>{{ editDetailForm.quality }}</el-tag></div> 
			  			</div>
			  			<div class="row"> 
			  				<div class="c-2">規範</div>
			  				<div class="c-10"><el-tag>{{ editDetailForm.nmSpec }}</el-tag></div> 
			  			</div>
			  			<div class="row"> 
			  				
			  				<div class="c-2">單位 </div>
			  				<div class="c-4">
			  					<el-tag>{{ editDetailForm.unitInv }}</el-tag>
			  				</div> 
			  				<div class="c-2">品級</div>
			  				<div class="c-4">
			  					<matrl-grade :matrl_grade.sync="editDetailForm.matrlGrade" :readonly="true"></matrl-grade>
			  				</div> 
			  			</div>
			  			<div class="row"> 
			  				<div class="c-2">庫別</div>
			  				<div class="c-4">
			  					<level1-loc :level1_loc.sync="editDetailForm.locNo" :readonly="true"></level1-loc>
			  				</div> 
			  				<div class="c-2">申請數量</div>
			  				<div class="c-4">
			  					<el-input v-model="editDetailForm.qty" maxlength="10" show-word-limit></el-input>
			  				</div> 
			  			</div>
			  			<template v-if="editDetailForm.locNo.substring(0, 1) == 'T'">
			  				<div class="row"> 
			  					<div class="c-2">儲位代碼</div>
			  					<div class="c-10">
			  						<location-t :loc_no.sync="editDetailForm.locNo" :child_loc_no.sync="editDetailForm.childLocNo" :layer_no.sync="editDetailForm.layer"></location-t>
			  					</div> 
		  					</div>
		  				</template>
			  			<div class="row"> 
			  				<div class="c-2">申請人職工編號</div>
			  				<div class="c-4"><el-tag>{{ editDetailForm.creEmpNo | user }}</el-tag></div> 
			  				<div class="c-2">申請日期</div>
			  				<div class="c-4"><el-tag>{{ editDetailForm.creDate | date }}</el-tag></div> 
			  			</div>
  					</div>
  				</div>
  			</div>
		</<el-form>
	</el-dialog>		
</div>
<!-- end content-body -->

<script>
var defaultSearchForm = {
	stus: '',
	assignLocNo: '',
	planTranDateArr: [],
	conNo: '',
	creEmpNo: '',
	issueTallyNoStart: '',
	issueTallyNoEnd: '',
	issueTallyNo: '',
	creDateArr: [],
	civiNo: '',
	costCenter: '',
	acctCode: '',
	
	dataTable: [],
	total: 0,
	limit: 5,
	offset: 0,
	currentPage: 1,
};
var defaultEditForm = {
	issueTallyNo: '', 
	stus: '',
	
	dataTable: [],
	/*
	stus: '',
	equipNo: '',
	creEmpNo: '',
	creDate: '',
	updEmpNo: '',
	updDate: '',
	*/

};
var defaultEditDetailForm = {
	issueTallyNo: '',
	seqNo: '',
	matrlNo: '',
	matrlGrade: '',
	cNmDesc: '',
	nmSpec: '',
	quality: '',
	unitInv: '',
	locNo: '',
	qty: '',
	creEmpNo: '',
	creDate: '',
	
	dataTable: [],
	total: 0,
	limit: 5,
	offset: 0,
	currentPage: 1,
};

// date filter
Vue.filter('date', function (value, formatString) {
    //formatString = 'YYYY-MM-DD';
	if (value != null && value != '') {
		var year = value.substring(0, 4);
		var month = value.substring(4, 6);
		var day = value.substring(6);
    	return year + "-" + month + "-" + day;
	} else
		return '';
});

var app = new Vue({
	el: '#appInu004',
	data: {
		searchForm: Object.assign({}, defaultSearchForm),
		editForm: Object.assign({}, defaultEditForm),
		editDetailForm: Object.assign({}, defaultEditDetailForm),
		isValid: false,
		isValidDetail: false,
		checkAmt: '',
		
		doAction: '',
		formTicket: '',
		creator: '${sessionScope.userInfo.userNo}',
		systemId: 'IN',
		fileName: 0,
		zipPassword: '',
		showMode: true,
		
		showPurCode: false,
		reqNoQry: '',
		reqNoCopy: '',
		dialogVisible: false,
		dialogDetailVisible: false,
		classIsClosed: false,
		tabPosition: 'top',

		appIdParam: 'INU004',
		signIdParam: 'A',
		msgUrlParam: '',
		handleUserParam: '',
		issueTallyNoParam: '${param.issueTallyNo}',
		stageIdParam: '',
		btnManual: '',
		btnVerify: '',
		btnApprove: '',
		approveUser: '',
		msgTitleParam: '',
		
		doAction: '',
		formTicket: 'INU004',
		creator: '${sessionScope.userInfo.userNo}',
		systemId: 'in',
		fileName: 0,
		zipPassword: '',
		showMode: true,

		destinationDisabled: true,
		
		error: false,
		
		loading: false,

		assignItemVisible: false,
		assignDescItemVisible: false,
		issueTallyNoVisible: false,
		workOrderVisible: false,
		directDeptDisable: false,
		signingVisible: false,

		isUpdate: true,
		isConfirm: true,
		isCancel: true,
		isDelete: true,
		
		activeTabs: 'tab1',

		applyStatusOptions: [],
		acctCodeOptions: [],
		
		selectedItems: [],
		selectedDetailItems: [],

		editRules: {
		},
		
		editDetailRules: {
		},
	},
	async created() {
		if (this.issueTallyNoParam != '') {
			// 需在electronic-signing取值之前，將editForm.issueTallyNo塞入值，否則批示記錄的按鈕會失效
			this.searchForm.issueTallyNo = this.issueTallyNoParam;
			await this.searchParam();
		}
	},
	watch: {
	},	
	mounted() {
		//this.init(),
		this.listByKey(true);
		this.listAcctCode();
		this.listAllApplyStatus();
		
		//this.searchParam();
		//this.getEditDetailOptions();

	},
	methods: {
    	listAcctCode() {
			var vm = this;
			inst.get('/api/in/listAcctCode', {
				params: {
					dealCode: '54',
				}
			}).then(function (response) {
				vm.acctCodeOptions = response.data;
			})
		},
		listAllApplyStatus() {
			var vm = this;
			inst.get('/api/in/listAllApplyStatus').then(function (response) {
				vm.applyStatusOptions = response.data;
			})
		},
		searchParam() {
			var vm = this;

			return new Promise(resolve => {
				setTimeout(() => {
					if (vm.issueTallyNoParam != '') {
						// 設定查詢條件
						vm.editForm.issueTallyNo = vm.issueTallyNoParam;
						vm.editDetailForm.issueTallyNo = vm.issueTallyNoParam;
						// 啟動所有查詢
						vm.listDetailByKey(true)
						.then(vm.findByKey(vm.searchForm.issueTallyNo))
						.then(vm.findSignIdAndHandleUser(vm.editForm.issueTallyNo));
						// .then(vm.findSignIdAndHandleUser(vm.editForm.issueTallyNo, vm.editForm.assignLocNo, vm.editForm.issueTranEmpo));
					}
				}, 1000);
			});

		},
		listByKey(isFirst) {
			var vm = this;
			if(isFirst) {
		    	vm.searchForm.offset = 0;
		    	vm.searchForm.currentPage = 1;
		    }
			//console.log('limit 2: '+vm.editDetailForm.limit);
			//console.log('offset 2: '+vm.editDetailForm.offset);
			
		    inst.post('/in/inu004/listByKey', vm.searchForm).then(function (response) {
				// 查詢回傳的是List, 故用索引值來取物件
				let data = response.data;
				//console.log(data.dataTable);
				vm.searchForm.dataTable = data.dataTable;
			    vm.searchForm.total = data.total;
			})
			
		},
		findByKey(issueTallyNo) {
			//console.log('findByKey start...');
			var vm = this;
			
			return new Promise(function(resolve, reject) {
				console.log('findByKey start 1...');
				inst.get('/in/inu004/findByKey', {
					params:{
						issueTallyNo: issueTallyNo
					}
				}).then(function(response) {
					// 查詢回傳的是List, 故用索引值來取物件
					let data = response.data;
					vm.setForm(data);
					
					// 查詢簽核代碼和承辦群組
					// 2022.09.15 改由Promise來呼叫
					//vm.findSignIdAndHandleUser(data.issueTallyNo, data.assignLocNo, data.issueTranEmpo);
					
					// 更新檔案上傳內容
					// 配合DR設定在key值前加上系統別
					vm.formTicket = "IN" + data.issueTallyNo;
					vm.doAction = '4';
					
				});
				
			});

		},
		/*
		async function getData(issueTallyNo) {
			try { // 專注在成功
		    	await listDetailByKey(true);
		    	await findByKey(issueTallyNo);
		    	//console.log(data1, data2);
		  	}
		  	catch (err) { // 專注在錯誤
		    	console.log('catch', err);
		  	}
		},
		*/
		listDetailByKey(isFirst) {
			var vm = this;
			
			return new Promise(function(resolve, reject) {
				console.log('listDetailByKey start 1...');
				if(isFirst) {
			    	vm.editDetailForm.offset = 0;
			    	vm.editDetailForm.currentPage = 1;
			    }
				//console.log('limit 2: '+vm.editDetailForm.limit);
				//console.log('offset 2: '+vm.editDetailForm.offset);
			    inst.get('/in/inu004/listDetailByKeyWithMaterialDetail', {
				    params: {
				    	issueTallyNo: vm.editForm.issueTallyNo,
				    	limit: 101,
				    	offset: 0,
					}
				}).then(function (response) {
					console.log('listDetailByKey start 2...');
					// 查詢回傳的是List, 故用索引值來取物件
					const data = response.data;
					//console.log(data.dataTable);
					vm.editDetailForm.dataTable = data.dataTable;
				    vm.editDetailForm.total = data.total;
				    
				    resolve(data);
				})
			});
			
		},
		clear() {
			var vm = this;
			vm.searchForm = Object.assign({}, defaultSearchForm);	
		},
		remove() {
			console.log('remove in...');
			var vm = this;
			
			//console.log('vm.selectedItems: ');
			//console.log(vm.selectedItems);
			vm.editForm.dataTable = vm.selectedItems;
			
			//console.log('vm.editForm.dataTable: ');
			//console.log(vm.editForm.dataTable);

			this.$confirm('是否確定刪除該筆資料?', '提示', {
          		confirmButtonText: '確定',
          		cancelButtonText: '取消',
          		type: 'warning'
        	}).then(() => {

				inst.post('/in/inu004/delete', vm.editForm).then(function(response) {
					const data = response.data;
					//Object.assign(vm.searchForm, defaultSearchForm);
					swal(data.title, data.text, data.type);
					
					// 重新查詢出剛新增出來的資料
					vm.listByKey();

					// 按鈕權限
					//vm.buttonAuth(vm.editForm.issueTallyNo);
				})
       		}).catch(() => {
          		this.$message({
            		type: 'info',
            		message: '請求發生異常，已取消刪除！'
         		});          
        	});
		},
		confirmDetail(formName) {
			var vm = this;

			this.$confirm('是否確定該筆資料?', '提示', {
          		confirmButtonText: '確定',
          		cancelButtonText: '取消',
          		type: 'warning'
        	}).then(() => {

				this.$refs[formName].validate((valid) => {
					if (valid) {
						inst.post('/in/inu004/confirmDetail', vm.editDetailForm).then(function(response) {
							const data = response.data;
							vm.setForm(data.result);
							swal(data.title, data.text, data.type);

							// 重新查詢出剛新增出來的資料
							vm.listDetailByKey(true);

						})
					} else {
						return false;
					}
				});

       		}).catch(() => {
          		this.$message({
            		type: 'info',
            		message: '請求發生異常，已取消新增！'
         		});          
        	});
		},
		/*
		updateRow() {
			var vm = this;

			this.$confirm('是否確定修改該筆資料?', '提示', {
          		confirmButtonText: '確定',
          		cancelButtonText: '取消',
          		type: 'warning'
        	}).then(() => {

				this.$refs[formName].validate((valid) => {
					if (valid) {
						inst.post('/in/inu004/updateDetail', vm.editDetailForm).then(function(response) {
							const data = response.data;
							vm.setForm(data.result);
							swal(data.title, data.text, data.type);

							// 重新查詢出剛新增出來的資料
							vm.listByKey();

						})
					} else {
						return false;
					}
				});

       		}).catch(() => {
          		this.$message({
            		type: 'info',
            		message: '請求發生異常，已取消新增！'
         		});          
        	});
		},
		*/
        removeRow() {
			var vm = this;
			// 勾選的項目
			vm.selectedDetailItems.forEach(function(item, index, object) {
				//console.log('item2.mtrlNo: '+item2.mtrlNo);
				// 明細的項目
				//console.log('vm.editDetailForm.dataTable: '+vm.editDetailForm.dataTable);
				vm.editDetailForm.dataTable.forEach(function(item2, index2, object2) {
					//console.log('item.mtrlNo: '+item.mtrlNo);
					if (item.mtrlNo === item2.mtrlNo) {
    					object2.splice(index2, 1);
  					}
				});
			});
        },
        removeMultiRow() {
        	var vm = this;
        	
			vm.editDetailForm.dataTable = vm.selectedDetailItems;
			
			//console.log('vm.editForm.dataTable: ');
			//console.log(vm.editForm.dataTable);

			this.$confirm('是否確定刪除該筆資料?', '提示', {
          		confirmButtonText: '確定',
          		cancelButtonText: '取消',
          		type: 'warning'
        	}).then(() => {

				inst.post('/in/inu004/deleteMultiDetail', vm.editDetailForm).then(function(response) {
					const data = response.data;
					//Object.assign(vm.searchForm, defaultSearchForm);
					swal(data.title, data.text, data.type);
					
					// 重新查詢出剛新增出來的資料
					vm.listDetailByKey(true);
				})
				
       		}).catch(() => {
          		this.$message({
            		type: 'info',
            		message: '請求發生異常，已取消刪除！'
         		});          
        	});
        },
        cancel() {
        	this.dialogDetailVisible = false;
        },
		print() {
			//console.log("print in...");
			var vm = this;

			if (vm.selectedItems.length == 0) {
				alert('請選擇一筆資料！');
				return;
			}
			window.open(CONTEXT_URL  + "/in/inu004/print?issueTallyNo="+ vm.selectedItems[0].issueTallyNo, "_blank");
		},
		openModal(value) {
			//console.log("openModal in...");
			var vm = this;
			vm.dialogVisible = true;
			
			if (value < 0) {
				vm.editForm = Object.assign({}, defaultEditForm);
			}
        },
        openDetailModal(value) {
        	console.log('openDetailModal in ...');
        	var vm = this;
			vm.dialogDetailVisible = true;
			
			if (value == 'create') {
				console.log('openDetailModal create in ...');
				// vm.editDetailForm = Object.assign({}, defaultEditDetailForm);
				
				vm.editDetailForm.inventoryType = '';
				vm.editDetailForm.matrlNo = '';
				vm.editDetailForm.matrlGrade = '';
				vm.editDetailForm.cNmDesc = '';
				vm.editDetailForm.nmSpec = '';
				vm.editDetailForm.quality = '';
				vm.editDetailForm.unitInv = '';
				//vm.editDetailForm.locNo = '';
				vm.editDetailForm.qty = '';
				vm.editDetailForm.creEmpNo = '';
				vm.editDetailForm.creDate = '';
			} else if (value == 'update') {
				console.log('openDetailModal update in ...');
				vm.editDetailForm.seqNo = vm.selectedDetailItems[0].seqNo;
				vm.editDetailForm.inventoryType = vm.selectedDetailItems[0].inventoryType;
				vm.editDetailForm.matrlNo = vm.selectedDetailItems[0].matrlNo;
				vm.editDetailForm.matrlGrade = vm.selectedDetailItems[0].matrlGrade;
				vm.editDetailForm.cNmDesc = vm.selectedDetailItems[0].cNmDesc;
				vm.editDetailForm.nmSpec = vm.selectedDetailItems[0].nmSpec;
				vm.editDetailForm.quality = vm.selectedDetailItems[0].quality;
				vm.editDetailForm.unitInv = vm.selectedDetailItems[0].unitInv;
				//vm.editDetailForm.locNo = vm.selectedDetailItems[0].locNo;
				vm.editDetailForm.qty = vm.selectedDetailItems[0].qty;
				vm.editDetailForm.creEmpNo = vm.selectedDetailItems[0].creEmpNo;
				vm.editDetailForm.creDate = vm.selectedDetailItems[0].creDate;
			}
        },
		setForm(data) {
			var vm = this;
			let assignLocNo = '';
			let assignLocName = '';

			if (data != null) {
				vm.editForm = data;

				//確保每個按鈕動作都會連動到明細
				if (vm.editForm.issueTallyNo != '')
					vm.editDetailForm.issueTallyNo = vm.editForm.issueTallyNo;

				console.log('setForm start 1...');
				// 更新簽核參數
				// 確保每個按鈕動作都會更新簽核參數
				vm.msgUrlParam = "/in/inu004/index?issueTallyNo=" + vm.editForm.issueTallyNo + "&assignLocNo=" + vm.editForm.assignLocNo;
				
				if (vm.editForm.assignLocNo != '' && vm.editForm.assignLocNo != null) {
					assignLocNo = vm.editForm.assignLocNo;
					
					if ('D' == assignLocNo) {
						assignLocName = '淡海輕軌機廠倉庫';
					} else if ('E' == assignLocNo) {
						assignLocName = '器材工具儲區';
					} else if ('I' == assignLocNo) {
						assignLocName = '直接發料儲區';
					} else if ('L' == assignLocNo) {
						assignLocName = '高雄輕軌前鎮機廠';
					} else if ('M' == assignLocNo) {
						assignLocName = '大寮機廠中心倉庫';
					} else if ('N' == assignLocNo) {
						assignLocName = '北機廠倉庫';
					} else if ('S' == assignLocNo) {
						assignLocName = '南機廠倉庫';
					} else if ('T' == assignLocNo) {
						assignLocName = '分存站';
					} 
					vm.msgTitleParam = "儲區["+assignLocNo+assignLocName+"]-領料申請["+vm.editForm.issueTallyNo+"]，待您審核！";
				} else {
					vm.msgTitleParam = '';
				}
				
				console.log('setForm start 2...');
				console.log('setForm msgUrlParam: '+vm.msgUrlParam);
				console.log('setForm assignLocNo: '+assignLocNo);
				console.log('setForm msgTitleParam: '+vm.msgTitleParam);

				//console.log('useDate: '+data.useDate);
				// 將查詢單筆資料後, 將值塞進Options下拉選單
				//vm.cardNoOptions = vm.editForm.shipNo;
				//vm.disbItemOptions = vm.editForm.projPurNo;				

				if (data.useDate != null)
					vm.editForm.useDate = moment(data.useDate, "YYYYMMDD").format('YYYY-MM-DD');
				if (data.createDate != null)
					vm.editForm.createDate = moment(new Date(data.createDate)).format('YYYY-MM-DDTHH:mm');
				if (data.updateDate != null)
					vm.editForm.updateDate = moment(new Date(data.updateDate)).format('YYYY-MM-DDTHH:mm');

			}
		},
		searchHandleSizeChange(limit) {
			//console.log('searchHandleSizeChange limit 1: '+limit);
			var vm = this;
        	vm.searchForm.limit = limit;
			// 因searchHandleSizeChange()和searchHandleCurrentChange()同時呼叫listByKey()時會造成分頁有問題，故延遲500毫秒來防止錯誤 
			setTimeout( function() {
                vm.listByKey(false);
            }, 500);
      	},
		searchHandleCurrentChange(page) {
			//console.log('searchHandleCurrentChange page');
			var vm = this;
			// 忽略前筆數使用
		    vm.searchForm.offset = (page-1) * vm.searchForm.limit
		    vm.listByKey(false);
		},
		handleSelectChange(value) {
			console.log('handleSelectChange in ...');
			//console.log('value:'+value);
			var vm = this;

			//vm.currentRow = value;
			// 按下修改按鈕時會觸發此條件，造成value為空，故多一個判斷value != null
			if (value != null) {
				// 將傳進來的value直接塞進editForm物件, 不用再查詢一次
				vm.editForm.issueTallyNo = value.issueTallyNo;
				vm.editDetailForm.issueTallyNo = value.issueTallyNo;
				
				//console.log('value.assignLocNo: '+value.assignLocNo);
				
				// 2022.08.26 由於要執行 findSignIdAndHandleUser 且需要 editDetailForm.dataTable 中的資料(需要六碼儲位)，故先執行 vm.listDetailByKey(true);
				//vm.listDetailByKey(true);
				// 由於經過反覆測試不重新查詢 findByKey 而改用setForm, 會導致第一次查詢批示記錄時無法顯示資料
				//vm.findByKey(vm.editForm.issueTallyNo);
				
				// 2022.08.31 改用 Promise 來確認 vm.listDetailByKey 先作完後，再作 vm.findByKey
				//let promise = new Promise((resolve) => {
					//return resolve(vm.listDetailByKey(true))
				//}).then(() => {
					//vm.findByKey(vm.editForm.issueTallyNo);
				//})
				// 2022.09.15 上列還是會有問題，改成下列
				vm.listDetailByKey(true)
				.then(vm.findByKey(vm.editForm.issueTallyNo))
				.then(vm.findSignIdAndHandleUser(vm.editForm.issueTallyNo));
				// .then(vm.findSignIdAndHandleUser(vm.editForm.issueTallyNo, vm.editForm.assignLocNo, vm.editForm.issueTranEmpo));
				
				// 2022.09.15 使用上未成功
				//vm.getData(vm.editForm.issueTallyNo)
		        
		        console.log('value.acctCode: '+value.acctCode)
      			if (value.acctCode == '52') {
      				this.workOrderVisible = true
      			} else {
      				this.workOrderVisible = false
      			}
			}
		},
		getSigningResult(data) {
			var vm = this;
			console.log('============ getSigningResult start============');
           	console.log('data.status='+data.status+' signId='+data.signId+' stageId='+data.stageId+' action='+data.action);
			var sLastlestStageId = ''; // 最後一筆階段
			var sApproveDate = '';     // 核准日期
			var sApproveStatus = '';   // 核准狀態
			
			if (data.status == 'Y') {
				// vm.findSignIdAndHandleUser(vm.editForm.issueTallyNo, vm.editForm.assignLocNo, vm.editForm.issueTranEmpo);
				vm.findSignIdAndHandleUser(vm.editForm.issueTallyNo);
				vm.loading = true;
				setTimeout( function() {
					// 2023.01.18 電子簽核元件需重新整理, 因需重新判斷是否需代入指定承辦人
					location.replace(CONTEXT_URL + "/in/inu004/index?issueTallyNo=" + vm.editForm.issueTallyNo);
					vm.loading = false;
	            }, 5000);
           	}
           	
		   	console.log('============ getSigningResult end============');
		},
		//findSignIdAndHandleUser(issueTallyNo, assignLocNo, issueTranEmpo) {
		findSignIdAndHandleUser(issueTallyNo) {	
			var vm = this;
			//console.log('findSignIdAndHandleUser in...');
			//console.log('vm.editDetailForm.dataTable.length: '+vm.editDetailForm.dataTable.length);
			//console.log('vm.editDetailForm.dataTable[0].locNo: '+vm.editDetailForm.dataTable[0].locNo);
			//console.log('vm.editDetailForm.dataTable[0].childLocNo: '+vm.editDetailForm.dataTable[0].childLocNo);
			//console.log('vm.editDetailForm.dataTable[0].layer: '+vm.editDetailForm.dataTable[0].layer);
			
			/*
			let locNo = '';
			let childLocNo = '';
			let layer = '';
			
			if (vm.editDetailForm.dataTable.length > 0) {
				locNo = vm.editDetailForm.dataTable[0].locNo
				childLocNo = vm.editDetailForm.dataTable[0].childLocNo
				layer = vm.editDetailForm.dataTable[0].layer
			}
			*/
			vm.signingVisible = false;
			return new Promise(function(resolve, reject) {
				// console.log('findSignIdAndHandleUser start 1...');
				
				// console.log('findSignIdAndHandleUser start 1-1...');
				setTimeout(() => {
					// console.log('findSignIdAndHandleUser start 1-2...');
					// 查詢簽核代碼和承辦群組
					inst.get('/in/inu004/findSignIdAndHandleUser', {
						params:{
							//assignLocNo: assignLocNo,
							//issueTranEmpo: issueTranEmpo,
							issueTallyNo: issueTallyNo,
							/*
							locNo: locNo,
							childLocNo: childLocNo,
							layer: layer,
							*/
						}
					}).then(function(response) {
						// console.log('findSignIdAndHandleUser start 2...');
						
						// 查詢回傳的是List, 故用索引值來取物件
						const data = response.data;
						
						vm.error = data.error;
						vm.handleUserParam = data.handleUser;
						vm.signIdParam = data.signId;
						vm.stageIdParam = data.stageId;
						vm.approveUser = data.approveUser;
						vm.btnManual = data.btnManual;
						vm.btnVerify = data.btnVerify;
						vm.btnApprove = data.btnApprove;
						console.log('error: '+vm.error);
						
						if (vm.error) {
							//swal(data.title, data.text, data.type);
							swal("取得分存站承辧人為空值，請確認設定分存站承辧人是否正確！", "", "error");
						}
						
						vm.signingVisible = true;
						// 2023.04.14 控制擬准和核准按鈕
						vm.electronicSigningButton();
					});
				}, 1000)
				
			});	
		},
		handleClick(tab, event) {
			var vm = this;
        	//console.log('tab.name: '+tab.name);
			//console.log('vm.editFormMp51.inspNo: '+vm.editFormMp51.inspNo);
      	},
      	viewClick(index) {
      		console.log('viewClick in...')
      		var vm = this;
      		this.activeTabs = 'tab2';
      		
      		console.log('viewClick assignLocNo: '+vm.searchForm.dataTable[index].assignLocNo);
      		vm.editDetailForm.locNo = vm.searchForm.dataTable[index].assignLocNo;
      		console.log('viewClick locNo: '+vm.editDetailForm.locNo);
      	},
      	eipLink(index) {
      		var vm = this;
      		console.log('eipLink: '+vm.searchForm.dataTable[index].issueTallyNo);
      		let issueTallyNo = vm.searchForm.dataTable[index].issueTallyNo;
      		if(window.location.hostname.match('erptest')  ) {
      			//location.replace(CONTEXT_URL + "/ds/d005/eip?url=http://test.krtco.com.tw/erp/in/injstst05?step=step0&txtFunc=I&compIdInq=krtc&issueTypeInq=C&issueTallyNoInqFr="+issueTallyNo+"&issueTallyNoInqTo="+issueTallyNo);
      			window.open(CONTEXT_URL  + "/ds/d005/eip?url=http://test.krtco.com.tw/erp/in/injstst05?step=step0&txtFunc=I&compIdInq=krtc&issueTypeInq=C&issueTallyNoInqFr="+issueTallyNo+"&issueTallyNoInqTo="+issueTallyNo, "_blank");
		    } else { 
		    	//location.replace(CONTEXT_URL + "/ds/d005/eip?url=http://prod.krtco.com.tw/erp/in/injstst05?step=step0&txtFunc=I&compIdInq=krtc&issueTypeInq=C&issueTallyNoInqFr="+issueTallyNo+"&issueTallyNoInqTo="+issueTallyNo);
		    	window.open(CONTEXT_URL  + "/ds/d005/eip?url=http://prod.krtco.com.tw/erp/in/injstst05?step=step0&txtFunc=I&compIdInq=krtc&issueTypeInq=C&issueTallyNoInqFr="+issueTallyNo+"&issueTallyNoInqTo="+issueTallyNo, "_blank");
		    }
      	},
      	selectionChange(val) {
			this.selectedItems = val;
		},
		selectionDetailChange(val) {
			this.selectedDetailItems = val;
		},
		getMaterialItemResult(event) {
			//console.log('event', event);
			//console.log('data', data);
			var vm = this;
                 
			vm.editDetailForm.cNmDesc = event.cNmDesc;
			vm.editDetailForm.nmSpec = event.nmSpec;
			vm.editDetailForm.quality = event.quality;
			vm.editDetailForm.unitInv = event.unitInv;
			vm.editDetailForm.matrlGrade = event.matrlGrade;
		},
		againAction(data) {
			var vm = this;
			vm.doAction = data;
		},
		electronicSigningButton() {
			console.log('electronicSigningButton in...')
			var vm = this;
			console.log('vm.approveUser: '+vm.approveUser)
			console.log('vm.stageIdParam: '+vm.stageIdParam)
			if (vm.stageIdParam != "S3") {
				//vm.btnManual = 'true';
				/*
				if (vm.approveUser == 'G-IN-INU004-Undertaker-M42') {
					vm.btnVerify = 'false';
					vm.btnApprove = 'true';
				} else {
					vm.btnVerify = 'true';
					vm.btnApprove = 'false';
				}
				*/
				vm.btnManual = '';
				vm.btnVerify = '';
				vm.btnApprove = '';
			}
		},
	}
})

</script>