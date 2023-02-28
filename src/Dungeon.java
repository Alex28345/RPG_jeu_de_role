import java.util.Random;

public class Dungeon {
    private int width, height;
    private char[][] map;
    private int[][] walls;

    Item[] contentC0 = {new Potion("potion_force", Potion.potionType.STRENGTH,5),new Item("Hache", Item.type.WEAPON)};
    Item[] contentC1 = {new Potion("potion_soin", Potion.potionType.HEAL,7),new Potion("potion_soin", Potion.potionType.HEAL,7)};
    Item[] contentC2 = {new Potion("elixir", Potion.potionType.HEAL,10),new Potion("fiole d'eau", Potion.potionType.HEAL, 1)};
    Item[] contentC3 = {new Potion("soupe_etrange", Potion.potionType.STRENGTH,7 ),new Potion("Truc_a_boire", Potion.potionType.HEAL,5)};


    private Chest[] chestList = {
            new Chest(1,1,contentC0),
            new Chest(1,5,contentC1),
            new Chest(5, 1,contentC2),
            new Chest(5,5,contentC3)

    };

    public Dungeon(int width, int height, int[][] walls) {
        this.width = width;
        this.height = height;
        this.walls = walls;

        this.map = new char[height][width];

        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < this.width; y++) {
                if(x == 0 || x == this.height-1 || y ==0 || y == this.width-1)
                    this.map[x][y] = 'X';
                else
                    this.map[x][y] = ' ';
            }
        }
        for (int[] wall : this.walls){
            this.map[wall[0]][wall[1]] = 'X';
        }
        for (Chest c : this.chestList){
            this.map[c.getX()][c.getY()] = 'C' ;
        }


        int nArtefact = 0;
        while(nArtefact < 3){
            Random random = new Random();
            int nb, nb2;
            nb = random.nextInt(this.height);
            nb2 = random.nextInt(this.width);
                    if(this.map[nb][nb2] == ' ' ) {
                        this.map[nb][nb2] = 'A';
                        nArtefact+=1;
                    }
                }
            }
    public void edit(int X, int Y, char c){
        this.map[X][Y] = c;
    }
    public char getChar(int X, int Y){
        return this.map[X][Y];
    }
    public char[][] getMap() {
        return this.map;
    }

    public Monster setRandomMonster(Hero hero){
        while(true){
            Random random = new Random();
            int nb, nb2;
            nb = random.nextInt(this.height);
            nb2 = random.nextInt(this.width);
            if(this.map[nb][nb2] == ' ' ) {

                if (hero.getXp()>= 100){
                    this.map[nb][nb2] = 'B';
                    return new Boss(nb, nb2);}
                else{
                    this.map[nb][nb2] = 'M';
                    return new Monster(nb, nb2);}
            }
        }
    }
    public void show(Entity player) {
        char initChar = this.map[player.getX()][player.getY()];
        this.map[player.getX()][player.getY()] = 'P';
        System.out.println("la carte : ");
        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < this.width; y++) {
                System.out.print("  "+ map[x][y]);
            }
            System.out.println();
        }
        this.map[player.getX()][player.getY()] = initChar;

    }

    Item[] artefactsList = {new Item("carte", Item.type.ARTEFACT), new Item("fee", Item.type.ARTEFACT), new Item("bouclier", Item.type.ARTEFACT)};
    public Item randomArtefact(){
        Random random = new Random();  // Create a random number generator
        int rand = random.nextInt(3);
        return artefactsList[rand];
    }

    public Chest getChest(Entity player) {
        for (Chest c : this.chestList){
            if (c.getX() == player.getX() && c.getY()== player.getY())
                return c;
        }
        return chestList[0];
    }

}
