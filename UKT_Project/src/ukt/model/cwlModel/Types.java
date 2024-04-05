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
	
	/**
	 * 
	 * @param value
	 * @return the enum type corresponding to the string value
	 */
	public static Types getEnum(String value) {
        for(Types t : values())
            if(t.toString().equalsIgnoreCase(value)) return t;
        throw new IllegalArgumentException();
    }
}
