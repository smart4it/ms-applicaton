package ru.itstamag.application.web.client.hh;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itstamag.application.web.client.hh.dto.DictionariesDto;
import ru.itstamag.application.web.client.hh.dto.FoundDto;
import ru.itstamag.application.web.client.hh.dto.VacanciesDto;

import java.util.Map;

@FeignClient(value = "hh-client", url = "https://api.hh.ru")
public interface HhClient {

    @GetMapping(path = "/vacancies", params = {"text", "experience", "employment", "schedule"})
    FoundDto countVacancies(@SpringQueryMap Map<String, String> params);

    @GetMapping("/dictionaries")
    DictionariesDto dictionaries();

    @GetMapping(path = "/vacancies?per_page=100")
    VacanciesDto vacancies(@RequestParam String text, @RequestParam int page);

    @GetMapping(path = "/vacancies")
    FoundDto vacanciesSize(@RequestParam String text);
}
