package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 5000);

            System.out.println("Client connecté au serveur.");

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);

            int x;

            do {
                System.out.print("Entrez un entier (0 pour arrêter) : ");
                x = scanner.nextInt();

                output.writeInt(x);
                output.flush();

                if (x != 0) {
                    int resultat = input.readInt();

                    System.out.println("Résultat reçu du serveur : " + resultat);
                }

            } while (x != 0);

            System.out.println("Communication terminée.");

            scanner.close();
            output.close();
            input.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}