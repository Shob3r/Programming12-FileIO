public class FileDecrypt extends EncryptionBase
{
    FileEncrypt fileEncrypt = new FileEncrypt();

    public boolean containsRealWord(String phrase)
    {
        FileRead reader = new FileRead();
        String[] phraseWords = phrase.split(" "); // Breaks my phrase down into individual words
        String allWords = reader.retrieveDataFromFile("src/AllWords.txt");

        // Special thanks to Phind AI for writing this little segment of the codebase
        // Basically, it loops through each word and checks if any word is not in AllWords. if that is the case, return false.
        // I'm sure the comments created by it are more than enough explanation for the reader, but I thought it may be useful to prove that
        // I do know what's going on, and I was just a little stupid when trying to improve this method
        // (The previous method didn't work, so I had to rewrite it)

        for (String word : phraseWords)
        {
            if (!allWords.contains(word))
            {
                return false; // If any word is not found in allWords, return false
            }
        }
        return true; // All words were found in allWords
    }
    public String bruteForceDecryptString(String encryptedData)
    {
        int currentShift = 1;
        while(true)
        {
            if(containsRealWord(decryptStringWithShift(encryptedData, currentShift))) break;
            else currentShift++;
        }

        return decryptStringWithShift(encryptedData, currentShift);
    }

    public String decryptStringWithShift(String encryptedData, int shift)
    {
        return fileEncrypt.encodeString(encryptedData, 26 - shift);
    }
}
