package org.tsl.tokenization;

import java.util.Objects;

public class RightSquiggly extends Token {
    RightSquiggly(String source) {
        if (Objects.equals(source, "}")) {
            src = source;
        } else {
            throw new RuntimeException("Tokenization Error. Tried to parse 'RightSquiggly Token' when source was " + source);
        }
    }
}
