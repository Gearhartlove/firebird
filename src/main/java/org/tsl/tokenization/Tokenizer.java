package org.tsl.tokenization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Tokenizer {
    ArrayList<Character> JSON_WHITESPACE;
    ArrayList<Character> JSON_SYNTAX;
    int cursor = 0;

    Tokenizer() {
        this.JSON_WHITESPACE = new ArrayList<Character>();
        this.JSON_WHITESPACE.add(' ');
        this.JSON_WHITESPACE.add('\t');
        this.JSON_WHITESPACE.add('\r');
        this.JSON_WHITESPACE.add('\n');

        this.JSON_SYNTAX = new ArrayList<Character>();
        this.JSON_SYNTAX.add('}');
        this.JSON_SYNTAX.add('{');
        this.JSON_SYNTAX.add(']');
        this.JSON_SYNTAX.add('[');
        this.JSON_SYNTAX.add(',');
    }

    public List<TokenPair> Tokenize(String source) {
        List<TokenPair> tokens = new ArrayList<>();

        while (cursor < source.length()) {
            var optJsonString = tokenizeString(source);
            if (optJsonString.map(tokens::add).orElse(false)) continue;

            var optJsonNumber = tokenizeNumber(source);
            if (optJsonNumber.map(tokens::add).orElse(false)) continue;

            var optJsonBoolean = tokenizeBoolean(source);
            if (optJsonBoolean.map(tokens::add).orElse(false)) continue;

            var optJsonNull = tokenizeNull(source);
            if (optJsonNull.map(tokens::add).orElse(false)) continue;

            if (JSON_WHITESPACE.contains(source.charAt(0))) {
                cursor++;
            }
            else if (JSON_SYNTAX.contains(source.charAt(0))) {
                var jsonSyntax = switch (source.charAt(0)) {
                    case '{' -> new TokenPair(Token.LEFT_SQUIGGLY, "{");
                    case '}' -> new TokenPair(Token.RIGHT_SQUIGGLY, "}");
                    default -> throw new IllegalStateException("Unexpected value: " + source.charAt(0));
                };
                tokens.add(jsonSyntax);
            }

        }

        return tokens;
    }

    private Optional<TokenPair> tokenizeNull(String source) {
        throw new UnsupportedOperationException("TODO");
    }

    private Optional<TokenPair> tokenizeBoolean(String source) {
        throw new UnsupportedOperationException("TODO");
    }

    private Optional<TokenPair> tokenizeNumber(String source) {
        throw new UnsupportedOperationException("TODO");
    }

    private Optional<TokenPair> tokenizeString(String source) {
        StringBuilder jsonString = new StringBuilder();

        if (source.charAt(cursor) == '"') cursor++;
        else return Optional.empty();

        for (char c : source.substring(cursor++).toCharArray()) {
            if (c == '"') return Optional.of(new TokenPair(Token.STRING, jsonString.toString()));
            else jsonString.append(c);
        }

        throw new RuntimeException("Expected end-of-string quote");
    }
}
