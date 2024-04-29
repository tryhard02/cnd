import java.io.*; 

import java.net.*; 

public class Server { 

    public static void main(String[] args) throws IOException { 

        ServerSocket serverSocket = null; 

        try { 

            serverSocket = new ServerSocket(6789); // Create socket and listen on port 6789 

            System.out.println("Server started and listening on port 6789"); 

            while (true) { 

                Socket clientSocket = serverSocket.accept(); // Accept client connection 

                System.out.println("Client connected"); 

// Create input and output streams for communication 

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 
                
 
                String messageFromClient = " "; 
                while ((messageFromClient = in.readLine()) != null) { 

                    System.out.println("Received from client: " + messageFromClient); 
                    System.out.print("Provide Message to Pass To Client: ");
                    BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in)); 
                    String svout = serverInput.readLine();
                    if((svout) != null){
                        out.println(svout); // Echo back to client 
                    }
                } 

                clientSocket.close(); // Close connection after communication ends 

            } 

        } catch (IOException e) { 

            e.printStackTrace(); 

        } finally { 

            if (serverSocket != null) { 

                serverSocket.close(); // Close server socket 

            } 

        } 

    } 

} 
