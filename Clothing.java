/**
 * Stub class Clothing, to be fleshed out later and used for the knit items to add more detail/complexity
 */
public class Clothing {
    private String type;
    private String color;
    private String description; //colorwork, cables, etc.

    /**
     * default constructor for Clothing with all parameters
     * @param type what type of clothing is this, sweater, socks, gloves etc.
     * @param color what color is this clothing? undyed, red, black, etc.
     * @param description what other information is important about this, is it cabled?
     */
    public Clothing(String type, String color, String description){
        this.type = type;
        this.color = color;
        this.description = description;
    }
}
