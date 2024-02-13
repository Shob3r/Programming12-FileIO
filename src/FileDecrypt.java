public class FileDecrypt extends EncryptionBase
{
    FileEncrypt fileEncrypt = new FileEncrypt();

    public boolean containsRealWord(String phrase)
    {
        FileRead reader = new FileRead();
        String[] phraseWords = phrase.split(" "); // Breaks my phrase down into individual words
        String allWords = reader.retrieveDataFromFile("src/AllWords.txt");

        // Special thanks to Phind AI for writing this portion of the codebase
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
        int currentShift = 0;
        // As there are only 26 different possible combinations, the brute force will loop 26 times UNTIL it finds a shift that works
        for(int i = 1; i <= 26; i++)
        {
            String currentDecryptedData = decryptStringWithShift(encryptedData, i);
            System.out.println(currentDecryptedData);
            if(containsRealWord(currentDecryptedData))
            {
                currentShift = i;
                break;
            }
        }
        return decryptStringWithShift(encryptedData, currentShift);
    }

    public String decryptStringWithShift(String encryptedData, int shift)
    {
        // Since the max value is 26 for a cipher in this program (which is just the exact same contents of the file), just add the remaining integer amount to the shift so it adds up to 26 to decrypt
        // For example, a file with a shift of 13 will be subtracted from 26 to get 13 to get to back to 26, which will then be the decrypted contents of the file
        // Somewhat difficult for me to explain this

        return fileEncrypt.encodeString(encryptedData, 26 - shift);
    }
}
