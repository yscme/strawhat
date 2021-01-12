//首页 标题模式
let index={
	data () {
      return {
		list:[],
		radio:'1',
		title:'',
		content:'',
		dialogTableVisible:false
      }
    },
    methods: {
		dialogclick(i){
			this.title=`标题: <span style="color:red">${i.title}</span>&nbsp;&nbsp;&nbsp;
			作者: <span style="color:red">${i.user.username}</span>&nbsp;&nbsp;&nbsp;
			时间: <span style="color:red">${i.time}</span>
			<span style="float: right;">${i.label}</span>`;
			this.content=i.content;
			this.dialogTableVisible=true;
		}
    },
	created(){
		axios.get('/blog/listall').then(resp=>{
			this.list=resp.data;
		}).catch(err=>{
			
		})
	},
	template:`
	<div>
	<div style="margin-bottom: 20px;">
	<router-link to="/"><el-radio v-model="radio" label="1" border>标题模式</el-radio></router-link>
    <router-link to="/indexContent"><el-radio v-model="radio" label="2" border>内容模式</el-radio></router-link>
	</div>
	  <ul class="infinite-list" style="overflow:auto;height: 660px;padding-left: 0px">
		<el-card v-for="i in list" class="box-card" shadow="hover" style="margin-bottom: 10px">
		<div @click="dialogclick(i)">
			<el-tag>标题: {{i.title}}</el-tag>
			<el-tag type="success">作者: {{i.user.username}}</el-tag>
			<el-tag type="info">时间: {{i.time}}</el-tag>
			<el-tag v-for="l in JSON.parse(i.label)" style="float: right;" effect="plain" size="mini">{{l}}</el-tag>
		</div>
		</el-card>
	  </ul>
<el-dialog :visible.sync="dialogTableVisible" :fullscreen="true">
  <div v-html="title"></div>
	<hr/>
  <div v-html="content"></div>
</el-dialog>
	</div>
	`
}
//首页 内容模式
let indexContent={
	data () {
      return {
		list:[],
		radio:'2',
		title:'',
		content:'',
		dialogTableVisible:false
      }
    },
    methods: {
		dialogclick(i){
			this.title=`标题: <span style="color:red">${i.title}</span>&nbsp;&nbsp;&nbsp;
			作者: <span style="color:red">${i.user.username}</span>&nbsp;&nbsp;&nbsp;
			时间: <span style="color:red">${i.time}</span>
			<span style="float: right;">${i.label}</span>`;
			this.content=i.content;
			this.dialogTableVisible=true;
		}
    },
	created(){
		axios.get('/blog/listall').then(resp=>{
			this.list=resp.data;
		}).catch(err=>{
			
		})
	},
	template:`
	<div>
	<div style="margin-bottom: 20px;">
	<router-link to="/"><el-radio v-model="radio" label="1" border>标题模式</el-radio></router-link>
    <router-link to="/indexContent"><el-radio v-model="radio" label="2" border>内容模式</el-radio></router-link>
	</div>
	  <ul class="infinite-list" style="overflow:auto;height:700px;padding-left: 0px">
		<el-card v-for="i in list" class="box-card" shadow="hover" style="margin-bottom: 10px">
		  <div slot="header" class="clearfix" @click="dialogclick(i)">
		    <el-tag>标题: {{i.title}}</el-tag>
			<el-tag type="success">作者: {{i.user.username}}</el-tag>
			<el-tag type="info">时间: {{i.time}}</el-tag>
			<el-tag v-for="l in JSON.parse(i.label)" style="float: right;" effect="plain" size="mini">{{l}}</el-tag>
		  </div>
		  <div class="text item" v-html="i.content">
		  </div>
		</el-card>
	  </ul>
<el-dialog :visible.sync="dialogTableVisible" :fullscreen="true">
  <div v-html="title"></div>
	<hr/>
  <div v-html="content"></div>
</el-dialog>
	</div>
	`
}