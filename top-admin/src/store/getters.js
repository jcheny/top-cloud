const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  menuTree: state => state.authorize.menuTree,
  roleId: state => state.user.roleId,
  roleName: state => state.user.roleName,
  permission_routes: state => state.authorize.routes,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  hasPermission: state => (auth) => {
    var authorize = state.authorize.authorize.length === 0 ? JSON.parse(localStorage.getItem('authorize')) : state.authorize.authorize
    var flag = false
    authorize.array.forEach(element => {
      if (element === auth) {
        flag = true
      }
    })
    return flag
  }
}
export default getters
