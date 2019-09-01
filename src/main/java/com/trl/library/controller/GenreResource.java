package com.trl.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/genre")
public class GenreResource {

    private static final Logger LOG = LoggerFactory.getLogger(GenreResource.class);

}
