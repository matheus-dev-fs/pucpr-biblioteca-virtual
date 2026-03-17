package util;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class InputHandler {

    private static Scanner scanner;

    public static void setScanner(Scanner sc) {
        InputHandler.scanner = sc;
    }

    public static Scanner getScanner() {
        return InputHandler.scanner;
    }

    public static void closeScanner() {
        if (InputHandler.getScanner() != null) {
            InputHandler.scanner.close();
        }
    }

    public static void validateScanner() throws IllegalStateException {
        if (InputHandler.getScanner() == null) {
            throw new IllegalStateException("Scanner não foi inicializado. Use InputHandler.setScanner() para definir um Scanner.");
        }
    }

    public static <T extends Number> T getNumericValue(
            String inputMessage,
            String errorMessage,
            Supplier<T> inputReader
    ) {
        T value = null;

        while (value == null) {
            try {
                System.out.print(inputMessage);
                value = inputReader.get();

                InputHandler.scanner.nextLine();

                if (value != null && value.doubleValue() < 0) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println(errorMessage);

                if (InputHandler.scanner.hasNextLine()) {
                    InputHandler.scanner.nextLine();
                }

                value = null;
            }
        }

        return value;
    }

    public static String getStringValue(
            String inputMessage,
            String errorMessage,
            Predicate<String> additionalValidation,
            String validationErrorMessage
    ) {
        String value = null;

        while (value == null || (additionalValidation != null && !additionalValidation.test(value))) {
            try {
                System.out.print(inputMessage);

                if (InputHandler.scanner.hasNextLine()) {
                    value = InputHandler.scanner.nextLine().trim();
                }

                if (value != null && value.isEmpty()) {
                    throw new IllegalStateException(errorMessage);
                }

                if (additionalValidation != null && !additionalValidation.test(value)) {
                    throw new IllegalStateException(validationErrorMessage);
                }
            } catch (InputMismatchException | IllegalStateException e) {
                System.out.println(e.getMessage());
                value = null;
            }
        }

        return value;
    }

    public static String getStringValue(String inputMessage, String errorMessage) {
        return InputHandler.getStringValue(inputMessage, errorMessage, null, null);
    }
}