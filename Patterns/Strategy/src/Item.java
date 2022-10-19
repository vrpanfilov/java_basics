public class Item {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Item) obj).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
