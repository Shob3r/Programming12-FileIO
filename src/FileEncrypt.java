import java.util.ArrayList;

public class FileEncrypt extends EncryptionBase
{
    FileRead read = new FileRead();
    FileWrite write = new FileWrite();

    public String encodeString(String message, int shift)
    {
        message = message.toLowerCase(); // Temporary until I add character conversion for capitalized letters
        StringBuilder encodedString = new StringBuilder();
        
        for(int i = 0; i < message.length(); i++) // Loop as many times as there are letters
        {
            char tempChar = message.charAt(i); // Snag each character from the string one at a time

            if(super.isCharacterSpecial(tempChar)) // Spacebar inputs should be ignored when encrypting the String
            {
                encodedString.append(" ");
            }
            else
            {
                // Now, do the converting
                int characterValue = confineIntegerWithinAlphabet(shift, characterCast(tempChar));
                char shiftedChar = convertBackToChar(characterValue);
                encodedString.append(shiftedChar);
            }
        }
        return encodedString.toString();
    }

    public int confineIntegerWithinAlphabet(int shift, int letterVal)
    {
        int shiftedLetterValue = letterVal + shift;

        // ASCII character 122 is lowercase 'z', while ASCII character 97 is uppercase 'a'
        // For now, I will only be dealing with lowercase ASCII characters, but will probably add capital letters to the encryption at some point (it's really easy not gonna lie
        if(shiftedLetterValue > 122)
        {
            int remainder = shiftedLetterValue - 122;
            shiftedLetterValue = 97 + (remainder - 1);
        }
        else if (shiftedLetterValue < 97)
        {
            int remainder = 97 - shiftedLetterValue;
            shiftedLetterValue = 122 - (shiftedLetterValue + 1);
        }
        return shiftedLetterValue;
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
