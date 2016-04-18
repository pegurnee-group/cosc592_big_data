package src;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class StyleAnalysisMapper extends
		Mapper<LongWritable, Text, StyleAnalysisKey, StyleAnalysisObject> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		final FileSplit fileSplit = (FileSplit) context.getInputSplit();
		final Path path = fileSplit.getPath();

		final String filename = path.getName();
		final String fileExt = filename.substring(filename.indexOf(".") + 1);
		final int repoNum = Integer.parseInt(path.getParent().getName());

		int numberCloseBraces = 0;
		int numberOpenBracesOnOwnLine = 0;

		// java regex magic -- dont need :P

		final Scanner in = new Scanner(value.toString());
		int commentCounter = 0;

		while (in.hasNext()) {
			final String nextLine = in.nextLine();
			final String line = nextLine.trim();
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

		final StyleAnalysisObject styleAnalysisObject = new StyleAnalysisObject(
				numberCloseBraces, numberOpenBracesOnOwnLine, commentCounter);

		context.write(new StyleAnalysisKey(fileExt, filename, repoNum),
				styleAnalysisObject);

		in.close();
	}
}
