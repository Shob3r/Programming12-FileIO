public class EncryptionBase
{
    // Prevents me from having to write these methods again in FileDecrypt

    public int characterCast(char letter)
    {
        return (int)letter;
    }

    public char convertBackToChar(int number)
    {
        return (char)number;
    }

    public boolean isSpecialCharacter(char letter)
    {
        // Checks if the input character is a special character (like !, #, %, *, etc.)
        return !Character.isLetter(letter);
    }
}
