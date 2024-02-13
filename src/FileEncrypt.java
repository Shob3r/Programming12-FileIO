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
            if(super.isSpecialCharacter(tempChar))
            {
                encodedString.append(tempChar);     // Instead of doing any converting on a special character, add it right back into the StringBuilder
            }
            else
            {
                // Now, do the converting
                int characterValue = shiftCharValue(shift, characterCast(tempChar));
                char shiftedChar = convertBackToChar(characterValue);
                encodedString.append(shiftedChar);
            }
        }
        return encodedString.toString();
    }

    public int shiftCharValue(int shift, int letterVal)   // Required if
    {
        int shiftedLetterValue = letterVal + shift;
        if(letterVal >= 65 && letterVal <= 90)
        {
            if(shiftedLetterValue > 90)
            {
                int remainder = shiftedLetterValue - 122;
                shiftedLetterValue = 65 + (remainder - 1);
            }
            else if (shiftedLetterValue < 65)
            {
                int remainder = 65 - shiftedLetterValue;
                shiftedLetterValue = 90 - (shiftedLetterValue + 1);
            }
        }

        else if(letterVal >= 97 && letterVal <= 122)
        {
            if(shiftedLetterValue > 122)
            {
                int remainder = shiftedLetterValue - 122;
                shiftedLetterValue = 97 + (remainder - 1);
            }
            else if(shiftedLetterValue < 97)
            {
                int remainder = 97 - shiftedLetterValue;
                shiftedLetterValue = 122 - (shiftedLetterValue + 1);
            }
        }

        return shiftedLetterValue;
    }

    public ArrayList<String> encodeArrayList(ArrayList<String> dataToEncrypt, int shift)
    {
        ArrayList<String> convertedData = new ArrayList<>();
        for (String data : dataToEncrypt)
        {
            convertedData.add(encodeString(data, shift));
        }
        return convertedData;
    }
}
