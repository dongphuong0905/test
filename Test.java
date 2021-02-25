public class Test {

    public static void findWord(String[][] input, String word) throws Exception {
        //get row and column lenght
        int m = input.length;
        int n = input[0].length;

        //create map of move
        boolean[][] visited = new boolean[input.length][input[0].length];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int count = 0;
                String result ="";
                //call recursion if input and first char of word is equals
                if(input[i][j].equals(Character.toString(word.charAt(0)))){
                    move(input, word, i, j, m -1, n -1, count, result+""+input[i][j],visited);
                    visited[i][j] = false;
                }
            }
        }
    }

    public static void move(String[][] input, String word, int rowIndex,
                               int colIndex, int mRange, int nRange,
                               int count, String result, boolean[][] visited) throws Exception{

        //check result string with word input
        if(result.equals(word)){
            System.out.println("True");
            return;
        }

        //init new index variable
        int newColIndex = 0;
        int newRowIndex = 0;

        //mark this position
        visited[rowIndex][colIndex] = true;
        //increase index of word char
        count++;
        //move up
        if( rowIndex - 1 >= 0 && colIndex >= 0 && rowIndex - 1 <= mRange && colIndex <= nRange
                && !visited[rowIndex - 1][colIndex] && input[rowIndex - 1][colIndex].equals(Character.toString(word.charAt(count)))){
            //add new index
            newRowIndex = rowIndex-1;
            //add result to compare
            result+= input[newRowIndex][colIndex];
            //recall
            move(input, word, newRowIndex, colIndex, mRange, nRange, count, result, visited);
            visited[newRowIndex][colIndex] = false;
        }

        //move down
        if( rowIndex + 1 >= 0 && colIndex >= 0 && rowIndex + 1 <= mRange && colIndex <= nRange
                && !visited[rowIndex + 1][colIndex] && input[rowIndex + 1][colIndex].equals(Character.toString(word.charAt(count)))){
            newRowIndex = rowIndex + 1;
            result+= input[newRowIndex][colIndex];
            move(input, word, newRowIndex, colIndex, mRange, nRange, count, result, visited);
            visited[newRowIndex][colIndex] = false;
        }

        //move right
        if( rowIndex >= 0 && colIndex + 1 >= 0 && rowIndex <= mRange && colIndex + 1<= nRange
                && !visited[rowIndex][colIndex+1]&& input[rowIndex][colIndex + 1].equals(Character.toString(word.charAt(count)))){

            newColIndex = colIndex + 1;
            result+= input[rowIndex][newColIndex];
            move(input, word, rowIndex, newColIndex, mRange, nRange, count, result, visited);
            visited[rowIndex][newColIndex] = false;
        }

        //move left
        if( rowIndex >= 0 && colIndex -1 >= 0 && rowIndex<= mRange && colIndex - 1 <= nRange
                && !visited[rowIndex][colIndex-1] && input[rowIndex][colIndex - 1].equals(Character.toString(word.charAt(count)))){
            newColIndex = colIndex - 1;
            result+= input[rowIndex][newColIndex];
            move(input, word, rowIndex, newColIndex, mRange, nRange, count, result, visited);
            visited[rowIndex][newColIndex] = false;
        }
    }

    public static void main(String[] args) throws Exception {

        String[][] input = new String[][]{
                {"A", "B", "C", "S"},
                {"F", "S", "C", "H"},
                {"A", "D", "E", "G"}
        };

        String word = "ABCCED";
        findWord(input, word);

        //print nothign = false
        String word2 = "ABCDE";
        findWord(input, word2);
    }
}