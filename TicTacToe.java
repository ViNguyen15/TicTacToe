package game;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicTacToe {
	public static byte count = 0;
	public static String results = "";
	public static String checker = "";
	
	//create a board
	public String[][] getBoard(){
		String[][] board = {{"-","-","-"},
							{"-","-","-"},
							{"-","-","-"}};
		return board;
	}
	
	//contains instructions for the game
	public void getInstructions() {
		System.out.println("Instructions\n"
				 + "7 8 9\n"
				 + "4 5 6\n"
				 + "1 2 3");
	}
	
	//seeing the board
	public void seeBoard(String[][] board) {
		for(String[] row : board) {
			for(String col : row)
				System.out.print(col + " ");
			System.out.println();
		}
	}
	
	//checking for duplicates
	public boolean checkInput(int player) {
		checker+=player;
		if (duplicateMap(checker)) {
			System.out.println("try again");
			return true;
		}
		return false;
	}
	
	//checking for how the game ends...please optimize this!
	public boolean checkEnd(String[][] board) {
		if( (board[2][0]+board[2][1]+board[2][2]).equals("xxx")|| //123
			(board[1][0]+board[1][1]+board[1][2]).equals("xxx")|| //456
			(board[0][0]+board[0][1]+board[0][2]).equals("xxx")|| //789
			(board[2][0]+board[1][0]+board[0][0]).equals("xxx")|| //147
			(board[2][1]+board[1][1]+board[0][1]).equals("xxx")|| //258
			(board[2][2]+board[1][2]+board[0][2]).equals("xxx")|| //369
			(board[2][0]+board[1][1]+board[0][2]).equals("xxx")|| //159
			(board[2][2]+board[1][1]+board[0][0]).equals("xxx")|| //357
			
			(board[2][0]+board[2][1]+board[2][2]).equals("ooo")|| //123
			(board[1][0]+board[1][1]+board[1][2]).equals("ooo")|| //456
			(board[0][0]+board[0][1]+board[0][2]).equals("ooo")|| //789
			(board[2][0]+board[1][0]+board[0][0]).equals("ooo")|| //147
			(board[2][1]+board[1][1]+board[0][1]).equals("ooo")|| //258
			(board[2][2]+board[1][2]+board[0][2]).equals("ooo")|| //369
			(board[2][0]+board[1][1]+board[0][2]).equals("ooo")|| //159
			(board[2][2]+board[1][1]+board[0][0]).equals("ooo")){ //357
			count--;
			results = getXO() + " wins";
			return true;
			}
		if(count==9) {
			results = "draw";
			return true;
		}
		return false;
	}
	
	//making changes to the board... optimize this too
	public void makeChanges(String[][] board, int player) {
		checker+=player;
		if(duplicateMap(checker)) {
			System.out.println("try again");
			checker=checker.substring(0,checker.length()-1);
		}else {
			String xo = getXO();
			if(player == 1) board[2][0] = xo;
			if(player == 2) board[2][1] = xo;
			if(player == 3) board[2][2] = xo;
			if(player == 4) board[1][0] = xo;
			if(player == 5) board[1][1] = xo;
			if(player == 6) board[1][2] = xo;
			if(player == 7) board[0][0] = xo;
			if(player == 8) board[0][1] = xo;
			if(player == 9) board[0][2] = xo;		
			count++;
		}
	}
	
	//determining x and o
	public String getXO() {
	if (count%2 == 0)
		return "x";
	else
		return "o";
	}
	
	//detecting duplicates
	public boolean duplicateMap(String word) {
		char[] chars = word.toCharArray();
        
        Map<Character, Integer> map = new HashMap<>();
        for(char c : chars){
            if(map.containsKey(c)) {
                int counter = map.get(c);
                map.put(c, ++counter);
            } else {
                map.put(c, 1);
            }
            if(map.get(c)>1)
            	return true;
        }
        return false;
	}
	
	//main method where we can test the game
	public static void main(String[] args) {
		TicTacToe ttt = new TicTacToe();
		Scanner input = new Scanner(System.in);
		boolean end = false;
		String[][]board = ttt.getBoard(); //tying the boards to each other (terrible but kept to make it easy on me)
		try {
			ttt.getInstructions();
			while(!end) {
				int player = input.nextInt();
				ttt.makeChanges(board, player);
				ttt.seeBoard(board);
				System.out.println("*******");
				end = ttt.checkEnd(board);
			}
			System.out.println(results);
			input.close();
		}
		catch(Exception e) {
			System.out.println("that's wrong");
		}
	}
}