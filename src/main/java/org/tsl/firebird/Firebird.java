package org.tsl.firebird;

import org.tsl.firebird.parsing.Parser;
import org.tsl.firebird.tokenization.Tokenizer;

public class Firebird {

    Tokenizer tokenizer = new Tokenizer();

    public Object fromString(String source) {
        var tokens = tokenizer.tokenize(source);
        var jsonObject = new Parser(tokens).parse();
        return jsonObject;
    }
}
