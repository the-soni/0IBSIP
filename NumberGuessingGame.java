import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int score = 0;
        boolean playAgain = true;

        System.out.println("=================================");
        System.out.println("      NUMBER GUESSING GAME");
        System.out.println("=================================");

        while (playAgain) {

            int secretNumber = random.nextInt(100) + 1;
            int maxAttempts = 7;
            int attempts = 0;
            boolean correct = false;

            System.out.println("\nI have selected a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts) {

                System.out.print("\nEnter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess < secretNumber) {
                    System.out.println("Too Low!");
                } 
                else if (guess > secretNumber) {
                    System.out.println("Too High!");
                } 
                else {
                    System.out.println("Correct! You guessed the number.");
                    System.out.println("Attempts used: " + attempts);
                    score++;
                    correct = true;
                    break;
                }
            }

            if (!correct) {
                System.out.println("\nYou Lost!");
                System.out.println("The correct number was: " + secretNumber);
            }

            System.out.println("\nYour Score: " + score);

            System.out.print("Do you want to play again? (yes/no): ");
            String answer = scanner.next();

            if (!answer.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\n=================================");
        System.out.println("        GAME OVER");
        System.out.println("Final Score: " + score);
        System.out.println("Thank you for playing!");
        System.out.println("=================================");

        scanner.close();
    }
}