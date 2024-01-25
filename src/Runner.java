
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner
{
    public static void main(String[] args) throws IOException
    {
        FileRead reader = new FileRead();
        FileWrite writer = new FileWrite();
        FileDecrypter decrypt = new FileDecrypter();
        FileEncrypter encrypt = new FileEncrypter();
        RunnerHandlers handlers = new RunnerHandlers();
        String allWordsDir = handlers.getAllWordsDir();
        ArrayList<String> characterCountFormattedWords = writer.wordLengthSortedAllWords(allWordsDir);


        // System.out.println(characterCountFormattedWords);

        handlers.createFileWithCharacterSortedWords("src/CharacterSorted.txt", characterCountFormattedWords);
        System.out.println("AllWords.txt contains " + reader.getTotalLines(allWordsDir) + " lines of text!");
        System.out.println("AllWords.txt contains " + reader.getTotalCharacters(allWordsDir) + " characters of text!");
        System.out.println("Finally, AllWords.txt contains " + reader.getTotalWords(allWordsDir) + " words!");

        handlers.doesFileContainStringRunner();
        handlers.onlyReturnCertainLengthWordsRunner();
    }
}

class RunnerHandlers
{
    private final String allWordsDir = "src/AllWords.txt";
    Scanner scanner = new Scanner(System.in);
    FileRead reader = new FileRead();
    public void doesFileContainStringRunner()
    {
        System.out.println("What word would you like to find in the file containing every word?");
        String wordToSearch = scanner.next();

        if(reader.doesFileContainString(allWordsDir, wordToSearch, false))
        {
            System.out.println(wordToSearch + " has been found in the file containing every single word!");
        }
        else
        {
            System.out.println("This file does NOT contain the word " + wordToSearch);
        }
    }

    public void onlyReturnCertainLengthWordsRunner()
    {
        System.out.println("Only show words with up to how many characters?");
        int maxCharacterCount = scanner.nextInt();

        System.out.println("Here is every single word that have at most " + maxCharacterCount + " characters!");
        System.out.println(reader.onlyReturnCertainLengthWords(allWordsDir, maxCharacterCount));
    }

    public String getAllWordsDir()
    {
        return allWordsDir;
    }

    public void createFileWithCharacterSortedWords(String saveToPath, ArrayList<String> dataToWrite)
    {
        File file = new File(saveToPath);
        try
        {
            FileWriter fileWriter = new FileWriter(saveToPath);
            fileWriter.write(dataToWrite.toString());
            fileWriter.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
