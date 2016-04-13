package src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class StyleAnalysisObject implements Writable {
	private int numberCloseBraces;
	private int numberOpenBracesOnOwnLine;

	public StyleAnalysisObject() {
		this.numberCloseBraces = 0;
		this.numberOpenBracesOnOwnLine = 0;
	}

	public StyleAnalysisObject(int numberCloseBraces,
			int numberOpenBracesOnOwnLine) {
		this.numberCloseBraces = numberCloseBraces;
		this.numberOpenBracesOnOwnLine = numberOpenBracesOnOwnLine;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		numberCloseBraces = in.readInt();
		numberOpenBracesOnOwnLine = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(numberCloseBraces);
		out.writeInt(numberOpenBracesOnOwnLine);
	}

	public int getNumberCloseBraces() {
		return numberCloseBraces;
	}

	public int getNumberOpenBracesOnOwnLine() {
		return numberOpenBracesOnOwnLine;
	}

}
