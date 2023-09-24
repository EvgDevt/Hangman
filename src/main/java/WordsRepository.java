import java.util.List;

public class WordsRepository {

	private Parser parser = new Parser();

	public String getRandomWord() {

		List<String> words = parser.fetchWordsOfRandomFirstLetter();
		int randomIndex = (int) (Math.random() * words.size());
		String wordToGuess = words.get(randomIndex);

		return wordToGuess;
	}

}
