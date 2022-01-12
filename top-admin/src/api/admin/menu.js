import request from '@/utils/request'

export function getMenuList(params) {
  return request({
    url: '/user/menu/menu',
    method: 'get',
    params
  })
}

export function getMenuTree(params) {
  return request({
    url: '/user/menu/list',
    method: 'get',
    params
  })
}
saveMenuBatch
export function saveMenu(params) {
  return request({
    url: '/user/menu',
    method: 'post',
    data: params
  })
}
export function saveMenuBatch(params) {
  return request({
    url: '/user/menu/batch',
    method: 'post',
    data: params
  })
}

export function updateMenu(params) {
  return request({
    url: '/user/menu',
    method: 'put',
    data: params
  })
}

export function deleteMenu(params) {
  return request({
    url: '/user/menu',
    method: 'delete',
    data: params
  })
}

export function menuAndUserTree(params) {
  return request({
    url: '/user/menu/menuAndUserTree',
    method: 'get',
    params
  })
}

export function userRoleAuthorize() {
  return request({
    url: '/user/menu/authorize',
    method: 'get'
  })
}

export function userTreeForLogin() {
  return request({
    url: '/user/menu/user/list',
    method: 'get'
  })
}

