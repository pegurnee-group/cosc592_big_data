package src;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class gitHubbing {

	 public static void main(String[] args) throws Exception {
		 if (args.length != 2) {
		      System.err.println("Usage: gitHubbing <input path> <output path>");
		      System.exit(-1);
		    }

		 @SuppressWarnings("deprecation")
		Job job = new Job();
		    job.setJarByClass(gitHubbing.class);
		    job.setJobName("gitHubbing");

		    FileInputFormat.addInputPath(job, new Path(args[0]));
		    FileOutputFormat.setOutputPath(job, new Path(args[1]));

		    job.setMapperClass(CodeBraceStyleMapper.class);
		    job.setReducerClass(StyleAnalysisReducer.class);

		    job.setOutputKeyClass(LongWritable.class);
		    job.setOutputValueClass(Text.class);

		    System.exit(job.waitForCompletion(true) ? 0 : 1);
	 	   }

}
