import java.util.Date;

public class CreditCard implements PaymentMethod {
    private String cardNumber;
    private String cardHolder;
    private String csvCode;
    private Date expirationDate;

    public CreditCard(String cardNumber, String cardHolder,
                      String csvCode, Date expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.csvCode = csvCode;
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean pay(int amount) {
        if (BankConnection.transferMoney(this, amount)) {
            System.out.println("Payed by credit card");
            return true;
        }
        System.out.println("Some error occured");
        return false;
    }
}
