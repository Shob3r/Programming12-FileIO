
import java.io.*;
import java.nio.file.Files;
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

    public String retrieveDataFromFile(String fileName)
    {
        StringBuilder data = new StringBuilder();
        
        // Attempt to read from file and build concatenated String
        try
        {
            Scanner fileIn = new Scanner(new FileReader(fileName));
            
            while(fileIn.hasNext())
            {
                data.append(fileIn.nextLine());
            }
            
            fileIn.close();
            
        }
        catch(IOException e)
        {
            System.out.println(e);
        }            
        return data.toString();
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
   public ArrayList<String> retrieveDataFromFileCustom(String fileName, String delimiter)
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

    public long getTotalLines(String inputFilePath) throws IOException
    {
        return Files.lines(Paths.get(inputFilePath)).count();
    }

    public int getTotalWords(String inputFilePath)
    {
        try
        {
            for(int i = 0; i < getTotalLines(inputFilePath); i++)
            {

            }
        }
        catch (IOException e)
        {
            System.out.println(e);
            return -1;
        }

        return 3424; // temp value
    }

    public boolean doesFileContainString(String inputFilePath, String word, boolean caseSensitive)
    {
        String fileContents =  retrieveDataFromFile(inputFilePath);
        if(!caseSensitive)
        {
            String formattedFileContents = fileContents.toLowerCase();
            String formattedWord = word.toLowerCase();

            return formattedFileContents.contains(word);
        }
        else
        {
            return fileContents.contains(word);
        }
    }
   
   //Create a method that returns a list that only includes words of a certain length (ex: all the words with 3 letters in them)  
}
