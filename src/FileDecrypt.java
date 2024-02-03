import java.util.ArrayList;
import java.util.Scanner;


public class FileDecrypt extends EncryptionBase
{
    FileEncrypt fileEncrypt = new FileEncrypt();

    public boolean containsRealWord(String phrase)
    {
        FileRead reader = new FileRead();

        String[] phraseWords = phrase.split(" "); // Breaks my phrase down into individual words
        ArrayList<String> allWords = reader.retrieveDataListFromFile("src/AllWords.txt");

        boolean foundRealWord = false;

        for(String myWord: phraseWords)
        {
            for(String dictWord: allWords)
            {
                if(dictWord.equalsIgnoreCase(myWord))
                {
                    System.out.println("decrypted");
                    foundRealWord = true;
                    break;
                }
            }
        }

        return foundRealWord;
    }

    // Using already existing code to make the decryption algorithms work!!
    public String bruteForceDecryptString(String encryptedData)
    {
        Scanner scanner = new Scanner(System.in);
        int currentShift = 1;
        while(true)
        {
            System.out.println(decryptStringWithShift(encryptedData, currentShift));
            System.out.println("Does the above data look correct to you?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = scanner.nextInt();

            if(choice == 1)
            {
                break;
            }
            else if(choice == 2)
            {
                currentShift++;
            }
        }
        return decryptStringWithShift(encryptedData, currentShift);
    }

    public String decryptStringWithShift(String encryptedData, int shift)
    {
        return fileEncrypt.encodeString(encryptedData, 26 - shift);
    }
}
