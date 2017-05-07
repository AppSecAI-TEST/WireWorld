package logic;

import java.lang.Integer;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return new String("(" + x + ", " + y + ")");
    }

    @Override
    public boolean equals(Object coordinates) {
        if ( coordinates == null ) return false;
        if ( this == coordinates ) return true;
        if ( coordinates.getClass() != Coordinates.class ) return false;

        Coordinates otherCoordinates = (Coordinates) coordinates;
        if( this.x == otherCoordinates.x && this.y == otherCoordinates.y)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return (Integer.hashCode(x) + Integer.hashCode(y) + 31) * 5;
    }
}
