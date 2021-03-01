package book;

import java.util.Random;
import java.util.Scanner;

public class FindWord {

    public static boolean move(String[][] input, String word, int i, int j){

        //get length of input
        int m = input.length;
        int n = input[0].length;
        //get length of word
        int wordLen = word.length();

        //create visit status of moving
        boolean[][] visited = new boolean[m][n];

        //2 arrays of move step
        //up, down, left, right
        int[] rowMove = {-1,+1,0,0};
        int[] colMove = {0,0,-1,+1};

        //save history of move
        int[] rowHistory = new int[wordLen];
        int[] colHistory = new int[wordLen];

        //count the character is same
        int count = 0;

        //compare fisrt input character with first word character
        if(input[i][j].equals(Character.toString(word.charAt(0)))){

            count = 1;

            //loop for browsing word
            for(int k = 1; k < wordLen; k++){

                //boolean value to break the loop if find same character
                boolean check = true;
                String wordAt = Character.toString(word.charAt(k));

                //loop for move up, down, right, left
                for(int p = 0; p < 4; p++) {

                    if(check){

                        //create new index for move
                        int nextRow = i + rowMove[p];
                        int nextCol = j + colMove[p];

                        if (nextRow >= 0 && nextCol >= 0 && nextRow <= m - 1 && nextCol <= n - 1
                                && input[nextRow][nextCol].equals(wordAt)
                                && !visited[nextRow][nextCol]) {

                            //save history
                            rowHistory[count] = i;
                            colHistory[count] = j;

                            //check visited
                            visited[nextRow][nextCol] = true;
                            //increase count same character
                            count++;
                            //pass new index
                            i = nextRow;
                            j = nextCol;
                            check = false;
                        }
                    }

                    //if move all way but can't find the same
                    //return back old postion to find another way
                    if(p == 3 && check && count >=2){
                        //get old position
                        i = rowHistory[count - 1];
                        j = colHistory[count - 1];
                        //decrease count
                        count--;
                        //decrease index of word
                        k = k - 2;
                    }

                }
            }
        }

        //check count to return result
        if(count == word.length()){
            return true;
        }else {
            return false;
        }
    }

    public static void find(String[][] input, String word){
        int m = input.length;
        int n = input[0].length;

        //loop for browsing the input
        for (int i = 0; i < m; i++){
            for ( int j = 0; j < n; j++){

                boolean result = move(input, word, i, j);

                if(result){
                    System.out.println("True");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        //enter m, n
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter m:");
        int m = keyboard.nextInt();
        System.out.println("Enter n:");
        int n = keyboard.nextInt();

        //random input
        String[][] input = new String[m][n];
        Random rnd = new Random();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                char randomChar = (char) ('A' + rnd.nextInt(26));
                input[i][j] = Character.toString(randomChar);
            }
        }

        //print input
        System.out.println("Random input");
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                System.out.print(input[i][j]+" ");
            }
            System.out.println();
        }
        
        String word = "";
        String c = "";
        
        //loop for enter word
        do{
            String b = keyboard.nextLine();
            System.out.print("Enter the word: ");
            word = keyboard.nextLine();
            
            //call method to find word
            find(input, word);

            System.out.println("Continute? (Y/N)");
            c = keyboard.nextLine();
        }while (c.equals("Y"));

    }
}