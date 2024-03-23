package org.tsl.tokenization;

import java.util.Objects;

public class LeftSquiggly extends Token {
    LeftSquiggly(String source) {
        if (Objects.equals(source, "{")) {
            src = source;
        } else {
            throw new RuntimeException("Tokenization Error. Tried to parse 'LeftSquiggly Token' when source was " + source);
        }
    }
}
