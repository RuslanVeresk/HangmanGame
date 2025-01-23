import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ControllerHangman {
    private short count = 0;
    private Hangman hangman;
    private StringBuilder inputLetters = new StringBuilder("");
    private StringBuilder errorLetters = new StringBuilder("");

    public ControllerHangman(){
        try{
            List<String> wordFromFile = Files.readAllLines(Path.of(GameApplication.class.getClassLoader().getResource("words.txt").toURI()));
            Random random = new Random();
            String randomWord = wordFromFile.get(random.nextInt(wordFromFile.size()));
            hangman = new Hangman(randomWord);
            createHiddenWord(hangman.getHiddenWord().toString());
        }catch(IOException e){
            System.out.println("Error");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void createHiddenWord(String word){
        for (int i = 0; i < word.length(); i++)
            inputLetters.append("*");
    }

    public void startGame(Scanner scanner){
        while(count < 6){
            System.out.println("Yor progress : \nWord  " + inputLetters + ", error : " + errorLetters);
            System.out.print("Enter letter : ");
            char letter = ' ';
            try {
                letter = scanner.nextLine().toLowerCase().charAt(0);
            }catch (Exception e){
                System.out.println("Error");
            }
            checkLetter(letter);
            if(hangman.getHiddenWord().toString().equals(inputLetters.toString().toLowerCase())){
                System.out.println(hangman.getHiddenWord() + "! " + "You win!");
                break;
            }
        }
        if (count == 6)
            System.out.println(String.format("Word : %s \nGame over!", hangman.getHiddenWord()));
    }

    private void checkLetter(Character inputLetter){
        String s = inputLetter.toString();
        int codePoint = (int) inputLetter;
        if ((codePoint >= 'а' && codePoint <= 'я')  && !errorLetters.toString().contains(inputLetter.toString())){
            String str = hangman.getHiddenWord();
            if (str.contains(inputLetter.toString())){
                char[] word = hangman.getHiddenWord().toCharArray();
                for (int i = 0; i < word.length; i++ ){
                    if (word[i] == inputLetter){
                        inputLetters.setCharAt(i,inputLetter);
                    }
                }
            }else{
                errorLetters.append(inputLetter);
                errorLetters.append(", ");
                count++;
            }
        }

    }
}
