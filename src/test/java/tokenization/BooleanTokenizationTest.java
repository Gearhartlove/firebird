package tokenization;

import org.junit.jupiter.api.Test;
import org.tsl.firebird.tokenization.Token;
import org.tsl.firebird.tokenization.TokenPair;
import org.tsl.firebird.tokenization.Tokenizer;

import java.util.List;

public class BooleanTokenizationTest {

    Tokenizer tokenizer = new Tokenizer();

    @Test
    void True1() {
        var source = "true";
        var tokens = tokenizer.tokenize(source);

        var expected = List.of(new TokenPair(Token.TRUE, source));
        assert(expected.equals(tokens));
    }

    @Test
    void False1() {
        var source = "false";
        var tokens = tokenizer.tokenize(source);

        var expected = List.of(new TokenPair(Token.FALSE, source));
        assert(expected.equals(tokens));
    }
}
