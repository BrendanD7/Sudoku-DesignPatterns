import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SudokuGUI extends JFrame implements ActionListener {

    private JPanel sudokuPanel, buttonPanel;
    private JButton checkButton, resetButton;
    private JTextField[][] sudokuCells;
    private SudokuBoard sudokuBoard;

    public SudokuGUI(String difficulty) {
        super("Sudoku");
        
        // Create sudoku board
        SudokuFactory factory = SudokuFactory.getInstance();
        sudokuBoard =  factory.buildBoard(difficulty);
        int boardSize = sudokuBoard.getSize();
        // Create sudoku panel
        int subgridSize = (int) Math.sqrt(boardSize);
        sudokuPanel = new JPanel();
        sudokuPanel.setLayout(new GridLayout(subgridSize, subgridSize, 2, 2));
        sudokuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sudokuCells = new JTextField[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            if (i % subgridSize == 0) { // Create new subgrid panel for every subgrid
                JPanel subgrid = new JPanel(new GridLayout(subgridSize, subgridSize, 2, 2));
                subgrid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                sudokuPanel.add(subgrid);
            }
            for (int j = 0; j < boardSize; j++) {
                JTextField cell = new JTextField(1);
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Arial", Font.PLAIN, 24));
                int cellValue = sudokuBoard.getCellValue(i, j);
                if (cellValue != 0) {
                    cell.setText("" + cellValue);
                    cell.setEditable(false);
                }
                sudokuCells[i][j] = cell;
                ((JPanel) sudokuPanel.getComponent(i/subgridSize)).add(cell); // Add cell to current subgrid panel

            }
        }
        
    
        // create button panel
        buttonPanel = new JPanel();
        checkButton = new JButton("Check");
        checkButton.addActionListener(this);
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        buttonPanel.add(checkButton);
        buttonPanel.add(resetButton);
    
        // add panels to frame
        add(sudokuPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    
        // set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {           
            for (int row = 0; row < sudokuBoard.getSize(); row++) {
                for (int col = 0; col < sudokuBoard.getSize(); col++) {
                    sudokuBoard.getCell(row,col).setValue(Integer.parseInt(sudokuCells[row][col].getText()));
                }
            }
            if (sudokuBoard.isBoardValid()) {
                JOptionPane.showMessageDialog(this, "Sudoku is valid!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } 
            else {
                JOptionPane.showMessageDialog(this, "Sudoku is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        // Reset the Board
        } else if (e.getSource() == resetButton) {
            for (int row = 0; row < sudokuBoard.getSize(); row++) {
                for (int col = 0; col < sudokuBoard.getSize(); col++) {
                    int cellValue = sudokuBoard.getCellValue(row, col);
                    if (cellValue == 0) {
                        sudokuCells[row][col].setText("");
                        sudokuCells[row][col].setEditable(true);
                    } else {
                        sudokuCells[row][col].setText("" + cellValue);
                        sudokuCells[row][col].setEditable(false);
                    }
                }
            }
        }
    }
    

    public static void main(String[] args) {
        System.out.println("Board difficulty (Easy, Medium, Hard): ");
        Scanner scan = new Scanner(System.in);
        String difficulty = scan.next();
        scan.close();
        new SudokuGUI(difficulty);
    }
}