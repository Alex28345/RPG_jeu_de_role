import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Hero player1 = new Hero(3,3);

        int[][] walls = {
                {2,2},{4,2},{2,4},{4,4}
        };

        Dungeon map = new Dungeon(7, 7, walls);
        Monster monster = map.setRandomMonster(player1);

        boolean startedGame = true;
        boolean endGame = false;

        map.show(player1);
        Interface.init(map);



        while(startedGame && !endGame){
            char pos = map.getChar(player1.getX(), player1.getY());
            //affichage pour detecter ou l'on se trouve sur la carte, et aider sur les actions possibles
            Interface.setColor(Interface.Color.GREEN);
            switch(pos) {

                case 'C':
                    System.out.println("Vous etes sur un coffre");
                    break;

                case 'M':
                    System.out.println("Vous avez la possibilité de combattre un monstre avec f");
                    Interface.setColor(Interface.Color.RESET);
                    System.out.println();
                    System.out.println("stats du monstre :");
                    monster.showStats();
                    System.out.println();
                    System.out.println("vos stats :");
                    player1.showStats();
                    System.out.println();
                    break;

                case 'B':
                    System.out.println("Vous avez la possibilité de combattre LE BOSS avec f");
                    Interface.setColor(Interface.Color.RESET);
                    System.out.println();
                    System.out.println("stats du Boss :");
                    monster.showStats();
                    System.out.println();
                    System.out.println("vos stats :");
                    player1.showStats();
                    System.out.println();
                    break;

                case 'A':
                    System.out.println("Vous etes sur un artefact");
                    break;
            }
            Interface.setColor(Interface.Color.RESET);

                String entry = Interface.input("action : ");
                switch(entry) {

                    case "help":
                        Interface.help();
                        break;

                    case "z":
                        Interface.setColor(Interface.Color.RED);
                        if (map.getChar(player1.getX()-1, player1.getY()) == 'X')
                            System.out.println("Il y a un mur");
                        else
                            player1.move(entry);
                        break;

                    case "q":
                        Interface.setColor(Interface.Color.RED);
                        if (map.getChar(player1.getX(), player1.getY()-1) == 'X')
                            System.out.println("Il y a un mur");
                        else
                            player1.move(entry);
                        break;

                    case "s":
                        Interface.setColor(Interface.Color.RED);
                        if (map.getChar(player1.getX()+1, player1.getY()) == 'X')
                            System.out.println("Il y a un mur");
                        else
                            player1.move(entry);
                        break;

                    case "d":
                        Interface.setColor(Interface.Color.RED);
                        if (map.getChar(player1.getX(), player1.getY()+1) == 'X')
                            System.out.println("Il y a un mur");
                        else
                            player1.move(entry);
                        break;

                    case "stats":
                        player1.showStats();
                        break;

                    case "i":
                        Interface.setColor(Interface.Color.YELLOW);
                        player1.openInventory();
                        break;

                    case "f":
                        if( pos == 'M' || pos == 'B') {
                            player1.fight(monster, startedGame, endGame);
                            map.edit(player1.getX(), player1.getY(), ' ');
                        }else{
                            Interface.setColor(Interface.Color.RED);
                            System.out.println("vous n'avez pas la possibilité de combattre");}

                        monster = map.setRandomMonster(player1);
                        break;

                    case "c":
                        //verification que l'on soit sur un coffre
                        if( pos == 'C'){
                            map.getChest(player1).showContent();
                            player1.openChest(map.getChest(player1));
                            map.edit(player1.getX(), player1.getY(),' ');}
                        else{
                            Interface.setColor(Interface.Color.RED);
                            System.out.println("vous n'etes pas sur un coffre");}
                        break;

                    case "a":
                        //verification que l'on soit sur un artefact
                        if( pos =='A') {
                            Item rArtefact = map.randomArtefact();
                            player1.take(rArtefact);
                            map.edit(player1.getX(), player1.getY(), ' ');
                            if (rArtefact.getName() == "bouclier"){
                                player1.setDefense(player1.getDefense() + 2);
                                System.out.println("defense augmentée | defense : " + player1.getDefense());
                            }
                        }
                        else{
                            Interface.setColor(Interface.Color.RED);
                            System.out.println("vous n'etes pas sur un artefact");}
                        break;

                    case "m":

                        if(player1.hasArtefact("carte")){
                            Interface.setColor(Interface.Color.BLUE);
                            map.show(player1);}
                        else{
                            Interface.setColor(Interface.Color.RED);
                            System.out.println("vous n'avez pas l'artefact pour cela");}
                        break;

                    case "n":
                        System.out.println(player1.getAttack());
                        break;

                    default:
                        Interface.setColor(Interface.Color.RED);
                        System.out.println("ce n'est pas une action");
                        break;

                }
                Interface.setColor(Interface.Color.RESET);

        }
        if (endGame){
            Interface.setColor(Interface.Color.BLUE);
            System.out.println("t'a fini bg");
            System.exit(1);
        }
    }
}
