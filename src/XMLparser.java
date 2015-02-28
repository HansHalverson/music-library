import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XMLparser {
	public static void main(String[] args) {
		File input = new File("/Users/HansHalverson/Music/iTunes/iTunes Music Library.xml");
		HashMap<Integer, MusicFile> library;
		library = parseXML(input);
		library.get(1837).printMusicFile();;
	}
	
	public static HashMap<Integer, MusicFile> parseXML(File input) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setIgnoringElementContentWhitespace(true);
		HashMap<Integer, MusicFile> library = new HashMap<Integer, MusicFile>();
		try {
			DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(input);
			Element root = doc.getDocumentElement();
			Node tempRoot = root.getFirstChild();
			NodeList trackDict = tempRoot.getChildNodes().item(17).getChildNodes();
			for (int i = 0; i < trackDict.getLength(); i += 2) {
				int songID = Integer.parseInt(trackDict.item(i).getFirstChild().getNodeValue());
				MusicFile song = new MusicFile();
				NodeList songProperties = trackDict.item(i + 1).getChildNodes();
				for (int j = 0; j < songProperties.getLength(); j +=2 ) {
					String att = songProperties.item(j).getFirstChild().getNodeValue();
					String value = "";
					if (songProperties.item(j + 1).hasChildNodes()) {
						value = songProperties.item(j + 1).getFirstChild().getNodeValue();
					}
					switch (att) {
					case "Track ID": 
						song.setTrackID(Integer.parseInt(value));
						break;
					case "Name": 
						song.setName(value);
						break;
					case "Artist": 
						song.setArtist(value);
	 			   		break;
					case "Album Artist": 
						song.setAlbumArtist(value);
	 				    break;
					case "Album": 
						song.setAlbum(value);
						break;
					case "Genre": 
						song.setGenre(value);
						break;
					case "Kind": 
						song.setFormat(value);
						break;
					case "Size": 
						song.setSize(Integer.parseInt(value));
						break;
					case "Total Time": 
						song.setTime(Integer.parseInt(value));
						break;
					case "Play Count": 
						song.setPlays(Integer.parseInt(value));
						break;
					case "Skip Count":  
						song.setPlays(Integer.parseInt(value));
						break;
					case "Disc Number": 
						song.setDiscNumber(Integer.parseInt(value));
	 					break;
					case "Disc Count": 
						song.setDiscCount(Integer.parseInt(value));
						break;
					case "Track Number": 
						song.setTrackNumber(Integer.parseInt(value));
	 					break;
					case "Track Count": 
						song.setTrackCount(Integer.parseInt(value));
	 					break;
					case "Year": 
						song.setYear(Integer.parseInt(value));
						break;
					case "Date Added": 
						song.setDateAdded(java.time.LocalDateTime.parse(value.substring(0, 19)));
						break;
					case "Bit Rate": 
						song.setBitRate(Integer.parseInt(value));
						break;
					case "Release Date": 
						song.setReleaseDate(java.time.LocalDateTime.parse(value.substring(0, 19)));
						break;
					case "Location": 
						song.setFile(value);
						break;
					}
					library.put(songID, song);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return library;
	}
	
	/*
	public static HashMap<Integer, MusicFile> parseXML(File input) {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		HashMap<Integer, MusicFile> library = new HashMap<Integer, MusicFile>();
		try {
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(input);
			doc.normalize();
			Element root = doc.getDocumentElement();
			Node tempRoot = root.getChildNodes().item(1);
			NodeList trackDict = tempRoot.getChildNodes().item(27).getChildNodes();
			for (int i = 1; i < trackDict.getLength(); i += 4) {
				int songID = Integer.parseInt(trackDict.item(i).getFirstChild().getNodeValue());
				MusicFile song = new MusicFile();
				NodeList songProperties = trackDict.item(i + 2).getChildNodes();
				for (int j = 1; j < songProperties.getLength(); j+= 3) {
					String att = songProperties.item(j).getFirstChild().getNodeValue();
					String value = "";
					if (songProperties.item(j + 1).hasChildNodes()) {
						value = songProperties.item(j + 1).getFirstChild().getNodeValue();
					}
					switch (att) {
					case "Track ID": 
						song.setTrackID(Integer.parseInt(value));
						break;
					case "Name": 
						song.setName(value);
						break;
					case "Artist": 
						song.setArtist(value);
	 			   		break;
					case "Album Artist": 
						song.setAlbumArtist(value);
	 				    break;
					case "Album": 
						song.setAlbum(value);
						break;
					case "Genre": 
						song.setGenre(value);
						break;
					case "Kind": 
						song.setFormat(value);
						break;
					case "Size": 
						song.setSize(Integer.parseInt(value));
						break;
					case "Total Time": 
						song.setTime(Integer.parseInt(value));
						break;
					case "Play Count": 
						song.setPlays(Integer.parseInt(value));
						break;
					case "Skip Count":  
						song.setPlays(Integer.parseInt(value));
						break;
					case "Disc Number": 
						song.setDiscNumber(Integer.parseInt(value));
	 					break;
					case "Disc Count": 
						song.setDiscCount(Integer.parseInt(value));
						break;
					case "Track Number": 
						song.setTrackNumber(Integer.parseInt(value));
	 					break;
					case "Track Count": 
						song.setTrackCount(Integer.parseInt(value));
	 					break;
					case "Year": 
						song.setYear(Integer.parseInt(value));
						break;
					case "Date Added": 
						song.setDateAdded(java.time.LocalDateTime.parse(value.substring(0, 19)));
						break;
					case "Bit Rate": 
						song.setBitRate(Integer.parseInt(value));
						break;
					case "Release Date": 
						song.setReleaseDate(java.time.LocalDateTime.parse(value.substring(0, 19)));
						break;
					case "Location": 
						song.setFile(value);
						break;
					}
				}
				library.put(songID, song);
			}
		} catch (ParserConfigurationException | IOException | SAXException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return library;
	} */
}
