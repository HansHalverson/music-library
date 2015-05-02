package data;

import java.util.ArrayList;

public class Album {

	private String name;
	private ArrayList<MusicFile> songs;
	
	public Album(String name) {
		this.name = name;
		this.songs = new ArrayList<MusicFile>();
	}
	
	public String getName() {return name;}
	
	public ArrayList<MusicFile> getSongs() {return songs;}
	
	public void addSong(MusicFile song) {
		songs.add(song);
	}
	
}
