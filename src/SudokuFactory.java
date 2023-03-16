public class SudokuFactory {
    private static SudokuFactory instance;
    private SudokuFactory(){}
    
    public static SudokuFactory getInstance(){
        if(instance == null){
            instance = new SudokuFactory();
        }
        return instance;
    }

    public SudokuBoard buildBoard(String difficulty){
        if(difficulty.equals("Easy")){
            return new EasySudokuBoard();
        }
        else if(difficulty.equals("Medium")){
            return new MediumSudokuBoard();
        }
        else if(difficulty.equals("Hard")){
            return new HardSudokuBoard();
        }
        else{
            return null;
        }
    }
}
