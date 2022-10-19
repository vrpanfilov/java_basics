public class WashingMachine {
    public static void main(String[] args) {
        BasicWashingProgram washingProgram = new BasicWashingProgram();
        DoubleSpinningProgram spinningProgram =
                new DoubleSpinningProgram(washingProgram);
        BoilingWashingProgram boilingProgram =
                new BoilingWashingProgram(spinningProgram);
        boilingProgram.executeProgram();
    }
}
