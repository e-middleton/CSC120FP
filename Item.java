/**
 * Class for Items in the game, contains a name (id), response if you try to talk to it
 * and a description if it is examined
 */
public class Item {
    private String name;
    private String response;
    private String description;

    /**
     * Default constructor for an item with all attributes
     * @param name the name of the item, it's id
     * @param response the response it gives if you try to talk to it
     * @param description a brief description of the item
     */
    public Item(String name, String response, String description){
        this.name = name;
        this.response = response;
        this.description = description;
    }

    /**
     * Stub constructor for temporary usage/testing
     * @param name the name of the item
     */
    public Item(String name){
        this.name = name;
        this.response = null;
        this.description = null;
    }
    
    /**
     * Getter for the name/id of an item
     * @return the name of the item
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for the response (if you try to talk or barter) of an object
     * @return the item's response to talking
     */
    public String getResponse(){
        return this.response;
    }

    /**
     * Getter for an item's description
     * @return description of the item
     */
    public String getDescription(){
        return this.description;
    }
    
    /**
     * To string method for an item, getting relevant information, just the name/id
     * @return a description of an item with all relevant information
     */
    public String toString(){
        return this.name;
    }

}
