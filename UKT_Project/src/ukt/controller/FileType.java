package ukt.controller;

public enum FileType {

	JSON("JSON", "json"),
	CWL("CWL", "cwl");

    private final String displayName;
    private final String extension;

    FileType(String displayName, String extension) {
        this.displayName = displayName;
        this.extension = extension;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getExtension() {
        return extension;
    }
}
