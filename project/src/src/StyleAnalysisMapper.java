package src;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class StyleAnalysisMapper extends
		Mapper<LongWritable, Text, Text, StyleAnalysisObject> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		final FileSplit fileSplit = (FileSplit) context.getInputSplit();
		final String filename = fileSplit.getPath().getName();
		final String fileExt = filename.substring(filename.indexOf("."));

		int numberCloseBraces = 0;
		int numberOpenBracesOnOwnLine = 0;

		// java regex magic -- dont need :P

		final Scanner in = new Scanner(value.toString());
		int commentCounter = 0;

		while (in.hasNext()) {
			final String line = in.nextLine().trim();
			if (line.length() != 0) {
				if (line.charAt(0) == '{') {
					numberOpenBracesOnOwnLine++;
				} else if (line.charAt(0) == '}') {
					numberCloseBraces++;
				}
				if (line.indexOf("//") > -1) {
					commentCounter++;
				}
			}
		}

		context.write(new Text(fileExt), new StyleAnalysisObject(
				numberCloseBraces, numberOpenBracesOnOwnLine, commentCounter));

	}

}
