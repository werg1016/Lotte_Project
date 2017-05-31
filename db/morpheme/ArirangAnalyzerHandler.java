package morpheme;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
import org.apache.lucene.analysis.ko.morph.AnalysisOutput;
import org.apache.lucene.analysis.ko.morph.CompoundEntry;
import org.apache.lucene.analysis.ko.morph.CompoundNounAnalyzer;
import org.apache.lucene.analysis.ko.morph.MorphAnalyzer;
import org.apache.lucene.analysis.ko.morph.MorphException;
import org.apache.lucene.analysis.ko.morph.WordSegmentAnalyzer;
 
/** 
* @FileName    : ArirangAnalyzerHandler.java 
* @Project     : search-core 
* @Date        : 2015. 8. 17. 
* @작성자                   :    Yujoo 
* @변경이력                  :  아리랑 형태소 분석기 라이브러리를 사용하여, 형태소 분석을 하는데, 필요한 기능들을 다루는 클레스
* @프로그램 설명       : 
*/
public class ArirangAnalyzerHandler {
    /** 
    * @Method Name : morphAnalyze 
    * @변경이력              : 
    * @Method 설명     : 형태소 분석
    * @param source
    * @return
    * @throws MorphException 
    */
    public String morphAnalyze(String source) throws MorphException {
        MorphAnalyzer maAnal = new MorphAnalyzer(); // 형태소 분석기 
         
        StringBuilder result = new StringBuilder();
         
        StringTokenizer stok = new StringTokenizer(source, " ");
        while (stok.hasMoreTokens()) {
            String token = stok.nextToken();
            List<AnalysisOutput> outList = maAnal.analyze(token);
            for (AnalysisOutput o : outList) {
                result.append(o).append(" ");
            }
        }
        return result.toString();
    }

     
    /** 
    * @Method Name : extractNoun 
    * @변경이력              : 
    * @Method 설명     : 명사 추출
    * @param searchQuery
    * @return
    * @throws MorphException 
    */
    public ArrayList<String> extractNoun(String searchQuery) throws MorphException{
        ArrayList<String> nounList = new ArrayList<String>();       
         
        MorphAnalyzer maAnal = new MorphAnalyzer(); // 형태소 분석기 
        StringTokenizer stok = new StringTokenizer(searchQuery, " "); // 쿼리문을 뛰어쓰기 기준으로 토큰화
         
        // 색인어 분석기를 통해 토큰에서 색인어 추출
        while (stok.hasMoreTokens()) {
            String token = stok.nextToken();
             
            // 형태소 분석
            List<AnalysisOutput> indexList = maAnal.analyze(token);
             
            for (AnalysisOutput morpheme : indexList) 
                // 명사 추출 
                if(morpheme.getPos() == 'N')
                    nounList.add(morpheme.getStem());
        }
         
        return nounList;
    }
}