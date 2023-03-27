package ru.itstamag.application.web.client.hh.dto;


import java.util.List;

public record DictionariesDto(List<IdNameDto> experience, List<IdNameDto> employment,  List<SchedulesDto> schedule) {

    public record IdNameDto(String id, String name) {
    }
}