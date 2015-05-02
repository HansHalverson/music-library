package scrapers;

import data.Genre;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenreScraper {
	
	private static HashMap<String, Genre> allGenres = new HashMap<String, Genre>();
		
	public static Genre makeGenreWithSubgenres(Element element) {
		String name = element.child(0).child(0).text();
		Genre newGenre = new Genre(name);
		Element subgenreBlock = element.select("blockquote").get(0);
		for (Element block : subgenreBlock.children()) {
			if (block.className().equals("genre")) {
				if (allGenres.containsKey(block.text())) {
					newGenre.addSubgenre(allGenres.get(block.text()));
				} else {
					Genre subgenre = new Genre(block.text());
					newGenre.addSubgenre(subgenre);
					allGenres.put(block.text(), subgenre);
				}
			} else if (block.tagName().equals("div")) {
				if (block.children().size() > 0 && block.child(0).tagName().equals("b")) {
					if (allGenres.containsKey(block.text())) {
						newGenre.addSubgenre(allGenres.get(block.text()));
					} else {
						Genre subgenre = makeGenreWithSubgenres(block);
						newGenre.addSubgenre(subgenre);
						allGenres.put(block.text(), subgenre);
					}
				}
			}
		}
		return newGenre;
	}
	
	public static ArrayList<Genre> getCurrentGenres() throws IOException {
		File input = new File("genres.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://rateyourmusic.com");
		ArrayList<Genre> genres = new ArrayList<Genre>();
		Elements contentElements = doc.select("#content > div");
		for (int i = 4; i < contentElements.size() - 3; i += 2) {
			Element section = contentElements.get(i);
			genres.add(makeGenreWithSubgenres(section));
		}
		FileWriter writer = new FileWriter("genres.txt");
		for (Genre genre : genres) {
			writer.write(genre + "\n");
		}
		writer.close();
		return genres;
	}
	
	public static void main(String[] args) {
		try {
			ArrayList<Genre> genres = getCurrentGenres();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
