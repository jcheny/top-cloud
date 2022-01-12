package com.ihave.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihave.api.R;
import com.ihave.constants.SysConstant;
import com.ihave.entity.Client;
import com.ihave.entity.OauthClientDto;
import com.ihave.fegin.OauthClientFegin;
import com.ihave.service.ClientService;
import com.ihave.vo.ClientVo;
import com.ihave.wrapper.ClientWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 客户端的controller
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/7/28 下午4:18
 */
@RestController
@RequestMapping("/client")
@Api(tags = "客户端控制器")
public class ClientController implements OauthClientFegin {

    @Autowired
    private ClientService clientService;

    /**
     * 查询客户端信息
     * 若带有clientId参数，则查询对应id的客户端信息
     * @param parameters
     * @return
     */
    @GetMapping
    @ApiOperation(value="客户端列表")
    public R<IPage<ClientVo>> list(@RequestParam Map<String,Object> parameters){
        // 分页查询
        Object pageNum = Optional.ofNullable(parameters.get("pageNum")).orElse(SysConstant.DEFAULT_PAGE_NUM);
        Object pageSize = Optional.ofNullable(parameters.get("pageSize")).orElse(SysConstant.DEFAULT_PAGE_SIZE);
        Page<Client> page = new Page<>(Integer.parseInt(pageNum.toString()), Integer.parseInt(pageSize.toString()));

        String clientId = Optional.ofNullable(parameters.get("clientId")).orElse("").toString();
        String resourceIds = Optional.ofNullable(parameters.get("resourceIds")).orElse("").toString();
        Page<Client> clientPage = clientService.page(page, new LambdaQueryWrapper<Client>()
            .like(Client::getClientId,clientId)
            .like(Client::getResourceIds,resourceIds));
        return R.data(ClientWrapper.build().pageVO(clientPage));
    }

    /**
     * 添加新的客户端
     * @param client
     * @return
     */
    @PostMapping
    @ApiOperation(value = "添加客户端")
    public R<Object> save(@RequestBody Client client){
        return R.status(clientService.saveClient(client));
    }

    /**
     * 删除客户端信息
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "删除客户端")
    public R<Object> delete(@RequestBody List<String> ids){
        if(null == ids || ids.size()<=0)
            throw new ApiException("客户端id列表为空");
        return R.status(clientService.removeClientByIds(ids));
    }

    /**
     * 更改客户端
     * @param client
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更改客户端信息")
    public R<Object> update(@RequestBody Client client){
        return R.data(clientService.updateClient(client));
    }


    @ApiIgnore
    @Override
    public List<OauthClientDto> getClient(String serverName) {
        List<Client> list = clientService.list(new LambdaQueryWrapper<Client>().like(Client::getResourceIds,serverName));
        return list.stream().map(client -> {
            OauthClientDto oauthClientDto = new OauthClientDto();
            assert client != null;
            BeanUtils.copyProperties(client, oauthClientDto);
            // 设置为秘钥对应的明文
            oauthClientDto.setClientSecret(client.getClientSecretName());
            return oauthClientDto;
        }).collect(Collectors.toList());
    }
}
