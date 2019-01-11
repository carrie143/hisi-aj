package com.hisi.model;

import java.util.ArrayList;
import java.util.List;

public class TreeGroup {
	private String key;
	private String value="";
	private String label;
	private List<TreeGroup> children=new ArrayList<TreeGroup>();
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<TreeGroup> getChildren() {
		return children;
	}
	public void setChildren(List<TreeGroup> children) {
		this.children = children;
	}
	
}
