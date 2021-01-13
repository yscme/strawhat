let user={
	data(){
		return {
			userData:{id:0,username:'',about:{个性标签:'',用户等级:'',注册时间:''},blogs:[]}
		}
	},
	computed:{
		countBlog(){
			let blogs=this.userData.blogs;
			let bk=0,cg=0;
			for(let i=0;i<blogs.length;i++){
				if(blogs[i].state==1){
					bk++;
				}else if(blogs[i].state==0){
					cg++;
				}
			}
			return {bk:bk,cg:cg};
		}
	},
	created(){
		axios.get("/user/getuser").then(resp=>{
			let user=resp.data;
			user.about=JSON.parse(user.about);
			this.userData=user;
		}).catch(()=>{
			
		}) 
	},
	template:`
	<el-card class="box-card" :body-style="{height: '700px'}">
	  	<el-row :gutter="20">
		  	<el-col :span="24">
				用户ID: <el-link type="danger">{{userData.id}}</el-link><br/>
				用户名: <el-link type="danger">{{userData.username}}</el-link><br/><br/>
				个性标签: <el-link type="primary">{{userData.about.个性标签}}</el-link><br/>
				用户等级: <el-link type="primary">{{userData.about.用户等级}}</el-link><br/>
				注册时间: <el-link type="primary">{{userData.about.注册时间}}</el-link><br/>
				博客数量: <el-link type="primary">{{countBlog.bk}}</el-link><br/>
				草稿数量: <el-link type="primary">{{countBlog.cg}}</el-link><br/>
				评论数量: <el-link type="primary">开发中</el-link><br/>
				获赞数量: <el-link type="primary">开发中</el-link><br/>
				被踩数量: <el-link type="primary">开发中</el-link><br/>
			</el-col>
		</el-row>
	</el-card>
	`
}


let error={
	template:`<h1>无权访问！</h1>`
}
