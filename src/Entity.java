public class Entity {
    private int X, Y;
    private int attack, defense, health, velocity;

    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getVelocity() {
        return velocity;
    }
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public Entity(int x, int y) {
        this.attack = 0;
        this.defense = 0;
        this.health = 0;
        this.velocity = 0;

        this.X = x;
        this.Y = y;
    }
    public void showStats(){
        System.out.println("vie : "+this.health);
        System.out.println("attaque : "+this.attack);
        System.out.println("defense : "+this.defense);
        System.out.println("vitesse : "+this.velocity);
    }
    public void showMiniStats(){
        if (this.health <= 0)
            System.out.println("vie : mort");
        else
            System.out.println("vie : " + this.health);
        System.out.println("attaque : "+this.attack);
    }

    public void move(String d){
        Interface.setColor(Interface.Color.VIOLET);
        switch (d) {
            case "z" -> {
                System.out.println("avance vers le haut");
                this.X -= 1;
            }
            case "q" -> {
                System.out.println("avance vers la gauche");
                this.Y -= 1;
            }
            case "s" -> {
                System.out.println("avance vers le bas");
                this.X += 1;
            }
            case "d" -> {
                System.out.println("avance vers la droite");
                this.Y += 1;
            }
        }
    }

    public void doDamage(Entity opponent){
        if (this.getAttack()-opponent.getDefense() < 0)
            opponent.setHealth(opponent.getHealth());
        else
            opponent.setHealth(opponent.getHealth()-(this.getAttack()-opponent.getDefense()));
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
}
