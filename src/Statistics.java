import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.time.Duration;
import java.io.*;

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
	
	class SkipPlayRatioCompare implements Comparator<MusicFile> {

		public SkipPlayRatioCompare() {};
		
		public int compare(MusicFile first, MusicFile that) {
			double thisRatio = 0, thatRatio = 0;
			if (first.getSkips() != 0) {
				thisRatio = first.getSkips() / first.getPlays();
			} else if (that.getSkips() == 0) {
				return 1;
			}
			if (that.getSkips() != 0) {
				thatRatio = that.getSkips() / that.getPlays();
			} else if (first.getSkips() == 0) {
				return -1;
			}
			if (first.getSkips() == 0 && that.getSkips() == 0) {
				return 0;
			}
			if (thisRatio > thatRatio) {
				return 1;
			} else if (thisRatio < thatRatio) {
				return -1;
			}
			return 0;
		}
	}
	
	public static void highestSkipToPlayRatio(HashMap<Integer, MusicFile> collection) {
		SkipPlayRatioCompare comparator = new SkipPlayRatioCompare();
		PriorityQueue sortList = new PriorityQueue<MusicFile>(collection.size(), new SkipPlayRatioCompare());
		for (MusicFile song : collection.values()) {
			
		}
		try {
			PrintWriter writer = new PrintWriter("Highest Skip to Play Ratio.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
	
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
				minutes.toMinutes() + " minutes " + seconds.toMillis()/1000 + " seconds of music listened to";
	}
	
	public static void main(String[] args) {
		//File input = new File("/Users/HansHalverson/Documents/workspace/MusicLibrary/src/iTunes Music Library.xml");
		File input = new File("/Users/HansHalverson/Music/iTunes/iTunes Music Library.xml");
		HashMap<Integer, MusicFile> library = XMLparser.parseXML(input);
		System.out.printf("%.2f%% percent of library played%n",percentListenedTo(library));
		System.out.println(totalTimeListeningToMusic(library));
	}

}
