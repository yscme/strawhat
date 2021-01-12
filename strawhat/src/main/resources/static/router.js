const router = new VueRouter({
	routes: [
		{
			path: '/',
			component: index
		},
		{
			path: '/indexContent',
			component: indexContent
		},
		{
			path: '/user',
			component: user
		},
		{
			path: '/writeblog',
			component: writeblog
		},
		{
			path: '/listblog',
			component: listblog
		},
		{
			path: '/listblogbak',
			component: listblogbak
		},
		{
			path: '/editblog',
			component: editblog
		},
		{
			path: '/tool',
			component: tool
		}
	]
	})
	
