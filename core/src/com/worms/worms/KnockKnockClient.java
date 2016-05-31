package com.worms.worms;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.io.*;
import java.net.*;

public class KnockKnockClient implements Runnable{
        public static boolean koniecAplikacji = false;
        public boolean obroty = true;
        Wynik J;
        public boolean wlacznik = true;
        public boolean justPressed = true;
        public boolean wyslij = true;

        @Override
        public void run(){

        String hostName = "127.0.0.1";
        int portNumber = 4444;

        try {
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(kkSocket.getInputStream()));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser = null;
            while (wlacznik) {

                if ((fromServer = in.readLine())!= null)
                {
                    System.out.println("Server: " + fromServer);
                    if (fromServer.equals("Zapisano.")) {
                        koniecAplikacji = true;
                    }
                }

                while(obroty) {
                    if (Gdx.input.isKeyPressed(Input.Keys.F1) && justPressed) {
                        fromUser = "Tak";
                        justPressed = false;
                        wyslij = true;
                    }
                    if (fromUser != null && wyslij) {
                        out.println(fromUser);
                        System.out.println(fromUser);
                        //justPressed = true;
                        wyslij = false;
                    }
                    //fromUser = stdIn.readLine();
//                    System.out.println(fromUser  + "   " + Integer.toString(J.wynik));
//                    if(fromUser == Integer.toString(J.wynik))
//                    {
//                        obroty = false;
//                    }
//                    if (fromUser != null) {
//                        //System.out.println("Client: " + fromUser);
//                        out.println(fromUser);
//                        fromUser = Integer.toString(J.wynik);
//
//                    }

                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
        }
}
