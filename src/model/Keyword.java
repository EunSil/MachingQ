package model;

public class Keyword {
	private int keyId;
	private String item;
	private String[] value;
	
	public Keyword() { }

	public Keyword(int keyId, String item, String[] value) {
		super();
		this.keyId = keyId;
		this.item = item;
		this.value = value;
	}
	
	public int getKeyId() {
		return keyId;
	}

	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String[] getValue() {
		return value;
	}

	public void setValue(String[] value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Keyword [keyId=" + keyId + ", item=" + item + ", value=" + value + "]";
	}

}
