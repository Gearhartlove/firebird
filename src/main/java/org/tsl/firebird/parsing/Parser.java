package org.tsl.firebird.parsing;

import org.tsl.firebird.tokenization.TokenPair;
import org.tsl.firebird.tokenization.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {

    ArrayList<TokenPair> tokens;

    public Parser(ArrayList<TokenPair> tokens) {
        this.tokens = tokens;
    }

    private ArrayList<Object> parseArray() {
        ArrayList<Object> jsonArray = new ArrayList<Object>();

        if (tokens.getFirst().kind() == Token.RIGHT_BRACKET) return jsonArray;

        while (true) {
            var json = parse();
            jsonArray.add(json);

            var next2 = tokens.removeFirst();
            if (next2.kind() == Token.RIGHT_BRACKET) return jsonArray;
            else if (next2.kind() != Token.COMMA) throw new RuntimeException("Expected comma after object in array");
        }

        // throw new RuntimeException("Expected end-of-array bracket");
    }

    private HashMap<String, Object> parseObject() {
        var jsonObject = new HashMap<String, Object>();

        while (true) {
            var next = tokens.removeFirst();
            if (next.kind() == Token.RIGHT_SQUIGGLY) return jsonObject;

            var jsonKey = next;
            // check for if the key is only a string here?
            var _colon = tokens.removeFirst();
            assert(_colon.kind().equals(Token.COLON));

            var jsonValue = parse();

            jsonObject.put(jsonKey.source(), jsonValue);

            var next2 = tokens.removeFirst();
            if (next2.kind() == Token.RIGHT_SQUIGGLY) return jsonObject;
            else if (next2.kind() != Token.COMMA) throw new RuntimeException("Expected comma after pain in object, got: " + next);
        }
    }

    public Object parse() {
        var start = tokens.removeFirst();
        if (start.kind() == Token.LEFT_BRACKET) return parseArray();
        else if (start.kind() == Token.LEFT_SQUIGGLY) return parseObject();
        else {
            var value = switch(start.kind()) {
                case Token.NUMBER -> Integer.parseInt(start.source());
                case Token.STRING -> start.source();
                case Token.NULL -> null;
                case Token.TRUE -> true;
                case Token.FALSE -> false;
                default -> throw new RuntimeException("Unrecognized Type");
            };
            return value;
        }
    }
}
