import ru.skillbox.*;

public class Main {

    public static void main(String[] args) {
        ArithmeticCalculator calc = new ArithmeticCalculator(9, 7);
        System.out.println("\n\nArithmeticCalculator");
        System.out.println(calc.getVar1() + " + " + calc.getVar2() + " = " +
                calc.calculate(Operation.ADD));
        System.out.println(calc.getVar1() + " - " + calc.getVar2() + " = " +
                calc.calculate(Operation.SUBSTRACT));
        System.out.println(calc.getVar1() + " * " + calc.getVar2() + " = " +
                calc.calculate(Operation.MULTIPLY));
    }
}
