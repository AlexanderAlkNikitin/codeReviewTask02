package ru.anikitin.codereview.task02.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.anikitin.codereview.task02.controller.dto.RequestDto;
import ru.anikitin.codereview.task02.controller.dto.ResponseDto;
import ru.anikitin.codereview.task02.controller.dto.StatResponseDto;
import ru.anikitin.codereview.task02.controller.mapper.LinkMapper;
import ru.anikitin.codereview.task02.controller.mapper.LinkStatMapper;
import ru.anikitin.codereview.task02.services.LinkProcessor;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkProcessor linkProcessor;
    private final LinkMapper linkMapper;
    private final LinkStatMapper linkStatMapper;

    @PostMapping(path = "/generate", consumes = APPLICATION_JSON_VALUE)
    public ResponseDto createShortUrl(@RequestBody RequestDto requestDto) {
        Response response = linkProcessor.process(linkMapper.map(requestDto));
        return linkMapper.map(response);
    }

    @GetMapping(path = "/l/{some-short-name}")
    public ResponseEntity redirectToOriginal(@PathVariable("some-short-name") String hash) {
        log.info(hash);
        String link = linkProcessor.getOriginalLink(hash);
        return getRedirectResponseEntity(link);
    }

    @GetMapping(path = "/stats/{some-short-name}")
    public StatResponseDto stat(@PathVariable("some-short-name") String hash) {
        return linkStatMapper.map(linkProcessor.stat(hash));
    }

    @GetMapping(path = "/stats")
    public List<StatResponseDto> stats() {
        return linkProcessor.stats().stream().map(linkStatMapper::map).collect(Collectors.toList());
    }

    private ResponseEntity<?> getRedirectResponseEntity(String link) {
        if (link != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", link);
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
