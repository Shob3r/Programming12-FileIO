import java.util.ArrayList;

public class FileEncrypter {
    
    FileRead read = new FileRead();
    FileWrite write = new FileWrite();
    
    public int characterCast(char letter)
    {
        return (int)letter;
    }
    
    public char convertBackToChar(int number)
    {
        return (char)number;
    }
    
    public String encodeString(String message, int shift)
    {
        String encodedString = "";
        
        for(int i = 0; i < message.length(); i++) // Loop as many times as there are letters
        {
            char tempChar = message.charAt(i); // Snag each character from the string one at a time
            
            // Now, do the converting
            int letterVal = characterCast(tempChar);
            char newChar = convertBackToChar(letterVal + shift);

            if(characterCast(newChar) > 126)
            {
                int remainder = characterCast(newChar) - 126;
                newChar = convertBackToChar(32 + (remainder - 1));
            }
            else if(characterCast(newChar) < 32)
            {
                int remainder = characterCast(newChar);
                while(characterCast(newChar) != 32)
                {
                    remainder++;
                }
                newChar = convertBackToChar(126 - remainder + 1);
            }
            encodedString += newChar;
        }
        
        return encodedString;
    }

    public ArrayList<String> encodedData(ArrayList<String> unencodedData, int shift)
    {
        ArrayList<String> convertedData = new ArrayList<>();
        for (String unencodedDatum : unencodedData)
        {
            convertedData.add(encodeString(unencodedDatum, shift));
        }
        return convertedData;
    }
}
