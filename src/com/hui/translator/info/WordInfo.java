package com.hui.translator.info;

/**
 * µ•¥ –≈œ¢
 */
public class WordInfo {
	
	private int _id;
	private String word;
	private String trans;
	
	public WordInfo() {
		// TODO Auto-generated constructor stub
	}

	public WordInfo(int _id, String word, String trans) {
		super();
		this._id = _id;
		this.word = word;
		this.trans = trans;
	}

	
	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "WordInfo [_id=" + _id + ", word=" + word + ", trans=" + trans
				+ "]";
	}
	
	
	
	
	
}
