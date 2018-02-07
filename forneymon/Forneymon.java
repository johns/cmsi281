abstract public class Forneymon {

    private int health;
    private String name;

    Forneymon (int h, String n) {
        health = h;
        name = n;
    }

    public int takeDamage (int dmg, String type) {
        health -= dmg;
        return health;
    }

    public String toString () {
        return name + " " + name;
    }

    public int getHealth () {return health;}
    public String getName () {return name;}

}
