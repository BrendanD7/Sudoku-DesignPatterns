/** Swing GUI to Visualize the Sudoku game */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SudokuGUI extends JFrame implements ActionListener {

    private JPanel sudokuPanel, buttonPanel;
    private JButton checkButton, resetButton, restartButton;
    private JTextField[][] sudokuCells;
    private SudokuBoard sudokuBoard;
    private static String difficulty;
    private static int boardSize;

    public SudokuGUI(String difficulty, int boardSize) {
        super("Sudoku Game");
        // Create sudoku board
        SudokuFactory factory = SudokuFactory.getInstance();
        sudokuBoard =  factory.createBoard(difficulty, boardSize);
        // Create sudoku panel
        int subgridSize = (int) Math.sqrt(boardSize);
        sudokuPanel = new JPanel();
        sudokuPanel.setLayout(new GridLayout(subgridSize, subgridSize));
        sudokuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sudokuCells = new JTextField[boardSize][boardSize];
        for (int i = 0; i < subgridSize; i++) {
            for (int j = 0; j < subgridSize; j++) {
                JPanel subgrid = new JPanel(new GridLayout(subgridSize, subgridSize, 2, 2));
                subgrid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                sudokuPanel.add(subgrid);
                for (int k = 0; k < subgridSize; k++) {
                    for (int l = 0; l < subgridSize; l++) {
                        int row = i * subgridSize + k;
                        int col = j * subgridSize + l;
                        JTextField cell = new JTextField(1);
                        cell.setHorizontalAlignment(JTextField.CENTER);
                        cell.setFont(new Font("Arial", Font.PLAIN, 24));
                        int cellValue = sudokuBoard.getCellValue(row, col);
                        if (cellValue != 0) {
                            cell.setText("" + cellValue);
                            cell.setEditable(false);
                        }
                        // Decorate user-selected cells
                        if (cell.isEditable()) {
                            BackgroundCellDecorator userBGDecorator = new BackgroundCellDecorator(new Color(211, 245, 187,255));
                            ForegroundCellDecorator userFGDecorator = new ForegroundCellDecorator(new Color(66, 135, 26, 255));
                            userBGDecorator.colour(cell);
                            userFGDecorator.colour(cell);
                        }
                        // Decorate software-generated cells
                        else {
                            BackgroundCellDecorator givenBGDecorator = new BackgroundCellDecorator(new Color(171, 230, 133, 255));
                            ForegroundCellDecorator givenFGDecorator = new ForegroundCellDecorator(new Color(48, 96, 19, 255));
                            givenBGDecorator.colour(cell);
                            givenFGDecorator.colour(cell);
                        }
                        sudokuCells[row][col] = cell;
                        subgrid.add(cell); // Add cell to current subgrid panel
                    }
                }
            }
        }
        
        // create button panel
        buttonPanel = new JPanel();
        checkButton = new JButton("Check Board");
        checkButton.addActionListener(this);
        resetButton = new JButton("Reset Selections");
        resetButton.addActionListener(this);
        restartButton = new JButton("New Game");
        restartButton.addActionListener(this);
        buttonPanel.add(checkButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(restartButton);
    
        // add panels to frame
        add(sudokuPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    
        // set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    

    /** Event handler for buttons
     * @param e The event to be handled
     */
    public void actionPerformed(ActionEvent e) {
        // Check button clicked
        if (e.getSource() == checkButton) {           
            for (int row = 0; row < sudokuBoard.getSize(); row++) {
                for (int col = 0; col < sudokuBoard.getSize(); col++) {
                    if(Integer.parseInt(sudokuCells[row][col].getText()) > sudokuBoard.getSize() || Integer.parseInt(sudokuCells[row][col].getText()) <= sudokuBoard.getSize()){
                        JOptionPane.showMessageDialog(this, "Invalid Inputs.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    else if(!sudokuCells[row][col].getText().equals("")){
                        sudokuBoard.getCell(row,col).setValue(Integer.parseInt(sudokuCells[row][col].getText()));
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Please fill in every cell.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            // Valid board
            if (sudokuBoard.isBoardValid()) {
                JOptionPane.showMessageDialog(this, "Sudoku is valid!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } 
            // Invalid board
            else {
                JOptionPane.showMessageDialog(this, "Sudoku is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        // Reset the Board
        } else if (e.getSource() == resetButton) {
            sudokuBoard.restore();
            for (int row = 0; row < sudokuBoard.getSize(); row++) {
                for (int col = 0; col < sudokuBoard.getSize(); col++) {
                    String value = sudokuBoard.getCellValue(row,col) + "";
                    if(value.equals("0")){
                        sudokuCells[row][col].setText("");
                    }
                    else{
                        sudokuCells[row][col].setText(value);
                    }
                }
            }
        }
        // Create new board
        else if(e.getSource() == restartButton){
            dispose();
            openScreen();
        }
    }
    
    /** Opening screen for the game to choose difficulty and size */
    public static void openScreen() {
        JFrame frame = new JFrame("Sudoku");
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel sizeBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox easyBox = new JCheckBox("Easy");
        JCheckBox mediumBox = new JCheckBox("Medium");
        JCheckBox hardBox = new JCheckBox("Hard");
    
        JCheckBox smallBox = new JCheckBox("4x4");
        JCheckBox middleBox = new JCheckBox("9x9");
        JCheckBox largeBox = new JCheckBox("16x16");
        JButton startButton = new JButton("Start Game");
    
        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(easyBox);
        difficultyGroup.add(mediumBox);
        difficultyGroup.add(hardBox);
    
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(smallBox);
        sizeGroup.add(middleBox);
        sizeGroup.add(largeBox);
    
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == startButton){
                    if(easyBox.isSelected()){
                        difficulty = "Easy";
                    }
                    else if(mediumBox.isSelected()){
                        difficulty = "Medium";
                    }
                    else if(hardBox.isSelected()){
                        difficulty = "Hard";
                    }
                    else{
                        return;
                    }
                    if(smallBox.isSelected()){
                        boardSize = 4;
                    }
                    else if(middleBox.isSelected()){
                        boardSize = 9;
                    }
                    else if(largeBox.isSelected()){
                        boardSize = 16;
                    }
                    else{
                        return;
                    }
                    new SudokuGUI(difficulty, boardSize);
                    frame.dispose();
                }
            }
        });
        checkBoxPanel.add(easyBox);
        checkBoxPanel.add(mediumBox);
        checkBoxPanel.add(hardBox);
        panel.add(checkBoxPanel, BorderLayout.NORTH);
    
        sizeBoxPanel.add(smallBox);
        sizeBoxPanel.add(middleBox);
        sizeBoxPanel.add(largeBox);
        panel.add(sizeBoxPanel, BorderLayout.CENTER);
    
        JPanel startButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startButtonPanel.add(startButton);
        panel.add(startButtonPanel, BorderLayout.SOUTH);
    
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(225, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /** Run the game */
    public static void main(String[] args) {
        if(args.length > 0){System.exit(0);}
        openScreen();
    }
}