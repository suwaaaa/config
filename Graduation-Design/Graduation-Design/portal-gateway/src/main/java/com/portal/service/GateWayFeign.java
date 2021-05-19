package com.portal.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "portal-sso")
public interface GateWayFeign {
}
