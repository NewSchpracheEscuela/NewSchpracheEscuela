package Documentation.Generators;

/**
 * Created by angre on 29.04.2017.
 */
public class CSVGenerator<T> implements IGenerator<T> {
    private boolean isProtected = false;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public String getType() {
        return "csv";
    }
}
