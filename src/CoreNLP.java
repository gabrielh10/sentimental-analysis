import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

public class CoreNLP {
		public ArrayList CoreAnalise() throws IOException{
        String text = "This place is Amazing";
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
        props.setProperty("ssplit.eolonly", "true");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        
        ArrayList<Integer> resultados = new ArrayList<Integer>();
		BufferedReader br = new BufferedReader(new FileReader("teste.txt"));
		while(br.ready()){
			String linha = br.readLine();
			Annotation annotation = pipeline.process(linha);
			List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
			for (CoreMap sentence : sentences) {
				String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
			//	String sentiment = annotation.get(SentimentCoreAnnotations.SentimentClass.class);
				if(sentiment.contains("Positive") || sentiment.contains("Very positive")) resultados.add(21);
				else if(sentiment.contains("Neutral")) resultados.add(10);
				else if(sentiment.contains("Negative") || sentiment.contains("Very negative")) resultados.add(20);
				System.out.println(sentiment + "\t" + sentence);
//				System.out.println(sentiment + "\t" + linha);
        
        	}
		}
		br.close();
		return resultados;
    }
} 