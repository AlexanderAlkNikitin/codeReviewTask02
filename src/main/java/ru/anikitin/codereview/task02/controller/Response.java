package ru.anikitin.codereview.task02.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private String link;
    private String error;
}
