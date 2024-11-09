import java.util.Random;

import java.util.Scanner;



public class MazeGame {

    private static final int WIDTH = 10;

    private static final int HEIGHT = 10;

    private static final char WALL = '#';

    private static final char PATH = ' ';

    private static final char PLAYER = 'P';

    private static final char EXIT = 'E';



    private char[][] maze;

    private int playerX, playerY;



    public MazeGame() {

        maze = new char[HEIGHT][WIDTH];

        generateMaze();

    }



    public void play() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Maze Game!");

        System.out.println("Navigate through the maze to reach the exit (E).");

        System.out.println("Use W (up), A (left), S (down), D (right) to move.\n");



        while (true) {

            printMaze();

            System.out.print("Enter your move: ");

            char move = scanner.next().charAt(0);



            if (movePlayer(move)) {

                System.out.println("Congratulations! You've reached the exit!");

                break;

            }

        }

        scanner.close();

    }



    private void generateMaze() {

        Random rand = new Random();



        // Fill maze with walls

        for (int i = 0; i < HEIGHT; i++) {

            for (int j = 0; j < WIDTH; j++) {

                maze[i][j] = WALL;

            }

        }



        // Create random paths

        for (int i = 1; i < HEIGHT - 1; i++) {

            for (int j = 1; j < WIDTH - 1; j++) {

                maze[i][j] = (rand.nextInt(3) == 0) ? WALL : PATH;

            }

        }



        // Set player start position

        playerX = 1;

        playerY = 1;

        maze[playerY][playerX] = PLAYER;



        // Set exit position

        maze[HEIGHT - 2][WIDTH - 2] = EXIT;

    }



    private boolean movePlayer(char direction) {

        int newX = playerX;

        int newY = playerY;



        switch (Character.toUpperCase(direction)) {

            case 'W': newY--; break; // Up

            case 'A': newX--; break; // Left

            case 'S': newY++; break; // Down

            case 'D': newX++; break; // Right

            default:

                System.out.println("Invalid move. Use W, A, S, or D.");

                return false;

        }



        // Check if the new position is within bounds and not a wall

        if (newX >= 0 && newX < WIDTH && newY >= 0 && newY < HEIGHT && maze[newY][newX] != WALL) {

            // Move player

            maze[playerY][playerX] = PATH;

            playerX = newX;

            playerY = newY;

            maze[playerY][playerX] = PLAYER;



            // Check if player reached the exit

            return maze[playerY][playerX] == EXIT;

        } else {

            System.out.println("You hit a wall dummy! Try a different direction.");

            return false;

        }

    }



    private void printMaze() {

        for (int i = 0; i < HEIGHT; i++) {

            for (int j = 0; j < WIDTH; j++) {

                System.out.print(maze[i][j]);

            }

            System.out.println();

        }

    }



    public static void main(String[] args) {

        MazeGame game = new MazeGame();

        game.play();

    }

}

