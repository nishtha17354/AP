import java.util.ArrayList;

public class WarehouseAdmin extends Admin{

    private Warehouse warehouse;

    public WarehouseAdmin(String loginID, String password, Warehouse warehouse) {
        super(loginID, password);
        this.warehouse = warehouse;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
