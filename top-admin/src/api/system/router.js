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
        url: '/system/gatewayRoutes',
        method: 'post',
        data: params
    })
}

export function updateGatewayRoutes(params) {
    return request({
        url: '/system/gatewayRoutes',
        method: 'put',
        data: params
    })
}

export function deleteGatewayRoutes(params) {
    return request({
        url: '/system/gatewayRoutes',
        method: 'delete',
        data: params
    })
}
