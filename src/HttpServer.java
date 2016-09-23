package httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Filename:    HttpServer.java
 * Purpose:     Implements a server that listens on a given port 
 *              and answers with a random hexadecimal 32 character string.
 * 
 * @author      Andrew Blackwood
 * @version     1.0, 22/09/2016
 */
public class HttpServer {

    public static void main(String[] args) {
        
        // Get port number from command line
        CommandLineParser commandLineParser = new CommandLineParser();
        int portNumber = commandLineParser.parse(args);
        
        ServerSocket serverSocket = null;
        
        // Check that server can listen on given port
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server listening on port " + portNumber
                    + "...");
        } 
        catch (IOException e) {
            ErrorHandler error = new ErrorHandler();
            error.printAndExit("Error: Server cannot listen on port " 
                    + portNumber, true, -1);
        }
        
        // Set up a thread pool
        int processorCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processorCount);

        // Add client connection thread to thread pool
        while (true) {
            try {
                ServerRunnable clientConnection;
                clientConnection = new ServerRunnable(serverSocket.accept());
                executor.execute(clientConnection);
            }
            catch (IOException e) {
                ErrorHandler error = new ErrorHandler();
                error.print("Error: Accept connection failed on port "
                               + portNumber);
            }
        }   
    }   
}
