package com.trl.libraryservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/publishingHouse/email")
public class EmailPublishingHouseResource {

    private static final Logger LOG = LoggerFactory.getLogger(EmailPublishingHouseResource.class);

}
