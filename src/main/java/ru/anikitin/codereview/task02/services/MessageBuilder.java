package ru.anikitin.codereview.task02.services;

import ru.anikitin.codereview.task02.controller.Request;

public interface MessageBuilder {
    String build(String msgType, Request request);
    String build(String msgType, String message);
}
