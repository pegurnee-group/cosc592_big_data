package src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class StyleAnalysisObject implements Writable {
	private int numberCloseBraces;
	private int numberOpenBracesOnOwnLine;
	private int numberOfInlineComments;

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

	@Override
	public void readFields(DataInput in) throws IOException {
		numberCloseBraces = in.readInt();
		numberOpenBracesOnOwnLine = in.readInt();
		numberOfInlineComments = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(numberCloseBraces);
		out.writeInt(numberOpenBracesOnOwnLine);
		out.writeInt(numberOfInlineComments);
	}

	public int getNumberCloseBraces() {
		return numberCloseBraces;
	}

	public int getNumberOpenBracesOnOwnLine() {
		return numberOpenBracesOnOwnLine;
	}
	
	public int getNumberOfInlineComments() {
		return numberOfInlineComments;
	}

}
