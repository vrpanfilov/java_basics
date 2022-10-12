public class Elevator {
    private int currentFloor;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        currentFloor = 1;
    }

    public void moveDown() {
        if (currentFloor > minFloor) {
            currentFloor--;
            System.out.println(currentFloor);
        }
    }

    public void moveUp() {
        if (currentFloor < maxFloor) {
            currentFloor++;
            System.out.println(currentFloor);
        }
    }

    public void move(int floor) {
        if (floor < minFloor || floor > maxFloor) {
            System.out.println("Значение этажа должно быть между " +
                    minFloor + " и " + maxFloor + " включительно");
            return;
        }
        while (currentFloor != floor) {
            if (currentFloor < floor) {
                moveUp();
            } else if (currentFloor > floor) {
                moveDown();
            }
        }
    }
}
