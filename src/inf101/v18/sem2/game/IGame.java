package inf101.v18.sem2.game;

import inf101.v18.sem2.gui.IImage;
import inf101.v18.sem2.gui.IUserInterface;
import inf101.v18.sem2.grid.IPosition;

import java.util.List;

/**
 * Interface for brettspill som passer til brettspill-GUIen
 *
 * Spillene er ment å bli kontrollert av GameGUI-en, som kaller metodene
 * definert under basert på knappene som brukerne trykker på.
 *
 * En del av metodene styrer oppsettet til GameGUI-en ved å, f.eks., velge hvor
 * vidt enkelte knapper skal være skrudd på, eller hvilke menyvalg som finnes.
 *
 * @author Anya Helene Bagge
 *
 */
public interface IGame {

	/**
	 * @return True hvis spillet kan endre størrelse uten at man kaller
	 *         newGame() etterpå
	 */
	boolean canChangeSize();

	/**
	 * Returnerer brettstørrelse-valg til menyen.
	 *
	 * Hver størrelse må strenger være på formen "WxH", der W og H er heltall.
	 *
	 * @return En liste med brettstørrelser, eller null for å bruke standard
	 *         størrelser.
	 */
	List<String> getBoardSizes();

	/**
	 * @return Height of a game cell, in pixels. (>= 1)
	 */
	int getCellHeight();

	/**
	 * @return Width of a game cell, in pixels. (>= 1)
	 */
	int getCellWidth();

	/**
	 * @return Høyden på spillet, i felter. (>= 1)
	 */
	int getHeight();

	IImage getImageAt(IPosition pos);

	/**
	 * Egne menyvalg for spillet.
	 *
	 * Denne metoden skal returnere enten null, hvis spillet ikke skal ha sin
	 * egen meny, eller en liste med menyvalg for spillet.
	 *
	 * @return En liste med menyvalg til en egen spill-meny, eller null hvis
	 *         spillet ikke har sin egen meny.
	 */
	List<String> getMenuChoices();

	/**
	 * @return Navnet på spillet
	 */
	String getName();

	/**
	 * @return Bredden på spillet, i felter. (>= 1)
	 */
	int getWidth();

	/**
	 * Initialise the game board.
	 *
	 * Should set up the game logic as necessary to begin a new game.
	 *
	 * Any previous state is lost.
	 *
	 */
	void newGame();

	/**
	 * Kalles når spilleren har gjort et menyvalg i GameGUI-en.
	 *
	 * @param s
	 *            En streng som tidligere er returnert fra getMenuChoices()
	 * @throws UnsupportedOperationException
	 *             hvis spillet ikke har sin egen meny
	 */
	void setMenuChoice(String s);

	/**
	 * Sett størrelsen på spillet.
	 *
	 * MERK: hvis spillet av en eller annen grunn ikke kan ha den gitt
	 * bredden/høyden, blir den nye bredden/høyden uendret, eller et sted mellom
	 * den gamle og den ønskede nye bredden/høyden. Sjekk alltid getWidth() /
	 * getHeight() etter å ha kalt setSize().
	 *
	 * Det kan være nødvendig å kalle newGame() etterpå for at spillet skal
	 * virke – i såfall vil canChangeSize() returnere false.
	 *
	 * @param width
	 *            Ønsket ny bredde
	 * @param height
	 *            Ønsket ny høyde
	 * @throws IllegalArgumentException
	 *             hvis width eller height er mindre enn 1
	 */
	void setSize(int width, int height);

	/**
	 * Set up the game's connection to the user interface.
	 *
	 * This method should set up listeners to receive time and mouse click
	 * events, configure the user interface, and so on.
	 *
	 * {@link #newGame()} will be called after this method, so there is no need
	 * to set up game logic here.
	 *
	 * @param ui
	 *            A user interface
	 */
	void setup(IUserInterface ui);
	
	/*
	 * Check if chosen pos is valid, i.e. is there an empty slot to put the disc in
	 * 
	 * @param pos
	 * 		clicked IPosition in grid
	 * @return
	 * 		a value from -1 to but not including height of grid, -1 means that the move is not valid
	 * 		0 or more gives the Y-coordinate of where the disc should be placed
	 */
	public int validMove(IPosition pos);
	
	/*
	 * place a disc at given pos
	 * 
	 * @param pos
	 * 		IPosition in grid to place the disc
	 * @param player
	 * 		The player who wants to place a disc (use this param to determine which type of disc to place)
	 */
	public void placeDisc(IPosition pos, IPlayer player);
	

}
