package org.tsl.firebird.tokenization;

import java.util.Objects;

public record TokenPair(Token kind, String source) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenPair tokenPair = (TokenPair) o;
        return kind == tokenPair.kind && Objects.equals(source, tokenPair.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, source);
    }
}
