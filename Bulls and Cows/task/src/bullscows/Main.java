package bullscows;

import java.util.*;

public class Main {
    private final static String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();

    private static int readStringAsInt() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int number = 0;
        try {
            number = Integer.parseInt(input);

        } catch (NumberFormatException e) {
            System.out.println("Error: \"" + input + "\" isn't a valid number.");
            System.exit(0);
        }
        return number;
    }

    public static void main(String[] args) {
        String number = "";
        int stepCounter = 0;
        System.out.println("Input the length of the secret code:");
        int length = readStringAsInt();
        if (length > 36 || length <= 0) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        int numberOfChars = readStringAsInt();
        if (numberOfChars < length) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.",
            length, numberOfChars);
            return;
        } else if (numberOfChars > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;

        }

        number = numberGenerator(length, numberOfChars);
        if (numberOfChars <= 10) {
            System.out.println("The secret is prepared: " + number.replaceAll("\\w", "*") + " (0-" + CHARS.charAt(numberOfChars - 1));
        } else if (numberOfChars >= 12) {
            System.out.println("The secret is prepared: " + number.replaceAll("\\w", "*") + " (0-9, a-" + CHARS.charAt(numberOfChars - 1) + ").");
        } else {
            System.out.println("The secret is prepared: *********** (0-9, a)");
        }

        System.out.println("Okay, let's start a game!");
        while (true) {
            System.out.println("Turn " + ++stepCounter + ".");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            int bulls = 0;
            int cows = 0;
            for (int i = 0; i < number.length(); i++) {
                if (number.charAt(i) == input.charAt(i)) {
                    bulls++;
                    continue;
                }
                for (int j = 0; j < number.length(); j++) {
                    if (number.charAt(j) == input.charAt(i)) {
                        cows++;
                    }
                }
            }
            System.out.print("Grade: ");
            if (bulls == 0 && cows == 0) {
                System.out.println("None");
            } else {
                if (bulls != 0) {
                    System.out.print(bulls + " bull(s)");
                }
                if (bulls != 0 && cows != 0) {
                    System.out.print(" and ");
                }
                if (cows != 0) {
                    System.out.print(cows + " cow(s)");
                }
                System.out.println();
            }
            if (bulls == number.length()) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
        }


    }

    private static String numberGenerator(int length, int numberOfChars) {
        StringBuilder number = new StringBuilder("");
        List<Character> digits = new LinkedList<>();

        //заполним список символами
        for (int i = 0; i < numberOfChars; i++) {
            digits.add(CHARS.charAt(i));
        }
        //генерируем строку
        for (int i = 0; i < length; i++) {
            number.append(digits.remove((int) (Math.random() * (numberOfChars - i))));
        }
        return number.toString();
    }
}
