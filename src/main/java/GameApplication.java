import java.io.IOException;
import java.util.Scanner;

public class GameApplication {

    public GameApplication() {
        super();
    }

    public static void main(String[] args) {
        System.out.println("START GAME\n");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Menu : \n 1 - New game\n 2 - Exit");
                System.out.print("Input number and press enter : ");
                String num = scanner.nextLine();
                start(num, scanner);
            }
        }
    }

    private static void start(String result, Scanner scanner){
        if(result.equals("1")){
            ControllerHangman controller = new ControllerHangman();
            controller.startGame(scanner);
        }else if(result.equals("2")){
            System.out.print("Exit.");
            System.exit(0);
        }
    }
}
