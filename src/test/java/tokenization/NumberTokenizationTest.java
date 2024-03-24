package tokenization;

import org.junit.jupiter.api.Test;
import org.tsl.firebird.tokenization.Token;
import org.tsl.firebird.tokenization.TokenPair;
import org.tsl.firebird.tokenization.Tokenizer;

import java.util.List;

public class NumberTokenizationTest {

    Tokenizer tokenizer;

    NumberTokenizationTest() {
        tokenizer = new Tokenizer();
    }

    @Test
    void number1() {
        var source = "124";
        var tokens = tokenizer.tokenize(source);

        var expected = List.of(new TokenPair(Token.NUMBER, source));
        assert(expected.equals(tokens));
    }

    @Test
    void number2() {
        var source = "0";
        var tokens = tokenizer.tokenize(source);

        var expected = List.of(new TokenPair(Token.NUMBER, source));
        assert(expected.equals(tokens));
    }

    @Test
    void number3() {
        var source = "-1";
        var tokens = tokenizer.tokenize(source);

        var expected = List.of(new TokenPair(Token.NUMBER, source));
        assert(expected.equals(tokens));
    }

    @Test
    void number4() {
        var source = "3.14";
        var tokens = tokenizer.tokenize(source);

        var expected = List.of(new TokenPair(Token.NUMBER, source));
        assert(expected.equals(tokens));
    }
}

