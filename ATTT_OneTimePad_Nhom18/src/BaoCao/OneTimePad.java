package BaoCao;
import java.util.Random;
import java.util.Scanner;

public class OneTimePad {
    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static String generateOTP() {
        char[] chars = CHARSET.toCharArray();
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        while (otp.length() < chars.length) {
            int randomIndex = random.nextInt(chars.length);
            char randomChar = chars[randomIndex];
            if (otp.indexOf(String.valueOf(randomChar)) == -1) {
                otp.append(randomChar);
            }
        }

        return otp.toString();
    }

    private static String encrypt(String plaintext, String otp) {
        StringBuilder result = new StringBuilder();
        plaintext = plaintext.toUpperCase();

        for (char c : plaintext.toCharArray()) {
            if (CHARSET.indexOf(c) != -1) {
                int index = CHARSET.indexOf(c);
                result.append(otp.charAt(index));
            }
        }

        return result.toString();
    }

    private static String decrypt(String otp, String secret) {
        StringBuilder result = new StringBuilder();
        secret = secret.toUpperCase();

        for (char c : secret.toCharArray()) {
            if (otp.indexOf(c) != -1) {
                int index = otp.indexOf(c);
                result.append(CHARSET.charAt(index));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        String otp = generateOTP();
        String encryptedText = encrypt(plaintext, otp);

        System.out.println("Generated OTP: " + otp);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decrypt(otp, encryptedText));

        scanner.close();
    }
}
