import java.util.Random;

public class Boss extends Monster{
    public Boss(int x, int y) {
        super(x, y);
        Interface.setColor(Interface.Color.BLUE);
        System.out.println("Un BOSS SPAWN !!!!");
        Interface.setColor(Interface.Color.RESET);
        this.setName("Boss");
        Random random = new Random();
        this.setAttack(random.nextInt(8,16));
        this.setHealth(random.nextInt(20,41));
        this.setVelocity(random.nextInt(11));
        this.setDefense(random.nextInt(5,16));
    }
}
