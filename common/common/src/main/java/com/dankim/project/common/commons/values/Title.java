package com.dankim.project.common.commons.values;

import com.dankim.project.common.commons.exceptions.common.SpecificationException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Title {
    Logger log = LoggerFactory.getLogger(Content.class);

    private final String title;

    public String get() {
        return title;
    }

    private Title(String title) {
        if (!validateTitle(title))
            throw new SpecificationException("tell them why it is illegal title");
        this.title = title;
    }

    public static Title of(String title) {
        return new Title(title);
    }

    private boolean validateTitle(String title) {
        log.info("Do some validation on Content here! ");
        return true;
    }
}
