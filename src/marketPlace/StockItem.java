package marketPlace;

public class StockItem implements Comparable<StockItem>{
    private final String name;
    private double price;
    private int quantityInStock;
    private int reserve = 0;


    public StockItem(String name, double price){
        this.name = name;
        this.price = price;
    }
    public StockItem(String name, double price,int quantityInStock){
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }
    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
    public int availableQuantity(){
        return this.quantityInStock - this.reserve;
    }
    public int reserveStock(int quantity){
        if(quantity <= availableQuantity()){
            this.reserve += quantity;
            return quantity;
        }
        return 0;
    }
    public int unReserveStock(int quantity){
        if(quantity <= this.reserve){
            this.reserve -= quantity;
            return quantity;
        }
        return 0;
    }
    public int finalise(int quantity){
        if(quantity <= this.reserve){
            this.quantityInStock -= quantity;
            this.reserve -= quantity;
            return quantity;
        }
        return 0;
    }
    public void setPrice(int price){
        if(price >= 0)
            this.price = price;
    }
    public void adjustQuantity(int quantity){
        int newQuantity = this.quantityInStock + quantity;
        if(newQuantity >= 0)
            this.quantityInStock = newQuantity;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode()+31;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null)||(obj.getClass() != this.getClass()))
            return false;

        String theObj = ((StockItem)obj).getName();
        return this.name.equals(theObj);
    }

    @Override
    public String toString() {
        return this.name+" price:"+this.price+". Reserved: "+this.reserve ;
    }

    @Override
    public int compareTo(StockItem o) {
        if(this==o)
            return 0;
        if(o != null){
            return this.name.compareTo(o.getName());
        }
        throw new NullPointerException();
    }
}

