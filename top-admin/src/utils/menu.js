/* Layout */
import Layout from '@/layout'

export default function transformMenuToRouter() {
  var dataRouters = []
  var menu = sessionStorage.getItem('menuTree')
  if (menu == null || menu === '') {
    console.log('没有可用的菜单')
    return []
  }
  var objMenu = JSON.parse(menu)
  objMenu.forEach(element => {
    var rootPath = element.targetUrl
    if (element.children.length > 0) {
      var childrenRouters = []
      element.children.forEach(ele => {
        var dataRouter = {
          path: `${ele.targetUrl}`,
          component: setView(rootPath, ele.targetUrl),
          name: ele.targetUrl.charAt(0).toUpperCase() + ele.targetUrl.slice(1),
          meta: { title: ele.name, icon: ele.icon, keys: ele.key }
        }
        childrenRouters.push(dataRouter)
      })
      var dataRouter = {
        path: `/${element.targetUrl}`,
        component: Layout,
        redirect: '/' + element.targetUrl + '/' + element.children[0].targetUrl,
        name: element.targetUrl.charAt(0).toUpperCase() + element.targetUrl.slice(1),
        meta: { title: element.name, icon: element.icon, keys: element.key },
        children: childrenRouters
      }
      dataRouters.push(dataRouter)
    }
  })
  return dataRouters
}

function setView(onePath, twoPath) {
  return () => Promise.resolve(require(`@/views/${onePath}/${twoPath}`).default)
}
