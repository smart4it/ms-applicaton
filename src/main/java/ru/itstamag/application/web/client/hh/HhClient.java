package ru.itstamag.application.web.client.hh;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itstamag.application.web.client.hh.dto.DictionariesDto;
import ru.itstamag.application.web.client.hh.dto.FoundDto;

@FeignClient(value = "hh-client", url = "https://api.hh.ru")
public interface HhClient {
    @GetMapping("/vacancies?text=java")
    FoundDto vacancies();

    @GetMapping("/dictionaries")
    DictionariesDto dictionaries();
}
