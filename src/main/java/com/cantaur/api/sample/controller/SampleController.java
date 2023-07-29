package com.cantaur.api.sample.controller;

import com.cantaur.api.sample.service.SampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cantaur/api/sample")
@Slf4j
@Tag(name = "샘플", description = "기본 샘플 API")
public class SampleController {

    final SampleService sampleService;

    @GetMapping("")
    @Operation(summary = "sample", description = "sample")
    @ApiResponse(
            responseCode = "200", description = "조회성공")
    public String sample(
            @RequestHeader(name = "Accept-Language", defaultValue = "ko") String langCd
    ) {
        log.info("");
        return sampleService.getSample();
    }

}
