public class SudokuFactory {
    private static SudokuFactory instance;
    private SudokuFactory(){}
    
    public static SudokuFactory getInstance(){
        if(instance == null){
            instance = new SudokuFactory();
        }
        return instance;
    }

    public SudokuBoard buildBoard(String difficulty, int size){
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
