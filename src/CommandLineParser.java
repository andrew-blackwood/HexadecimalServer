package httpserver;

/**
 * Filename:    CommandLineParser.java
 * Purpose:     Parse the command-line arguments given to the HttpServer
 *              application.
 * 
 * @author      Andrew Blackwood
 * @version     1.0, 22/09/2016
 */
class CommandLineParser {
        
    public CommandLineParser() {}
    
    public int parse(String[] args) {
        
        int portNumber = 8888;
        
        for (int i = 0; i < args.length; i++) {
            if (args[i].matches("-port")){    
                if (args.length > i + 1) {
                    try {
                        return Integer.parseInt(args[i + 1]);
                    }
                    catch (Exception e) {
                        ErrorHandler error = new ErrorHandler();
                        error.printAndExit("Error: The port number you entered"
                            + " is not valid.",
                            true, 1);
                    }
                }
                else {
                    ErrorHandler error = new ErrorHandler();
                    error.printAndExit("Error: You need to provide a port"
                            + " number argument after the -port argument.",
                            true, 1);
                }
            }
        }
        
        ErrorHandler error = new ErrorHandler();
        error.printAndExit("Error: You need to enter the -port argument"
                + " followed by a port number for the server to listen on.",
                true, 1);
       
        return portNumber;
    }
}
