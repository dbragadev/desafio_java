import java.util.Scanner;
import java.text.DecimalFormat;

public class Challenge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String clientName = getValidName(scanner);
        String accountType = getValidAccountType(scanner);
        double openingBalance = getValidOpeningBalance(scanner);

        System.out.println("""
                *******************
                
                Initial Client Data:
                Name: %s
                Account type: %s
                Opening balance: %.2f
                
                *******************
                """.formatted(clientName, accountType, openingBalance));

        int operation = 0;

        while (operation != 4) {
            System.out.println("""
                    ** Operations: **

                    1 - Consult balance.
                    2 - Receive value.
                    3 - Transfer value.
                    4 - Back.

                    """);

            try {
                operation = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (operation) {
                case 1:
                    System.out.println("Current balance: " + formatAmount(openingBalance));
                    break;
                case 2:
                    double receiveValue = getValidAmount(scanner, "Enter the amount that will be received: ");
                    openingBalance += receiveValue;
                    System.out.println("Deposit made successfully! Your current balance is: " + formatAmount(openingBalance));
                    break;
                case 3:
                    double transferValue = getValidAmount(scanner, "Enter the amount that will be transferred: ");
                    if (transferValue <= openingBalance) {
                        openingBalance -= transferValue;
                        System.out.println("Transfer successfully completed! Your current balance is: " + formatAmount(openingBalance));
                    } else {
                        System.out.println("Insufficient balance to make the operation.");
                    }
                    break;
                case 4:
                    System.out.println("Application closed.");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static String getValidName(Scanner scanner) {
        String name;
        do {
            System.out.print("Enter customer's name: ");
            name = scanner.nextLine();
            if (name.isBlank()) {
                System.out.println("Invalid input. Name cannot be empty.");
            }
        } while (name.isBlank());
        return name;
    }

    public static String getValidAccountType(Scanner scanner) {
        String accountType;
        do {
            System.out.print("Enter the customer's account type: ");
            accountType = scanner.nextLine();
            if (accountType.isBlank()) {
                System.out.println("Invalid input. Account type cannot be empty.");
            }
        } while (accountType.isBlank());
        return accountType;
    }

    public static double getValidOpeningBalance(Scanner scanner) {
        double openingBalance = 0;
        boolean validBalance = false;
        while (!validBalance) {
            System.out.print("Enter the customer's opening balance: ");
            try {
                openingBalance = Double.parseDouble(scanner.nextLine());
                validBalance = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return openingBalance;
    }

    public static double getValidAmount(Scanner scanner, String message) {
        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print(message);
            try {
                amount = Double.parseDouble(scanner.nextLine());
                validAmount = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return amount;
    }

    public static String formatAmount(double amount) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(amount);
    }
}