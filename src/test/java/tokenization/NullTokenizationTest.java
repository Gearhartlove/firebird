package tokenization;

import org.junit.jupiter.api.Test;
import org.tsl.firebird.tokenization.Token;
import org.tsl.firebird.tokenization.TokenPair;
import org.tsl.firebird.tokenization.Tokenizer;

import java.util.List;

public class NullTokenizationTest {

    Tokenizer tokenizer = new Tokenizer();

    @Test
    void Null1() {
        var source = "null";
        var tokens = tokenizer.tokenize(source);

        var expected = List.of(new TokenPair(Token.NULL, "null"));
        assert(expected.equals(tokens));
    }
}
