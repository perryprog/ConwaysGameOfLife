import javax.swing.*;

public class GameCreator {
    public static void main(String[] args) {

        String boardHeight = JOptionPane.showInputDialog(null, "What is the board width?");
        String boardWidth = JOptionPane.showInputDialog(null, "What is the board height?");
        String cellSize = JOptionPane.showInputDialog(null, "What is the cell height?");
        String whiteIsAlive = JOptionPane.showInputDialog(null, "Should white be alive? (true/false)");

        MainBoard mainBoard = new MainBoard(Integer.parseInt(boardHeight), Integer.parseInt(boardWidth), Integer.parseInt(cellSize), Boolean.parseBoolean(whiteIsAlive));
//        MainBoard mainBoard = new MainBoard(200, 200, 10, false);

        mainBoard.makeFrames().
                genPanel().
                addComponent(new JLabel("SUP DAG")).
                init().
                showFrame();

        while (true) {
            mainBoard.conwaysCheker();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
