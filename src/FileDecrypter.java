
import java.util.ArrayList;


public class FileDecrypter
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
                if(dictWord.toLowerCase().equals(myWord.toLowerCase()))
                {
                    foundRealWord = true;
                    break;
                }
            }
        }
        
        return foundRealWord;
    }
}
