<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <style type="text/css">
            .msg {
                color: red;
                font-size: 16px;
                font-weight: bold;
                text-align: left;
            }

            .title {
                color: blue;
                font-size: 16px;
                text-align: left;
            }


            .el-form-item {
                margin-bottom: 8px
            }

            @media screen and (max-width: 900px) {
                .el-dialog {
                    width: 90% !important;
                    min-width: 90% !important;
                }
            }
        </style>

        <div class="content-body" id="pra001" v-cloak>

            <h1 class="card-title">位置設定資料維護</h1>
            <div style="margin: 20px;"></div>
            <div class="table2">
                <div class="row">
                    <div class="c-1">功能</div>
                    <div class="col">
                        <el-button type="success" icon="fas fa-plus" @click="dialogCreateVisible = true">新增</el-button>
                        <el-button id="insert" type="primary" @click="listByKey(true)">查詢</el-button>
                    </div>
                </div>
            </div>
            <!-- Start dialog -->
            <el-dialog title="新增位置設定" :visible.sync="dialogCreateVisible">
                <el-form :model="createForm">
                    <el-form-item label="位置代碼" :label-width="formLabelWidth">
                        <el-input v-model="createForm.locNo" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="位置描述" :label-width="formLabelWidth">
                        <el-input v-model="createForm.locDesc" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="狀態" :label-width="formLabelWidth">
                        <el-input v-model="createForm.status" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="新增人員" :label-width="formLabelWidth">
                        <el-input v-model="createForm.crtEmpNo" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="新增日期" :label-width="formLabelWidth">
                        <el-input v-model="createForm.crtDate" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="更新人員" :label-width="formLabelWidth">
                        <el-input v-model="createForm.uptEmpNo" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="更新日期" :label-width="formLabelWidth">
                        <el-input v-model="createForm.uptDate" autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogCreateVisible = false">關閉</el-button>
                    <el-button type="primary" @click="insert">新增</el-button>
                </div>
            </el-dialog>


            <div style="margin: 20px;"></div>
            <div class="col-12 mb-1">
                <el-table ref="searchTable" :data="searchForm.dataTable" highlight-current-row
                    @current-change="handleSelectChange" @row-click="handleRowClick" @selection-change="selectionChange"
                    border style="width: 100%">
                    <el-table-column label="項次" type="index" align="center" width=75>
                        <template slot-scope="scope">
                            <span>{{ scope.$index + 1 }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="locNo" label="位置代碼">
                        <template slot-scope="scope">
                            <div>
                                <el-tag>{{ scope.row.locNo }}</el-tag>
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="locDesc" label="位置描述">
                        <template slot-scope="scope">
                            <div>
                                {{ scope.row.locDesc }}
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="status" label="狀態">
                        <template slot-scope="scope">
                            <div>
                                {{ scope.row.status }}
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="crtEmpNo" label="新增人員">
                        <template slot-scope="scope">
                            <div>
                                {{ scope.row.crtEmpNo }}
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="crtDate" label="新增日期">
                        <template slot-scope="scope">
                            <div>
                                {{ scope.row.crtDate}}
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="uptEmpNo" label="更新人員">
                        <template slot-scope="scope">
                            <div>
                                {{ scope.row.uptEmpNo}}
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="uptDate" label="更新日期">
                        <template slot-scope="scope">
                            <div>
                                {{ scope.row.uptDate}}
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" width="120" align="center">
                        <template slot-scope="scope">
                            <el-button size="medium" type="text" @click="remove(scope.row.locNo, scope.row.locDesc)">
                                <span style="color: red"> 刪除 <i class="el-icon-delete"></i> </span>
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="margin: 20px;"></div>
                <div class="d-flex">
                    <div class="ml-auto">
                        <el-pagination background @size-change="searchHandleSizeChange"
                            @current-change="searchHandleCurrentChange" :current-page.sync="searchForm.currentPage"
                            :page-sizes="[5, 10, 25, 50, 100]" :page-size="5" layout="total, sizes, prev, pager, next"
                            :total="searchForm.total">
                        </el-pagination>
                    </div>
                </div>
                <div style="margin: 20px;"></div>
                <div id="edit" class="col-xl-12 col-md-12 col-12">
                    <div class="card tasks">
                        <div class="card-content collapse show">
                            <div :class="{bevel: classIsClosed}">
                                <div class="card-body">
                                    <el-tabs :tab-position="tabPosition" style="">
                                        <el-tab-pane label="資料維護">
                                            <el-form :model="editForm" :rules="editRules" ref="editForm"
                                                label-width="100px" label-position="top" class="demo-ruleForm">
                                                <el-row class="mt-1" :gutter="20">
                                                    <el-col :xs="24" :sm="24" :md="12" :lg="6" :xl="6">
                                                        <el-form-item label="位置代碼">
                                                            <el-tag>{{ editForm.locNo}}</el-tag>
                                                        </el-form-item>
                                                    </el-col>
                                                    <el-col :xs="24" :sm="24" :md="12" :lg="6" :xl="6">
                                                        <el-form-item label="位置敘述">
                                                            <el-input v-model="editForm.locDesc"></el-input>
                                                        </el-form-item>
                                                    </el-col>
                                                    <el-col :xs="24" :sm="24" :md="12" :lg="6" :xl="6">
                                                        <el-form-item label="狀態" prop="status">
                                                            <el-tag>{{ editForm.status }}</el-tag>
                                                        </el-form-item>
                                                    </el-col>
                                                </el-row>
                                                <el-row :gutter="20">
                                                    <el-col :xs="24" :sm="24" :md="12" :lg="6" :xl="6">
                                                        <el-form-item label="新增人員">
                                                            <el-tag>{{ editForm.crtEmpNo}}</el-tag>
                                                        </el-form-item>
                                                    </el-col>
                                                    <el-col :xs="24" :sm="24" :md="12" :lg="6" :xl="6">
                                                        <el-form-item label="新增時間">
                                                            <el-input v-model="editForm.crtDate"></el-input>
                                                        </el-form-item>
                                                    </el-col>
                                                    <el-col :xs="24" :sm="24" :md="12" :lg="6" :xl="6">
                                                        <el-form-item label="更新人員">
                                                            <el-tag>{{ editForm.uptEmpNo }}</el-tag>
                                                        </el-form-item>
                                                    </el-col>
                                                    <el-col :xs="24" :sm="24" :md="12" :lg="6" :xl="6">
                                                        <el-form-item label="更新日期">
                                                         <el-input v-model="editForm.uptDate"></el-input>
                                                        </el-form-item>
                                                    </el-col>
                                                </el-row>
                                            </el-form>
                                            <hr>
                                            <el-button type="warning" icon="fas fa-edit" @click="update('editForm')">
                                                修改</el-button>
                                        </el-tab-pane>
                                    </el-tabs>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            	
            	<template v-if='signingVisible'>
					<electronic-signing :app-Id="appIdParam" :form-Id="editForm.locNo" :sign-Id="signIdParam" :msg-Url="msgUrlParam" :msg-Title="msgTitleParam" :handle-User="handleUserParam" :readonly="false" :btn-Manual="btnManual" :btn-Verify="btnVerify" :btn-Approve="btnApprove" @signing-result="getSigningResult">
					</electronic-signing>
				</template>	
            
            </div>
       </div> 

            <script>
                var defaultSearchForm = {
                    locNo: '',
                    locDesc: '',
                    status: '',
                    crtEmpNo: '',
                    crtDate: '',
                    uptEmpNo: '',
                    uptDate: '',
                    dataTable: [],
                    limit: 5,
                    offset: 0,
                    total: 0,
                    currentPage: 1,
                };
                var defaultCreateForm = {
                    locNo: '',
                    locDesc: '',
                    status: '',
                    crtEmpNo: '',
                    crtDate: '',
                    uptEmpNo: '',
                    uptDate: '',
                };

                var defaultEditForm = {
                    locNo: '',
                    locDesc: '',
                    status: '',
                    crtEmpNo: '',
                    crtDate: '',
                    uptEmpNo: '',
                    uptDate: '',
                };
                var app = new Vue({
                    el: "#pra001",
                    data: {
                        searchForm: Object.assign({}, defaultSearchForm),
                        createForm: Object.assign({}, defaultCreateForm),
                        editForm: Object.assign({}, defaultEditForm),
                        dataTable: [],
                        tabPosition: 'top',
                        dialogCreateVisible: false,
                        selectedItems: [],
                        formLabelWidth: '120px',

                        classIsClosed: false,
                        
                        signingVisible: false,
                        
                		appIdParam: 'PRA001',
                		signIdParam: 'A',
                		msgUrlParam: '',
                		handleUserParam: '',
                		locNoParam: '${param.locNo}',
                		stageIdParam: '',
                		btnManual: '',
                		btnVerify: '',
                		btnApprove: '',
                		approveUser: '',
                		msgTitleParam: '',
                		error: false,
                		loading: false,
                        
                		editRules: {
                			locDesc: [
                            	{ required: true, message: '位置描述不得為空', trigger: 'blur' },
                          	],
                			crtDate: [
                            	{ required: true, message: '新增時間不得為空', trigger: 'blur' },
                          	],
                		},
                    },
                    async created(){
                    	if(this.locNoParam != ''){
                    		this.searchFrom.locNo = this.locNoParam;
                    		await this.searchParam();
                    	}
                    },
                    mounted() {
                        this.listByKey(true);
                    },
                    methods: {
                        listByKey(isFirst) {
                            var vm = this;
                            if (isFirst) {
                                vm.searchForm.offset = 0;
                                vm.searchForm.currentPage = 1;
                            }
                            inst.post('/pr/pra001/listByKey', vm.searchForm).then((response) => {
                                const data = response.data;
                                console.log(data);
                                vm.searchForm.dataTable = data.dataTable;
                                vm.searchForm.total = data.total;
                            })
                        },
                        findByKey(locNo) {
                            var vm = this;
                            inst.get('/pr/pra001/findByKey', {
                                params: {
                                    locNo: locNo,
                                }
                            }).then(function (response) {
                                // 查詢回傳的是List, 故用索引值來取物件
                                let data = response.data;
                                vm.setForm(data);
                                
                            });
                        },
                        searchParam(){
                        	var vm = this;
                			return new Promise(resolve => {
                				setTimeout(() => {
                					if (vm.locNoParam != '') {
                						// 設定查詢條件
                						vm.editForm.locNo = vm.locNo;
                						// 啟動所有查詢
                						vm.listDetailByKey(true)
                						.then(vm.findByKey(vm.searchForm.locNo))
                						.then(vm.findSignIdAndHandleUser(vm.editForm.locNo));
                						// .then(vm.findSignIdAndHandleUser(vm.editForm.issueTallyNo, vm.editForm.assignLocNo, vm.editForm.issueTranEmpo));
                					}
                				}, 1000);
                			});
                        },
                        insert() {
                            var vm = this;
                            console.log('Insert()');
                            inst.post('/pr/pra001/insert', vm.createForm)
                                .then(function (response) {
                                    const data = response.data;
                                    console.log('data',)
                                    if (data.isSuccess) {
                                        vm.$message.success(data.msg)
                                    } else {
                                        swal('', data.msg, 'error');
                                    }
                                    vm.listByKey(true);
                                })
                        },
                        remove(locNo, locDesc) {
                            if (confirm('是否要刪除')) {
                                var vm = this;
                                inst.post('/pr/pra001/remove',
                                    {
                                        locNo: locNo,
                                        locDesc: locDesc,
                                    }).then(function (response) {
                                        const data = response.data;
                                        vm.listByKey(true);
                                        console.log('remove()');
                                        vm.$message.success(data.msg);
                                    })

                            }
                        },
                        openDialog() {
                            var vm = this;
                            vm.createForm = Object.assign({}, defaultCreateForm);
                            vm.createDialog = true;
                        },
                        searchHandleCurrentChange(page) {
                            //console.log('searchHandleCurrentChange page');
                            var vm = this;
                            // 忽略前筆數使用
                            vm.searchForm.offset = (page - 1) * vm.searchForm.limit;
                            vm.listByKey(false);
                        },
                        searchHandleSizeChange(limit) {
                            //console.log('searchHandleSizeChange limit 1: '+limit);
                            var vm = this;
                            vm.searchForm.limit = limit;
                            // 因searchHandleSizeChange()和searchHandleCurrentChange()同時呼叫listByKey()時會造成分頁有問題，故延遲500毫秒來防止錯誤 
                            setTimeout(function () {
                                vm.listByKey(false);
                            }, 500);
                        },
                        handleRowClick() {
                            //增加錨點功能，點選後直接跳至下方明細檔
                            this.$el.querySelector('#edit').scrollIntoView();
                        },
                        handleSelectChange(value) {
                            console.log('handleSelectChange in ...');
                            var vm = this;
					
                            //vm.currentRow = value;
                            // 按下修改按鈕時會觸發此條件，造成value為空，故多一個判斷value != null
                            if (value != null) {
                                // 將傳進來的value直接塞進editForm物件, 不用再查詢一次
                                vm.editForm.locNo = value.locNo;
                                vm.editForm.locDesc = value.locDesc;
                                vm.editForm.status = value.status;
                                vm.editForm.crtEmpNo = value.crtEmpNo;
                                vm.editForm.crtDate = value.crtDate;
                                vm.editForm.uptEmpNo = value.uptEmpNo;
                                vm.editForm.uptDate = value.uptDate; 
                                
                                vm.findSignIdAndHandleUser(vm.editForm.locNo);
                            }
                        },
                      	selectionChange(val) {
                			this.selectedItems = val;
                		},
                        update(formName) {
                            var vm = this;

                            this.$refs[formName].validate((valid) => {
                                if (valid) {
                                    swal({
                                        title: "是否確定修改該筆資料?",
                                        text: "",
                                        icon: "warning",
                                        buttons: {
                                            cancel: {
                                                text: "取消",
                                                value: null,
                                                visible: true,
                                                className: "",
                                                closeModal: true,
                                            },
                                            confirm: {
                                                text: "修改",
                                                value: true,
                                                visible: true,
                                                className: "",
                                                closeModal: false
                                            }
                                        }
                                    })
                                        .then((isConfirm) => {
                                            if (isConfirm) {
                                                inst.post('/pr/pra001/update', vm.editForm).then((response) => {
                                                    let data = response.data;
                                                    swal(data.title, data.text, data.type);
                                                    if (data.type === 'success')
                                                        vm.listByKey(false);
                                                });
                                            }
                                        });
                                } else {
                                    return false;
                                }
                            });
                        },
                		getSigningResult(data) {
                			var vm = this;
                			console.log('============ getSigningResult start============');
                           	console.log('data.status='+data.status+' signId='+data.signId+' stageId='+data.stageId+' action='+data.action);
                			
                			if (data.status == 'Y') {
                				// vm.findSignIdAndHandleUser(vm.editForm.issueTallyNo, vm.editForm.assignLocNo, vm.editForm.issueTranEmpo);
                				vm.findSignIdAndHandleUser(vm.editForm.locNo);
                				vm.loading = true;
                           	}
                           	
                		   	console.log('============ getSigningResult end============');
                		},
                		findSignIdAndHandleUser(locNo) {	
                			var vm = this;
                			//console.log('findSignIdAndHandleUser in...');
                			//console.log('vm.editDetailForm.dataTable.length: '+vm.editDetailForm.dataTable.length);
                			//console.log('vm.editDetailForm.dataTable[0].locNo: '+vm.editDetailForm.dataTable[0].locNo);
                			//console.log('vm.editDetailForm.dataTable[0].childLocNo: '+vm.editDetailForm.dataTable[0].childLocNo);
                			//console.log('vm.editDetailForm.dataTable[0].layer: '+vm.editDetailForm.dataTable[0].layer);
                			
                			vm.signingVisible = false;
                			return new Promise(function(resolve, reject) {
                				console.log('findSignIdAndHandleUser start 1-1...');
                				
                				setTimeout(() => {
                				 	console.log('findSignIdAndHandleUser start 1-2...');
                					// 查詢簽核代碼和承辦群組
                					inst.get('/pr/pra004/findSignIdAndHandleUser', {
                						params:{
                							locNo: locNo,
                						}
                					}).then(function(response) {
                						console.log('findSignIdAndHandleUser start 2...');
                						
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
                						
                						if(vm.error){
                							swal("取得資料為空值，請確認資料是否正確", "", "error");
                						}
                						
                						vm.signingVisible = true;
                						// 2023.04.14 控制擬准和核准按鈕
                						vm.electronicSigningButton();
                					});
                				}, 1000)
                				
                			});	
                		},
                		setForm(data){
                			var vm = this;
                			let assignLocNo = '';
                			let assignLocName = '';
                			if(data != null){
                				vm.editForm = data;
                			console.log('setForm start 1....');
                			vm.msgUrlParam = "/pr/pra001/index?locNo="+vm.editForm.locNo;
                			}
                			console.log('setForm start 2....');
                			console.log('setForm msgUrlParam: ' + vm.msgUrlParam);
                		},
                		electronicSigningButton(){
                			console.log('electronicSigningButton in...')
                			var vm = this;
                			console.log('vm.approveUser:' + vm.approveUser)
                			console.log('vm.stageIdParam:' + vm.stageIdParam)
                			if(vm.stageIdParm != "S3"){
                				vm.btnManual = '';
                				vm.btnVerify = '';
                				vm.btnApprove = '';
                			}
                		},
                		
                    }
                });
</script>