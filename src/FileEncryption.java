import java.util.concurrent.ThreadLocalRandom;

public class FileEncryption
{
    private final int cypherShift;
    FileRead fileRead = new FileRead();
    public FileEncryption(int shift)
    {
        cypherShift = shift;
    }


    public String encryptFile(String file, boolean isRandomShift)
    {
        String fileData = fileRead.retrieveDataFromFile(file);
        if(isRandomShift)
        {
            int randomShift = ThreadLocalRandom.current().nextInt(1, 26 + 1);
        }

        return "";
    }

    public String decryptFile(String encryptedFile, boolean bruteForce)
    {
        String encryptedFileData = fileRead.retrieveDataFromFile(encryptedFile);
        if(bruteForce)
        {
            return "";
        }
        else
        {
            return  "";
        }


    }

    public String encryptFileWithRandomShift(String file)
    {
        String fileData = fileRead.retrieveDataFromFile(file);

        return "";
    }

    public int getCypherShift()
    {
        return cypherShift;
    }
}
