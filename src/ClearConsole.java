public class ClearConsole 
{
    public static void ClearScreen()
    {
        //Clearing console
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}