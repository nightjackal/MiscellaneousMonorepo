import java.util.Scanner;
/**     NOTES
 * Turn pet display into one sentence
 * Add weight into the formula for HP damage
 * Make the names and types customizable
 */
public class Main extends PetRunner{
    public static void main(String[] args) {
        PetRunner petRunner = new PetRunner();
        petRunner.petRunner();
    }
}

class Attributes { // All of the attributes
    String name;
    String type;
    int HP;
    int experience;
    int weight;
    
    private void petAssemble (String nameInput, String typeInput) {
        SetterMethods set = new SetterMethods();
        name = set.setName(nameInput);
        type = set.setType(typeInput);
        HP = set.setHP();
        experience = set.setExperience();
        weight = set.setWeight();
    }

    public void petPrint (String nameInput, String typeInput) {
        petAssemble(nameInput, typeInput); // CHANGE THIS TO ONE SENTENCE!
        System.out.println("\033[0mThis is \033[1;31m" + name + "\033[0m and their type/power is \033[0m\033[1;32m" + type + "\033[0m; " + name + "\'s health is at \033[0m\033[1;33m" + HP + "\033[0m and their experience is \033[0m\033[1;34m" + experience + "\033[0m -- " + name + " weighs \033[0m\033[1;35m" + weight + " \033[0mkg.");
    }
}

class GetterMethods { // Fetches the attributes of the pet
    public String getName() {
        Attributes attributes = new Attributes();
        return attributes.name;
    }

    public String getType () {
        Attributes attributes = new Attributes();
        return attributes.type;
    }

    public int getHP () {
        Attributes attributes = new Attributes();
        return attributes.HP;
    }

    public int getExperience () {
        Attributes attributes = new Attributes();
        return attributes.experience;
    }

    public double getHeight () {
        Attributes attributes = new Attributes();
        return attributes.weight;
    }
}

class SetterMethods { // Sets the attributes of the pets
    public String setName (String name) { // Sets for the name
        return name;
    }

    public String setType (String type) { // Sets for the type
        return type;
    }
    
    public int setHP () { // Sets for the HP
        int randomNumber = (int) (Math.random() * 3) + 1;
        if (randomNumber == 1) {
            return 100;
        }
        else if (randomNumber == 2) {
            return 75;
        }
        else {
            return 50;
        }
    }

    public int setExperience () { // Sets for the experience
        return 0;
    }

    public int setExperience (int experience) {
        return experience;
    }

    public int setWeight () { // Sets for the weight
        return (int) (Math.random() * 5) + 5;
    }

    public void theGreatReset (int experience) { // Resets pets after one dies but adds to XP
        Attributes attributes = new Attributes();
        attributes.HP = setExperience();
        attributes.weight = setWeight();
        attributes.experience = setExperience(experience);
    }
}

class Attacks {
    private String attackTypes[] = { // To determine what type attack is under way
        "Programming", "Math",  "Physics", "Programming"
    };

    private boolean typeAttack (String petType1, String petType2) { // Determines what type of attack 
        for (int i = 0; i < 3; i++) {
            if ((petType1.equals(attackTypes[i])) && (petType1.equals(attackTypes[(i + 1)]))) {
                return true;
            }

        }

        return false;

    }

    public double damageDone (int HP, String petType1, String petType2, String attackAccuracy, int weight1, int weight2) { // Does damage for attacking
        int damage = 0;
        boolean doubleDamage = typeAttack(petType1, petType2);
        if (attackAccuracy.equals("A")) {
            damage += 5;
        }

        else {
            double num = Math.random();
            if (num < 0.5) {
            damage += 10;
            }        
        }

        if (doubleDamage) { // If the damage is meant to be double, it does this lol
            return damage * 2.0 + (weight1 * weight2);
        }

        else {
            return damage;
        }

    }

    public int addHealth (int HP)   { // Adds health for not attacking
        int HPBack = (int) (Math.random() * 5) + 1;
        HP += HPBack;
        return HP;

    }

}

class PetRunner {
    protected void petRunner() {
        System.out.println("\n\n"); // Make things nice and clean.
        Attributes pet1 = new Attributes(); // Setting pet attributes
        pet1.petPrint("\033[0m\033[1;31mJake\033[0m", "\033[0\033[1;32mPhysics\033[0m");
        Attributes pet2 = new Attributes();
        pet2.petPrint("\033[0m\033[1;31mLuke\033[0m", "\033[0\033[1;32mProgramming\033[0m");
        String line = ""; // Takes in all responses
        String currentPet = pet2.name; // Sets the current pet which is immediately subject to change, thus I chose pet 2 first here
        Scanner input = new Scanner(System.in);
        System.out.println("type \"stop\" to stop");
        while (true) { // To continue the fight
            if (currentPet.equals(pet1.name)) {
                currentPet = pet2.name;
            }
            else if (currentPet.equals(pet2.name)) {
                currentPet = pet1.name;
            }
            if (pet1.HP <= 0) { // If a pet has died
                pet2.experience += 5;
                System.out.print("\n" + pet1.name + " ");
                try { // Thread.sleeps for dramatic effect (;
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.print(".");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println( " has fallen!\nResetting stage, type \"stop\" to stop");
                SetterMethods setterMethods = new SetterMethods();
                setterMethods.theGreatReset(5);
                System.out.println("Would you like to play again (Y/n)?");
                line = input.nextLine();
                if (line.equals("n")) {
                    break;
                }
                else {
                    pet1.petPrint("\033[0m\033[1;31mJake\033[0m", "\033[0\033[1;32mPhysics\033[0m");
                    pet2.petPrint("\033[0m\033[1;31mLuke\033[0m", "\033[0\033[1;32mProgramming\033[0m");
                }
            }
            else if (pet2.HP <= 0) {
                pet2.experience += 5;
                System.out.print("\n" + pet2.name + " ");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.print(".");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println( " has fallen!");
                SetterMethods setterMethods = new SetterMethods();
                setterMethods.theGreatReset(5);
                System.out.println("Would you like to play again (Y/n)?");
                line = input.nextLine();
                if (line.equals("n")) {
                    break;
                }
                else {
                    pet1.petPrint("\033[0m\033[1;31mJake\033[0m", "\033[0\033[1;32mPhysics\033[0m");
                    pet2.petPrint("\033[0m\033[1;31mLuke\033[0m", "\033[0\033[1;32mProgramming\033[0m");
                }
            }
            System.out.println(currentPet + ", would you like to attack (Y/n)?"); // Asks main question
            line = input.nextLine();
            if (line.equals("stop")) { // In case they wish to stop
                System.out.println(pet1.name + " has " + pet1.HP + " and " + pet2.name + " has " + pet2.HP + "\nThis means that " + currentPet + " looses!");
                input.close();
                break;
            }
            else if ((line.equals("Y")) && (currentPet.equals(pet1.name))) { // For attacking accurately and inaccurately 
                System.out.println("Would you like to hit accurately or inaccurately (A/i)?");
                line = input.nextLine();
                if (line.equals("A") | line.equals("i")) { // If they so chose to risk inaccuracy
                    Attacks attack = new Attacks();
                    pet2.HP -= attack.damageDone(pet2.HP, pet1.type, pet2.type, line.substring(0, 1), pet1.weight, pet2.weight);
                    System.out.println(pet2.name + " now has \033[1;33m" + pet2.HP + "\033[0m HP!");
                }
                else {
                    System.out.println("Wrong! lol");
                }
            }
            else if ((line.equals("Y")) && (currentPet.equals(pet2.name))) {
                System.out.println("Would you like to hit accurately or inaccurately (A/i)?");
                line = input.nextLine();
                if (line.equals("A") | line.equals("i")) {
                    Attacks attack = new Attacks();
                    pet1.HP -= attack.damageDone(pet1.HP, pet2.type, pet1.type, line.substring(0, 1), pet2.weight, pet1.weight);
                    System.out.println(pet1.name + " now has \033[1;33m" + pet1.HP + "\033[0m HP!");
                }
                else {
                    System.out.println("Wrong! lol");
                }
                
            }

            else if ((line.equals("n")) && (currentPet.equals(pet1.name))) { // TO give HP back to pets if they do not want to attack + their experience
                Attacks attacks = new Attacks();
                pet1.HP = attacks.addHealth(pet1.HP) + pet1.experience;
                System.out.println("Ok, good luck...lol\n\033[1mI guess you get some HP back, " + pet1.name + "\033[1m you now have \033[1;33m" + pet1.HP + ".\033[0m");
            }

            else if ((line.equals("n")) && (currentPet.equals(pet2.name))) {
                Attacks attacks = new Attacks();
                pet2.HP = attacks.addHealth(pet2.HP) + pet2.experience;
                System.out.println("Ok, good luck...lol\n\033[1mI guess you get some HP back, " + pet2.name + "\033[1m you now have \033[1;33m" + pet2.HP + ".\033[0m");
            }

            else {
                System.out.println("Wrong! lol"); // For those who cannot type
            }

        }

    }

}