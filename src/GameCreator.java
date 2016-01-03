import javax.swing.*;

public class GameCreator {
    public static void main(String[] args) {
        MainBoard mainBoard = new MainBoard(300, 300, 10);

        mainBoard.makeFrames().
            genPanel().
            addComponent(new JLabel("SUP DAWG")).
            init().
            showFrame();
    }
}
