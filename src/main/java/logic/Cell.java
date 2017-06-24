package logic;

import java.util.Comparator;

/*klasa reprezentujaca pojedyncza komorke*/
public class Cell {
    private Coordinates coordinates;
    private State state;

    public Cell(Coordinates coordinates, State state) {
        this.coordinates = coordinates;
        this.state = state;
    }

    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.state = State.EMPTY;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public static Comparator<Cell> compareCells() {
        Comparator comparator = new Comparator<Cell>() {
            @Override
            public int compare(Cell cell1, Cell cell2) {
                Coordinates c1 = cell1.getCoordinates();
                Coordinates c2 = cell2.getCoordinates();
                if(c1.getX() < c2.getX())
                    return -1;
                else if(c1.getX() > c2.getX())
                    return 1;
                else {
                    if(c1.getY() < c2.getY())
                        return -1;
                    else if(c1.getY() > c2.getY())
                        return 1;
                }
                return 0;
            }
        };
        return comparator;
    }

    @Override
    public String toString() {
        return new String("[" + coordinates + ": " + state + "]");
    }

    @Override
    public boolean equals(Object cell) {
        if ( cell == null ) return false;
        if ( this == cell ) return true;
        if ( cell.getClass() != Cell.class ) return false;

        Cell otherCell = (Cell) cell;

        if( this.coordinates.equals(otherCell.coordinates) && this.state.equals(otherCell.state))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return (coordinates.hashCode() + state.hashCode() * 89) % 1009;
    }
}
