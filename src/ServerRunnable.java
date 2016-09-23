package httpserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Filename:    ServerRunnable.java
 * Purpose:     A runnable to be executed by a thread added to
 *              the thread pool in the HttpServer application.
 * 
 * @author      Andrew Blackwood
 * @version     1.0, 22/09/2016
 */
public class ServerRunnable implements Runnable {
  
    Socket clientSocket;
    String hex;
   
    ServerRunnable(Socket clientSocket) {
      this.clientSocket = clientSocket;
    }
    
    @Override
    public void run() {   
        try {
            RandomHex randomHex = new RandomHex();
            this.hex = randomHex.getRandomHex(32);
                                
            try {
                PrintWriter out
                        = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(hex);
                out.close();
            } 
            catch (IOException e) {
                ErrorHandler error = new ErrorHandler();
                error.print("Error: Issue with getting output stream"
                        + "for client connection.");
            }        
        }
        finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                ErrorHandler error = new ErrorHandler();
                error.print("Error: Unable to close client connection.");
            }
        }
    }
}
