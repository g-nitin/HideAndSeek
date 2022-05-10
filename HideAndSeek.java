/*
Nitin
9/21
Class example
 */

import java.util.Random;
import java.util.Scanner;

public class HideAndSeek {

    public static final int BOARD_SIZE = 10; // assume square board

    // (multiply by itself because distance was never square rooted)
    public static final int COLD_DIST = (BOARD_SIZE/2)*(BOARD_SIZE/2);
    public static final int WARM_DIST = (BOARD_SIZE/4)*(BOARD_SIZE/4);

    public static final char EMPTY = '_';
    public static final char PLAYER = 'X';
    public static final char GOAL = '_'; // start out with hiding the GOAL
    public static final char WALED_PATH = '#'; // the breadcrumb after the player leaves

    // TODO if the user wants to play again


    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Random r = new Random();

        // player's positions...
        int pX = 0; // start in the top left corner
        int pY = 0;

        // goal's positions (randomly)...
        int gX = r.nextInt(BOARD_SIZE); // 0 to BOARD_SIZE-1 are all the valid indices
        int gY = r.nextInt(BOARD_SIZE);

        // TODO chnage if the gX == pY and pX == pY (rare occurence)

        // 2 bytes in a character rather than 4 in an int...
        // using char also makes it easier to print later on
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i<board.length; i++)
        {
            for (int j = 0; j<board[i].length; j++) // board[i] is an array
            {
                board[i][j] =  EMPTY; // cleaning the board
            }
        }

        // place the pieces on the board...
        board[pY][pX] = PLAYER; // first index corresponds to the vertical part
        board[gY][gX] = GOAL;

        System.out.println("Welcome to hide and seek");

        //game loop
        boolean gameOver = false;

        while(!gameOver)
        {
            //show the board
            for (int i = 0; i<board.length; i++)
            {
                for (int j = 0; j<board[i].length; j++)
                {
                    System.out.print(board[i][j]); // NOT println
                }
                System.out.println(); // to go to the next row (line)
            }

            int distance = (pX-gX)*(pX-gX) + (pY-gY)*(pY-gY); // refer to every distance as the
            // square distance. Don't need to use the square root

            if (distance > COLD_DIST)
            {
                System.out.println("You are cold.");
            }
            else if (distance > WARM_DIST) // if here then cannot be larger than COLD_DIST
            {
                System.out.println("You are warm.");
            }
            else // if here then cannot be larger than WARM_DIST and COLD_DIST
            {
                System.out.println("You are hot");
            }

            // take inputs....
            System.out.println("Enter either -1, 0, or 1 to move in the x");
            int dX = keyboard.nextInt(); // change in X
            keyboard.nextLine();

            System.out.println("Enter either -1, 0, or 1 to move in the y");
            int dY = keyboard.nextInt(); // change in Y
            keyboard.nextLine();

            //Error checking...
            if (dX < -1 || dX > 1)
            {
                System.out.println("That's invalid!");
                dX = 0;
            }
            if (dY < -1 || dY > 1)
            {
                System.out.println("That's invalid!");
                dY = 0;
            }

            //Move the player
            board[pY][pX] = WALED_PATH;
            pX += dX;
            pY += dY;

            // is pX valid?
            if (pX < 0) // pX corresponds to the indices
            {
                pX = 0; // pushed them back
            }
            else if (pX > BOARD_SIZE - 1)
            {
                pX = BOARD_SIZE - 1;
            }
            // is pY valid?
            if (pY < 0) // pY corresponds to the indices
            {
                pY = 0; // pushed them back
            }
            else if (pY > BOARD_SIZE - 1)
            {
                pY = BOARD_SIZE - 1;
            }

            board[pY][pX] = PLAYER; // drop the player

            // check if they won
            if (pX == gX && pY == gY)
            {
                System.out.println("YOU WIN!");
                gameOver = true;
            }

        }


    }


}
