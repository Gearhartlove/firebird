package org.tsl.tokenization;

import java.util.Objects;

public abstract class Token {
    String src;

    public String getSrc() {
        return src;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o; // checks weather o is null or if o is not an instance of teh Token class
        return Objects.equals(src, token.src);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src);
    }
}

