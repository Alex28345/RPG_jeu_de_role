import java.util.Scanner;

public class Interface {
    public Interface() {
    }
    public static void init(Dungeon map) {

        System.out.println("Pour jouer veuillez taper une lettre pour effectuer une action a l'aide des commandes : ");
        Interface.help();

    }
    public static String input(String text) {
        Scanner scan = new Scanner(System.in);
        System.out.print(text);
        String entry = scan.next();
        return entry;
    }


    public static void help() {
        System.out.println("\u001B[35m(z, s, q, d)\u001B[0m pour un deplacement ");
        System.out.println("\u001B[35mi\u001B[0m pour ouvrir l'inventaire");
        System.out.println("\u001B[35mc\u001B[0m pour ouvrir un coffre");
        System.out.println("\u001B[35ma\u001B[0m pour ramasser un artefact");
        System.out.println("\u001B[35mm\u001B[0m pour voir la carte");
        System.out.println("\u001B[35mhelp\u001B[0m pour revoir les commandes");
    }
    public enum Color {
        RESET, RED, GREEN, BLUE, YELLOW, VIOLET
    }

    public static void setColor(Color color){
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_VIOLET = "\u001B[35m";
        switch (color){
            case RESET:
                System.out.print(ANSI_RESET);
                break;
            case RED:
                System.out.print(ANSI_RED);
                break;
            case GREEN:
                System.out.print(ANSI_GREEN);
                break;
            case BLUE:
                System.out.print(ANSI_BLUE);
                break;
            case YELLOW:
                System.out.print(ANSI_YELLOW);
                break;
            case VIOLET:
                System.out.print(ANSI_VIOLET);
                break;
        }
    }
}
