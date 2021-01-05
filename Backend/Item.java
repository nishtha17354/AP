public class Item {

    private static int totalItems = 0;
    private int code;
    private String name;
    //private String location;
    private String cat;
    private String subcat;
    private int fixedCost;
    private int demand;

    public Item(String name, int fixedCost, int demand, String cat, String subcat){

        this.name = name ;
        this.fixedCost= fixedCost ;
        this.demand = demand ;
        this.cat = cat;
        this.subcat = subcat;
        code = totalItems ;
        totalItems += 1;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /*public String getLocation() {
        return location;
    }*/

    public String getCat() {
        return cat;
    }

    public String getSubcat() {
        return subcat;
    }

    public int getFixedCost() {
        return fixedCost;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public void setFixedCost(int fixedCost) {
        this.fixedCost = fixedCost;
    }
}

