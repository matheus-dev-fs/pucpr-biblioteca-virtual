package util.sorting;

import java.util.Random;

public class BookNameGenerator {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String[] generateNames(int amount) {
        Random random = new Random();
        String[] names = new String[amount];

        for (int i = 0; i < amount; i++) {
            names[i] = generateRandomName(random);
        }

        return names;
    }

    private static String generateRandomName(Random random) {
        int length = random.nextInt(15) + 5;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        return sb.toString();
    }
}