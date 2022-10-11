public class Main {
    public static void main(String[] args) {
        System.out.println("Система расчёта стоимости топлива");

        int fuelType = 95;
        int amount = 50;

        double fuel92Price = 60.2;
        double fuel95Price = 67.33;

        double fuelPrice = 0;

        int maxAmount = 400;

        if(fuelType == 92) {
            fuelPrice = fuel92Price;
        }
        else if(fuelType == 95) {
            fuelPrice = fuel95Price;
        } else {
            System.out.println("Указан неверный тип топлива");
        }

        if(amount < 1) {
            System.out.println("Указано слишком малое количество топлива");
            amount = 0;
        }

        if(amount > maxAmount) {
            System.out.println("Указанное количество топлива превышает максимально допустимое");
        }

        System.out.println("Цена выбранного топлива: " + fuelPrice + " руб.");

        double totalPrice = fuelPrice * amount;
        System.out.println("Общая стоимость заправки: " + totalPrice + " руб. ");
    }
}
