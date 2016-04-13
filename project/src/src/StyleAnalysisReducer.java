package src;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StyleAnalysisReducer extends
		Reducer<Text, StyleAnalysisObject, Text, DoubleWritable> {

	@Override
	protected void reduce(Text key, Iterable<StyleAnalysisObject> values,
			Context context) throws IOException, InterruptedException {

		double percentage = 0.0;
		int sumOfOpening = 0, sumOfClosing = 0;

		for (StyleAnalysisObject value : values) {
			sumOfOpening += value.getNumberOpenBracesOnOwnLine();
			sumOfClosing += value.getNumberCloseBraces();

		}

		percentage = (sumOfOpening / (sumOfOpening + (double)sumOfClosing)) * 100.0;

		context.write(key, new DoubleWritable(percentage));
	}

}
