package org.tsl.tokenization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Tokenizer {
    ArrayList<Character> JSON_WHITESPACE;
    ArrayList<Character> JSON_SYNTAX;
    ArrayList<Character> JSON_NUMBER_EXTRAS;
    int cursor = 0;

    public Tokenizer() {
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
        this.JSON_SYNTAX.add(':');

        this.JSON_NUMBER_EXTRAS = new ArrayList<Character>();
        this.JSON_NUMBER_EXTRAS.add('-');
        this.JSON_NUMBER_EXTRAS.add('e');
        this.JSON_NUMBER_EXTRAS.add('.');
    }

    public List<TokenPair> tokenize(String source) {
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

            if (JSON_WHITESPACE.contains(source.charAt(cursor))) {
                cursor++;
            }
            else if (JSON_SYNTAX.contains(source.charAt(cursor))) {
                var jsonSyntax = switch (source.charAt(cursor)) {
                    case '{' -> new TokenPair(Token.LEFT_SQUIGGLY, "{");
                    case '}' -> new TokenPair(Token.RIGHT_SQUIGGLY, "}");
                    case '[' -> new TokenPair(Token.LEFT_BRACKET, "[");
                    case ']' -> new TokenPair(Token.RIGHT_BRACKET, "]");
                    case ',' -> new TokenPair(Token.COMMA, ",");
                    case ':' -> new TokenPair(Token.COLON, ":");
                    default -> throw new IllegalStateException("Unexpected value: " + source.charAt(cursor));
                };
                cursor++;
                tokens.add(jsonSyntax);
            }

        }

        return tokens;
    }

    private Optional<TokenPair> tokenizeNull(String source) {
        if (source.startsWith("null")) {
            cursor += "null".length();
            return Optional.of(new TokenPair(Token.NULL, "null"));
        }

        return Optional.empty();
    }

    private Optional<TokenPair> tokenizeBoolean(String source) {
        if (source.startsWith("true", cursor)) {
            cursor += "true".length();
            return Optional.of(new TokenPair(Token.TRUE, "true"));
        }

        if (source.startsWith("false", cursor)) {
            cursor += "false".length();
            return Optional.of(new TokenPair(Token.FALSE, "false"));
        }

        return Optional.empty();
    }

    private Optional<TokenPair> tokenizeNumber(String source) {
        StringBuilder jsonNumber = new StringBuilder();

        for (char c : source.substring(cursor).toCharArray()) {
            if (Character.isDigit(c) || JSON_NUMBER_EXTRAS.contains(c)) {
                jsonNumber.append(c);
                cursor++;
            }
            else break;
        }

        if (jsonNumber.isEmpty()) return Optional.empty();

        return Optional.of(new TokenPair(Token.NUMBER, jsonNumber.toString()));
    }

    private Optional<TokenPair> tokenizeString(String source) {
        StringBuilder jsonString = new StringBuilder();

        if (source.charAt(cursor) == '"') cursor++;
        else return Optional.empty();

        for (char c : source.substring(cursor).toCharArray()) {
            cursor++;
            if (c == '"') return Optional.of(new TokenPair(Token.STRING, jsonString.toString()));
            else jsonString.append(c);
        }

        throw new RuntimeException("Expected end-of-string quote");
    }
}
