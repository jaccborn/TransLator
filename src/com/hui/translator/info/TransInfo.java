package com.hui.translator.info;

/**
 * ∑≠“Î–≈œ¢
 */
public class TransInfo {
	
	private int _id;
	private String wordKey;
	private String pron;
	private String translation;
	
	public TransInfo() {
		// TODO Auto-generated constructor stub
	}

	public TransInfo(int _id, String wordKey, String pron, String translation) {
		super();
		this._id = _id;
		this.wordKey = wordKey;
		this.pron = pron;
		this.translation = translation;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String getWordKey() {
		return wordKey;
	}

	public void setWordKey(String wordKey) {
		this.wordKey = wordKey;
	}

	public String getPron() {
		return pron;
	}

	public void setPron(String pron) {
		this.pron = pron;
	}

	@Override
	public String toString() {
		return "TransInfo [_id=" + _id + ", wordKey=" + wordKey + ", pron="
				+ pron + ", translation=" + translation + "]";
	}

	
	
	
	
}
