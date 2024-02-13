
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner
{
    public static void main(String[] args) throws IOException
    {
        FileDecrypt decrypt = new FileDecrypt();
        FileEncrypt encrypt = new FileEncrypt();
        RunnerHandlers handlers = new RunnerHandlers();
        System.out.println(decrypt.bruteForceDecryptString(encrypt.encodeString("the quick brown fox jumped over the lazy dog", 3)));
        handlers.fileIOMenu();
    }
}

class RunnerHandlers
{
    private final String allWordsDir = "src/AllWords.txt";
    Scanner scanner = new Scanner(System.in);
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    FileRead reader = new FileRead();
    FileWrite writer = new FileWrite();
    FileEncrypt encryptor = new FileEncrypt();
    FileDecrypt decryptor = new FileDecrypt();

    public void fileIOMenu()
    {
        System.out.println("Welcome the the FileIO project! (Created by Andrew)");
        while(true)
        {
            try
            {

                System.out.println("-----------------");
                System.out.println("What would you like to do?");
                System.out.println("1. Display basic information about the built-in dictionary");
                System.out.println("2. Check if the built-in dictionary contains a string of characters");
                System.out.println("3. Only display words with a certain length");
                System.out.println("4. Create a file with custom data");
                System.out.println("5. Encrypt a file");
                System.out.println("6. Decrypt a file");
                System.out.println("7. Quit the app");

                int choice = Integer.parseInt(inputReader.readLine());
                if(choice == 7) break;

                if(choice >= 1 && choice <= 6)
                {
                    switch(choice)
                    {
                        case 1:
                            displayFileInfo();
                            break;
                        case 2:
                            doesFileContainString();
                            break;
                        case 3:
                            onlyReturnCertainLengthWords();
                            break;
                        case 4:
                            createFile();
                            break;
                        case 5:
                            encryptFile();
                            break;
                        case 6:
                            decryptFile();
                            break;
                    }
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println(e);
            }
            catch (IOException e)
            {
                System.out.println("Please enter a valid integer!");
            }
        }
    }

    public void displayFileInfo()
    {
        try
        {
            System.out.println("AllWords.txt contains " + reader.getTotalLines(allWordsDir) + " lines of text!");
            System.out.println("AllWords.txt contains " + reader.getTotalCharacters(allWordsDir) + " characters of text!");
            System.out.println("Finally, AllWords.txt contains " + reader.getTotalWords(allWordsDir) + " words!");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
    public void doesFileContainString() throws IOException
    {
        System.out.println("What word would you like to find in the file containing every word?");
        String wordToSearch = inputReader.readLine();

        if(reader.doesFileContainString(allWordsDir, wordToSearch, false))
        {
            System.out.println(wordToSearch + " has been found in the file containing every single word!");
        }
        else
        {
            System.out.println("This file does NOT contain the word " + wordToSearch);
        }
    }

    public void onlyReturnCertainLengthWords()
    {
            System.out.println("Only show words with up to how many characters?");
            try
            {
                // Maybe it won't break as much with the parseInt method
                int maxCharacterCount = Integer.parseInt(inputReader.readLine());
                System.out.println("Here is every single word that have at most " + maxCharacterCount + " characters!");
                System.out.println(reader.onlyReturnCertainLengthWords(allWordsDir, maxCharacterCount));
            }
            catch (InputMismatchException mismatchException)
            {
                System.out.println("Your input was not valid! try again!");
                onlyReturnCertainLengthWords();
            }
            catch (IOException e)
            {
                System.out.println(e);
                onlyReturnCertainLengthWords();
            }
    }

    public void createFile() throws IOException
    {
        int isFirstLine = 0;

        System.out.println("Which directory should this file be saved to? (Please use '\\\\' to separate your directories)");
        String dir = inputReader.readLine();

        System.out.println("What is the name of the file? (Include file extension)");
        String fileName = inputReader.readLine();

        System.out.println("Write whatever you want here! It will show up in the newly created file. Type 'QUIT' to finish your file!");
        StringBuilder customFileData = new StringBuilder();

        while(true)
        {
            String currentLine = inputReader.readLine();
            if(currentLine.equals("QUIT"))
            {
                break;
            }

            customFileData.append(currentLine).append("\n");
        }

        try
        {
            writer.writeFileWithCustomData(customFileData.toString(), fileName, dir);
        }
        catch (IOException e)
        {
            System.out.println("This script was not able to write your file because of the following reason: " + e);
            return;
        }
        System.out.println("congratulations! the file has been written to your disk! Your file can be found at " + dir + "\\\\" + fileName);
    }

    public void encryptFile() throws IOException
    {
        System.out.println("Where is the file located?");
        String fileLocation = inputReader.readLine();

        System.out.println("What is the name of the file? include file extension");
        String fileName = inputReader.readLine();

        System.out.println("This project uses caesar cipher encryption. What is the shift value you would like to input?");
        int shift = Integer.parseInt(inputReader.readLine());

        if(shift >= 1 && shift <= 26)
        {
            String combinedFileDir = fileLocation + "/" + fileName;
            String fileData = reader.retrieveDataFromFile(combinedFileDir);
            if(fileData != null)
            {
                String encryptedData = encryptor.encodeString(fileData, shift);
                try
                {
                    writer.writeFileWithCustomData(encryptedData, fileName, fileLocation);
                }
                catch (IOException e)
                {
                    System.out.println("Error: " + e);
                }

            }
        }
        else System.out.println("Error! please input a value ranging from 1 to 26 next time!");
    }

    public void decryptFile() throws IOException
    {
        String decryptedContents = "";  // here so the end of this method can be slightly more clean

        System.out.println("Where is the file located?");
        String fileLocation = inputReader.readLine();

        System.out.println("What is the name of the file? include file extension");
        String fileName = inputReader.readLine();

        String fullFilePath = fileLocation + "/" + fileName;
        String fileData = reader.retrieveDataFromFile(fullFilePath);


        System.out.println("Do you remember the shift you encrypted the file with? (yes/no)");
        String determinePrompt = inputReader.readLine().toLowerCase();

        if(yesOrNo(determinePrompt))
        {
            System.out.println("What is the shift? Remember, the value can only be from (1-26)");
            int shift = Integer.parseInt(inputReader.readLine());
            if(shift >= 1 && shift <= 26)
            {
                decryptedContents = decryptor.decryptStringWithShift(fileData, shift);
            }
        }
        else
        {
            System.out.println("Ok, performing brute force decryption. this may take a while depending on file length");
            decryptedContents = decryptor.bruteForceDecryptString(fileData);
        }

        System.out.println(decryptedContents + "\nDoes this content look correct? (yes/no)");
        String choice = inputReader.readLine().toLowerCase();

        if(yesOrNo(choice))
        {
            System.out.println("Ok! writing the unencrypted data to the file!");
            try
            {
                writer.writeFileWithCustomData(decryptedContents, fileName, fileLocation);
            }
            catch (IOException e)
            {
                System.out.println("Error" + e);
            }
        }
        else
        {
            System.out.println("Ok, re-running script.....");
            decryptFile();
        }
    }

    private boolean yesOrNo(String input)
    {
        return input.equals("yes") || input.equals("y");
    }
}
