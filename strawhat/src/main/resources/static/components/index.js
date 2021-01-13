//首页 标题模式
let index={
	data () {
      return {
		list:[],
		radio:'1'
      }
    },
    methods: {
		dialogclick(id){
			window.open(`/app/blog/${id}`)
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
    <router-link to="/path/indexContent"><el-radio v-model="radio" label="2" border>内容模式</el-radio></router-link>
	</div>
	  <ul class="infinite-list" style="overflow:auto;height: 660px;padding-left: 0px">
		<el-card v-for="i in list" class="box-card" shadow="hover" style="margin-bottom: 10px">
		<div @click="dialogclick(i.id)">
			<el-tag>标题: {{i.title}}</el-tag>
			<el-tag type="success">作者: {{i.user.username}}</el-tag>
			<el-tag type="info">时间: {{i.time}}</el-tag>
			<el-tag v-for="l in JSON.parse(i.label)" style="float: right;" effect="plain" size="mini">{{l}}</el-tag>
		</div>
		</el-card>
	  </ul>
	</div>
	`
}
//首页 内容模式
let indexContent={
	data () {
      return {
		list:[],
		radio:'2'
      }
    },
    methods: {
		dialogclick(id){
			window.open(`/app/blog/${id}`)
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
    <router-link to="/path/indexContent"><el-radio v-model="radio" label="2" border>内容模式</el-radio></router-link>
	</div>
	  <ul class="infinite-list" style="overflow:auto;height:700px;padding-left: 0px">
		<el-card v-for="i in list" class="box-card" shadow="hover" style="margin-bottom: 10px">
		  <div slot="header" class="clearfix" @click="dialogclick(i.id)">
		    <el-tag>标题: {{i.title}}</el-tag>
			<el-tag type="success">作者: {{i.user.username}}</el-tag>
			<el-tag type="info">时间: {{i.time}}</el-tag>
			<el-tag v-for="l in JSON.parse(i.label)" style="float: right;" effect="plain" size="mini">{{l}}</el-tag>
		  </div>
		  <div class="text item" v-html="i.content">
		  </div>
		</el-card>
	  </ul>
	</div>
	`
}
