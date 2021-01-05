import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.*;


abstract class Superstore {

    private String name;
    private int ID;
    //IDs for warehouses and stores would be in a sequential order starting from 0, in both cases.
    private int carryingCost;
    private HashMap <String, HashMap> inventory = new HashMap <>();
    private HashMap <Item, Integer> count = new HashMap<>() ;

    public Superstore (String name, int carryingCost) {
        this.name = name;
        this.carryingCost = carryingCost;
    }

    public int getCarryingCost() {
        return carryingCost;
    }

    public void setCarryingCost(int carryingCost) {
        this.carryingCost = carryingCost;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public HashMap getInventory() {
        return inventory;
    }

    public HashMap getCount() {
        return count;
    }

    public void setCount(Item item, Integer newQuant){
        this.count.replace(item, newQuant);
    }

    //Note: It has been assumed that if the superuser wants to create a new category or subcategory,
    //then, it would be compulsory for him/her to create an item under it.

    public void addCat(Item item, int units) {

        String catName = item.getCat();
        String subcatName = item.getSubcat();
        String itemName = item.getName();

        HashMap<String,HashMap> subcatMap = new HashMap<String,HashMap>();
        HashMap<String,Integer> itemMap = new HashMap<String,Integer>();

        count.put(item, units);
        itemMap.put(itemName, units);
        subcatMap.put(subcatName, itemMap);
        inventory.put(catName, subcatMap);
    }

    public void addSubcat(Item item, int units) {
        String catName = item.getCat();
        String subcatName = item.getSubcat();
        String itemName = item.getName();

        HashMap subcatMap = inventory.get(catName);
        HashMap itemMap = new HashMap();

        itemMap.put(itemName, units);
        count.put(item, units);
        subcatMap.put(subcatName, itemMap);
    }

    public void addItem(Item item, int units) {

        String catName = item.getCat();
        String subcatName = item.getSubcat();
        String itemName = item.getName();

        HashMap subcatMap = inventory.get(catName);
        HashMap itemMap = (HashMap) subcatMap.get(subcatName);
        itemMap.put(itemName,units);
        count.put(item,units);
    }

    public void deleteCat(String cat){

        HashMap subcatMap = this.searchCat(cat);
        HashMap itemMap;
        ArrayList<String> itemsToBeDeleted = new ArrayList<String>();

        for (Object subcatObj : subcatMap.keySet()) {
            String subcatName = (String) subcatObj;
            itemMap = (HashMap) subcatMap.get(subcatName);
            for (Object itemObj : itemMap.keySet()) {
                String itemName = (String) itemObj ;
                itemsToBeDeleted.add(itemName);
            }
        }

        this.inventory.remove(cat);
        String itemName;

        for (Item item: count.keySet()){
            itemName = item.getName();
            if (itemsToBeDeleted.contains(itemName)){
                count.remove(item);
            }
        }
    }

    public void deleteSubcat(String cat, String subcat) {

        HashMap<String, HashMap> subcatMap = this.inventory.get(cat);
        HashMap<String, Integer> itemMap = subcatMap.get(subcat);

        for (String itemName : itemMap.keySet()){
            Item i = this.searchItem(itemName);
            count.remove(i);
        }

        subcatMap.remove(subcat);

        /*for (String cat : inventory.keySet()) {
            subcatMap = inventory.get(cat);
            for (String subcatName : subcatMap.keySet() ){
                if (subcat == subcatName) {
                    subcatMap.remove(subcat);
                    break;
                }
            }
        }*/
    }

    public void deleteItem(String cat, String subcat, String itemName) {

        Item item = this.searchItem(itemName);
        count.remove(item);

        HashMap itemMap = (HashMap) inventory.get(cat).get(subcat);
        itemMap.remove(itemName);
    }

    public HashMap searchCat(String cat) {
        HashMap subCategories = (HashMap) inventory.get(cat);
        return subCategories;
    }

    public HashMap searchSubcat(String subcatName) {

        HashMap<String, HashMap> subcatMap = new HashMap<String, HashMap>();

        for (String cat : inventory.keySet()) {
            subcatMap = inventory.get(cat);
            for (String subcat : subcatMap.keySet() ){
                if (subcat == subcatName) {
                    return subcatMap.get(subcat);
                }
            }
        }
        return subcatMap;
    }

    public Item searchItem(String itemName) {

        Item i = new Item("i",0,0, "s", "ss");

        for (Item item : count.keySet()) {
            if (item.getName() == itemName) {
                return item;
            }
        }
        return i;
    }

    public void changeItemUnits(String item, int newQuant){

        Item i = this.searchItem(item);
        count.replace(i,newQuant);

        HashMap catMap = this.getInventory();
        HashMap subcatMap = (HashMap) catMap.get(i.getCat());
        HashMap itemMap = (HashMap) subcatMap.get(i.getSubcat());
        itemMap.replace(item,newQuant);
    }

    public void changeItemFixedCost(String item, int newCost){

        Item i = this.searchItem(item);
        i.setFixedCost(newCost);
    }

    public void changeItemDemand(String item, int newDemand){

        Item i = this.searchItem(item);
        i.setDemand(newDemand);
    }

    public void alert(Warehouse optimizedWarehouse, Record itemNeeded) {
        optimizedWarehouse.addToAlertMessage(itemNeeded);
    }

    //public abstract HashMap<Integer,Boolean> sell(String itemName, int quant);
}

public class Warehouse extends Superstore{

    private static int total ;
    private static ArrayList<Warehouse> warehouseListID = new ArrayList<>();
    //private static ArrayList<Warehouse> warehouseListDist = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> distanceMatrix = new ArrayList<>();
    private ArrayList<Record> alertMessage = new ArrayList<>();

    public Warehouse(String n, int carryingCost) {
        super(n, carryingCost);
        this.setID(total);
        total += 1;
        warehouseListID.add(this);
        int numberOfWarehouses = warehouseListID.size();
        //We have generated random distances from the given warehouse to all the other warehouses.
        // The chosen range is: [1,20].
        int maxDist = 21;
        int minDist = 1;
        ArrayList<Integer> distancesForThisWarehouse = new ArrayList<>();

        for (int i = 0; i<numberOfWarehouses-1; i++){
            Random random = new Random();
            int randDist = random.nextInt(maxDist) + minDist;
            distancesForThisWarehouse.add(randDist);
            distanceMatrix.get(i).add(randDist);
        }

        distancesForThisWarehouse.add(0);
        distanceMatrix.add(distancesForThisWarehouse);

    }

    public static ArrayList<ArrayList<Integer>> getDistanceMatrix() {
        return distanceMatrix;
    }

    public void addToAlertMessage(Record itemNeeded) {
        this.alertMessage.add(itemNeeded);
    }

    public ArrayList<Record> getAlertMessage() {
        return alertMessage;
    }

    public static int getTotal() {
        return total;
    }

    public static ArrayList<Warehouse> getWarehouseList() {
        return warehouseListID;
    }

    public Record MainMessage (){

        Store s = null;
        Record itemRequested = new Record("sample", 00, 0, s); //These are just sample returns

        if (this.getAlertMessage().isEmpty() == false) {
            for (int i = 0; i < this.getAlertMessage().size(); i++) {
                itemRequested = this.getAlertMessage().get(i); //wanna make this a deep copy
            }
        }
        return itemRequested;
    }

    public void respond(boolean response, Record itemRequested, ArrayList warehouses){

        if (response == true) {
            Store receivingEntity = itemRequested.receiver;
            if (receivingEntity.getLinkedWarehouse().getID() == this.getID()) {
                String itemName = itemRequested.itemName;
                int quant = itemRequested.quantReq;
                Item item = this.searchItem(itemName);
                Integer currentQuant = (Integer) this.getCount().get(item);
                if (currentQuant < quant) {
                    Record updatedItem = itemRequested;
                    updatedItem.setQuantReq(currentQuant);
                    transferToStore(warehouses, updatedItem);
                    receivingEntity.transferAcceptance(itemRequested.itemName, currentQuant);
                }
                else {
                    transferToStore(warehouses, itemRequested);
                    receivingEntity.transferAcceptance(itemRequested.itemName, itemRequested.quantReq);
                }
            }
            else {
                transferToStore(warehouses, itemRequested);
                receivingEntity.transferAcceptance(itemRequested.itemName, itemRequested.quantReq);
            }
        }
        else {
            alertOptimizedWarehouses(warehouses,itemRequested);
        }
    }

    public ArrayList<Warehouse> transferToStore(ArrayList<Warehouse> optimizedWarehouses, Record itemRequested) {
        //quant should be less than or equal to the available units

        String itemName = itemRequested.itemName;
        int quant = itemRequested.quantReq;

        Item item = this.searchItem(itemName);
        System.out.println(item.getName());

        Integer currentQuant = (Integer) this.getCount().get(item);
        System.out.println(this.getCount().values());
        System.out.println("Required quant " + quant);
        System.out.println("currentQuant " + currentQuant);

        Integer newQuant = currentQuant - quant;
        this.changeItemUnits(itemName, newQuant);
        System.out.println("newQuant " + newQuant);

        if (newQuant == 0) {
            int EOQ = (item.getFixedCost() * item.getDemand() * 2) / this.getCarryingCost();
            System.out.println("EOQ " + EOQ);

            Record itemDetails = new Record(itemName, item.getCode(), EOQ, itemRequested.receiver);
            int source = itemRequested.receiver.getLinkedWarehouse().getID();

            System.out.println("source " + source);
            int numberOfWarehouses = warehouseListID.size();
            System.out.println("total warehouses " + numberOfWarehouses);

            this.alertOptimizedWarehouses(optimizedWarehouses, itemDetails);
        }

        return optimizedWarehouses;
    }

    public void alertOptimizedWarehouses(ArrayList<Warehouse> optimizedWarehouses, Record itemNeeded) {

        String itemName = itemNeeded.itemName;
        int requiredQuant = itemNeeded.quantReq;

        while (requiredQuant != 0) {
            Warehouse optimizedWarehouse = optimizedWarehouses.get(0);
            System.out.println(optimizedWarehouse.getName());
            Item i = optimizedWarehouse.searchItem(itemName);
            Integer availableUnits = (Integer) optimizedWarehouse.getCount().get(i);
            System.out.println("available units: " + availableUnits);


            if ((availableUnits < requiredQuant) && (availableUnits > 0)) {
                int diff = requiredQuant - availableUnits;
                Record r = itemNeeded; // original record shouldn't be changed. Anew record of the items available should alert the warehouses.
                r.setQuantReq(diff);
                this.alert(optimizedWarehouse, r);
                requiredQuant = requiredQuant-diff;
            }

            else if (availableUnits > requiredQuant){
                this.alert(optimizedWarehouse, itemNeeded);
                requiredQuant = 0;
            }
            optimizedWarehouses.remove(0);
        }
    }

    public static void main(String[] args) {

        Warehouse testW1 = new Warehouse("NFC", 10);
        Warehouse testW2 = new Warehouse("Rohini", 20);
        Warehouse testW3 = new Warehouse("GK", 10);
        Warehouse testW4 = new Warehouse("EOK", 20);
        Warehouse testW5 = new Warehouse("Gurugram", 10);
        Store store = new Store("onlyStore", 10, testW2);
        Item iPhone = new Item("iPhone", 15, 10, "Electronics", "Phones");
        Item s9 = new Item("s9", 5, 10, "Electronics", "Phones");
        Item tv = new Item("tv", 10, 10, "Electronics", "Home Appliances");

        System.out.println(warehouseListID);
        System.out.println(distanceMatrix);

        testW1.addCat(iPhone, 5);
        testW1.addItem(s9,5);
        testW1.addSubcat(tv,100);

        testW2.addCat(iPhone, 5);
        testW2.addItem(s9,10);
        testW2.addSubcat(tv,100);
        System.out.println("count " + testW2.getCount());

        testW3.addCat(iPhone, 3);
        testW3.addItem(s9,3);
        testW3.addSubcat(tv,100);

        testW4.addCat(iPhone, 7);
        testW4.addItem(s9,7);
        testW4.addSubcat(tv,100);

        testW5.addCat(iPhone, 1);
        testW5.addItem(s9,2);
        testW5.addSubcat(tv,100);

        store.addCat(iPhone, 5);
        store.addItem(s9,5);
        store.addSubcat(tv,100);
        ArrayList<Warehouse> warehouses = store.sell("s9",5);

        System.out.println("store count :" + store.getCount());
        Record s9Request = testW2.MainMessage();
        testW2.respond(true,s9Request,warehouses);
        System.out.println("store count :" + store.getCount());

        if (testW1.MainMessage()!= null){
            System.out.println("entered1");
            testW1.respond(true,testW1.MainMessage(),warehouses);
        }

        if (testW3.MainMessage()!=null){
            System.out.println("entered3");

            testW3.respond(true,testW3.MainMessage(),warehouses);
        }

        if (testW4.MainMessage()!=null){
            System.out.println("entered4");

            testW4.respond(true,testW4.MainMessage(),warehouses);
        }

        if (testW5.MainMessage()!=null){
            System.out.println("entered5");

            testW5.respond(true,testW5.MainMessage(),warehouses);
        }
        System.out.println("warehouse count :" + testW2.getCount());
    }
}
