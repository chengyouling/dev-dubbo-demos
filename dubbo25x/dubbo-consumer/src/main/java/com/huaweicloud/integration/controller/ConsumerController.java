/*
 * Copyright (C) 2022-2022 Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huaweicloud.integration.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.rpc.RpcContext;
import com.huaweicloud.integration.service.HelloService;

/**
 * 测试接口
 *
 * @author provenceee
 * @since 2022-04-28
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Resource(name = "helloService")
    private HelloService helloService;

    /**
     * 测试接口
     *
     * @param name 参数
     * @return 测试信息
     */
    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String name, @RequestHeader Map<String, String> headers) {
        RpcContext.getContext().setAttachments(headers);
        System.out.println("comsumer---------------------" + name + "-----------" + headers.size() + "----------------" + headers.get("id"));
        String result = helloService.sayHello(name);
        System.out.println("result---------------------" + result);
        return result;
    }
}