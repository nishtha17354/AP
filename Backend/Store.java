import java.util.ArrayList;
import java.util.HashMap;

public class Store extends Superstore {

    private Warehouse linkedWarehouse;
    private static int total ;

    public Store(String name, int carryingCost, Warehouse linkedWarehouse) {
        super(name, carryingCost);
        this.linkedWarehouse = linkedWarehouse;
        this.setID(total);
        total += 1;
    }

    public Warehouse getLinkedWarehouse() {
        return linkedWarehouse;
    }

    public void setLinkedWarehouse(Warehouse linkedWarehouse) {
        this.linkedWarehouse = linkedWarehouse;
    }

    public static int getTotal() {
        return total;
    }

    public void transferAcceptance(String i, int quant){

        Item item = this.searchItem(i);
        Integer currentQuant = (Integer) getCount().get(item);
        Integer newQuant = currentQuant + quant ;
        this.changeItemUnits(i,newQuant);
    }

    public ArrayList<Warehouse> sell(String itemName, int quant) {

        //quant should be less than or equal to the available units

        ArrayList<Warehouse> optimizedWarehouses = null;

        Item item = this.searchItem(itemName);
        Integer currentQuant = (Integer) this.getCount().get(item);
        Integer newQuant = currentQuant - quant;
        this.changeItemUnits(item.getName(), newQuant);

        if (newQuant == 0) {
            int EOQ = (item.getFixedCost() * item.getDemand() * 2) / this.getCarryingCost();
            Record itemDetails = new Record(itemName, item.getCode(), EOQ, this);
            this.alert(this.linkedWarehouse, itemDetails);
            Backend b = new Backend();
            optimizedWarehouses = b.dijkstra(this.linkedWarehouse.getDistanceMatrix(),linkedWarehouse.getID(),this.linkedWarehouse);
        }

        return optimizedWarehouses;
    }
}
