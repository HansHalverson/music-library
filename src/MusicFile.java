import java.time.*;

/**
 * Represents a song
 * 
 * @author HansHalverson
 */
public class MusicFile {
	
	private int trackID;
	private String name;
	private String artist;
	private String albumArtist;
	private String album;
	private String genre;
	private String format;
	private int size;
	private int time;
	private int plays;
	private int skips;
	private int discNumber;
	private int discCount;
	private int trackNumber;
	private int trackCount;
	private int year;
	private LocalDateTime dateAdded;
	private int bitRate;
	private LocalDateTime releaseDate;
	private String file;
	
	//All getters and setters:
	
	public MusicFile() {};
	
	public int getTrackID () {return trackID;}
	
	public void setTrackID (int id) {trackID = id;}
	
	public String getName () {return name;}
	
	public void setName (String n) {name = n;}
	
	public String getArtist () {return artist;}
	
	public void setArtist (String a) {artist = a;}
	
	public String getAlbumArtist () {return albumArtist;}
	
	public void setAlbumArtist (String a) {albumArtist = a;}
	
	public String getAlbum () {return album;}
	
	public void setAlbum (String a) {album = a;}
	
	public String getGenre () {return genre;}
	
	public void setGenre (String g) {genre = g;}
	
	public String getFormat () {return format;}
	
	public void setFormat (String f) {format = f;}
	
	public int getSize () {return size;}
	
	public void setSize (int s) {size = s;}
	
	public int getTime () {return time;}
	
	public void setTime (int t) {time = t;}
	
	public int getPlays () {return plays;}
	
	public void setPlays (int p) {plays = p;}
	
	public int getSkips () {return skips;}
	
	public void setSkips (int s) {skips = s;}
	
	public int getDiscNumber () {return discNumber;}
	
	public void setDiscNumber (int d) {discNumber = d;}
	
	public int getDiscCount () {return discCount;}
	
	public void setDiscCount (int d) {discCount = d;}
	
	public int getTrackNumber () {return trackNumber;}
	
	public void setTrackNumber (int t) {trackNumber = t;}
	
	public int getTrackCount () {return trackCount;}
	
	public void setTrackCount (int t) {trackCount = t;}
	
	public int getYear () {return year;}
	
	public void setYear (int y) {year = y;}
	
	public LocalDateTime getDateAdded() {return dateAdded;}
	
	public void setDateAdded(LocalDateTime d) {dateAdded = d;}
	
	public int getBitRate () {return bitRate;}
	
	public void setBitRate (int b) {bitRate = b;}
	
	public LocalDateTime getReleaseDate() {return releaseDate;}
	
	public void setReleaseDate(LocalDateTime r) {releaseDate = r;}
	
	public String getFile () {return file;}
	
	public void setFile (String f) {file = f;}
	
	/**
	 * Prints all music file information to the console, for testing purposes
	 */
	public void printMusicFile () {
		System.out.println("Track ID: " + getTrackID() +
						   "\nName: " + getName() +
				           "\nArtist: " + getArtist() +
						   "\nAlbum Artist: " + getAlbumArtist() +
				           "\nAlbum: " + getAlbum() +
						   "\nGenre: " + getGenre() +
						   "\nFormat: " + getFormat() +
						   "\nSize: " + getSize() +
						   "\nTime: " + getTime() +
						   "\nPlays: " + getPlays() +
						   "\nSkips: " + getSkips() +
						   "\nDisc Number: " + getDiscNumber() +
						   "\nDisc Count: " + getDiscCount() +
						   "\nTrack Number: " + getTrackNumber() +
						   "\nTrack Count: " + getTrackCount() +
						   "\nYear: " + getYear() +
						   "\nDate Added: " + getDateAdded() +
						   "\nBit Rate: " + getBitRate() +
						   "\nRelease Date: " + getReleaseDate() +
						   "\nFile: " + getFile());
	}
	
}
