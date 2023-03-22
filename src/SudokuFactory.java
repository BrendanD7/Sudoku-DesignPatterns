/** Factory class to create SudokuBoard objects of varying size and difficulty */
public class SudokuFactory {
    /* The instance of this class for the Singleton pattern */
    private static SudokuFactory instance;
    /** Private constructor for the Singleton pattern */
    private SudokuFactory(){}
    
    /** Retrieve the instance of this class
     * @return The one instance of the SudokuFactory class
     */
    public static SudokuFactory getInstance(){
        if(instance == null){
            instance = new SudokuFactory();
        }
        return instance;
    }

    /** Creates a Sudoku Board object that matches the given difficulty and size
     * @param difficulty The difficulty of the board to be created
     * @param size The size of the board to be created 
     * @return A new SudokuBoard object with the given difficulty and size
     */
    public SudokuBoard createBoard(String difficulty, int size){
        if(difficulty.equals("Easy")){
            return new EasySudokuBoard(size);
        }
        else if(difficulty.equals("Medium")){
            return new MediumSudokuBoard(size);
        }
        else if(difficulty.equals("Hard")){
            return new HardSudokuBoard(size);
        }
        else{
            return null;
        }
    }
}
