const router = new VueRouter({
	mode: 'history',
    base: '/app/',
	routes: [
		{
			path: '/',
			component: index,
			meta: {
				title: "首页",
				content: ""
			}
		},
		{
			path: '/path/indexContent',
			component: indexContent,
			meta: {
				title: "首页",
				content: ""
			}
		},
		
		{
			path: '/path/user',
			component: user,
			meta: {
				title: "个人中心",
				content: ""
			}
		},
		{
			path: '/path/writeblog',
			component: writeblog,
			meta: {
				title: "写博客",
				content: ""
			}
		},
		{
			path: '/path/listblog',
			component: listblog,
			meta: {
				title: "我的博客",
				content: ""
			}
		},
		{
			path: '/path/listblogbak',
			component: listblogbak,
			meta: {
				title: "我的草稿",
				content: ""
			}
		},
		{
			path: '/path/editblog',
			component: editblog,
			meta: {
				title: "更新博客",
				content: ""
			}
		},
		{
			path: '/path/tool',
			component: tool,
			meta: {
				title: "工具箱",
				content: ""
			}
		}
	]
	});
router.beforeEach((to, from, next) => {
	console.log(window.location.href)
    /* 路由发生变化修改页面meta */
    if (to.meta.content) {
        let head = document.getElementsByTagName('head');
        let meta = document.createElement('meta');
        meta.content = to.meta.content;
        head[0].appendChild(meta)
    }
    /* 路由发生变化修改页面title */
    if (to.meta.title) {
        document.title = to.meta.title;
    }
    next()
});
	
