package marketPlace;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private Map<String,StockItem> list;

    public StockList(){
        this.list = new LinkedHashMap<>();
    }
    public int addStock(StockItem item){
        if(item != null) {
            StockItem inStock = this.list.getOrDefault(item.getName(), item);
            // StockItem inStock = list.get(item.getName());
            System.out.println("*****************************LIST FOUND IN INSTOCK*****************************\n"+inStock);

            if(inStock != item) //or (inStock != null)
                item.adjustQuantity(inStock.availableQuantity());
            list.put(item.getName(), item);
            System.out.println("*****************************LIST FOUND IN LIST*****************************");
            for(StockItem listStock : this.list.values())
                System.out.println(listStock);
            return item.availableQuantity();
        }
        return 0;
    }
    public int sellStock(String item,int quantity){

        if(item != null){
            StockItem inStock = list.get(item);
            if(inStock != null && quantity > 0){
                return inStock.finalise(quantity);
            }
        }
//        if(item != null){
//            StockItem inStock = list.getOrDefault(item,null);
//            if((inStock != null) && (inStock.availableQuantity()>=quantity) && (quantity>0)){
//                inStock.adjustQuantity(-quantity);
//                return quantity;
//            }
//        }
        return 0;
    }
    public StockItem getStockItem (String key){
        return this.list.get(key);
    }
    public Map<String,StockItem> items(){
        return Collections.unmodifiableMap(this.list);
    }
    public int reserveStock(String item, int quantity){
        if(item != null){
            StockItem stockItem = list.get(item);
            if(stockItem != null && quantity > 0){
                return stockItem.reserveStock(quantity);
            }
        }
        return 0;
    }
    public int unReserveStock(String item, int quantity){
        if(item != null){
            StockItem stockItem = list.get(item);
            if(stockItem != null && quantity > 0){
                return stockItem.unReserveStock(quantity);
            }
        }
        return 0;
    }
    @Override
    public String toString() {
        String s ="\nStock List\n";
        double totalCost = 0.0;
        for(Map.Entry<String,StockItem> item : this.list.entrySet()){
            StockItem stockItem = item.getValue();
            double itemValue = stockItem.getPrice()*stockItem.availableQuantity();
            s = s + stockItem + ". There are "+ stockItem.availableQuantity() + " in stock. Value of items: ";
            System.out.println(s);
            s = s + String.format("%.2f",itemValue) + "\n";//two decimal numbers after floating number
            System.out.println(s);
            totalCost += itemValue;
        }
        return s + "Total stock value "+totalCost;
    }
}

