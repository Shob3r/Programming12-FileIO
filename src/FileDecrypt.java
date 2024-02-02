import java.util.ArrayList;


public class FileDecrypt extends EncryptionBase
{
    public boolean containsRealWord(String phrase)
    {
        FileRead reader = new FileRead();
        
        String[] phraseWords = phrase.split(" "); // Breaks my phrase down into individual words
        ArrayList<String> allWords = reader.retrieveDataListFromFile("AllWords.txt");
        
        boolean foundRealWord = false;
        
        for(String myWord: phraseWords)
        {
            for(String dictWord: allWords)
            {
                if(dictWord.equalsIgnoreCase(myWord))
                {
                    foundRealWord = true;
                    break;
                }
            }
        }
        
        return foundRealWord;
    }
    public String bruteForceDecryptString(String encryptedData)
    {
        for(int i = 0; i < 26; i++)
        {
            String currentDecryptedData = "";
            if(containsRealWord(currentDecryptedData))
            {
                return currentDecryptedData;
            }
        }
        // If the String cannot match anything found in AllWords, return null (may change this later)
        return null;
    }
    public void decryptStringWithShift(String encryptedData, int shift)
    {
        
    }
}
