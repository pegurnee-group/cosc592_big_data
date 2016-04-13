package src;
import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CodeBraceStyleMapper extends
		Mapper<LongWritable, Text, Text, StyleAnalysisObject> {
	
	@Override
	protected void map(
			LongWritable key,
			Text value,
			Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//		super.map(key, value, context);
		
		//please do java regex
		int numberCloseBraces = 0;
		int numberOpenBracesOnOwnLine = 0;
		
		//java regex magic -- dont need :P
		
	  Scanner in  = new Scanner(value.toString());
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
		
	  context.write(new Text("Java"), new StyleAnalysisObject(numberCloseBraces, numberOpenBracesOnOwnLine));
		
	}

}
