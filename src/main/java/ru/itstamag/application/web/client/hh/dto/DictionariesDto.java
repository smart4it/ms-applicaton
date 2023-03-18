package ru.itstamag.application.web.client.hh.dto;


import java.util.List;

public record DictionariesDto(List<IdNameDto> experience) {

    public record IdNameDto(String id, String name) {
    }
}