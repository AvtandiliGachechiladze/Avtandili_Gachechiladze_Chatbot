package ge.edu.btu.chat.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket client;
    private  int clientID;
    private boolean running = true;

    public ServerThread(Socket client, int clientID) {
        this.client = client;
        this.clientID = clientID;
    }

    ObjectOutputStream objectOutputStream = null;

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("მიმდინარეობს მონაცემების წაკითხვა ...");
                ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
                String str = (String) objectInputStream.readObject();
                System.out.println("მიღებული შეტყობინება : " + str);

            String answer = null;
            switch (str) {
                case "გამარჯობა":
                    answer = "მოგესალმებით, რით შემიძლია დაგეხმაროთ?";
                    break;
                case "მაჩვენე კურსი":
                    answer = "3.0";
                    break;
                case "მაჩვენე ფილიალები":
                    answer = "ჭავჭავაძის ფილიალი, ქავთარაძის ფილიალი, ვაჟას ფილიალი ნ1";
                    break;
                case "ნახვამდის":
                    answer = "მადლობთ, რომ დაგვიკავშირდით, ნახვამდის!";
                    break;
                default:
                    answer = "სამწუხაროდ ამ კითხვაზე პასუხი არ მაქვს!";
            }
                System.out.println(answer);
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectOutputStream.writeObject(answer);
            } catch (Exception e) {
                System.out.println("მოხდა შეცდომა : " + e);
            }
        }
    }
}

