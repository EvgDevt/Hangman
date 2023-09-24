import java.util.Scanner;

/**
 * Класс обработки ввода игрока.
 */
public class IOHandler {
	
	private static final String WELCOME_MESSAGE = "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n   "
			+ "Добро пожаловать в игру Виселица!\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~";
	private static final String NAME_QUESTION = "\n   Введите ваше имя:";
	private static final String START_QUESTION = "\n   Хотите сыграть, %s?";
	private static final String PLAYER_AGREE_PROMPT = "Попробуйте отгадать слово:"; 
	private static final String PLAYER_REFUSE_PROMPT = "До новых встреч, %s.";
	private static final String INVALID_INPUT_PROMPT = "Вы сделали некорректный ввод, %s!";
	private static final String ACCEPTABLE_ANSWERS_PROMPT = "Введите \"да\" или \"д\", чтобы начать.  Введите \"нет\" или \"н\", чтобы выйти.";
	private static final String REPLAY_QUESTION = "\nХотите сыграть еще раз?";
	private boolean validInput;
	
	
	public void greet(Player player, Scanner sc) {
		System.out.println(WELCOME_MESSAGE);
		
		System.out.println(NAME_QUESTION);
		player.setName(sc.nextLine());
		System.out.println();
		System.out.println(START_QUESTION.formatted(player.getName()));

		do {
			processPlayerInput(player, sc);
		} while (!validInput);
	}
	
	/**
	 * Запрашиваем у игрока повтор игры
	 */
	public void askForReplay(Player player, Scanner sc) {
		System.out.println(REPLAY_QUESTION);
		do {
			processPlayerInput(player, sc);
		} while (!validInput);
	}

	/**
	 * Обрабатываем ввод игрока.
	 */
	private boolean processPlayerInput(Player player, Scanner sc) {
		System.out.println("Да/Нет:");
		String playerAnswerInput = sc.nextLine().toLowerCase();

		if (playerAnswerInput.equals("да") || playerAnswerInput.equals("д")) {
			System.out.println(PLAYER_AGREE_PROMPT);
			System.out.println();
			validInput = true;
			player.setPlaying(true);
		} else if (playerAnswerInput.equals("нет") || playerAnswerInput.equals("н")) {
			System.out.println(PLAYER_REFUSE_PROMPT.formatted(player.getName()));
			validInput = true;
			player.setPlaying(false);
		} else {
			validInput = false;
			System.out.println(INVALID_INPUT_PROMPT.formatted(player.getName()));
			System.out.println(ACCEPTABLE_ANSWERS_PROMPT);
		}
		return validInput;
	}
}
