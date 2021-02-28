package src.hello;

import java.io.*;
import java.net.*;
import java.util.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 
public class Client {
    public static void main(String[] args) throws IOException {
        if (args.length <= 3) {
             System.out.println("Usage:\n java Client <host_name> <remote_object_name> <oper> <opnd>*\n");
             return;
        }

        if ((args[2].equals("LOOKUP")) && (args.length != 4)) {
            System.out.println("Usage:\n java Client <host_name> <remote_object_name> <oper> <opnd>*\n");
            return;
        }

        if ((args[2].equals("REGISTER")) && (args.length != 5))  {
            System.out.println("Usage:\n java Client <host_name> <remote_object_name> <oper> <opnd>*\n");
            return;
        }

        int host = Integer.parseInt(args[0]);
        String remote_object_name = args[1];

        String request = args[2].toUpperCase() + " ";
        for(int i = 3; i < args.length; i++) {
            request += args[i] + " ";
        }
        
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup(remote_object_name);
            String response = stub.sayHello(request);
            System.out.println("response: " + response); 
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}