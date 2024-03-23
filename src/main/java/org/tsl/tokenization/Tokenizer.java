package org.tsl.tokenization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Tokenizer {
    int cursor = 0;

    public List<TokenPair> Tokenize(String source) {
        List<TokenPair> tokens = new ArrayList<>();

        while (cursor < source.length()) {

            if (tokenizeString(source).ifPresent(tokens::add)) continue;
            if (optJsonString.isPresent()) {
                tokens.add(optJsonString.get());
                continue;
            }

            Optional<TokenPair> optJsonNumber = tokenizeNumber(source);
            if (optJsonNumber.isPresent()) {
                tokens.add(optJsonNumber.get());
                continue;
            }

        }

        return tokens;
    }

    private Optional<TokenPair> tokenizeNumber(String source) {
        throw new UnsupportedOperationException("TODO");
    }

    public Optional<TokenPair> tokenizeString(String source) {


        return Optional.of(new TokenPair(Token.STRING, ""));
    }

//    public TokenTest number(String source) {
//        char c;
//        int colonCount = 0;
//
//        int start = cursor;
//        do {
//            c = source.charAt(cursor++);
//            if (c == '.') colonCount++;
//        } while ((Character.isDigit(c) || c == '.')
//                && cursor < source.length());
//        int end = cursor;
//
//        if (colonCount > 1) {
//            return new TokenError(source.substring(start, end));
//        } else {
//            return  new Number(source.substring(start, end));
//        }
//    }
//
//    public Optional<TokenPair> tokenizeString(String source) {
//        expect('"', source);
//        Stringy identifier = identifier(source);
//        expect('"', source);
//        return identifier;
//    }
//
//    private Stringy identifier(String source) {
//        int start = cursor;
//        char c;
//
//        do {
//            c = source.charAt(cursor);
//            cursor++;
//        } while (c != '"');
//
//        int end = cursor;
//
//        String stringy = source.substring(start, end);
//
//        return new Stringy(stringy);
//    }
//
//    private void expect(Character expect, String source) {
//
//    }
}
