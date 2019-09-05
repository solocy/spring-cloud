package com.example.feigneurekaclient;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Objects;

public class CustomRule extends AbstractLoadBalancerRule {

    @Override
    public Server choose(Object o) {
        return choose(getLoadBalancer(),o);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    private int total;  // 访问次数

    private int currentIndex;  // 服务的机器号

    private Server choose(ILoadBalancer lb,Object key) {

        if (Objects.isNull(lb)) {
            return null;
        }
        List<Server> upServer = lb.getReachableServers();
        if (upServer.isEmpty()) {
            return null;
        }
        if (total < 5) {
            total++;
        } else {
            total = 0;
            currentIndex ++;
            if (currentIndex >=  upServer.size()) {
                currentIndex = 0;
            }
            total ++;
        }
        return upServer.get(currentIndex);

    }
}
