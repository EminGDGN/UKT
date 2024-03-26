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
	
	public static Types getEnum(String value) {
        for(Types t : values())
            if(t.toString().equalsIgnoreCase(value)) return t;
        throw new IllegalArgumentException();
    }
}
