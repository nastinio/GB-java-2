public class MainHomeWork {
    private static int arrayDefaultSize = 4;

    public static void main(String[] args) {

        String[][] input = {{"1", "4", "5", "2"}, {"6", "6", "-1", "0"},
                {"8", "89", "56", "23"}, {"3", "7", "9", "5"}};
        //Запуск с верными данными
        try {
            System.out.println("Сумма матрицы: "+getMatrixSum(input));
        } catch (MyArraySizeException e) {
            System.out.println("Неверный размер входного массива");
            e.printStackTrace();
        }catch (MyArrayDataException e){
            System.out.printf("Неверный формат данных в ячейке [%d][%d]\n",e.getLine(),e.getColumn());
            e.printStackTrace();
        }


        /*String[][] inputWrongData = {{"1", "4", "5", "2"}, {"6", "6", "-1", null},
                {"8", "y", "56", "23"}, {"3", "7", "h", "5"}};

        String[][] inputWrongSize = {{"1", "4", "5", "2","5"}, {"6", "6", "-1", null},
                {"8", "y", "56", "23"}, {"3", "7", "h", "5"}};
        //С неверным размером
        try {
            System.out.println("Сумма матрицы: "+getMatrixSum(inputWrongSize));
        } catch (MyArraySizeException e) {
            System.out.println("Неверный размер входного массива");
            e.printStackTrace();
        }catch (MyArrayDataException e){
            System.out.printf("Неверный формат данных в ячейке [%d][%d]\n",e.getLine(),e.getColumn());
            e.printStackTrace();
        }

        //С неверным типом данных
        try {
            System.out.println("Сумма матрицы: "+getMatrixSum(inputWrongData));
        } catch (MyArraySizeException e) {
            System.out.println("Неверный размер входного массива");
            e.printStackTrace();
        }catch (MyArrayDataException e){
            System.out.printf("Неверный формат данных в ячейке [%d][%d]\n",e.getLine(),e.getColumn());
            e.printStackTrace();
        }*/
    }

    public static int getMatrixSum(String[][] inputData) throws MyArraySizeException, MyArrayDataException {
        //Проверим входной массив
        checkDataSize(inputData);

        int resSum = 0;
        for (int i = 0; i < arrayDefaultSize; i++) {
            for (int j = 0; j < arrayDefaultSize; j++) {
                try{
                    int current = Integer.parseInt(inputData[i][j]);
                    resSum+=current;
                }catch (NumberFormatException n){
                    throw new MyArrayDataException("Неверный формат данных",i,j);
                }
            }
        }


        return resSum;
    }


    public static void checkDataSize(String[][] inputData) throws MyArraySizeException {
        //Количество строк
        int numberLines = inputData.length;
        if (numberLines != arrayDefaultSize) {
            throw new MyArraySizeException("Количество строк не соответствует заданному значению", numberLines);
        }
        //Проверим, количество столбцов в каждлй строке
        for (int i = 0; i < numberLines; i++) {
            if (inputData[i].length != arrayDefaultSize) {
                throw new MyArraySizeException("Количество столбцов не соответствует заданному значению", inputData[i].length);
            }

        }
    }

}

class MyArrayDataException extends Exception {
    private int line;
    private int column;

    MyArrayDataException(String msg, int line, int column) {
        super(msg);
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}

class MyArraySizeException extends Exception {
    private int numberLines;
    private int numberColumn;

    MyArraySizeException(String msg, int numberLines, int numberColumn) {
        super(msg);
        this.numberLines = numberLines;
        this.numberColumn = numberColumn;
    }

    MyArraySizeException(String msg, int numberLines) {
        super(msg);
        this.numberLines = numberLines;
    }

    public int getNumberLines() {
        return numberLines;
    }

    public int getNumberColumn() {
        return numberColumn;
    }
}
