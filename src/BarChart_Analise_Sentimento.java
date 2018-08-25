import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart_Analise_Sentimento extends ApplicationFrame {
   
   public BarChart_Analise_Sentimento( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Category",            
         "Classificação",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset( ) {
      final String coreNLP = "CoreNLP";        
      final String gm = "GM";        
      final String real = "Real";               
      final String negative = "Negative";        
      final String positive = "Positive";        
      final String neutral = "Neutral";        
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      int quantNegativeNossa = 0;
      int quantPositiveNossa = 0;
      int quantNeutralNossa = 0;
      int quantNegativeCore = 0;
      int quantPositiveCore = 0;
      int quantNeutralCore = 0;
      
      for(int i=0; i<nossaAnalise.size();i++){
    	  if(nossaAnalise.get(i).equals(20)){
    		  quantNegativeNossa++;
    	  }else if(nossaAnalise.get(i).equals(21)){
    		  quantPositiveNossa++;
    	  }else if(nossaAnalise.get(i).equals(10)){
    		  quantNeutralNossa++;
    	  }
      }
      
      for(int i=0; i<coreAnalise.size();i++){
    	  if(coreAnalise.get(i).equals(20)){
    		  quantNegativeCore++;
    		  
    	  }else if(coreAnalise.get(i).equals(21)){
    		  quantPositiveCore++;
    	  }else if(coreAnalise.get(i).equals(10)){
    		  quantNeutralCore++;
    	  }
      }
      System.out.println("Core Neg: "+quantNegativeCore);
      System.out.println("Core Pos: "+quantPositiveCore);
      System.out.println("Core Neutro: "+quantNeutralCore); 
      System.out.println("Nosso Neg: "+quantNegativeNossa);
      System.out.println("Nosso Pos: "+quantPositiveNossa);
      System.out.println("Nosso Neutro: "+quantNeutralNossa); 
      
      
      
      dataset.addValue( quantNegativeCore  , coreNLP , negative ); 
      dataset.addValue( 30 , real , negative ); 
      dataset.addValue( quantNegativeNossa , gm , negative);  
      
      dataset.addValue( quantNeutralCore , coreNLP , neutral );     
      dataset.addValue( quantNeutralNossa , gm , neutral ); 
      dataset.addValue( 5 , real , neutral);   
      
        
      dataset.addValue( quantPositiveCore , coreNLP , positive);     
      dataset.addValue( quantPositiveNossa , gm , positive); 
      dataset.addValue( 5 , real , positive);   
      
     
    
      return dataset; 
   }
   static ArrayList nossaAnalise;
   static ArrayList coreAnalise;
   
   public static void main( String[ ] args ) throws IOException {
	   AnaliseSentimento nossa = new AnaliseSentimento();
	   nossaAnalise = nossa.NossaAnalise();
	   CoreNLP core = new CoreNLP();
	   coreAnalise = core.CoreAnalise();
		
	   BarChart_Analise_Sentimento chart = new BarChart_Analise_Sentimento("Resultados Análise de Sentimento", 
         "Algoritmos");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}