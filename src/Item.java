public class Item {
    public enum type {
        EMPTY, WEAPON, ARTEFACT, POTION
    }


    private String name;
    private Item.type type;

    public Item(String name, Item.type type) {
        this.name = name;
        this.type = type;
    }

    public Item.type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

}
