package ukt.model.cwlModel;

public enum Types {
	
	STRING("string"),
	BOOLEAN("boolean"),
	INTEGER("int"),
	LONG("long"),
	FLOAT("foat"),
	DOUBLE("double"),
	NULL("null"),
	DIRECTORY("Directory"),
	FILE("File");
	
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
