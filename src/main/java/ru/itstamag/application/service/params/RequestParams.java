package ru.itstamag.application.service.params;

public enum RequestParams {
    TEXT("text"), EMPLOYMENT("employment"), EXPERIENCE("experience"), SCHEDULE("schedule");

    private final String value;

    RequestParams(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
