import java.util.ArrayList;


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
        String currentDecryptedData = encryptedData;

        for(int i = 1; i <= 26; i++)
        {
            currentDecryptedData = fileEncrypt.encodeString(encryptedData, 26 - i);
            if(containsRealWord(currentDecryptedData))
            {
                break;
            }
        }
        // If the String cannot match anything found in AllWords, return null (may change this later)
        return currentDecryptedData;
    }

    public String decryptStringWithShift(String encryptedData, int shift)
    {
        return fileEncrypt.encodeString(encryptedData, 26 - shift);
    }
}
