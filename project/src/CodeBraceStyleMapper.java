import java.io.IOException;

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
		
		//java regex magic
		
		context.write(key, new StyleAnalysisObject(numberCloseBraces, numberOpenBracesOnOwnLine));
		
	}

}
