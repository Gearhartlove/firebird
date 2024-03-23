package org.tsl.tokenization;

import java.util.Objects;

public class Colon extends Token {
    Colon(String source) {
        if (Objects.equals(source, ":")) {
            src = source;
        } else {
            throw new RuntimeException("Tokenization Error. Tried to parse 'True Token' when source was " + source);
        }
    }

}
