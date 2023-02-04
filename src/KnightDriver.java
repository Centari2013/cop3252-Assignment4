import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class KnightDriver {
    public static void main(String[] args) {
        String[] knightNames = {"Garfunkle", "Bobo", "Doodlebob", "Steve", "Alex"};
        Knight.Weapon[] weapons = {new Knight.Weapon("CHICKEN BONE", 3, 0),
                new Knight.Weapon("PVC PIPE", 50, 25),
                new Knight.Weapon("LITER SODA BOTTLE", 5, 1),
                new Knight.Weapon("HOT SAUCE BOTTLE", 30, 15)};
        Knight.Armor[] armors = {new Knight.Armor("METAL", -10),
        new Knight.Armor("LEATHER", -5),
        new Knight.Armor("CARD BOARD", -3),
        new Knight.Armor("PAPER", -1)};
        Knight player = generateRandomKnight(knightNames, weapons, armors);


        while(KnightDriver.mainFunctionality(player, knightNames, weapons, armors)) {


        }



    }

    static private boolean mainFunctionality(Knight player, String[] knightNames, Knight.Weapon[] weapons, Knight.Armor[] armors){
        Scanner input = new Scanner(System.in);
        System.out.printf("Welcome to Knight Fight!%n" +
                "Enter the name of your knight: ");
        player.setName(input.nextLine());

        System.out.printf("Now select your weapon! (Choose number)%n");
        KnightDriver.printAvailableWeapons();

        boolean validInput = false;

        while (!validInput) {
            switch (input.nextInt()) {
                case 1:
                    player.setWeapon(weapons[0]);
                    validInput = true;
                    break;
                case 2:
                    player.setWeapon(weapons[1]);
                    validInput = true;
                    break;
                case 3:
                    player.setWeapon(weapons[2]);
                    validInput = true;
                    break;
                case 4:
                    player.setWeapon(weapons[3]);
                    validInput = true;
                    break;
                default:
                    System.out.print("Select a valid weapon. (Choose number)%n");
                    KnightDriver.printAvailableWeapons();
                    break;

            }
        }

        Knight opponent = KnightDriver.generateRandomKnight(knightNames, weapons, armors);
        System.out.print("Would you like to auto generate your opponent? (y|n)");
        validInput = false;

        String choice;
        while (!validInput) {
            choice = input.nextLine();
            switch ((choice.toLowerCase())) {
                case "y":
                    validInput = true;
                    break;
                case "n":
                    System.out.print("Enter your opponent's name: ");
                    opponent.setName(input.nextLine());
                    System.out.print("Choose your opponent's weapon: ");
                    KnightDriver.printAvailableWeapons();
                    int choice2 = input.nextInt();
                    while (choice2 < 1 || choice2 > 4) {
                        System.out.print("Please pick an option from 1 to 4...");
                        KnightDriver.printAvailableWeapons();
                        choice2 = input.nextInt();
                    }

                    opponent.setWeapon(weapons[choice2 - 1]);
                    validInput = true;
                    break;
                default:
                    System.out.printf("Please enter 'y' or 'n'.%nWould you like to auto generate your opponent? (y|n)");
                    break;
            }
        }

        System.out.print("YOU:\n" + player.toString());
        System.out.print("OPPONENT:\n" + opponent.toString());

        System.out.print("Would you like to start the game? (y/n)");
        validInput = false;

        while (!validInput) {
            switch ((input.nextLine().toLowerCase())) {
                case "y":
                    validInput = true;
                    break;
                case "n":
                    return true;
                default:
                    System.out.printf("Please enter 'y' or 'n'.%nWould you like to start the game? (y|n)");
                    break;
            }
        }

        KnightDriver.battle(player, opponent);

        System.out.print("Would you like to play again? (y/n) ");


        while (true) {
            switch ((input.nextLine().toLowerCase())) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.printf("Please enter 'y' or 'n'.%nWould you like to play again? (y|n)");
                    break;
            }
        }

    }

    static void printAvailableWeapons(){
        System.out.printf("1) chicken bone%n" +
                "2) pvc pipe%n" +
                "3) liter soda bottle%n" +
                "4) hot sauce bottle%n"+
                "Your choice? : ");
    }

    static Knight generateRandomKnight(String[] names, Knight.Weapon[] weapons, Knight.Armor[] armors){
        Random r = new Random();
        Knight k = new Knight();
        k.setName(names[r.nextInt(names.length)]);

        //generates health from 100 to 200
        k.setHealth(r.nextInt(101) + 100);

        //generates random weapon from list
        k.setWeapon(weapons[r.nextInt(weapons.length)]);

        //generates random armor from list
        k.setArmor(armors[r.nextInt(armors.length)]);

        return k;
    }

    static void battle(Knight player, Knight op){
        Random r = new Random();
        boolean playerTurn = r.nextBoolean(), gameOver = false;
        int damageGiven, damageTaken;

        while(!gameOver){
            if (playerTurn){
                System.out.printf("%s attacks %s with your %s!%n", player.getName(),op.getName(), player.getWeapon().getName());
                damageGiven = Math.abs(op.armor.getDamageReduction() - player.fight());
                op.setHealth(op.getHealth() - damageGiven);
                System.out.printf("%s lost %d health!%n", op.getName(), damageGiven);
                System.out.printf("%s's health: %d%n%n", op.getName(), op.getHealth());

                if (op.getHealth() < 1){
                    System.out.printf("%s WINS%n%n%n", player.getName());
                    gameOver = true;
                }else {
                    playerTurn = false;
                }

            }else{
                System.out.printf("%s attacks %s with your %s!%n", op.getName(), player.getName(), player.getWeapon().getName());
                damageTaken = Math.abs(player.armor.getDamageReduction() - op.fight());
                player.setHealth(player.getHealth() - damageTaken);
                System.out.printf("%s lost %d health!%n", player.getName(), damageTaken);
                System.out.printf("%s's health: %d%n%n", player.getName(), player.getHealth());

                if (player.getHealth() < 1){
                    System.out.printf("%s WINS%n%n%n", op.getName());
                    gameOver = true;
                }else {
                    playerTurn = true;
                }
            }
        }

    }
}
