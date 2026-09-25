import java.util.Scanner;

interface IDiscountable{
    double getDiscountPrice();
}

abstract class Product implements IDiscountable{
    protected String id;
    protected String name;
    protected double originPrice;
    public Product(String id, String name, double originPrice){
        this.id = id;
        this.name = name;
        this.originPrice  = originPrice;
    }
    public String getID(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public double getOriginPrice(){
        return this.originPrice;
    }
    @Override
    public abstract double getDiscountPrice();
}

class ElectronicProduct extends Product{
    public ElectronicProduct(String id, String name, double originPrice){
        super(id, name, originPrice);
    }
    @Override
    public double getDiscountPrice(){
        return getOriginPrice() * 0.95d;
    }
}


class FashionProduct extends Product{
    public FashionProduct(String id, String name, double originPrice){
        super(id, name, originPrice);
    }
    @Override
    public double getDiscountPrice(){
        return getOriginPrice() * 0.8d;
    }
}

public class MainExcercise3{
    private static Scanner sc = new Scanner(System.in);
    private static Product[] listPro = new Product[5001];
    private static int i = 0;
    public static void main(String[] args){
        listPro[i++] = new ElectronicProduct("1000", "Laptop Dell", 10000000);
        listPro[i++] = new FashionProduct("2000", "Áo khoắc Jean", 1000000);
        inDong();
        System.out.printf("|%50s|%n", centerText("BANG GIA SAN PHAM KHUYEN MAI", 50));
        inDong();
        for(int j = 0; j < i; j++){
            inDongSanPham(listPro[j]);
        }
        inDong();
    }
    private static void inDong() {
        System.out.println("+--------------------------------------------------+");
    }
    private static void inDongSanPham(Product sp) {
        String tenFormat = String.format("[%s]", sp.getName());
        System.out.printf("| %-20s Niem yet: %.2f | Sau KM: %.2f|%n",
                tenFormat,
                sp.getOriginPrice(),
                sp.getDiscountPrice());
    }

    // Định dạng số có dấu phẩy ngăn cách hàng nghìn
    private static String formatTien(long soTien) {
        return String.format("%,d", soTien);
    }

    // Căn giữa chuỗi trong độ rộng cho trước
    private static String centerText(String text, int width) {
        int padding = width - text.length();
        int padStart = padding / 2 + text.length();
        return String.format("%" + padStart + "s%" + (width - padStart) + "s", text, "");
    }
}
