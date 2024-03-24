package parsing;

import org.junit.jupiter.api.Test;
import org.tsl.firebird.parsing.Parser;
import org.tsl.firebird.tokenization.Tokenizer;

import java.util.List;
import java.util.Map;

public class ParsingArrayTest {

    Tokenizer tokenizer = new Tokenizer();

    @Test
    void Array1() {
        var source = """
                {"foo": [{"zip":"zap"}, {"true": true}]}""";
        var tokens = tokenizer.tokenize(source);
        var jsonObject = new Parser(tokens).parse();

        assert(jsonObject.equals(
                Map.of("foo", List.of(
                        Map.of("zip", "zap"),
                        Map.of("true", true)
                ))
        ));
    }
}
