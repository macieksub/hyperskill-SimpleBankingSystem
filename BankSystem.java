package banking;
import java.util.*;

public class BankSystem {

    static int counter = 0;
    static long number;
    static long pin;
    static int status = 1;
    static int status2 = 1;
    static int[] accountDigits = new int[15];

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        long[][] cards = new long[20][2];
        while (status != 0) {
            System.out.println("1. Create an account\n2. Log into account\n0. Exit");
            status = sc.nextInt();
            if (status == 1) {
                CreateAnAccount(random, cards);
            } else if (status == 2) {
                LogIn(sc, cards);
            }
        }
        System.out.println("Bye!");
    }
    static void CreateAnAccount(Random random, long[][] cards) {
        long accountNumber = 400000000000000L;
        accountNumber += random.nextInt(999999999 - 99999999) + 100000000;
//        System.out.println(accountNumber);
        int ending = FinalCardNumber(accountNumber);
        accountNumber *= 10;
        accountNumber += ending;
        int pin = random.nextInt(9999 - 999) + 1000;
        cards[counter][0] = accountNumber;
        cards[counter][1] = pin;
        counter ++;
        System.out.println("Your card has been created\nYour card number:");
        System.out.println(accountNumber);
        System.out.println("Your card PIN:");
        System.out.println(pin);
    }
    static void LogIn(Scanner sc, long[][] cards) {
        System.out.println("Enter your card number:");
        number = sc.nextLong();
        System.out.println("Enter your PIN:");
        pin = sc.nextLong();
        for (int i = 0; i < cards.length; i++) {
            if (cards[i][0] == number) {
                if (cards[i][1] == pin) {
                    System.out.println("You have successfully logged in!");
                    Logged(sc);
                    break;
                }
            } else if (i + 1 == cards.length) {
                System.out.println("Wrong card number or PIN!");
            }
        }
    }
    static void Logged(Scanner sc) {
        while (status2 != 0) {
            System.out.println("1. Balance\n2. Log out\n0. Exit");
            status2 = sc.nextInt();
            if (status2 == 1) {
                System.out.println("Balance: 0");
            } else if (status2 == 2) {
                System.out.println("You have successfully logged out!");
                status2 = 0;
            } else if (status2 == 0) {
                status = 0;
            }
        }
    }
    static int FinalCardNumber(long accountNumber) {
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0) {
                if ((accountNumber % 10) * 2 > 9) {
                    accountDigits[i] = (int) ((accountNumber % 10) * 2 - 9);
                } else {
                    accountDigits[i] = (int) ((accountNumber % 10) * 2);
                }
            } else {
                accountDigits[i] = (int) (accountNumber % 10);
            }
            accountNumber /= 10;
        }
        int suma = 0;
        for (int aa : accountDigits) {
            suma += aa;
        }
        Arrays.fill(accountDigits, 0);
        suma = suma % 10;
        if (suma != 0) {
            return 10 - suma;
        } else {
            return 0;
        }
    }
}