package com.sharing.cn.factory;

import com.sharing.cn.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BussnessServiceFactory
 *
 * @author ext.shikai1
 */
@Component
@Slf4j
public class ServiceFactory {

    Map<String, Service> businessServiceMap = new HashMap<>();

    public ServiceFactory(List<Service> bussnessOaServices) {
        for (Service service : bussnessOaServices) {
            businessServiceMap.put(service.getCode(), service);
        }
    }

}
