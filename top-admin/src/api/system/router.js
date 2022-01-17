import request from '@/utils/request'

export function getGatewayRoutesList(params) {
    return request({
        url: '/system/route',
        method: 'get',
        params
    })
}

export function saveGatewayRoutes(params) {
    return request({
        url: '/system/route',
        method: 'post',
        data: params
    })
}

export function updateGatewayRoutes(params) {
    return request({
        url: '/system/route',
        method: 'put',
        data: params
    })
}

export function deleteGatewayRoutes(params) {
    return request({
        url: '/system/route',
        method: 'delete',
        data: params
    })
}
