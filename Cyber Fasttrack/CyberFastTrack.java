import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CyberFastTrack {
	
	static String combined = "";

	public static void main(String[] args) {
		getLetters("https://assess.cyber-fasttrack.org/challenge-files/clock-pt1?verify=JHyGJkhlaYeeHagix6us1g%3D%3D");
		getLetters("https://assess.cyber-fasttrack.org/challenge-files/clock-pt2?verify=JHyGJkhlaYeeHagix6us1g%3D%3D");
		getLetters("https://assess.cyber-fasttrack.org/challenge-files/clock-pt3?verify=JHyGJkhlaYeeHagix6us1g%3D%3D");
		getLetters("https://assess.cyber-fasttrack.org/challenge-files/clock-pt4?verify=JHyGJkhlaYeeHagix6us1g%3D%3D");
		getLetters("https://assess.cyber-fasttrack.org/challenge-files/clock-pt5?verify=JHyGJkhlaYeeHagix6us1g%3D%3D");
		
		System.out.println(combined);
		
		System.out.println();
		
		getFlag();
	}

	public static void getLetters(String URL) {

		try {
			Document doc = Jsoup.connect(URL).get();
			Elements temp = doc.select("body");
			//System.out.println(temp.text());
			combined += temp.text();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getFlag() {

		try {
			Document doc = Jsoup.connect("https://assess.cyber-fasttrack.org/challenge-files/get-flag?verify=JHyGJkhlaYeeHagix6us1g%3D%3D&string=" + combined).get();
			Elements temp = doc.select("body");
			System.out.println(temp.text());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

