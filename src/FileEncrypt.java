import java.util.ArrayList;

public class FileEncrypt extends EncryptionBase
{
    
    FileRead read = new FileRead();
    FileWrite write = new FileWrite();
    

    
    public String encodeString(String message, int shift)
    {
        StringBuilder encodedString = new StringBuilder();
        
        for(int i = 0; i < message.length(); i++) // Loop as many times as there are letters
        {
            char tempChar = message.charAt(i); // Snag each character from the string one at a time

            // Now, do the converting
            int letterVal = characterCast(tempChar);
            int shiftedLetterValue = letterVal + shift;
            // ASCII character 122 is lowercase 'z', while ASCII character 65 is uppercase 'A'
            if(shiftedLetterValue > 122)
            {
                int remainder = shiftedLetterValue - 122;
                shiftedLetterValue = 65 + (remainder - 1);
            }
            else if (shiftedLetterValue < 65)
            {
                int remainder = 65 - shiftedLetterValue;
                shiftedLetterValue = 122 - (shiftedLetterValue + 1);
            }

            char newChar = convertBackToChar(shiftedLetterValue);
            encodedString.append(newChar);
        }
        
        return encodedString.toString();
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
