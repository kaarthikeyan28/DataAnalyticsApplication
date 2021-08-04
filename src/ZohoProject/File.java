package ZohoProject;

public class File {
    private String name;
    private boolean isUploaded;

    File(String name){
        this.name = name;
        this.isUploaded = false;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isUploaded() {
        return isUploaded;
    }
    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }

}
