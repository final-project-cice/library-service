package com.trl.libraryservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/book/comment")
public class CommentBookResource {

    private static final Logger LOG = LoggerFactory.getLogger(CommentBookResource.class);

}