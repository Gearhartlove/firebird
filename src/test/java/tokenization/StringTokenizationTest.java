package tokenization;

import org.junit.jupiter.api.Test;
import org.tsl.tokenization.Token;
import org.tsl.tokenization.TokenPair;
import org.tsl.tokenization.Tokenizer;

import java.util.List;

public class StringTokenizationTest {

    Tokenizer tokenizer = new Tokenizer();

    @Test
    void String1() {
        var source = "\"foo\"";
        var tokens = tokenizer.tokenize(source);

        var expected = List.of(new TokenPair(Token.STRING, "foo"));
        assert(expected.equals(tokens));
    }
}
