
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner
{
    public static void main(String[] args) throws IOException
    {
        FileRead reader = new FileRead();
        FileWrite writer = new FileWrite();
        FileDecrypt decrypt = new FileDecrypt();
        FileEncrypt encrypt = new FileEncrypt();
        RunnerHandlers handlers = new RunnerHandlers();
        String allWordsDir = handlers.getAllWordsDir();
        ArrayList<String> characterCountFormattedWords = writer.wordLengthSortedAllWords(allWordsDir);


        // System.out.println(characterCountFormattedWords);


        System.out.println("AllWords.txt contains " + reader.getTotalLines(allWordsDir) + " lines of text!");
        System.out.println("AllWords.txt contains " + reader.getTotalCharacters(allWordsDir) + " characters of text!");
        System.out.println("Finally, AllWords.txt contains " + reader.getTotalWords(allWordsDir) + " words!");

        System.out.println(decrypt.bruteForceDecryptString(encrypt.encodeString("the quick brown fox jumped over the lazy dog", 3)));

        handlers.doesFileContainStringRunner();
        handlers.onlyReturnCertainLengthWordsRunner();
        handlers.createFileWithCharacterSortedWords();
    }
}

class RunnerHandlers
{
    private final String allWordsDir = "src/AllWords.txt";
    Scanner scanner = new Scanner(System.in);
    FileRead reader = new FileRead();
    FileWrite writer = new FileWrite();

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
            try
            {
                // Maybe it won't break as much with the parseInt method
                int maxCharacterCount = Integer.parseInt(scanner.next());
                System.out.println("Here is every single word that have at most " + maxCharacterCount + " characters!");
                System.out.println(reader.onlyReturnCertainLengthWords(allWordsDir, maxCharacterCount));
            }
            catch (InputMismatchException mismatchException)
            {
                System.out.println("Your input was not valid! try again!");
                onlyReturnCertainLengthWordsRunner();
            }
    }

    public String getAllWordsDir()
    {
        return allWordsDir;
    }

    public void createFileWithCharacterSortedWords()
    {
        System.out.println("Which directory should this file be saved to? (Please use '\\\\' to separate your directories)");
        String dir = scanner.next();

        System.out.println("What is the name of the file? (Include file extension)");
        String fileName = scanner.next();

        System.out.println("Write whatever you want here! It will show up in the newly created file!");
        String customFileData = scanner.next();

        try
        {
            writer.writeFileWithCustomData(customFileData, fileName, dir);
        }
        catch (IOException e)
        {
            System.out.println("This script was not able to write your file because of the following reason: " + e);
            return;
        }
        System.out.println("congratulations! the file has been written to your disk! Your file can be found at " + dir + "\\\\" + fileName);
    }

}
