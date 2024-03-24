package parsing;

import org.junit.jupiter.api.Test;
import org.tsl.firebird.tokenization.Tokenizer;

public class ParsingTest {
    Tokenizer tokenizer = new Tokenizer();

    @Test
    void Parsing1() {
        var source = """
                {
                "name":"John",
                "age":30,
                "cars":["Ford", "BMW", "Fiat"]
                }""";
    }
}
