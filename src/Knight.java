import java.util.Random;

public class Knight {
    String name;
    int health;
    Weapon weapon;
    Armor armor;

    void setName(String n){
        name = n;
    }
    String getName(){
        return name;
    }

    void setHealth(int h){
        health = h;
    }
    int getHealth(){
        return health;
    }

    void setWeapon(Weapon w) {weapon = w;}
    Weapon getWeapon(){return weapon;}

    void setArmor(Armor a) {armor = a;}
    Armor getArmor(){return armor;}


    static class Armor {
        String type;
        int damageReduction;

        Armor(String t, int dr){
           type = t;
           damageReduction = dr;
        }

        void setType(String t){
            type = t;
        }
        String getType(){
            return type;
        }

        void setDamageReduction(int dr){
            damageReduction = dr;
        }
        int getDamageReduction(){
            return damageReduction;
        }
    }

    static class Weapon {
        String name;
        int maxDamage, minDamage;

        Weapon(String n, int maxD, int minD){
            name = n;
            maxDamage = maxD;
            minDamage = minD;
        }

        void setName(String n){
            name = n;
        }
        String getName(){
            return name;
        }

        void setMaxDamage(int maxD){
            maxDamage = maxD;
        }
        int getMaxDamage(){
            return maxDamage;
        }

        void setMinDamage(int minD){
            minDamage = minD;
        }
        int getMinDamage(){
            return minDamage;
        }
    }

    public int fight(){
        //returns random damage done
        Random r = new Random();
        return r.nextInt(weapon.getMaxDamage() - weapon.getMinDamage()) + weapon.getMinDamage();
    }

    public String toString(){
        String knight = String.format("---%s--------------------------%n" +
                "Health: %s%n" +
                "Weapon: %s%n" +
                "-----------------------------%n", name, health, weapon.getName());
        return knight;
    }

}
