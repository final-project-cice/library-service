package com.trl.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/address")
public class AddressResource {

    private static final Logger LOG = LoggerFactory.getLogger(AddressResource.class);

}
