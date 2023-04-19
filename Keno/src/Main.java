import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Keno k = new Keno();
        k.setContentPane(k.kenoPanel);
        k.setTitle("Casino");
        k.setSize(800,600);
        k.setVisible(true);
        k.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
