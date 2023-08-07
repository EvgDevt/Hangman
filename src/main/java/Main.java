public class Main {

  public static void main(String[] args) {
  	
  	Player player = new Player();
  	WordsRepository wordsRepository = new WordsRepository();
  	IOHandler ioHandler = new IOHandler();
  	
  	HangmanGame game = new HangmanGame(player, wordsRepository, ioHandler);
  	
  	game.play();
  }
}
