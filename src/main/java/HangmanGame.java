import java.util.List;
import java.util.Scanner;
import java.util.Set;

// Класс HangmanGame представляет основную логику игры Виселица.
public class HangmanGame {
	
	private final Player player;
	private final WordsRepository wordsRepository;
	private final IOHandler ioHandler;
	private String wordToGuess; 
	private List<Character> playerGuesses;
	private Set<Character> wrongGuessedLetters;
	
	public HangmanGame(Player player, WordsRepository wordsRepository, IOHandler ioHandler) {
		this.player = player;
		this.wordsRepository = wordsRepository;
		this.ioHandler = ioHandler;
		this.wordToGuess = wordsRepository.getRandomWord();
		playerGuesses = player.getPlayerGuesses();
		wrongGuessedLetters = player.getWrongGuessedLetters();
	}
	
	/**
	 * Начинает и управляет игрой Виселица.
	 */
	public void play() {
		try (Scanner sc = new Scanner(System.in)) {
			ioHandler.greet(player, sc);
			
			System.out.println(wordToGuess);
			while (player.isPlaying()) {
				makeGuess(player, sc, wordToGuess, playerGuesses);
				
				if (isWordGuessed(wordToGuess, playerGuesses)) {
					System.out.println("\nВы победили, %s!".formatted(player.getName()));
					player.setPlaying(false);
					replay(player, sc);
				} else if (player.getIncorrectGuesses() >= 6) {
					System.out.println("\nВы проиграли!");
					player.setPlaying(false);
				}
				if (!player.isPlaying()) {
					replay(player, sc);
					if (player.isPlaying()) {
						resetGameState();
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Делает предположение для игрока и обновляет состояние игры соответственно.
	 * @param player Объект игрока.
	 * @param sc Сканер для чтения пользовательского ввода.
	 * @param word Слово для угадывания.
	 * @param guesses Угаданные буквы игрока.
	 */
	private void makeGuess(Player player, Scanner sc, String word, List<Character> guesses) {
		printWordState(word, guesses);
		System.out.println("Пожалуйста введите букву:");

		char guessedLetter = validateLetterInput(sc);
		
		if (word.contains(String.valueOf(guessedLetter))) {
			player.incrementCorrectGuesses();
			guesses.add(guessedLetter);
		} else {
			player.incrementIncorrectGuesses();
			wrongGuessedLetters.add(guessedLetter);
			HangmanDrawing.drawHangman(player.getIncorrectGuesses());
			System.out.println("Количество неудачных попыток: " + player.getIncorrectGuesses());
			System.out.print("Ошибки: ");
			printWrongGuessedLetters();
		}
	}

	/**
	 * Запрашивает у игрока повтор игры и обрабатывает его ответ.
	 * @param player Объект игрока.
	 * @param sc Сканер для чтения пользовательского ввода.
	 */
	private void replay(Player player, Scanner sc) {
		printEndGameData();
		player.setPlaying(false);
		ioHandler.askForReplay(player, sc);
	}

	/*
	 * Сбрасывает состояние игры: выбирает новое слово, очищает список с буквами,
	 * которые отгадывает игрок, возвращает счетчикам значение 0.
	 */
	private void resetGameState() {
		wordToGuess = wordsRepository.getRandomWord();
		playerGuesses.clear();
		wrongGuessedLetters.clear();
		player.setCorrectGuesses(0);
		player.setIncorrectGuesses(0);
	}

	/**
	 * Выводит состояние слова на экран. Печатает символы слова, заменяя неугаданные буквы символами "-".
	 * @param word Слово для отображения состояния.
	 * @param guesses Список угаданных букв игрока.
	 */
	private void printWordState(String word, List<Character> guesses) {
		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			if (guesses.contains(letter)) {
				System.out.print(letter);
			} else {
				System.out.print("-");
			}
		}
		System.out.println();
	}
	
	/**
	 * Выводит список неправильно угаданных букв на экран.
	 * Если список пуст, выводит сообщение "отсутствуют".
	 */
	private void printWrongGuessedLetters() {
		StringBuilder sb = new StringBuilder();
		
		for (char letter : wrongGuessedLetters) {
			sb.append(letter)
				.append(", ");
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 2);
			System.out.println(sb.toString());
		} else {
			System.out.println("отсутствуют");
		}
	}

	/**
	 * Проверяет ввод игрока на корректность в соответствии
	 * с заданными критериями.
	 */
	private char validateLetterInput(Scanner sc) {
		while(true) {
			String input = sc.nextLine().trim().toLowerCase();
			if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
				System.out.println("Притормози ка! По одной букве за раз, и только букве!");
			} else {
					return input.charAt(0);
			}
		}
	}
	
	
	private boolean isWordGuessed(String word, List<Character> guesses) {
		for (int i = 0; i < word.length(); i++) {
			if (!guesses.contains(word.charAt(i)))
				return false;
		}
		return true;
	}
	

	private void printEndGameData() {
		System.out.println("________________________________");
		System.out.println("Слово было: " + wordToGuess);
		System.out.println("Общее количество попыток: " + (player.getIncorrectGuesses() + player.getCorrectGuesses()));
		System.out.println("Количество неудачных попыток: " + player.getIncorrectGuesses());
		System.out.println("________________________________");
	}
}
