import { userRoleAuthorize, userTreeForLogin } from '@/api/admin/menu'
import { constantRoutes } from '@/router'
import transformMenuToRouter from '@/utils/menu'
const getDefaultState = () => {
  return {
    menuTree: {},
    authorize: [],
    routes: [],
    addRoutes: []
  }
}
function hasPermission(authorize, route) {
  if (route.meta && route.meta.keys) {
    return authorize.some(role => route.meta.keys.includes(role))
  } else {
    return true
  }
}
export function filterAsyncRoutes(routes, authorize) {
  const res = []
  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(authorize, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, authorize)
      }
      res.push(tmp)
    }
  })
  return res
}
const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_MENUTREE: (state, menuTree) => {
    state.menuTree = menuTree
  },
  SET_AUTHORIZE: (state, authorize) => {
    state.authorize = authorize
  },
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  loadAuthorize({ commit }) {
    return new Promise((resolve, reject) => {
      userRoleAuthorize().then(response => {
        var authorize = []
        response.data.forEach(element => {
          authorize.push(element.authority)
        })
        sessionStorage.setItem('authorize', JSON.stringify(authorize))
        commit('SET_AUTHORIZE', authorize)
        resolve(authorize)
      }).catch(error => {
        console.log(error)
        reject(error)
      })
    })
  },
  loadMenu({ commit }) {
    return new Promise((resolve, reject) => {
      userTreeForLogin().then(response => {
        sessionStorage.setItem('menuTree', JSON.stringify(response.data))
        commit('SET_MENUTREE', response.data)
        resolve(response)
      }).catch(error => {
        console.log(error)
        reject(error)
      })
    })
  },
  generateRoutes({ commit }, authorize) {
    return new Promise(resolve => {
      const asyncRoutes = transformMenuToRouter()
      const accessedRoutes = filterAsyncRoutes(asyncRoutes, authorize)
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  },

  // remove token
  removeAuthorize({ commit }) {
    return new Promise(resolve => {
      sessionStorage.removeItem('menuTree')
      sessionStorage.removeItem('authorize')
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

