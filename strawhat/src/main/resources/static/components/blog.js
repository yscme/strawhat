let writeblog={
	data(){
		return{
			title:'',
			editor:null,
			label:[],
			radio:'3'
		};
	},
	computed:{
		
	},
	methods:{
		getHtml(){
	    	  console.log(this.editor.txt.html());
	    },
		clear(){
			this.editor.txt.clear();
			this.$message({
			  type: 'success',
			  message: '清空成功!'
			});
		},
		write(){
			this.fullscreenLoading = true;
			axios.post("/blog/insert",{title:this.title,text:this.editor.txt.text(),content:this.editor.txt.html(),label:JSON.stringify(this.label)}).then(resp=>{
				if(resp.data.state){
					this.$message({
					  type: 'success',
					  message: '发布成功!'
					});
				}else{
					this.$message({
					  type: 'error',
					  message: resp.data.msg
					});
				}
			}).catch(err=>{
				this.$message({
				  type: 'error',
				  message: "异常，请联系管理员"
				});
			});
			this.fullscreenLoading = false;		
		},
		writebak(){
			this.fullscreenLoading = true;
			axios.post("/blog/insertbak",{title:this.title,text:this.editor.txt.text(),content:this.editor.txt.html(),label:JSON.stringify(this.label)}).then(resp=>{
				if(resp.data.state){
					this.$message({
					  type: 'success',
					  message: '保存成功!'
					});
				}else{
					this.$message({
					  type: 'error',
					  message: resp.data.msg
					});
				}
			}).catch(err=>{
				this.$message({
				  type: 'error',
				  message: "异常，请联系管理员"
				});
			});
			this.fullscreenLoading = false;
		}
	},
	created(){
		const E = window.wangEditor;
			this.editor = new E('#editor');
			this.editor.config.placeholder = "";
			this.editor.config.height = 500;
			this.editor.config.focus=true;
			this.editor.config.pasteIgnoreImg = false; //忽略粘贴的图片
			this.editor.config.pasteFilterStyle = false //关闭样式过滤
			this.editor.config.uploadImgShowBase64 = true;
			this.editor.$textElem.attr('contenteditable', true);
			this.editor.highlight = hljs;
			this.editor.config.languageTab = '    ';
			// 配置粘贴文本的内容处理
			//this.editor.config.pasteTextHandle = function (pasteStr) {
			    // 对粘贴的文本进行处理，然后返回处理后的结果
			   // return pasteStr + '巴拉巴拉'
			// }
	},
	mounted(){	
	    this.editor.create();
	 	// 禁用
	    //editor.disable();  
	    // 启用
	    //editor.enable();
	},
	template:`
	<div>
	<div style="margin-bottom: 20px;">
	<router-link to="/listblog"><el-radio v-model="radio" label="1" border>我的发布</el-radio></router-link>
    <router-link to="/listblogbak"><el-radio v-model="radio" label="2" border>我的草稿</el-radio></router-link>
	<router-link to="/writeblog"><el-radio v-model="radio" label="3" border>我要发布</el-radio></router-link>
	</div>
		<el-row :gutter="20">
		  	<el-col :span="16">
				<el-input
				  placeholder="请输入标题"
				  v-model="title"
				  clearable>
				</el-input>
				<el-checkbox-group v-model="label">
				  <el-checkbox label="知识"></el-checkbox>
				  <el-checkbox label="技术"></el-checkbox>
				  <el-checkbox label="分享"></el-checkbox>
				  <el-checkbox label="软件"></el-checkbox>
				  <el-checkbox label="疑问"></el-checkbox>
			 	  <el-checkbox label="活动"></el-checkbox>
				  <el-checkbox label="其他"></el-checkbox>
				</el-checkbox-group>
			</el-col>
		  	<el-col :span="8">
				<el-button-group>
				<el-tooltip class="item" effect="dark" content="发布博客" placement="top">
				 	<el-button @click="write()" type="primary" icon="el-icon-circle-plus-outline" plain></el-button>
				</el-tooltip>
				<el-tooltip class="item" effect="dark" content="保存到草稿" placement="top">
			  		<el-button @click="writebak()" type="primary" icon="el-icon-notebook-2" plain></el-button>
				</el-tooltip>
				<el-tooltip class="item" effect="dark" content="清空" placement="top">
				  	<el-button @click="clear()" type="primary" icon="el-icon-delete" plain></el-button>
				</el-tooltip>
				</el-button-group>	
			</el-col>
		</el-row>
				
		<div id="editor" style="margin-top: 20px;"></div>
	</div>
	`	
}

let listblog={
	data(){
		return{
			radio:'1',
			tableData: [],
			multipleSelection: []
		};
	},
	computed:{
		getIds(){
			let ids=[];
			for(let i=0;i<this.multipleSelection.length;i++){
				ids[i]=this.multipleSelection[i].id;
			}
			return ids;
		}
	},
	methods: {
		handleSelectionChange(val) {
			this.multipleSelection = val;
		},
		handleEdit(index, row) {
	        console.log(index, row);
 		},
		handleDelete(row){
			this.$confirm('此操作将永久删除该博客(id:'+row.id+' 标题:'+row.title+'), 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
				axios.delete(`blog/delete/${id}`).then(resp=>{
					if(resp.data.state){
						this.$message({type: 'success',message: '删除成功!'});
						axios.get('/blog/list/1').then(resp=>{this.tableData=resp.data;});
					}else{
						this.$message({type: 'success',message: resp.data.msg});
					}					
				}).catch(err=>{
					this.$message({type: 'success',message: '删除失败'});
				});
	        }).catch(() => {
	          this.$message({
	            type: 'info',
	            message: '已取消删除'
	          });          
	        });
		},
		moveAll(){
			this.$confirm('此操作将选中博客移至草稿, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
				console.log({ids:this.getIds});
				axios.put(`blog/setState/0/${this.getIds}`).then(resp=>{
					if(resp.data.state){
						this.$message({type: 'success',message: '移动成功!'});
						axios.get('/blog/list/1').then(resp=>{this.tableData=resp.data;});
					}else{
						this.$message({type: 'success',message: resp.data.msg});
					}					
				}).catch(err=>{
					this.$message({type: 'success',message: '移动失败'});
				});
	        }).catch(() => {
	          this.$message({
	            type: 'info',
	            message: '已取消移动'
	          });          
	        });
		},
		deleteAll(){
			this.$confirm('此操作将永久删除选中的博客, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
				console.log({ids:this.getIds});
				axios.delete(`blog/deleteall/${this.getIds}`).then(resp=>{
					if(resp.data.state){
						this.$message({type: 'success',message: '删除成功!'});
						axios.get('/blog/list/1').then(resp=>{this.tableData=resp.data;});
					}else{
						this.$message({type: 'success',message: resp.data.msg});
					}					
				}).catch(err=>{
					this.$message({type: 'success',message: '删除失败'});
				});
	        }).catch(() => {
	          this.$message({
	            type: 'info',
	            message: '已取消删除'
	          });          
	        });
		}
	},
	created(){
		axios.get('/blog/list/1').then(resp=>{
			this.tableData=resp.data;
		}).catch(err=>{
			
		})
	},
	mounted(){	
		
	},
	template:`
	<div>
	<div style="margin-bottom: 20px;">
	<router-link to="/listblog"><el-radio v-model="radio" label="1" border>我的发布</el-radio></router-link>
    <router-link to="/listblogbak"><el-radio v-model="radio" label="2" border>我的草稿</el-radio></router-link>
	<router-link to="/writeblog"><el-radio v-model="radio" label="3" border>我要发布</el-radio></router-link>
	</div>
	<el-button-group>
		<el-tooltip class="item" effect="dark" content="转移到草稿" placement="top">
	  		<el-button @click="moveAll()" type="primary" icon="el-icon-goods" plain></el-button>
		</el-tooltip>
		<el-tooltip class="item" effect="dark" content="删除选项" placement="top">
		  	<el-button @click="deleteAll()" type="primary" icon="el-icon-delete" plain></el-button>
		</el-tooltip>
	</el-button-group>
		<el-table
	    :data="tableData"
		height="680px"
		ref="multipleTable"
		@selection-change="handleSelectionChange"
		stripe
	    style="width: 100%">
		<el-table-column
	      type="selection"
	      width="55">
	    </el-table-column>
	    <el-table-column type="expand">
	      <template slot-scope="props">
				<div v-html="props.row.content"></div>
	      </template>
	    </el-table-column>
	    <el-table-column
	      label="ID"
		  sortable
	      prop="id">
	    </el-table-column>
	    <el-table-column
	      label="标题"
	      prop="title">
	    </el-table-column>
	    <el-table-column
	      label="标签"
	      prop="label">
	    </el-table-column>
		<el-table-column
	      label="时间"
		  sortable
	      prop="time">
	    </el-table-column>
		<el-table-column label="操作">
	      <template slot-scope="scope">
	        <el-button
	          size="mini"
	          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
	        <el-button
	          size="mini"
	          type="danger"
	          @click="handleDelete(scope.row)">删除</el-button>
	      </template>
	    </el-table-column>
	  </el-table>
	</div>
	`
}

let listblogbak={
	data(){
		return{
			radio:'2',
			tableData: [],
			multipleSelection: []
		};
	},
	computed:{
		getIds(){
			let ids=[];
			for(let i=0;i<this.multipleSelection.length;i++){
				ids[i]=this.multipleSelection[i].id;
			}
			return ids;
		}
	},
	methods: {
		handleSelectionChange(val) {
        	this.multipleSelection = val;
		},
		handleEdit(index, row) {
	        console.log(index, row);
 		},
		handleDelete(row){
			this.$confirm('此操作将永久删除该博客(id:'+row.id+' 标题:'+row.title+'), 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
				axios.delete(`blog/delete/${id}`).then(resp=>{
					if(resp.data.state){
						this.$message({type: 'success',message: '删除成功!'});
						axios.get('/blog/list/0').then(resp=>{this.tableData=resp.data;});
					}else{
						this.$message({type: 'success',message: resp.data.msg});
					}					
				}).catch(err=>{
					this.$message({type: 'success',message: '删除失败'});
				});
	        }).catch(() => {
	          this.$message({
	            type: 'info',
	            message: '已取消删除'
	          });          
	        });
		},
		moveAll(){
			this.$confirm('此操作将选中博客全部发布, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
				console.log({ids:this.getIds});
				axios.put(`blog/setState/1/${this.getIds}`).then(resp=>{
					if(resp.data.state){
						this.$message({type: 'success',message: '发布成功!'});
						axios.get('/blog/list/0').then(resp=>{this.tableData=resp.data;});
					}else{
						this.$message({type: 'success',message: resp.data.msg});
					}					
				}).catch(err=>{
					this.$message({type: 'success',message: '发布失败'});
				});
	        }).catch(() => {
	          this.$message({
	            type: 'info',
	            message: '已取消发布'
	          });          
	        });
		},
		deleteAll(){
			this.$confirm('此操作将永久删除选中的博客, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
				console.log({ids:this.getIds});
				axios.delete(`blog/deleteall/${this.getIds}`).then(resp=>{
					if(resp.data.state){
						this.$message({type: 'success',message: '删除成功!'});
						axios.get('/blog/list/0').then(resp=>{this.tableData=resp.data;});
					}else{
						this.$message({type: 'success',message: resp.data.msg});
					}					
				}).catch(err=>{
					this.$message({type: 'success',message: '删除失败'});
				});
	        }).catch(() => {
	          this.$message({
	            type: 'info',
	            message: '已取消删除'
	          });          
	        });
		}
	},
	created(){  
		
		axios.get('/blog/list/0').then(resp=>{
			this.tableData=resp.data;
		}).catch(err=>{
			
		})
	},
	mounted(){	

	},
	template:`
	<div>
	<div style="margin-bottom: 20px;">
	<router-link to="/listblog"><el-radio v-model="radio" label="1" border>我的发布</el-radio></router-link>
    <router-link to="/listblogbak"><el-radio v-model="radio" label="2" border>我的草稿</el-radio></router-link>
	<router-link to="/writeblog"><el-radio v-model="radio" label="3" border>我要发布</el-radio></router-link>
	</div>
	<el-button-group>
		<el-tooltip class="item" effect="dark" content="发布选项" placement="top">
	  		<el-button @click="moveAll()" type="primary" icon="el-icon-upload2" plain></el-button>
		</el-tooltip>
		<el-tooltip class="item" effect="dark" content="删除选项" placement="top">
		  	<el-button @click="deleteAll()" type="primary" icon="el-icon-delete" plain></el-button>
		</el-tooltip>
	</el-button-group>
		<el-table
	    :data="tableData"
		height="680px"
		ref="multipleTable"
		@selection-change="handleSelectionChange"
		stripe
	    style="width: 100%">
		<el-table-column
	      type="selection"
	      width="55">
	    </el-table-column>
	    <el-table-column type="expand">
	      <template slot-scope="props">
				<div v-html="props.row.content"></div>
	      </template>
	    </el-table-column>
	    <el-table-column
	      label="ID"
		  sortable
	      prop="id">
	    </el-table-column>
	    <el-table-column
	      label="标题"
	      prop="title">
	    </el-table-column>
	    <el-table-column
	      label="标签"
	      prop="label">
	    </el-table-column>
		<el-table-column
	      label="时间"
		  sortable
	      prop="time">
	    </el-table-column>
		<el-table-column label="操作">
	      <template slot-scope="scope">
	        <el-button
	          size="mini"
	          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
	        <el-button
	          size="mini"
	          type="danger"
	          @click="handleDelete(scope.row)">删除</el-button>
	      </template>
	    </el-table-column>
	  </el-table>
	</div>
	`
}