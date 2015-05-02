package data;

import java.util.ArrayList;

public class Genre {
	
	private String name;
	private ArrayList<Genre> subgenres;
	private ArrayList<Album> albums;
	
	public Genre(String name) {
		this.name = name;
		subgenres = new ArrayList<Genre>();
		albums = new ArrayList<Album>();
	}
	
	public String getName() {return name;}
	
	public ArrayList<Genre> getSubgenres() {return subgenres;}
		
	public void addSubgenre(Genre genre) {
		subgenres.add(genre);
	}
	
	public ArrayList<Album> getAlbums() {return albums;}
	
	public void addAlbum(Album album) {
		albums.add(album);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(name + "\n");
		for (Genre subgenre : subgenres) {
			builder.append(subgenre.toString());
		}
		return builder.toString();
	}
}
