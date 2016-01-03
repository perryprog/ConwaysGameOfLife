import javax.swing.*;

public class GameCreator {
    public static void main(String[] args) {
        MainBoard mainBoard = new MainBoard(200, 200, 10, false);

        mainBoard.makeFrames().
            genPanel().
            addComponent(new JLabel("SUP DAWG")).
            init().
            showFrame();

        while (true) {
            mainBoard.conwaysCheker();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
