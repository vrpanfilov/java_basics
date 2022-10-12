public class Main {

    public static void main(String[] args) {
        Book book = new Book("Колобок", "Народная");
        book.setPageNumber(12);
        book.setYearOfPublication(2000);
        System.out.println("Название: " + book.getName());
        System.out.println("Автор: " + book.getAuthor());
        System.out.println("К-во страниц: " + book.getPageNumber());
        System.out.println("Год издания: " + book.getYearOfPublication());
    }
}
