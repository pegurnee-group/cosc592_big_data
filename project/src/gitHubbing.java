import java.util.Map;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;



public class gitHubbing {

	 public static void main(String[] args) throws Exception {
			     JobConf conf = new JobConf(CodeBraceStyleMapper.class);
		  	     conf.setJobName("githubbing");
		 	
		 	     conf.setOutputKeyClass(Text.class);
			     conf.setOutputValueClass(IntWritable.class);
		 	
		 	     conf.setMapperClass((Class<? extends Mapper>) CodeBraceStyleMapper.class);
		 	     conf.setReducerClass((Class<? extends Reducer>) StyleAnalysisReducer.class);
		  	
		 	     conf.setInputFormat(TextInputFormat.class);
		 	     conf.setOutputFormat(TextOutputFormat.class);
		 	
	 	     FileInputFormat.setInputPaths(conf, new Path(args[0]));
		 	     FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		 	
		 	     
		 	     JobClient.runJob(conf);
	 	   }

}
