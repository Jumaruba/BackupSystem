package main;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Services extends Remote{
    String backup(String filePath, int replicationDeg) throws IOException;
}