package ru.anikitin.codereview.task02.services.impl;

import org.springframework.stereotype.Service;
import ru.anikitin.codereview.task02.services.LinkShorter;

@Service
public class LinkShorterImpl implements LinkShorter {

    @Override
    public String toShortLink(String original) {
        return "" + original.hashCode();
    }
}
