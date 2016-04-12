import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.*;

public class StyleAnalysisReducer extends
		Reducer<CodeLanguage, StyleAnalysisObject, CodeLanguage, DoubleWritable > {

	 public void reduce(CodeLanguage key, Iterable<StyleAnalysisObject> values,
             Context context) throws IOException, InterruptedException {
		 
		 
		double percentage = 0.0;
		int sumOfOpening = 0, sumOfClosing = 0;
		
		for(StyleAnalysisObject value : values){
			
			
			String[] val = value.toString().split("+");
			sumOfOpening += Integer.parseInt(val[0]);
			sumOfClosing += Integer.parseInt(val[1]);
			
		}
		
		percentage = (sumOfOpening / (sumOfOpening + sumOfClosing)) * 100;
		
			 
	context.write(key, new DoubleWritable(percentage));
	 }
	
		 
	
	
}
