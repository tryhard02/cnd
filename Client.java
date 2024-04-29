import java.io.*; 

import java.net.*; 

public class Client { 

    public static void main(String[] args) throws IOException { 

        Socket clientSocket = null; 

        try { 

            clientSocket = new Socket("localhost", 6789); // Connect to server on localhost, port 6789 

            System.out.print("Provide Message to Pass TO Server: ");

            // Create input and output streams for communication 

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true); 

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)); 

            String messageToSend = " ";
            

            while ((messageToSend = userInput.readLine()) != null) { 
                out.println(messageToSend); // Send message to server 
                System.out.println("Received from server: " + in.readLine() +"\n");
                System.out.print("Provide Message to Pass To Server: ");  // Receive server's response 

            } 

        } catch (IOException e) { 

            e.printStackTrace(); 

        } finally { 

            if (clientSocket != null) { 

                clientSocket.close(); // Close client socket 

            } 

        } 

    } 

} 
