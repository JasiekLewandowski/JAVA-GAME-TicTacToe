import java.util.*;

public class KolkoKrzyzyk {
    public final static List<Integer> playerPositions = new ArrayList<>();
    public final static List<Integer> computerPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] rulesGameBoard = {{'1', '|','2','|', '3'},
                {'-', '+', '-', '+', '-'},
                {'4', '|', '5', '|', '6'},
                {'-', '+', '-', '+', '-'},
                {'7', '|', '8', '|', '9'}};

        char[][] gameBoard = {{' ', '|',' ','|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printRules(rulesGameBoard);
        System.out.println("NEW GAME");
        printGameBoard(gameBoard);
        Random random = new Random();

        int gameRule = 1;
        while(gameRule == 1) {
            while(true){
                System.out.println("Enter a number (1-9)");
                int playerPosition = getPosition();
                if (validate(playerPosition)){
                    placePiece(gameBoard, playerPosition, "player");
                    playerPositions.add(playerPosition);
                    if (!checkWin().equals("")) {
                        System.out.println(checkWin());
                        gameRule = 0;
                        break;
                    }
                    break;
                } else {
                    System.out.println("This place is already taken! Try again!");
                }
            }
            while(gameRule == 1){
                int computerPosition = random.nextInt(9) + 1;
                if (validate(computerPosition)){
                    placePiece(gameBoard, computerPosition, "computer");
                    computerPositions.add(computerPosition);
                    if (!checkWin().equals("")) {
                        System.out.println(checkWin());
                        gameRule = 0;
                        break;
                    }
                    break;
                }
            }
            printGameBoard(gameBoard);
        }
    }
    private static void printGameBoard(char[][] gameBoard){
        for (char[] row : gameBoard){
            for (char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    private static void printRules(char[][] gameBoard){
        System.out.println("This is the board!");
        for (char[] row : gameBoard){
            for (char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println("Try to beat the computer, by entering three 'X' symbols in line!");
    }

    private static int getPosition() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextInt();
            } catch (NoSuchElementException e) {
                System.out.println("Only digits! Try again.");
                scanner.nextLine();
            }
        }
    }
    private static void placePiece (char[][] gameBoard, int pos, String user){
        char symbol;
        if (user.equals("player"))
            symbol = 'X';
        else
            symbol = 'O';

        switch (pos){
            case 1:
                gameBoard [0][0] = symbol;
                break;
            case 2:
                gameBoard [0][2] = symbol;
                break;
            case 3:
                gameBoard [0][4] = symbol;
                break;
            case 4:
                gameBoard [2][0] = symbol;
                break;
            case 5:
                gameBoard [2][2] = symbol;
                break;
            case 6:
                gameBoard [2][4] = symbol;
                break;
            case 7:
                gameBoard [4][0] = symbol;
                break;
            case 8:
                gameBoard [4][2] = symbol;
                break;
            case 9:
                gameBoard [4][4] = symbol;
                break;
            default:
                break;
        }
    }
    private static String checkWin(){
        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> midRow = Arrays.asList(4,5,6);
        List<Integer> botRow = Arrays.asList(7,8,9);
        List<Integer> leftCol = Arrays.asList(1,4,7);
        List<Integer> midCol = Arrays.asList(2,5,8);
        List<Integer> rightCol = Arrays.asList(3,6,9);
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List list : winning){
            if (playerPositions.containsAll(list)){
                return "Congratulations, you won!";
            }
            else if (computerPositions.containsAll(list)){
                return "You lost!";
            }
            else if (playerPositions.size() + computerPositions.size() >= 9){
                return "It's a tie!";
            }
        }
        return "";
    }
    private static boolean validate(int position){
        return !playerPositions.contains(position) && !computerPositions.contains(position);
    }
}
