import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Random;

public class MainBoard {
    private int boardHeight;
    private int boardWidth;
    private int cellSize;

    private boolean isWhiteAlive;

    private JFrame jFrame;
    private JPanel jPanel;
//    private List<JPanel> jPanelArrayList = new ArrayList<>();

    private boolean[][] currentCells;
    private boolean[][] cellsBackup;

    public MainBoard(int boardHeight, int boardWidth, int cellSize, boolean isWhiteAlive) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.cellSize = cellSize;
        this.isWhiteAlive = isWhiteAlive;

        currentCells = new boolean[boardHeight][boardWidth];
        cellsBackup = new boolean[boardHeight][boardWidth];


//        currentCells[0][0] = true;
//        currentCells[4][8] = true;
        initializeBoard();
    }


    public MainBoard makeFrames() {
        log("Generating Frame...");
        jFrame = new JFrame("Conway's Game Of Life");
        jFrame.setSize(boardWidth * cellSize, boardHeight * cellSize);
        log("Done!");

        return this;
    }

    public MainBoard genPanel() {
        log("Generating Panel...");


        jPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                //initializeBoard();
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

        for (int col = 0; col < boardHeight; col++) {
            for (int row = 0; row < boardWidth; row++) {
                if (isWhiteAlive) {
                    g.setColor(currentCells[row][col] ? Color.WHITE : Color.BLACK);
                } else {
                    g.setColor(currentCells[row][col] ? Color.BLACK : Color.WHITE);

                }
                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);

            }
        }
    }

    public void initializeBoard() {
        Random random = new Random();

        for (int col = 0; col < boardHeight; col++) {
            for (int row = 0; row < boardWidth; row++) {
                currentCells[row][col] = random.nextBoolean();
            }
        }
    }

    public void conwaysCheker() {
        cellsBackup = currentCells;
        currentCells = new boolean[boardHeight][boardWidth];

        for (int row = 0; row < boardHeight; row++) {
            for (int col = 0; col < boardWidth; col++) {

                int aliveNeighbors = 0;

                try {
//                    currentCells[row][col] = cellsBackup[row][col - 1] && cellsBackup[row][col + 1];
                    if (cellsBackup[row - 1][col - 1]) {
                        aliveNeighbors++;
                    }
                    if (cellsBackup[row - 1][col]) {
                        aliveNeighbors++;
                    }
                    if (cellsBackup[row - 1][col + 1]) {
                        aliveNeighbors++;
                    }
                    if (cellsBackup[row][col - 1]) {
                        aliveNeighbors++;
                    }
                    if (cellsBackup[row][col + 1]) {
                        aliveNeighbors++;
                    }
                    if (cellsBackup[row + 1][col - 1]) {
                        aliveNeighbors++;
                    }
                    if (cellsBackup[row + 1][col]) {
                        aliveNeighbors++;
                    }
                    if (cellsBackup[row + 1][col + 1]) {
                        aliveNeighbors++;
                    }


                    if (cellsBackup[row][col]) {
                        if (aliveNeighbors < 2) {
                            currentCells[row][col] = false;
                        }
                        if (aliveNeighbors == 2 || aliveNeighbors == 3) {
                            currentCells[row][col] = true;
                        }
                        if (aliveNeighbors > 3) {
                            currentCells[row][col] = false;
                        }
                    } else {
                        if (aliveNeighbors == 3) {
                            currentCells[row][col] = true;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException err) {
                    //log("Ignoring Error Message -> " + err.toString());
                    cellsBackup[row][col] = false;
                }
            }
        }

        jPanel.repaint();
    }
}
