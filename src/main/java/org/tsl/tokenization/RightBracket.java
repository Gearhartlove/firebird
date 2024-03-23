package org.tsl.tokenization;

import java.util.Objects;

public class RightBracket extends Token {
    RightBracket(String source) {
        if (Objects.equals(source, "]")) {
            src = source;
        } else {
            throw new RuntimeException("Tokenization Error. Tried to parse 'RightBracket Token' when source was " + source);
        }
    }

}
