package org.tsl.tokenization;

import java.util.Objects;

public class False extends Token {
    False(String source) {
        if (Objects.equals(source, "false")) {
            src = source;
        } else {
            throw new RuntimeException("Tokenization Error. Tried to parse 'False Token' when source was " + source);
        }
    }
}
