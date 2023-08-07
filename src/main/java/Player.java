import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Класс Player представляет игрока в игре Виселица и отслеживает его игровую статистику.
public class Player {
	
	private String name;
	private char letterToGuess;
	private boolean playing;
	private int correctGuesses;
	private int incorrectGuesses;
	private List<Character> playerGuesses;
	private Set<Character> wrongGuessedLetters;
	
	public Player() {
		this.incorrectGuesses = 0;
		this.correctGuesses = 0;
		this.playerGuesses = new ArrayList<>();
		this.wrongGuessedLetters = new HashSet<>();
	}
	
	/**
	 * Увеличивает количество неправильных попыток.
	 */
	public void incrementIncorrectGuesses() {
		incorrectGuesses++;
	}
	
	/**
	 * Увеличивает количество правильных попыток.
	 */
	public void incrementCorrectGuesses() {
		correctGuesses++;
	}
	
	public char getLetterToGuess() {
		return letterToGuess;
	}
	
	public void setLetterToGuess(char letterToGuess) {
		this.letterToGuess = letterToGuess;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Character> getPlayerGuesses() {
		return playerGuesses;
	}

	public void setPlayerGuesses(List<Character> playerGuesses) {
		this.playerGuesses = playerGuesses;
	}
	
	public Set<Character> getWrongGuessedLetters(){
		return wrongGuessedLetters;
	}

	public void setWrongGuessedLetters(Set<Character> wrongGuessedLetters) {
		this.wrongGuessedLetters = wrongGuessedLetters;
	}
	
	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean isPlaying) {
		this.playing = isPlaying;
	}
	
	public void setIncorrectGuesses(int incorrectGuesses) {
		this.incorrectGuesses = incorrectGuesses;
	}

	public int getIncorrectGuesses() {
		return incorrectGuesses;
	}

	public int getCorrectGuesses() {
		return correctGuesses;
	}

	public void setCorrectGuesses(int correctGuesses) {
		this.correctGuesses = correctGuesses;
	}

}
