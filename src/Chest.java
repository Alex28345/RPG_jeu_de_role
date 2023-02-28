public class Chest {

    private int x, y;
    private Item[] content;

    public Chest(int x, int y, Item[] content) {
        this.x = x;
        this.y = y;
        this.content = content;
    }
    public void showContent(){
        System.out.print("le coffre contient : ");
        for (Item i : this.content){
            System.out.print(i.getName()+" - ");
        }
        System.out.println();
    }

    public Item[] getContent() {
        return this.content;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
