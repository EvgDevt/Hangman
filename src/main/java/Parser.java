import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


// Получение слов(а) из указанного источника (веб-сайт).
public class Parser {

	private static final char[] LETTERS = { 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'К', 'Л', 'М', 'Н', 'О',
			'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Э', 'Ю', 'Я' };
	private static final String BASIC_URL = "https://kupidonia.ru/spisok/spisok-suschestvitelnyh-russkogo-jazyka/bukva/";
	private static final String HTML_CLASS_NAME = "position_title";

	
	/**
	* Получаем слова с произвольной первой буквой с веб-сайта.
	*/
	public List<String> fetchWordsOfRandomFirstLetter() {

		List<String> wordList = new ArrayList<>();
		String url = constructRandomUrl(BASIC_URL, LETTERS);

		try {
			Document doc = Jsoup.connect(url).get();
			Elements wordEl = doc.getElementsByClass(HTML_CLASS_NAME);

			wordEl.forEach(el -> {
				String word = el.text();
				if (!word.contains("-") && (word.length() > 4 && word.length() < 8))
					wordList.add(word);
			});


		} catch (IOException e) {
			e.printStackTrace();
		}
		return wordList;
	}

	/**
	 * Строим случайный URL, используя базовый URL и случайную букву из массива букв.
	 */
	private String constructRandomUrl(String url, char[] letters) {
		Random random = new Random();
		String result = url + encodeLetterToUrlCode(letters[random.nextInt(letters.length)]);
		return result;
	}

	/**
	 * Кодируем букву в URL-кодировку с использованием UTF-8.
	 */
	private String encodeLetterToUrlCode(char letter) {
		String letterAsString = String.valueOf(letter);
		return URLEncoder.encode(letterAsString, StandardCharsets.UTF_8);
	}
}
