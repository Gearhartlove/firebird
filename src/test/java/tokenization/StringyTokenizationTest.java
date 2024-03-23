package tokenization;

import org.junit.jupiter.api.Test;
import org.tsl.tokenization.*;

public class StringyTokenizationTest {
    Tokenizer tokenizer;

    public StringyTokenizationTest() {
        this.tokenizer = new Tokenizer();
    }

    @Test
    void Stringy1() {
        String s = "\"foobar\"";
        Stringy expect = new Stringy(s);
        TokenTest result = tokenizer.tokenizeString(s);

        assert(expect.equals(result));
    }
}
