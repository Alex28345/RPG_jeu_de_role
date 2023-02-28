import java.util.Random;

public class Monster extends Entity{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Monster(int x, int y) {
        super(x, y);
        this.name = "Monstre";
        Random random = new Random();
        this.setAttack(random.nextInt(5,11));
        this.setHealth(random.nextInt(10,21));
        this.setVelocity(random.nextInt(11));
        this.setDefense(random.nextInt(2,6));
    }



}
