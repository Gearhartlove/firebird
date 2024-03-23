package tokenization;

import org.junit.jupiter.api.Test;
import org.tsl.tokenization.Tokenizer;

public class NumberTokenizationTest {


    Tokenizer tokenizer;

    NumberTokenizationTest() {
        tokenizer = new Tokenizer();
    }

    @Test
    void number1() {
        String number = "124";
        Number expect = new Number(number);
        TokenTest result = tokenizer.number(number);

        assert(expect.equals(result));
    }

    @Test
    void number2() {
        String number = "0";
        Number expect = new Number(number);
        TokenTest result = tokenizer.number(number);

        assert(expect.equals(result));
    }

    @Test
    void number3() {
        String number = "0.123";
        Number expect = new Number(number);
        TokenTest result = tokenizer.number(number);

        assert(expect.equals(result));
    }

    @Test
    void number4() {
        String number = "123.456";
        Number expect = new Number(number);
        TokenTest result = tokenizer.number(number);

        assert(expect.equals(result));
    }

    @Test
    void number5() {
        String number = "123.456.789";
        TokenError expect = new TokenError(number);
        TokenTest result = tokenizer.number(number);

        assert(expect.equals(result));
    }
}

