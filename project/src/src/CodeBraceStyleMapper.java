package src;
import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.*;

public class CodeBraceStyleMapper extends
		Mapper<CodeLanguage, Text, CodeLanguage, StyleAnalysisObject> {
	
	@Override
	protected void map(
			CodeLanguage key,
			Text value,
			Mapper<CodeLanguage, Text, CodeLanguage, StyleAnalysisObject>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.map(key, value, context);
		
		//please do java regex
		int numberCloseBraces = 0;
		int numberOpenBracesOnOwnLine = 0;
		
		//java regex magic -- dont need :P
		
	  Scanner in  = new Scanner((Readable) value);
	  int commentCounter = 0;
	  while(in.hasNext()){
		  String line = in.nextLine().trim();
		  if(line.length() != 0){
			  if(line.charAt(0) == '{'){
				  numberOpenBracesOnOwnLine++; 
			  } else if(line.charAt(0) == '}'){
				  numberCloseBraces++;
			  } 
			  if(line.indexOf("//") > -1){
				  commentCounter++;
			  }
			
		  }
  
	  }
		
	  context.write(key, new StyleAnalysisObject(numberCloseBraces, numberOpenBracesOnOwnLine));
		
	}

}
