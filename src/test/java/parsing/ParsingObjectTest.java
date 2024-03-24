package parsing;

import org.junit.jupiter.api.Test;
import org.tsl.firebird.parsing.Parser;
import org.tsl.firebird.tokenization.Tokenizer;

import java.util.Map;

public class ParsingObjectTest {

    Tokenizer tokenizer = new Tokenizer();


    @Test
    void Object1() {
        var source = """
            {"foo":"bar"}""";
        var tokens = tokenizer.tokenize(source);
        var jsonObject = new Parser(tokens).parse();

        assert(jsonObject.equals(Map.of("foo", "bar")));
    }

    @Test
    void Object2() {
        var source = """
                {"foo": {"zip": 42}} """;
        var tokens = tokenizer.tokenize(source);
        var jsonObject = new Parser(tokens).parse();

        assert(jsonObject.equals(
                Map.of("foo", Map.of("zip", 42))
        ));
    }

    @Test
    void Object3() {
        var source = """
                {"foo": {"zip": "zap", "answer":42}}""";
        var tokens = tokenizer.tokenize(source);
        var jsonObject = new Parser(tokens).parse();

        assert(jsonObject.equals(
                Map.of("foo", Map.of("zip", "zap", "answer", 42))
        ));
    }
}
