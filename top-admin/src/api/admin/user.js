import request from '@/utils/request'

export function getUserList(params) {
  return request({
    url: '/user/user',
    method: 'get',
    params
  })
}

export function saveUser(params) {
  return request({
    url: '/user/user',
    method: 'post',
    data: params
  })
}

export function updateUser(params) {
  return request({
    url: '/user/user',
    method: 'put',
    data: params
  })
}

export function deleteUser(params) {
  return request({
    url: '/user/user',
    method: 'delete',
    data: params
  })
}

export function updatePassword(params) {
  return request({
    url: '/user/user/updatePwd',
    method: 'put',
    data: params
  })
}
