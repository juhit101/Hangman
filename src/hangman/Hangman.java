package hangman;

public class Hangman {

    private int counter;

    public Hangman() {
        this.counter = 0;
    }

    public void addLimb() {
        if (this.counter == 0) {
            counter += 1;
            System.out.println("   O"); // add head
        } else if (this.counter == 1) {
            counter += 1;
            System.out.println("   O");
            System.out.println("   |"); // add abdomen
        } else if (this.counter == 2) {
            counter += 1;
            System.out.println("   O");
            System.out.println("  /| "); // add right arm
        } else if (this.counter == 3) {
            counter += 1;
            System.out.println("   O");
            System.out.println("  /|\\ "); // add left arm
        } else if (this.counter == 4) {
            counter += 1;
            System.out.println("   O");
            System.out.println("  /|\\ ");
            System.out.println("  /"); // add right leg
        } else if (this.counter == 5) {
            counter += 1;
            System.out.println("   O");
            System.out.println("  /|\\ ");
            System.out.println("  / \\ "); // add left leg
        }
    }
}
