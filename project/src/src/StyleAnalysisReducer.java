package src;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StyleAnalysisReducer extends
		Reducer<Text, StyleAnalysisObject, Text, StyleAnalysisOutput> {

	@Override
	protected void reduce(Text key, Iterable<StyleAnalysisObject> values,
			Context context) throws IOException, InterruptedException {

		double percentage = 0.0, percentComment = 0.0;
		int sumOfOpening = 0, sumOfClosing = 0, count = 0;

		for (final StyleAnalysisObject value : values) {
			sumOfOpening += value.getNumberOpenBracesOnOwnLine();
			sumOfClosing += value.getNumberCloseBraces();
			percentComment += value.getNumberOfInlineComments();
			count++;
		}

		percentage = (sumOfOpening / (sumOfOpening + (double) sumOfClosing)) * 100.0;
		// percentComment /= count * 100.0;

		context.write(key, new StyleAnalysisOutput(sumOfOpening, sumOfClosing,
				percentComment));
	}

}
