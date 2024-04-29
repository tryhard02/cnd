import java.util.Scanner;

public class HammingCode {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the data stream:");
        String dataStream = scanner.nextLine();

        int m = dataStream.length();
        int n = 1;
        while (Math.pow(2, n) <= m + n + 1) {
            n++;
        }

        int[] hammingCode = encodeHamming(dataStream, m, n);

        System.out.println("Encoded Hamming Code:");
        for (int bit : hammingCode) {
            System.out.print(bit);
        }
    }

    public static int[] encodeHamming(String dataStream, int m, int n) {
        int[] hammingCode = new int[m + n];
        int j = 0;
        for (int i = 0; i < m + n; i++) {
            if (isPowerOfTwo(i + 1)) {
                hammingCode[i] = 0; // Parity bit initially set to 0
            } else {
                hammingCode[i] = Character.getNumericValue(dataStream.charAt(j));
                j++;
            }
        }

        // Calculate parity bits
        for (int i = 0; i < n; i++) {
            int parityIndex = (int) Math.pow(2, i) - 1;
            int parity = calculateParity(hammingCode, parityIndex);
            hammingCode[parityIndex] = parity;
        }

        return hammingCode;
    }

    public static boolean isPowerOfTwo(int x) {
        return x != 0 && ((x & (x - 1)) == 0);
    }

    public static int calculateParity(int[] hammingCode, int parityIndex) {
        int parity = 0;
        for (int i = parityIndex; i < hammingCode.length; i++) {
            if (((i + 1) & (parityIndex + 1)) != 0) { // Check if i+1 has a 1 in the same position as parityIndex+1
                parity ^= hammingCode[i];
            }
        }
        return parity;
    }
}

