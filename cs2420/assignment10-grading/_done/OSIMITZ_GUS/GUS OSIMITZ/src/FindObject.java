package assignment10;

/**
 * FindObject stores the location the object should be as well as whether it actually exists or not
 *
 * @author Thomas Osimitz U0970671
 */
public class FindObject {
    private int location;
    private boolean exists;
    public FindObject(int _location, boolean _exists) {
        location = _location;
        exists = _exists;
    }
    public int getLocation() {
        return location;
    }

    public boolean doesExist() {
        return exists;
    }
}
