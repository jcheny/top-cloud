import request from '@/utils/request'

export function getAutoCodeList(params) {
    return request({
        url: '/system/autoCode/page',
        method: 'get',
        params
    })
}

export function saveAutoCode(params) {
    return request({
        url: '/system/autoCode',
        method: 'post',
        data: params
    })
}

export function updateAutoCode(params) {
    return request({
        url: '/system/autoCode',
        method: 'put',
        data: params
    })
}

export function deleteAutoCode(params) {
    return request({
        url: '/system/autoCode',
        method: 'delete',
        data: params
    })
}

export function checkAble(params) {
    return request({
        url: '/system/autoCode/check/' + params,
        method: 'get'
    })
}

export function autoAble(params) {
    return request({
        url: '/system/autoCode/auto/' + params,
        method: 'get'
    })
}
