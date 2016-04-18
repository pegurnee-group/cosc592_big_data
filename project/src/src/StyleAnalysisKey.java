package src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class StyleAnalysisKey implements WritableComparable<StyleAnalysisKey> {
	private static final String SPLITTER = " | ";

	private String filename;
	private String language;
	private long repoId;

	public StyleAnalysisKey() {
		this.language = null;
		this.filename = null;
		this.repoId = 0;
	}

	public StyleAnalysisKey(String language, String filename, long repoId) {
		this.language = language;
		this.filename = filename;
		this.repoId = repoId;
	}

	private String combine() {
		return this.language + SPLITTER + this.repoId + SPLITTER
				+ this.filename;
	}

	@Override
	public int compareTo(StyleAnalysisKey o) {
		// TODO Auto-generated method stub
		return this.combine().compareTo(o.combine());
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		final String[] vals = in.readLine().split(SPLITTER);

		this.language = vals[0];
		this.repoId = Integer.parseInt(vals[1]);
		this.filename = vals[2];
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeChars(this.language + SPLITTER);
		out.writeLong(this.repoId);
		out.writeChars(SPLITTER + this.filename);
	}

}
