package com.sdk.itjobs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SuperJob", url = "https://api.superjob.ru/2.0")
public interface SuperJobClient {
    @RequestMapping(method = RequestMethod.GET, value = "/vacancies")
    String getVacancies(@RequestHeader("X-Api-App-Id") String secretToken, @RequestParam("keyword") String keyword, @RequestParam("page") Long page, @RequestParam("count") Long size);
}
