package morpheme;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.ko.morph.MorphException;
 
public class StoSMain {
    public static void main(String[] args) throws MorphException, IOException {
    	
    	
    	String content="";
    	for (String string : args) {
            content = content + string +" ";
        }

    	String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
    	content = content.replaceAll(match, " ");
    			// 형태소 분석
        ArirangAnalyzerHandler aah = new ArirangAnalyzerHandler();

        ArrayList<String> nuonList = aah.extractNoun(content);
        for (String string : nuonList) {
            System.out.print(string + " ");
        }
    }
}