package model;

public class Words {

    public String type = "Words";
    private String name;
    private String animal;
    private String thing;
    private String site;
    private int id;

    public Words(int id,String name, String animal, String thing, String site) {
        this.id=id;
        this.name = name;
        this.animal = animal;
        this.thing = thing;
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    

}
