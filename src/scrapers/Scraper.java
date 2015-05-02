package scrapers;

import java.util.regex.*;
import java.net.*;
import java.io.*;

public class Scraper {
	
	public static void main(String[] args) {
		String s = "<head></head><a class=\"genre\">West Coast Hip Hop</a><a class=\"genre\">Conscious Hip Hop</a>";
		try {
			URL url = new URL("http://rateyourmusic.com/release/album/arcade_fire/funeral");
			URLConnection connection = url.openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = reader.readLine();
			StringBuilder builder = new StringBuilder(line);
			while (line != null) {
				line = reader.readLine();
				builder.append(line);
			}
			String html = builder.toString();
			Pattern p = Pattern.compile("(?:class=\"genre\" href=\"[/+A-Za-z0-9]+\").*?</a>");
			Matcher matcher = p.matcher(html);
			System.out.println(line);
			while(!matcher.hitEnd()) {
				if (matcher.find()) {
					System.out.println(matcher.group());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
