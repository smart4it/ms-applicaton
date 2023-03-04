package ru.itstamag.application.web.client.hh;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itstamag.application.web.client.hh.dto.FoundDto;

@FeignClient(value = "hh-client", url = "https://api.hh.ru/vacancies?text=java")
public interface HhClient {
    @RequestMapping(method = RequestMethod.GET)
    FoundDto vacancies();
}
