package ru.anikitin.codereview.task02.services.impl;

import org.springframework.stereotype.Service;
import ru.anikitin.codereview.task02.controller.Request;
import ru.anikitin.codereview.task02.services.MessageBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageBuilderImpl implements MessageBuilder {

    private final Map<String, String> map = new HashMap<>();

    {
        //todo: to config
        map.put("INVALID", "Incorrect request %s");
        map.put("DB_ERROR", "Db error %s");
    }

    @Override
    public String build(String msgType, Request request) {
        return String.format(map.get(msgType), request);
    }

    @Override
    public String build(String msgType, String message) {
        return String.format(map.get(msgType), message);
    }
}
