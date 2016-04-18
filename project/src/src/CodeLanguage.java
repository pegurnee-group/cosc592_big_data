package src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public enum CodeLanguage implements WritableComparable<CodeLanguage> {
	C, CPP, JAVA, JS, OBJECTIVE_C, PHP, PYTHON, RUBY, SWIFT;

	@Override
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub

	}
}
