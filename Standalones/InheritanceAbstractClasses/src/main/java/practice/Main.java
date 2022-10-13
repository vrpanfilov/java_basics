package practice;

public class Main {
    public static void main(String[] args) {
        Client person = new PhysicalPerson();
        person.put(15000);
        person.take(2000);
        System.out.println("PhysicalPerson");
        System.out.println(person.info());

        person = new LegalPerson();
        person.put(70000);
        person.put(32000);
        person.take(8000);
        System.out.println("LegalPerson");
        System.out.println(person.info());

        person = new IndividualBusinessman();
        person.put(720000);
        person.take(380000);
        System.out.println("IndividualBusinessman");
        System.out.println(person.info());
    }
}
