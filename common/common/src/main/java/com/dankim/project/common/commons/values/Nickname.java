package com.dankim.project.common.commons.values;

import com.dankim.project.common.commons.exceptions.common.SpecificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nickname {
    Logger log = LoggerFactory.getLogger(Content.class);

    private final String nickname;

    public String get() {
        return nickname;
    }

    private Nickname(String nickname) {
        if (!validateContent(nickname))
            throw new SpecificationException("tell them why it is illegal nickname");
        this.nickname = nickname;
    }

    public static Nickname of(String nickname) {
        return new Nickname(nickname);
    }

    private boolean validateContent(String nickname) {
        log.info("Do some validation on Content here! ");
        return true;
    }
}
