import request from '@/utils/request'

export function getConfigList(params) {
    return request({
        url: '/system/config',
        method: 'get',
        params
    })
}

export function saveConfig(params) {
    return request({
        url: '/system/config',
        method: 'post',
        data: params
    })
}

export function updateConfig(params) {
    return request({
        url: '/system/config',
        method: 'put',
        data: params
    })
}

export function deleteConfig(params) {
    return request({
        url: '/system/config',
        method: 'delete',
        data: params
    })
}
