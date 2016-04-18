package src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class StyleAnalysisOutput implements Writable {
	private double numberCloseBraces;
	private double numberOfInlineComments;
	private double numberOpenBracesOnOwnLine;

	public StyleAnalysisOutput() {
		this.numberCloseBraces = 0;
		this.numberOpenBracesOnOwnLine = 0;
		this.numberOfInlineComments = 0;
	}

	public StyleAnalysisOutput(double numberCloseBraces,
			double numberOpenBracesOnOwnLine) {
		this.numberCloseBraces = numberCloseBraces;
		this.numberOpenBracesOnOwnLine = numberOpenBracesOnOwnLine;
	}

	public StyleAnalysisOutput(double numberCloseBraces,
			double numberOpenBracesOnOwnLine, double numOfInlineComments) {
		this.numberCloseBraces = numberCloseBraces;
		this.numberOpenBracesOnOwnLine = numberOpenBracesOnOwnLine;
		this.numberOfInlineComments = numOfInlineComments;
	}

	public double getNumberCloseBraces() {
		return this.numberCloseBraces;
	}

	public double getNumberOfInlineComments() {
		return this.numberOfInlineComments;
	}

	public double getNumberOpenBracesOnOwnLine() {
		return this.numberOpenBracesOnOwnLine;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.numberCloseBraces = in.readDouble();
		this.numberOpenBracesOnOwnLine = in.readDouble();
		this.numberOfInlineComments = in.readDouble();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeDouble(this.numberCloseBraces);
		out.writeDouble(this.numberOpenBracesOnOwnLine);
		out.writeDouble(this.numberOfInlineComments);
	}

}
