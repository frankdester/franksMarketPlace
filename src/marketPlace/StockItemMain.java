package marketPlace;

import java.util.Map;

public class StockItemMain {
    private static StockList stockList = new StockList();
    public static void main(String[] args) {
        StockItem stockItem= new StockItem("bread",0.86,100);
        stockList.addStock(stockItem);

        stockItem= new StockItem("cake",1.09,7);
        stockList.addStock(stockItem);

        stockItem= new StockItem("yogurt",0.5,20);
        stockList.addStock(stockItem);

        stockItem= new StockItem("merguez",0.9,30);
        stockList.addStock(stockItem);

        stockItem= new StockItem("bread",0.86,100);
        stockList.addStock(stockItem);

        stockItem= new StockItem("meat",2.32,21);
        stockList.addStock(stockItem);

        stockItem= new StockItem("fish",1.22,30);
        stockList.addStock(stockItem);

        stockItem= new StockItem("burger",0.61,10);
        //stockList.items().put(stockItem.getName(),stockItem);
        stockList.items().get("fish").adjustQuantity(255);


        stockItem= new StockItem("cheese",0.61,10);
        stockList.addStock(stockItem);

        stockItem= new StockItem("cheese",0.61,10);
        stockList.addStock(stockItem);
        System.out.println(stockList);
        for(StockItem item : stockList.items().values())
            System.out.println(item);
        for(String item : stockList.items().keySet())//while not having getKey()
            System.out.println(item);
        System.out.println(stockList.items());
        Basket franksBasket = new Basket("franksBasket");
        System.out.println(franksBasket);
        sellItem(franksBasket,"cheese",4);
        sellItem(franksBasket,"merguez",24);

        sellItem(franksBasket,"bread",4);
        sellItem(franksBasket,"yogurt",34);
        sellItem(franksBasket,"cheese",4);
        sellItem(franksBasket,"cheese",4);
//        if(sellItem(franksBasket,"cheese",4) != 4)
//            System.out.println("there is no more cheese!");
        System.out.println(franksBasket);
        sellItem(franksBasket,"mango",4);
        removeItem(franksBasket,"cheese",5);

        System.out.println(franksBasket);
        for(StockItem item : stockList.items().values())
            System.out.println(item);

    }

    public static int sellItem(Basket basket,String item,int quantity){
        StockItem stockItem = stockList.getStockItem(item);
        if(stockItem == null){
            System.out.println("We don't sell "+item);
            return 0;
        }
        if(stockList.reserveStock(item, quantity) != 0){
            return basket.addToBasket(stockItem,quantity);
        }
        return 0;
    }
    public static int removeItem(Basket basket,String item,int quantity){
        StockItem stockItem = stockList.getStockItem(item);
        if(stockItem == null){
            System.out.println("We don't sell "+item);
            return 0;
        }
        if(stockList.unReserveStock(item, quantity) <= 0){
            return basket.removeFromBasket(stockItem,quantity);
        }
        return 0;
    }
    public static void checkOut(Basket basket){
        for(Map.Entry<StockItem,Integer> item : basket.items().entrySet())
            stockList.sellStock(item.getKey().getName(),item.getValue());
        basket.clearBasket();
    }
}

