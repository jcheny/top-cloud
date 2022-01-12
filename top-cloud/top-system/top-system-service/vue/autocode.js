import request from '@/utils/request'

export function getAutoCodeList(params) {
    return request({
        url: '/system/AutoCode',
        method: 'get',
        params
    })
}

export function saveAutoCode(params) {
    return request({
        url: '/system/AutoCode',
        method: 'post',
        data: params
    })
}

export function updateAutoCode(params) {
    return request({
        url: '/system/AutoCode',
        method: 'put',
        data: params
    })
}

export function deleteAutoCode(params) {
    return request({
        url: '/system/AutoCode',
        method: 'delete',
        data: params
    })
}
