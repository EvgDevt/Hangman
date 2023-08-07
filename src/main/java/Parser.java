import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


// Получение слов(а) из указанного источника (веб-сайт).
public class Parser {

	private static final char[] LETTERS = { 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'К', 'Л', 'М', 'Н', 'О',
			'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Э', 'Ю', 'Я' };
	private static final String BASIC_URL = "https://kupidonia.ru/spisok/spisok-suschestvitelnyh-russkogo-jazyka/bukva/";
	private static final int MAX_WORDS_PER_LETTER = 1;
	private static final String HTML_CLASS_NAME = "position_title";

	
	/**
	* Получает слова с произвольной первой буквой с веб-сайта.
	* @return Список слов, соответствующих заданным критериям.
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

			wordList.addAll(wordList.stream().limit(MAX_WORDS_PER_LETTER).collect(Collectors.toList()));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return wordList;
	}

	/**
	 * Строит случайный URL, используя базовый URL и случайную букву из массива букв.
	 * @param url Базовый URL.
	 * @param letters Массив букв для построения URL.
	 * @return Строка с конструированным случайным URL.
	 */
	private String constructRandomUrl(String url, char[] letters) {
		Random random = new Random();
		String result = url + encodeLetterToUrlCode(letters[random.nextInt(letters.length)]);
		return result;
	}

	/**
	 * Кодирует букву в URL-кодировку с использованием кодировки UTF-8.
	 * @param letter Буква для кодирования.
	 * @return Закодированная в URL-кодировку буква.
	 */
	private String encodeLetterToUrlCode(char letter) {
		String letterAsString = String.valueOf(letter);
		return URLEncoder.encode(letterAsString, StandardCharsets.UTF_8);

	}
}
