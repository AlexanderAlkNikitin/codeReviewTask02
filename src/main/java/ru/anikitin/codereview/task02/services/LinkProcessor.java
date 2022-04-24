package ru.anikitin.codereview.task02.services;

import ru.anikitin.codereview.task02.controller.Request;
import ru.anikitin.codereview.task02.controller.Response;
import ru.anikitin.codereview.task02.domain.Link;

import java.util.List;

public interface LinkProcessor {
    Response process(Request requestDto);

    String getOriginalLink(String hash);

    Link stat(String hash);

    List<Link> stats();

}
