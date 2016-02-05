package com.hui.translator.info;

/**
 * ∑≠“Î¿˝æ‰–≈œ¢¿‡
 */
public class SentenceInfo {
	
	private int _id;
	private String orig;
	private String trans;
	
	public SentenceInfo() {
		// TODO Auto-generated constructor stub
	}

	public SentenceInfo(int _id, String orig, String trans) {
		super();
		this._id = _id;
		this.orig = orig;
		this.trans = trans;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getOrig() {
		return orig;
	}

	public void setOrig(String orig) {
		this.orig = orig;
	}

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	@Override
	public String toString() {
		return "SentenceInfo [_id=" + _id + ", orig=" + orig + ", trans="
				+ trans + "]";
	}
	
	
}
