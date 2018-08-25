import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnaliseSentimento {
	private static ArrayList<Dicionario> adjetivos = new ArrayList<Dicionario>();
	
	public ArrayList NossaAnalise() throws IOException{
		treinar();		
		BufferedReader br = new BufferedReader(new FileReader("teste.txt"));
		ArrayList<Integer> resultados = new ArrayList<Integer>();
		while(br.ready()){
		
			String linha = br.readLine();
			String[] palavras = linha.split("\\s+");
			Dicionario[] adjetivosArray = new Dicionario[adjetivos.size()];
			adjetivos.toArray(adjetivosArray);	
			int score = 0;
		    
		for(int i=0; i<palavras.length;i++){
			
				if(palavras[0].toLowerCase().contains("not") || palavras[0].toLowerCase().contains("no") || palavras[0].toLowerCase().contains("dont") || palavras[0].toLowerCase().contains("don't")){
					score-=10;
					break;
				}else if(palavras[0].toLowerCase().contains("yes") || palavras[0].toLowerCase().contains("ya") || palavras[0].toLowerCase().contains("sure") || palavras[1].toLowerCase().contains("yes") || palavras[1].toLowerCase().contains("ya") || palavras[1].toLowerCase().contains("sure")){
					score+=10;
					break;
				}else{
				
			for(int j=0; j<adjetivos.size();j++){
				if(i > 2){
					if(palavras[i].toLowerCase().contains(adjetivosArray[j].adjetivo)){					
						if(!((palavras[i-2].contains("not")) || palavras[i-1].contains("not"))){         //inverte valor se not
							score+=(adjetivosArray[j].valor);
					}else if(palavras[i-2].toLowerCase().contains("not") && palavras[i].toLowerCase().contains(adjetivosArray[j].adjetivo)){
						score+=((adjetivosArray[j].valor*(-1))*2);
					}else{
						score+=(adjetivosArray[j].valor*(-1));
					}
				}					
			}
		}
		}	
		}	
		if(score>0){
			resultados.add(21);
			System.out.println("Sentimento Positivo "+linha);
		}else if(score<0){
			resultados.add(20);
			System.out.println("Sentimento Negativo "+linha);
		}else{
			resultados.add(10);
			System.out.println("Sentimento Neutro "+linha);
			}
		}		
		br.close();
		return resultados;
}		
	public static void treinar(){
		//GOD WORDS
		adjetivos.add(new Dicionario("better", 1));
		adjetivos.add(new Dicionario("content", 1));
		adjetivos.add(new Dicionario("enjoy", 1));
		adjetivos.add(new Dicionario("glad", 1));
		adjetivos.add(new Dicionario("good",1));
		adjetivos.add(new Dicionario("great", 1));
		adjetivos.add(new Dicionario("happy", 1));
		adjetivos.add(new Dicionario("intelligent", 1));
		adjetivos.add(new Dicionario("love", 1));
		adjetivos.add(new Dicionario("best", 1));
		adjetivos.add(new Dicionario("nice", 1));
		adjetivos.add(new Dicionario("brilliant", 1));
		adjetivos.add(new Dicionario("courageous", 1));
		adjetivos.add(new Dicionario("efficient", 1));
		adjetivos.add(new Dicionario("excellent", 1));
		adjetivos.add(new Dicionario("fabulous", 1));
		adjetivos.add(new Dicionario("honest", 1));
		adjetivos.add(new Dicionario("pleasant", 1));
		adjetivos.add(new Dicionario("powerful", 1));
		adjetivos.add(new Dicionario("successful", 1));
		
		//BAD WORDS
		adjetivos.add(new Dicionario("bad", -1));
		adjetivos.add(new Dicionario("disgraceful", -1));
		adjetivos.add(new Dicionario("feeble",-1));
		adjetivos.add(new Dicionario("liar", -1));
		adjetivos.add(new Dicionario("sad", -1));
		adjetivos.add(new Dicionario("against", -1));
		adjetivos.add(new Dicionario("feeble", -1));
		adjetivos.add(new Dicionario("dumb", -1));
		adjetivos.add(new Dicionario("angry", -1));
		adjetivos.add(new Dicionario("alarming", -1));
		adjetivos.add(new Dicionario("awful", -1));
		adjetivos.add(new Dicionario("boring", -1));
		adjetivos.add(new Dicionario("broken", -1));
		adjetivos.add(new Dicionario("can't", -1));
		adjetivos.add(new Dicionario("corrupt", -1));
		adjetivos.add(new Dicionario("creepy", -1));
		adjetivos.add(new Dicionario("criminal", -1));
		adjetivos.add(new Dicionario("deny", -1));
		adjetivos.add(new Dicionario("disgusting", -1));
		adjetivos.add(new Dicionario("fail", -1));
		adjetivos.add(new Dicionario("naive", -1));
		adjetivos.add(new Dicionario("no", -1));
		adjetivos.add(new Dicionario("stupid", -1));
		adjetivos.add(new Dicionario("worthless", -1));
		adjetivos.add(new Dicionario("guilty", -1));
		//YES AND NO
	}	
}
