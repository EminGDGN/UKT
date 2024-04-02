package ukt.model.cwlModel;

import java.util.ArrayList;

public abstract class Linkable {

	protected Process parent;
	protected String name;
	
	public Linkable(String name, Process parent){
		this.name = name;
		this.parent = parent;
	}
	
	public abstract String toString();
	
	public String tab() {
		return this.parent.tab() + "  ";
		
	}
	
	public String getCWLPath() {
		if(this.parent.getParent() == null) {
			return this.name;
		}else {
			return this.parent.getName() + "/" + this.name;
		}
	}
	
	public Process getParent() {
		return this.parent;
	}
}
