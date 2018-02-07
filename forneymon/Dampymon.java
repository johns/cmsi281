public class Dampymon extends Forneymon implements MinForneymon {

    Dampymon (String n) {
        super(25, n);
    }

    public int takeDamage (int dmg, String type) {
        if (type.equals("burny")) {
            dmg += 5;
        }
        return super.takeDamage(dmg, type);
    }

}
