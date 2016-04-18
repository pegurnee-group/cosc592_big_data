package src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class StyleAnalysisObject implements Writable {
	private int numberCloseBraces;
	private int numberOfInlineComments;
	private int numberOpenBracesOnOwnLine;

	public StyleAnalysisObject() {
		this.numberCloseBraces = 0;
		this.numberOpenBracesOnOwnLine = 0;
		this.numberOfInlineComments = 0;
	}

	public StyleAnalysisObject(int numberCloseBraces,
			int numberOpenBracesOnOwnLine) {
		this.numberCloseBraces = numberCloseBraces;
		this.numberOpenBracesOnOwnLine = numberOpenBracesOnOwnLine;
	}

	public StyleAnalysisObject(int numberCloseBraces,
			int numberOpenBracesOnOwnLine, int numOfInlineComments) {
		this.numberCloseBraces = numberCloseBraces;
		this.numberOpenBracesOnOwnLine = numberOpenBracesOnOwnLine;
		this.numberOfInlineComments = numOfInlineComments;
	}

	public int getNumberCloseBraces() {
		return this.numberCloseBraces;
	}

	public int getNumberOfInlineComments() {
		return this.numberOfInlineComments;
	}

	public int getNumberOpenBracesOnOwnLine() {
		return this.numberOpenBracesOnOwnLine;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.numberCloseBraces = in.readInt();
		this.numberOpenBracesOnOwnLine = in.readInt();
		this.numberOfInlineComments = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(this.numberCloseBraces);
		out.writeInt(this.numberOpenBracesOnOwnLine);
		out.writeInt(this.numberOfInlineComments);
	}

}
