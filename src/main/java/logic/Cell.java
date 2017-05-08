package logic;

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
