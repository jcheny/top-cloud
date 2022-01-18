import request from '@/utils/request'

export function getGatewayRoutesList(params) {
    return request({
        url: '/system/route/page',
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

export function pushGatewayRoutes(params) {
    return request({
        url: '/system/route/push',
        method: 'get',
        params
    })
}

export function outlineGatewayRoutes(params) {
    return request({
        url: '/system/route/outline',
        method: 'get',
        params
    })
}
