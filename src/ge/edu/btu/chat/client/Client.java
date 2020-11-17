package ge.edu.btu.chat.client;

import ge.edu.btu.chat.server.Server;
import ge.edu.btu.chat.server.ServerThread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket; // კლიენტი
        ObjectOutputStream objectOutputStream = null; // ნაკადი
        boolean isStart = true;
        String stopper = "ნახვამდის";


        try {
            socket = new Socket("localhost", 9011);
            System.out.println("მიმდინარეობს კავშირის დამყარება ...");

            Scanner myObj = new Scanner(System.in);

            while (isStart) {
                System.out.println("დასვი კითხვა: ");
                String question = myObj.nextLine();

                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(question);

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                String str = (String) objectInputStream.readObject();
                System.out.println(str);

                if(question.equals(stopper)){
                    isStart = false;
                }
            }
            objectOutputStream.close();
            socket.close();
            System.out.println("კავშირის დასასრული !!!");

        } catch (Exception e) {
            System.out.println("შეცდომა : " + e);
        }
    }
}
