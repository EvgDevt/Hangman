import java.util.List;

// Класс WordsRepository управляет получением слова для игры Виселица.
public class WordsRepository {

	private Parser parser = new Parser();

	/**
	 * Получает случайное слово для угадывания из хранилища слов.
	 * 
	 * @return Случайное слово.
	 */
	public String getRandomWord() {

		List<String> words = parser.fetchWordsOfRandomFirstLetter();
		int randomIndex = (int) (Math.random() * words.size());
		String wordToGuess = words.get(randomIndex);

		return wordToGuess;
	}

}
