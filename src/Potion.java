public class Potion extends Item{
    public enum potionType{
        STRENGTH, HEAL
    }
    private potionType potType;
    private int power;
    public Potion(String name, potionType potType, int power) {
        super(name, Item.type.POTION);
        this.potType = potType;
        this.power = power;
    }

    public potionType getPotType() {
        return potType;
    }

    public int getPower() {
        return power;
    }
}
