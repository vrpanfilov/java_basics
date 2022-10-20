public class BasicWashingProgram implements WashingProgram {
    @Override
    public void executeProgram() {
        fillByWater();
        takePowder();
        for (int i = 0; i < 40; i++)  {
            rotateDrum(30);
            rotateDrum(-30);
        }
        drainOff();
        rotateDrum(1500);
        drainOff();
        rotateDrum(1500);
    }

     public void fillByWater() {
         System.out.println("Filling by new water");
     }

     public void takePowder() {
         System.out.println("Taking powder");
     }

     public void rotateDrum(int speed) {
         System.out.println("Rotate drum at speed " + speed + "rpm");
     }

     public void drainOff() {
         System.out.println("Draining of the water");
     }
}
