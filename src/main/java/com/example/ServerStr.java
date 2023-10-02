package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStr{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;




    public Socket attendi(){
        try{
            System.out.println("Il server è aperto...");
            server = new ServerSocket(3000);
            client = server.accept();
            server.close();

            inDalClient = new BufferedReader(new InputStreamReader (client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");

        }

        return client;
    }



    public void Comunica(){
        try {
            System.out.println("benvenuto client,scrivi una frase e lo trasformo in maiscuolo");
            stringaRicevuta = inDalClient.readLine();
            System.out.println("Stringa ricevuta dal cliente: " + stringaRicevuta);

            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("unvio la stringa modificata al client...");

            outVersoClient.writeBytes(stringaModificata+'\n');


            System.out.println("server: fine elaborazione...ciao!");
            client.close();

        } catch (Exception e) {
            System.out.println("Errore di comunicazione");
        }
    }
}