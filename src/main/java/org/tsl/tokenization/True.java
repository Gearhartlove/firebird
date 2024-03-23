package org.tsl.tokenization;

import java.util.Objects;

public class True extends Token {
    True(String source) {
        if (Objects.equals(source, "true")) {
            src = source;
        } else {
            throw new RuntimeException("Tokenization Error. Tried to parse 'True Token' when source was " + source);
        }
    }
}
