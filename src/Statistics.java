import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.time.Duration;
import java.io.*;

/**
 * Contains methods used to analyze listening habits
 * 
 * @author HansHalverson
 */
public class Statistics {
	public static double percentListenedTo(HashMap<Integer, MusicFile> collection) {
		int numListenedTo = 0;
		int numTotal = 0;
		for (MusicFile song : collection.values()) {
			numTotal++;
			if (song.getPlays() > 0) {
				numListenedTo++;
			}
		}
		return (float) numListenedTo / numTotal * 100;
	}
	
	/**
	 * Comparator used to order songs by ratio of skips to plays
	 */
	private static class SkipPlayRatioComparator implements Comparator<MusicFile> {
		
		@Override
		public int compare(MusicFile song1, MusicFile song2) {
			double ratio1 = 0;
			double ratio2 = 0;
			if (song1.getPlays() != 0) {
				ratio1 = song1.getSkips() / song1.getPlays();
			}
			if (song2.getPlays() != 0) {
				ratio2 = song2.getSkips() / song2.getPlays();
			}
			if (ratio1 > ratio2) {
				return -1;
			} else if (ratio1 < ratio2) {
				return 1;
			} else {
				if (song1.getPlays() > song2.getPlays()) {
					return -1;
				} else if (song1.getPlays() < song2.getPlays()) {
					return 1;
				}
				return 0;
			}
		}
	}
	
	/**
	 * Writes a text file containing all songs sorted by their skip to play ratio
	 * 
	 * @param collection - the collection of songs to be sorted
	 */
	public static void highestSkipToPlayRatio(HashMap<Integer, MusicFile> collection) {
		MusicFile[] songList = new MusicFile[collection.size()];
		collection.values().toArray(songList);
		Arrays.sort(songList, new SkipPlayRatioComparator());
		try {
			BufferedWriter outStream = new BufferedWriter(new FileWriter("Skip to Play Ratio.txt"));
			int i = 1;
			for (MusicFile song : songList) {
				StringBuilder songInfo = new StringBuilder(String.valueOf(i)).append(". ");
				songInfo.append(song.getName()).append(" - ").append(song.getArtist()).append("     ");
				songInfo.append(song.getSkips()).append("/").append(song.getPlays());
				outStream.write(songInfo.toString());
				outStream.newLine();
				i++;
			}
			outStream.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
	
	/**
	 * Comparator used to order songs by the total time listened to them
	 */
	private static class TotalSongTimeComparator implements Comparator<MusicFile> {
		
		@Override
		public int compare(MusicFile song1, MusicFile song2) {
			int length1 = song1.getPlays() * song1.getTime();
			int length2 = song2.getPlays() * song2.getTime();
			if (length1 > length2) {
				return -1;
			} else if (length1 < length2) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}
	
	/**
	 * Writes a text file containing all songs sorted by the total time spent
	 * listening to them
	 * 
	 * @param collection - the collection of songs to be sorted
	 */
	public static void totalTimePerSong(HashMap<Integer, MusicFile> collection) {
		MusicFile[] songList = new MusicFile[collection.size()];
		collection.values().toArray(songList);
		Arrays.sort(songList, new TotalSongTimeComparator());
		try {
			BufferedWriter outStream = new BufferedWriter(new FileWriter("Total Time Per Song.txt"));
			int i = 1;
			for (MusicFile song : songList) {
				Duration totalTime = Duration.ofMillis(song.getTime() * song.getPlays());
				Duration minutes = totalTime.minusHours(totalTime.toHours());
				Duration seconds = minutes.minusMinutes(minutes.toMinutes());
				StringBuilder songInfo = new StringBuilder(String.valueOf(i)).append(". ");
				songInfo.append(song.getName()).append(" - ").append(song.getArtist());
				songInfo.append(" - ").append(totalTime.toHours());
				songInfo.append(" Hours ").append(minutes.toMinutes()).append(" Minutes ");
				songInfo.append(seconds.toMillis() / 1000).append(" Seconds");
				outStream.write(songInfo.toString());
				outStream.newLine();
				i++;
			}
			outStream.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
	
	/**
	 * Writes a text file containing all artists ordered by the total time spent
	 * listening to them
	 * 
	 * @param collection - the collection of songs to be sorted
	 */
	public static void totalTimePerArtist(HashMap<Integer, MusicFile> collection) {
		HashMap<String, Integer> artists = new HashMap<String, Integer>();
		for (MusicFile song : collection.values()) {
			if (collection.containsKey(song.getArtist())) {
				int newTime = artists.get(song.getArtist()) + song.getTime() * song.getPlays();
				artists.put(song.getArtist(), newTime);
			} else {
				artists.put(song.getArtist(), song.getTime() * song.getPlays());
			}
		}
		
	}
	
	/**
	 * Finds the total time spent listening to music in the provided collection
	 * 
	 * @param collection - the collection of songs to be analyzed
	 * @return a string representing the total time spent listening to music
	 */
	public static String totalTimeListeningToMusic(HashMap<Integer, MusicFile> collection) {
		long totalTime = 0;
		for (MusicFile song : collection.values()) {
			totalTime += song.getPlays() * song.getTime();
		}
		Duration time = Duration.ofMillis(totalTime);
		Duration hours = time.minusDays(time.toDays());
		Duration minutes = hours.minusHours(hours.toHours());
		Duration seconds = minutes.minusMinutes(minutes.toMinutes());
		return time.toDays() + " days " + hours.toHours() + " hours " + 
				minutes.toMinutes() + " minutes " + seconds.toMillis()/1000 + 
				" seconds of music listened to";
	}
	
	public static void main(String[] args) {
		//File input = new File("/Users/HansHalverson/Documents/workspace/MusicLibrary/src/iTunes Music Library.xml");
		File input = new File("/Users/HansHalverson/Music/iTunes/iTunes Music Library.xml");
		HashMap<Integer, MusicFile> library = XMLparser.parseXML(input);
		System.out.printf("%.2f%% percent of library played%n",percentListenedTo(library));
		System.out.println(totalTimeListeningToMusic(library));
		highestSkipToPlayRatio(library);
		totalTimePerSong(library);
	}

}
