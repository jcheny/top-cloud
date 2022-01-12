import request from '@/utils/request'

export function getLogList(params) {
  return request({
    url: '/system/log',
    method: 'get',
    params
  })
}

export function saveLog(params) {
  return request({
    url: '/system/log',
    method: 'post',
    data: params
  })
}

export function updateLog(params) {
  return request({
    url: '/system/log',
    method: 'put',
    data: params
  })
}

export function deleteLog(params) {
  return request({
    url: '/system/log',
    method: 'delete',
    data: params
  })
}
