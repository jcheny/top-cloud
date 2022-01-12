import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    params: data
  })
}

export function getInfo() {
  return request({
    url: '/user/user/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/user/user/info',
    method: 'get'
  })
}
