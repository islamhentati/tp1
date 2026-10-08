package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Serveur en attente de connexion...");

            Socket socket = serverSocket.accept();

            System.out.println("Client connecté.");

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            int x;

            do {
                x = input.readInt();

                if (x != 0) {
                    int resultat = x * 5;

                    System.out.println("Nombre reçu : " + x);
                    System.out.println("Résultat : " + resultat);

                    output.writeInt(resultat);
                    output.flush();
                }

            } while (x != 0);

            System.out.println("Le client a terminé.");

            input.close();
            output.close();
            socket.close();
            serverSocket.close();

            System.out.println("Connexion fermée.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}