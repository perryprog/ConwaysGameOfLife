import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Random;

public class MainBoard {
    private int boardHeight;
    private int boardWidth;
    private int cellSize;

    private JFrame jFrame;
    private JPanel jPanel;
//    private List<JPanel> jPanelArrayList = new ArrayList<>();

    private boolean[][] cells;

    public MainBoard(int boardHeight, int boardWidth, int cellSize) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.cellSize = cellSize;

        cells = new boolean[boardHeight][boardWidth];

//        cells[0][0] = true;
//        cells[4][8] = true;
        initializeBoard();
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public MainBoard makeFrames() {
        log("Generating Frame...");
        jFrame = new JFrame("Conway's Game Of Life");
        jFrame.setSize(boardWidth * cellSize, boardHeight * cellSize);
        log("Done!");

        return this;
    }

//    public void genGui() {
//        JButton jButton = new JButton("Reset")
//    }

    public MainBoard genPanel() {
        log("Generating Panel...");

        final Random randColor = new Random();


        jPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                //g.setColor(new Color(randColor.nextInt(255) + 1, randColor.nextInt(255) + 1, randColor.nextInt(255) + 1));
                //g.drawString("your ma", 10, 10);
                //g.fillRect(10, 10, cellSize, cellSize);
                initializeBoard();
                _PaintGrid(g);
            }
        };
        log("Done!");

        return this;
    }

//    public void addPanel() {
//        jPanelArrayList.add();
//    }

    public MainBoard init() {
        log("Adding Panels...");
        jFrame.add(jPanel);
        log("Done!");

        return this;
    }

    public MainBoard showFrame() {
        jFrame.setVisible(true);
        log("Frame Visible!");

        return this;
    }

    public MainBoard addComponent(Component component) {
        log("Adding Component...");
        jPanel.add(component);
        log("Done!");

        return this;
    }

    public void log(String message) {
        System.out.print(Calendar.getInstance().getTime().toString() + ": ");
        System.out.println(message);
    }

    private void _PaintGrid(Graphics g) {

        int row = 0;
        for (boolean[] rowCells : cells) {

            int col = 0;
            for (boolean cell : rowCells) {

                g.setColor(cell ? Color.WHITE : Color.BLACK);

                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                ++col;
            }

            ++row;
        }
    }

    public void initializeBoard() {
        int row = 0;
        Random random = new Random();
        for (boolean[] rowCells : cells) {

            int col = 0;
            for (boolean cell : rowCells) {

                cells[row][col] = random.nextBoolean();

                ++col;
            }

            ++row;
        }
    }
}
