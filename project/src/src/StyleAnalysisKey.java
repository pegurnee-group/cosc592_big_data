package src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class StyleAnalysisKey implements Writable {
	private static final String SPLITTER = " | ";

	private String filename;
	private String language;
	private long repoId;

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		final String[] vals = in.readLine().split(SPLITTER);

		this.language = vals[0];
		this.repoId = Integer.parseInt(vals[1]);
		this.filename = vals[2];
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeChars(this.language + SPLITTER);
		out.writeLong(this.repoId);
		out.writeChars(SPLITTER + this.filename);
	}

}
