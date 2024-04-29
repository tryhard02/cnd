import java.util.Scanner;

public class CRC {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Input data array
    int[] data = getInputArray("data", scanner);

    // Input divisor array
    int[] divisor = getInputArray("divisor", scanner);

    // Calculate CRC code
    int[] crc = calculateCRC(data, divisor);

    // Display generated CRC code
    System.out.println("Generated CRC code is:");
    displayBits(crc);

    // Input data to be sent
    int[] sentData = getInputArray("data to be sent", scanner);

    // Receive and check data
    receiveData(sentData, divisor);
  }

  // Method to get input array from user
  private static int[] getInputArray(String name, Scanner scanner) {
    System.out.println("Enter the size of the " + name + " array:");
    int size = scanner.nextInt();
    int[] array = new int[size];
    System.out.println("Enter " + name + " bits in the array one by one:");
    for (int i = size - 1; i >= 0; i--) {
      System.out.println("Enter bit " + (size - i) + ":");
      array[i] = scanner.nextInt();
    }
    return array;
  }

  // Method to calculate CRC code
  private static int[] calculateCRC(int[] data, int[] divisor) {
    int[] rem = new int[data.length + divisor.length - 1];
    System.arraycopy(data, 0, rem, 0, data.length);

    for (int i = 0; i <= data.length - divisor.length; i++) {
      if (rem[i] == 1) {
        for (int j = 1; j < divisor.length; j++) {
          rem[i + j] ^= divisor[j];
        }
      }
    }
    return rem;
  }

  // Method to display bits
  private static void displayBits(int[] bits) {
    for (int i = bits.length - 1; i >= 0; i--) {
      System.out.print(bits[i]);
    }
    System.out.println();
  }

  // Method to receive and check data
  private static void receiveData(int[] data, int[] divisor) {
    int[] rem = calculateCRC(data, divisor);
    boolean isError = false;
    for (int i = 0; i < rem.length; i++) {
      if (rem[i] != 0) {
        isError = true;
        break;
      }
    }
    if (isError) {
      System.out.println("Corrupted data received...");
    } else {
      System.out.println("Data received without any error.");
    }
  }
}