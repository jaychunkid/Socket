package socket;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//负责读取图鉴文件
class FileScanner {

	private File pokedex = new File("d:/pokemon.txt");
	private Scanner scanner;

	public ArrayList<Pokemon> start() throws FileNotFoundException {
		scanner = new Scanner(pokedex);
		ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
		//读取文件内容并建立一个Pokemon类的ArrayList
		while (scanner.hasNext()) {
			String id = scanner.next();
			String name = scanner.next();
			String type = scanner.next();
			String evolution = scanner.next();
			String evoluteWay = scanner.next();
			String nextEvolution = scanner.next();
			pokemonList.add(new Pokemon(id, name, type, evolution, evoluteWay, nextEvolution));
		}
		return pokemonList;
	}
}