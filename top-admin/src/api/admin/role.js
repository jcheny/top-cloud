import request from '@/utils/request'

export function getRoleList(params) {
  return request({
    url: '/user/role',
    method: 'get',
    params
  })
}

export function saveRole(params) {
  return request({
    url: '/user/role',
    method: 'post',
    data: params
  })
}

export function updateRole(params) {
  return request({
    url: '/user/role',
    method: 'put',
    data: params
  })
}

export function deleteRole(params) {
  return request({
    url: '/user/role',
    method: 'delete',
    data: params
  })
}

export function authorizeRole(params) {
  return request({
    url: '/user/role/authorize',
    method: 'post',
    data: params
  })
}

export function authorizeRoleRun(params) {
  return request({
    url: '/user/role/authorizeRun',
    method: 'post',
    data: params
  })
}

