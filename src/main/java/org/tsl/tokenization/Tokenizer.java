package org.tsl.tokenization;

import java.util.ArrayList;
import java.util.List;

public final class Tokenizer {
    int cursor;

    public Tokenizer() {
        cursor = 0;
    }

    public List<Token> Tokenize(String source) {
        List<Token> tokensList = new ArrayList<>();

        while (cursor < source.length()) {
            char c = source.charAt(cursor);

            final Token token;

            if (Character.isDigit(c)) {
                token = number(source);
            } else {
                throw new UnsupportedOperationException("");
            }

            tokensList.add(token);
            cursor++;
        }

        return tokensList;
    }

    public Token number(String source) {
        char c;
        int colonCount = 0;

        int start = cursor;
        do {
            c = source.charAt(cursor++);
            if (c == '.') colonCount++;
        } while ((Character.isDigit(c) || c == '.')
                && cursor < source.length());
        int end = cursor;

        if (colonCount > 1) {
            return new TokenError(source.substring(start, end));
        } else {
            return  new Number(source.substring(start, end));
        }
    }

    public Token stringy(String source) {
        int start = cursor;
        do {
            c = source.charAt(cursor);
            cursor++;
        } while (c != '"');
        int end = cursor;

        String stringy = source.substring(start, end);

        return new Stringy(stringy);
    }
}
