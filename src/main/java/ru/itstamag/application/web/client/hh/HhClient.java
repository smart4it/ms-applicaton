package ru.itstamag.application.web.client.hh;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itstamag.application.dao.entity.SearchParameters;
import ru.itstamag.application.web.client.hh.dto.DictionariesDto;
import ru.itstamag.application.web.client.hh.dto.FoundDto;

import java.util.Map;

@FeignClient(value = "hh-client", url = "https://api.hh.ru")
public interface HhClient {

    @GetMapping(path = "/vacancies", params = {"text", "experience", "employment", "schedule"})
    FoundDto vacancies(@SpringQueryMap Map<String, String> params);

    @GetMapping("/dictionaries")
    DictionariesDto dictionaries();
}
