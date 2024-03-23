package org.tsl.tokenization;

import java.util.Objects;

public class LeftBracket extends Token {
    LeftBracket(String source) {
        if (Objects.equals(source, "[")) {
            src = source;
        } else {
            throw new RuntimeException("Tokenization Error. Tried to parse 'LeftBracket Token' when source was " + source);
        }
    }
}
