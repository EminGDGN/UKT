package ukt.model.cwlModel;

public enum Types {
	
	STRING("string"),
	BOOLEAN("boolean"),
	INTEGER("int"),
	LONG("long"),
	FLOAT("foat"),
	DOUBLE("double"),
	NULL("null");
	
	public final String prettyName;
	
	Types(String prettyName){
		this.prettyName = prettyName;
	}
	
	@Override
	public String toString() {
	    return prettyName;
	}
}
