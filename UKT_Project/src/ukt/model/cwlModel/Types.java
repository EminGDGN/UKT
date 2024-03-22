package ukt.model.cwlModel;

public enum Types {
	
	String("string"),
	Boolean("boolean"),
	Integer("int"),
	Long("long"),
	Float("foat"),
	Double("double"),
	Null("null");
	
	public final String prettyName;
	
	Types(String prettyName){
		this.prettyName = prettyName;
	}
	
	@Override
	public String toString() {
	    return prettyName;
	}
}
