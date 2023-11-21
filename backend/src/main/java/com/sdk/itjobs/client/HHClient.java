package com.sdk.itjobs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "HH", url = "https://api.hh.ru")
public interface HHClient {
    @RequestMapping(method = RequestMethod.GET, value = "/vacancies")
    String getVacancies(@RequestParam("text") String text, @RequestParam("page") Long page, @RequestParam("per_page") Long size);
}
