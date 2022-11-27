package example;

public class Fire {
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Fire(Room room) {
        this.room = room;
    }

    private Room room;
    // Getter and setter methods
}
