
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
public class FileRead {
      
    /**
     * Read all data from specified file
     */
    public void readFromFile(String fileName)
    {
        // Attempt to read from the generic file stored in 'allWordsFile' variable
        try {
            Scanner fileIn = new Scanner(new FileReader(fileName));
            
            while(fileIn.hasNext())
            {
                System.out.println(fileIn.nextLine());
            }
            fileIn.close();
        } catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    /**
     * Print out contents of specified file using custom delimiter
     * @param fileName
     * @param delimiter 
     */
    public void readFromFileCustomDelimiter(String fileName, String delimiter)
    { 
        // Attempt to read from the generic file stored in 'allWordsFile' variable
        try
        {
            Scanner fileIn = new Scanner(new FileReader(fileName));
            fileIn.useDelimiter(delimiter);
            
            while(fileIn.hasNext())
            {
                System.out.println(fileIn.next());
            }
            
            fileIn.close();
        }

        catch(IOException e)
        {
            System.out.println(e);
        }        
    }
    
    /**
     * Retrieve data from file and return it as a single String
     * @param fileName
     * @return 
     */

    // Refactored to use Java 11 code instead of pre-Java 11 code (Which is just better)
    // https://stackoverflow.com/questions/3849692/whole-text-file-to-a-string-in-java
    public String retrieveDataFromFile(String fileName)
    {
        try
        {
            return Files.readString(Path.of(fileName));
        }
        catch (IOException e)
        {
            System.out.println(e);
            return null;
        }
    }
    
    /**
     * Retrieve data from file and return array list (string) of all entries
     * @param fileName
     * @return
     */
    public ArrayList<String> retrieveDataListFromFile(String fileName)
    {
        ArrayList<String> fileData = new ArrayList<>();
        
        // Attempt to read from file and build array list of data
        try
        {
            Scanner fileIn = new Scanner(new FileReader(fileName));
            
            while(fileIn.hasNext())
            {
                fileData.add(fileIn.nextLine());
            }
            
            fileIn.close();
            
        }
        catch(IOException e)
        {
            System.out.println(e);
        }            
        
        return fileData;
    }
    
    /**
     * Retrieve and array list (string) of data from file, using custom delimiter
     * @param fileName
     * @param delimiter
     * @return 
     */
   public ArrayList<String> retrieveDataFromFileCustomDelimiter(String fileName, String delimiter)
    {
        ArrayList<String> fileData = new ArrayList<>();
        
        // Attempt to read from the generic file stored in 'allWordsFile' variable
        try
        {
            Scanner fileIn = new Scanner(new FileReader(fileName));
            fileIn.useDelimiter(delimiter);
            
            while(fileIn.hasNext())
            {
                String data = fileIn.next();
                if(!data.isEmpty()) // Ignore any empty entries; only store meaningful data
                {
                    fileData.add(data);
                }           
            }
            fileIn.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return fileData;
    }

    // Should Return 466,544 each time since that's how many lines there are in the file
    // For whatever reason I have to use long for this method because it does not allow int (this is so sad)
    public long getTotalLines(String inputFilePath) throws IOException
    {
        return Files.lines(Paths.get(inputFilePath)).count();
    }
    public long getTotalWords(String file) throws IOException
    {
        String fileData = retrieveDataFromFile(file);
        String[] words = fileData.split("\\s+");

        return words.length;
    }
    public long getTotalCharacters(String file) throws IOException
    {
        String fileData = retrieveDataFromFile(file);
        return fileData.length();
    }
    // Any file path stuff just assumes a path I am referencing is in the root directory (As an example, what should be "AllWords.txt" can only be written as "src/AllWords.txt", or it will think it's in (goodbye 30 minutes of my time))
    public boolean doesFileContainString(String inputFilePath, String word, boolean isCaseSensitive)
    {
        String fileContents = retrieveDataFromFile(inputFilePath);
        if(!isCaseSensitive)
        {
            fileContents = fileContents.toLowerCase();
            String formattedWord = word.toLowerCase();
        }
        return fileContents.contains(word);
    }
   
   //Create a method that returns a list that only includes words of a certain length (ex: all the words with 3 letters in them)
   public String onlyReturnCertainLengthWords(String file, int maximumLength)
   {
       // Phind AI has optimized this code (It used to be a for loop (very inefficient))

       StringBuilder certainLengthWords = new StringBuilder();
       try (BufferedReader reader = new BufferedReader(new FileReader(file)))
       {
           String currentLine;
           while ((currentLine = reader.readLine()) != null)
           {
               if (!(currentLine.length() > maximumLength))
               {
                   certainLengthWords.append("\n").append(currentLine);
               }
           }
       }
       catch (IOException e)
       {
           System.out.println(e);
       }
       return certainLengthWords.toString();
   }
}
