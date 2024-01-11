
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
    
    public void encodeString(String message, int shift)
    {
        String encodedString = "";
        
        for(int i = 0; i < message.length(); i++) // Loop as many time as there are letters
        {
            char tempChar = message.charAt(i); // Snag each character from the string one at a time
            
            // Now, do the converting
            int letterVal = characterCast(tempChar);
            char newChar = convertBackToChar(letterVal + shift);
            encodedString += newChar;
        }
        
        System.out.println(encodedString);
    }
    
}
