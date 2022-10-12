public class Country {
    private String name;
    private int population;
    private int square;
    private String capital;

    private boolean hasAccessToSea;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public boolean isHasAccessToSea() {
        return hasAccessToSea;
    }

    public void setHasAccessToSea(boolean hasAccessToSea) {
        this.hasAccessToSea = hasAccessToSea;
    }
}
