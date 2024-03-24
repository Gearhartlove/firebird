package tokenization;

import org.junit.jupiter.api.Test;
import org.tsl.firebird.tokenization.Token;
import org.tsl.firebird.tokenization.TokenPair;
import org.tsl.firebird.tokenization.Tokenizer;

import java.util.List;

public class TokenizationTest {

    Tokenizer tokenizer = new Tokenizer();

    @Test
    void Tokenization1() {
        var source = """
                {"foo": [1, 2, {"bar": 2}]}""";
        var tokens = tokenizer.tokenize(source);

        var expected = List.of(
                new TokenPair(Token.LEFT_SQUIGGLY, "{"),
                new TokenPair(Token.STRING, "foo"),
                new TokenPair(Token.COLON, ":"),
                new TokenPair(Token.LEFT_BRACKET, "["),
                new TokenPair(Token.NUMBER, "1"),
                new TokenPair(Token.COMMA, ","),
                new TokenPair(Token.NUMBER, "2"),
                new TokenPair(Token.COMMA, ","),
                new TokenPair(Token.LEFT_SQUIGGLY, "{"),
                new TokenPair(Token.STRING, "bar"),
                new TokenPair(Token.COLON, ":"),
                new TokenPair(Token.NUMBER, "2"),
                new TokenPair(Token.RIGHT_SQUIGGLY, "}"),
                new TokenPair(Token.RIGHT_BRACKET, "]"),
                new TokenPair(Token.RIGHT_SQUIGGLY, "}")
        );

        assert(expected.equals(tokens));

    }

}
