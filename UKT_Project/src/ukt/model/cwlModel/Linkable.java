package ukt.model.cwlModel;

public abstract class Linkable {

	protected Process parent;					// Process containing this linkable
	protected String name;
	
	public Linkable(String name, Process parent){
		this.name = name;
		this.parent = parent;
	}
	
	public abstract String toString();
	
	/**
	 * 
	 * @return indentation for cwl 
	 */
	public String tab() {
		return this.parent.tab() + "  ";
		
	}
	
	/**
	 * 
	 * @return a String conataining the path used in 'source' field
	 */
	public String getCWLPath() {
		if(this.parent.isMainWorkflow()) {
			return this.name;
		}else {
			return this.parent.getName() + "/" + this.name;
		}
	}
	
	public Process getParent() {
		return this.parent;
	}
	
	public String getName() {
		return this.name;
	}
}
