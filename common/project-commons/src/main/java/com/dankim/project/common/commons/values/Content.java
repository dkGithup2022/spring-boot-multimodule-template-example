package com.dankim.project.common.commons.values;

import com.dankim.project.common.commons.exceptions.common.SpecificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Content {
    Logger log = LoggerFactory.getLogger(Content.class);

    private final String content;

    private Content(String content) {
        if (!validateContent(content))
            throw new SpecificationException("tell them why it is illegal content");
        this.content = content;
    }

    public static Content of(String content) {
        return new Content(content);
    }

    private boolean validateContent(String content) {
        log.info("Do some validation on Content here! ");
        return true;
    }
}
