package com.codiceplastico.mars.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codiceplastico.mars.dto.InitializationDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * MarsController
 */
@RestController
@Slf4j
public class MarsController {

    @PostMapping("/initialize")
    public void initialize(@RequestBody InitializationDTO body) {

    }

}
