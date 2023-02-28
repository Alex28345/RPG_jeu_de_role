
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hero extends Entity{

    private int xp;
    Item empty = new Item("empty", Item.type.EMPTY);
    private Item[][] inventory = {new Item[2],new Item[3],new Item[5]};
    private List choicePotion = Arrays.asList("1","2","3","4","5");

    private Potion actualPotion;

    private Item initialWeapon = new Item("EPEE", Item.type.WEAPON);

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    @Override
    public void showStats() {
        super.showStats();
        System.out.println("xp : "+this.xp);
    }

    public boolean hasArtefact(String str){
        for (Item i : this.inventory[1]){
            if (i.getName()==str)
                return true;
        }
        return false;
    }
    public Hero(int x, int y) {
        super(x, y);
        this.setAttack(10);
        this.setDefense(3);
        this.setHealth(20);
        this.setVelocity(10);
        this.xp = 0;

        for (int type = 0; type < this.inventory.length ; type++) {
            for (int i = 0; i < this.inventory[type].length ; i++) {
                    this.inventory[type][i] = empty;
            }
        }
        this.inventory[0][0] = this.initialWeapon;
    }
    public void fight(Monster monster, boolean startedGame, boolean endGame){
        boolean playerTurn;
        if(this.getVelocity() > monster.getVelocity())
            playerTurn = true;
        else
            playerTurn = false;

        while(this.getHealth()>=0 && monster.getHealth()>=0) {

            Scanner scan = new Scanner(System.in);

            while (playerTurn) {
                System.out.println("a - attaquer");
                System.out.println("p - potion");
                System.out.print("action : ");

                String entry = scan.next();
                switch (entry) {
                    case "a":
                        System.out.println();
                        Interface.setColor(Interface.Color.YELLOW);
                        System.out.println("WOW les degats");
                        Interface.setColor(Interface.Color.RESET);
                        this.doDamage(monster);
                        System.out.println(monster.getName() + " : ");
                        monster.showMiniStats();
                        System.out.println();

                        playerTurn = false;
                        break;
                    case "p":
                        System.out.println("selectionner potion [1-2-3-4-5] ou retour [0] : ");
                        this.showPotions();
                        String choice = scan.next();
                        if (Integer.parseInt(choice) == 0) {
                            break;
                        } else if (choicePotion.contains(choice)) {
                            if (this.getPotions()[Integer.parseInt(choice) - 1].getType().equals(Item.type.POTION)) {
                                actualPotion = this.getPotionByIndex(Integer.parseInt(choice) - 1);
                                System.out.println("utilisation de " + actualPotion.getName());
                                if (actualPotion.getPotType().equals(Potion.potionType.HEAL))
                                    this.setHealth(this.getHealth() + actualPotion.getPower());
                                if (actualPotion.getPotType().equals(Potion.potionType.STRENGTH))
                                    this.setAttack(this.getAttack() + actualPotion.getPower());
                                this.showMiniStats();
                                this.deletePotion(Integer.parseInt(choice) - 1);

                                playerTurn = false;
                                break;
                            } else {
                                Interface.setColor(Interface.Color.RED);
                                System.out.println("Pas de potions");
                                Interface.setColor(Interface.Color.RESET);
                            }
                        } else
                            System.out.println("entree invalide");
                    default:
                        System.out.println("commande non valide");
                }
            }
            if (playerTurn = false) {
                monster.doDamage(this);
                Interface.setColor(Interface.Color.RED);
                System.out.println(monster.getName() + " vous attaque : ");
                Interface.setColor(Interface.Color.RESET);
                this.showMiniStats();
                System.out.println();
                playerTurn = true;
            }
            if (this.getHealth() < 0) {
                Interface.setColor(Interface.Color.RED);
                System.out.println("vous etes mort");
                startedGame = false;

            } else if (monster.getHealth() < 0) {
                Interface.setColor(Interface.Color.GREEN);
                System.out.println("le monstre est mort bravo");
                Interface.setColor(Interface.Color.RESET);
                this.setXp(this.getXp() + 10);
                if (this.hasArtefact("fee"))
                    this.setXp(this.getXp() + 10);
                if (monster.getName() == "Boss")
                    endGame = true;

            }
        }
    }

    public void openInventory(){
        for(Item[] i : this.inventory){
            if(i.length == 2)
                System.out.print("Armes : ");
            else if(i.length == 3)
                System.out.print("Artefacts : ");
            else if(i.length == 5)
                System.out.print("Potions : ");
            for (Item j : i){
                System.out.print(j.getName() + " ");
            }
            System.out.println();
        }
    }
    private boolean verification(){
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.print("action : ");
            String entry = scan.next();
            switch (entry) {
                case "o":
                    return true;
                case "n":
                    return false;
                default:
                    Interface.setColor(Interface.Color.RED);
                    System.out.println("commande non valide");
                    Interface.setColor(Interface.Color.RESET);
            }
        }
    }
    public void openChest(Chest chest){
        for (Item i : chest.getContent()){
            System.out.print("Voulez vous ramassez ");
            Interface.setColor(Interface.Color.BLUE);
            System.out.print(i.getName());
            Interface.setColor(Interface.Color.RESET);
            System.out.println(" ?");
            System.out.println("o - ramasser");
            System.out.println("n - jeter");
            if(verification())
                this.take(i);
            else{
                Interface.setColor(Interface.Color.RED);
                System.out.println("[-] " + i.getName());
                Interface.setColor(Interface.Color.RESET);}
        }
    }
    public void deletePotion(int j){
        this.inventory[2][j]=empty;
    }
    public Item[] getPotions() {
        return inventory[2];
    }
    public Potion getPotionByIndex(int j) {
        return (Potion) this.inventory[2][j];
    }
    public void showPotions() {
        int cpt = 1;
        for (Item potion : this.getPotions()) {
            System.out.println(cpt + " - " + potion.getName() + "  ");
            cpt++;
        }
    }

    public void take(Item item){
        int indice = 0;
        switch (item.getType()) {
            case WEAPON:
                indice = 0;
                break;
            case ARTEFACT:
                indice = 1;
                break;
            case POTION:
                indice = 2;
                break;
        }
        for (int y = 0 ; y < this.inventory[indice].length; y++) {
            if (this.inventory[indice][y].getType() == Item.type.EMPTY){
                if(indice == 0){
                    this.setAttack(this.getAttack()+3);
                    Interface.setColor(Interface.Color.GREEN);
                    System.out.println("de meilleurs degats avec cette arme !");
                    Interface.setColor(Interface.Color.RESET);
                }
                this.inventory[indice][y] = item;
                Interface.setColor(Interface.Color.GREEN);
                System.out.println("[+] "+item.getName() );
                Interface.setColor(Interface.Color.RESET);
                return;
            }
        }
        Interface.setColor(Interface.Color.RED);
        System.out.println("plus de place pour : " + item.getName());
        Interface.setColor(Interface.Color.RESET);
    }

    public void drop(){
    }

    public Item getInitialWeapon() {
        return initialWeapon;
    }

    public void setInitialWeapon(Item initialWeapon) {
        this.initialWeapon = initialWeapon;
    }
}
