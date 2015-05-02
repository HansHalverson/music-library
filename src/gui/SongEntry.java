package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import data.MusicFile;

public class SongEntry {
	
	private SimpleStringProperty name;
	private SimpleStringProperty time;
	private SimpleIntegerProperty plays;
	private String file;
	
	public SongEntry(MusicFile song) {
		this.name = new SimpleStringProperty(song.getName());
		int millis = song.getTime();
		int min = millis / 60000;
		int sec = Math.round((float) (millis - (min * 60000)) / 1000 );
		this.time = new SimpleStringProperty(String.valueOf(min) + ":" + String.valueOf(sec));
		this.plays = new SimpleIntegerProperty(song.getPlays());
		this.file = song.getFile();
	}
	
	public SimpleStringProperty getName() {return name;}
	
	public SimpleStringProperty getTime() {return time;}
	
	public SimpleIntegerProperty getPlays() {return plays;}
	
	public String getFile() {return file;}
	
}
