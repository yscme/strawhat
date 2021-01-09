let user={
	template:`
	<el-card class="box-card">
	  <div v-for="o in 4" :key="o" class="text item">
	    {{'列表内容 ' + o }}
	  </div>
	</el-card>
	`
}


let error={
	template:`<h1>无权访问！</h1>`
}
