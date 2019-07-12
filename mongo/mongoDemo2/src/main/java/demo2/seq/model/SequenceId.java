package demo2.seq.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 db.sequence.insert({_id: "hosting",seq: 0})
 */
@Document(collection = "sequence")
public class SequenceId {

	@Id
	private String id;

	private long seq;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "SequenceId [id=" + id + ", seq=" + seq + "]";
	}

}
