import java.io.*;
import java.util.*;
import java.util.Base64;

public class Main {
    static final String FILE_NAME = "passwords.txt";
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("\n--- Simple Password Manager ---");
            System.out.println("1. Save Credentials");
            System.out.println("2. View Credentials");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = reader.readLine();

            if (choice.equals("1")) {
                System.out.print("Enter website: ");
                String website = reader.readLine();
                System.out.print("Enter username: ");
                String username = reader.readLine();
                System.out.print("Enter password: ");
                String password = reader.readLine();

                String encoded = Base64.getEncoder().encodeToString(password.getBytes());
                FileWriter fw = new FileWriter(FILE_NAME, true);
                fw.write(website + "," + username + "," + encoded + "\n");
                fw.close();
                System.out.println("Saved.");
            } else if (choice.equals("2")) {
                BufferedReader fileReader = new BufferedReader(new FileReader(FILE_NAME));
                String line;
                System.out.println("\nSaved Credentials:");
                while ((line = fileReader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String decoded = new String(Base64.getDecoder().decode(parts[2]));
                        System.out.println("Website: " + parts[0] + ", Username: " + parts[1] + ", Password: " + decoded);
                    }
                }
                fileReader.close();
            } else if (choice.equals("3")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}
