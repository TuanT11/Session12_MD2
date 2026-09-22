abstract class GameCharacter{
    protected String name;
    protected int hp;
    public GameCharacter(String name, int hp){
        this.name = name;
        this.hp = hp;
    }
    public abstract void attack();
    public String getName(){
        return this.name;
    }
    public int getHP(){
        return this.hp;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setHP(int hp){
        this.hp = hp;
    }
}

class Warrior extends GameCharacter{
    public Warrior(String name, int hp){
        super(name, hp);
    }
    @Override
    public void attack(){
        System.out.println("[Warrior " + getName() + " - HP : " + getHP() + "] slashes with Greatsword!");
    }
}

class Mage extends GameCharacter{
    public Mage(String name, int HP){
        super(name, HP);
    }
    @Override
    public void attack(){
        System.out.println( "[Mage " + getName() + " - HP :" + getHP() + "] casts Fireball!");
    }
}

public class GameRikkei{
    private static final int WIDTH = 58;
    public static void main(String[] args){
        System.out.println("--- KHỞI TẠO 2 NHÂN VẬT GAME ---");
        GameCharacter warrior = new Warrior("Author", 500);
        GameCharacter mage = new Mage("Merlin", 300);
        printBorder();
        printCenter("ĐẤU TRƯỜNG NHÂN VẬT RIKKEI RPG");
        printBorder();
        printLine("[Warrior" + warrior.getName() +  "- HP: " + warrior.getHP()+"] slashes with Greatsword !");
        printLine("[Mage "+mage.getName()+" - HP: " + mage.getHP()+"]    casts Fireball!");

        printBorder();
    }
    public static void printBorder(){
        StringBuilder sb = new StringBuilder("+");
        for (int i = 0; i < WIDTH; i++) sb.append("-");
        sb.append("+");
        System.out.println(sb);
    }
    private static void printCenter(String text) {
        int padding = WIDTH - text.length();
        int left = padding / 2;
        int right = padding - left;
        System.out.println("|" + " ".repeat(left) + text + " ".repeat(right) + "|");
    }
    private static void printLine(String text) {
        String content = " " + text;
        int padding = WIDTH - content.length();
        if (padding < 0) padding = 0;
        System.out.println("|" + content + " ".repeat(padding) + "|");
    }
}
