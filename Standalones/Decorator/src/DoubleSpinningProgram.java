public class DoubleSpinningProgram extends SpecialWashingProgram {
    public DoubleSpinningProgram(WashingProgram washingProgram) {
        super(washingProgram);
    }

    @Override
    public void executeProgram() {
        BasicWashingProgram washingProgram =
                (BasicWashingProgram) this.washingProgram;
        washingProgram.executeProgram();
        for (int i = 0; i < 5; i++) {
            washingProgram.rotateDrum(-30);
            washingProgram.rotateDrum(30);
        }
        washingProgram.rotateDrum(1500);
        washingProgram.drainOff();
    }
}
