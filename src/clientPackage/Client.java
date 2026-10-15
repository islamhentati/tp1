
package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("10.248.38.120", 1234);
             DataOutputStream out =
                     new DataOutputStream(socket.getOutputStream());
             DataInputStream in =
                     new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Client connecte au serveur.");

            while (true) {
                System.out.print("Premier entier (0 pour arreter) : ");
                int a = scanner.nextInt();
                out.writeInt(a);
                out.flush();

                if (a == 0) {
                    break;
                }

                System.out.print("Deuxieme entier : ");
                int b = scanner.nextInt();

                System.out.print("Operation (+, -, *, /) : ");
                char operation = scanner.next().charAt(0);

                out.writeInt(b);
                out.writeChar(operation);
                out.flush();

                System.out.println("Resultat : " + in.readUTF());
            }

            System.out.println("Communication terminee.");

        } catch (IOException e) {
            System.out.println("Erreur client : " + e.getMessage());
        }
    }
}
