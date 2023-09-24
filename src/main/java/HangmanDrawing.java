
public class HangmanDrawing {

	public static void drawHangman(int incorrectGuesses) {

		switch (incorrectGuesses) {
		case 0:
			System.out.println(" -------");
			System.out.println("|      |");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			break;
		case 1:
			System.out.println("  -------");
			System.out.println(" |      |");
			System.out.println(" |      O");
			System.out.println(" |");
			System.out.println(" |");
			System.out.println(" |");
			System.out.println(" |");
			System.out.println("_|___");
			break;
		case 2:
			System.out.println("  -------");
			System.out.println(" |      |");
			System.out.println(" |      O");
			System.out.println(" |     /");
			System.out.println(" |");
			System.out.println(" |");
			System.out.println(" |");
			System.out.println("_|___");
			break;
		case 3:
			System.out.println("  -------");
			System.out.println(" |      |");
			System.out.println(" |      O");
			System.out.println(" |     /|");
			System.out.println(" |");
			System.out.println(" |");
			System.out.println(" |");
			System.out.println("_|___");
			break;
		case 4:
			System.out.println("  -------");
			System.out.println(" |      |");
			System.out.println(" |      O");
			System.out.println(" |     /|\\");
			System.out.println(" |");
			System.out.println(" |");
			System.out.println(" |");
			System.out.println("_|___");
			break;
		case 5:
			System.out.println("  -------");
			System.out.println(" |      |");
			System.out.println(" |      O");
			System.out.println(" |     /|\\");
			System.out.println(" |     /");
			System.out.println(" |");
			System.out.println(" |");
			System.out.println("_|___");
			break;
		case 6: 
			System.out.println("  -------");
			System.out.println(" |      |");
			System.out.println(" |      O");
			System.out.println(" |     /|\\");
			System.out.println(" |     / \\");
			System.out.println(" |");
			System.out.println(" |");
			System.out.println("_|___");
			break;
		default:
			System.out.println("Упс... что-то посчитали неправильно.");
			break;
		}
	}
}
