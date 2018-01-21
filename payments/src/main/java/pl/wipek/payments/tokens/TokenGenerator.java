package pl.wipek.payments.tokens;

import java.util.Random;

public class TokenGenerator {
    private static int TOKEN_LENGTH = 8;

    public static String generateToken() {
        StringBuilder token = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            token.append((random.nextInt() & Integer.MAX_VALUE)%10);
        }
        return token.toString();
    }
}
