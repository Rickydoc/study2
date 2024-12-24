package common;

import java.sql.Timestamp;

public class Doc {

	private String ID;
	private String creator;
	private Timestamp timestamp;
	private String description;
	private String filename;

	public Doc(String ID, String creator, Timestamp timestamp, String description, String filename) {
		this.setID(ID);
		this.setCreator(creator);
		this.setTimestamp(timestamp);
		this.setDescription(description);
		this.setFilename(filename);
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String toString() {
		return "ID:" + this.ID + " Creator:" + this.creator + " Time:" + this.timestamp + " Filename:" + this.filename
				+ " Description:" + this.description;
	}

}

