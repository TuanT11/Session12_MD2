import java.util.Scanner;

interface ISecureTransaction{
    boolean authenticate(String PIN);
    boolean executeTransaction(double amount);
}

class InValidException extends RuntimeException{
    public InValidException(String ex){
        super(ex);
    }
    @Override
    public String getMessage(){
        return super.getMessage();
    }
}

class NotEqualsException extends RuntimeException{
    public NotEqualsException(String ex){
        super(ex);
    }
    @Override
    public String getMessage(){
        return super.getMessage();
    }
}

class CheckNumberAmount {
    public static boolean checkNumberAmount(String amount) {
        try {
            double res = Double.parseDouble(amount);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

class OnlineBankTransaction implements ISecureTransaction{
    private String validPIN = "1234";
    private boolean isAuthenticate = false;
    private double balance = 10000000.0d;
    public boolean authenticate(String PIN){
        if(this.validPIN.equals(PIN)){
            return true;
        }
        throw new InValidException("Mã PIN không tồn tai!");
    }
    public boolean executeTransaction(double amount){
        if(this.balance < amount){
            throw new NotEqualsException("Số dư không đủ để thực hiện giao dịch !");
        }
        this.balance -= amount;
        return true;
    }

    public double getBalance(){
        return this.balance;
    }
}
public class Bank{
    private static Scanner sc = new Scanner(System.in);
    private static OnlineBankTransaction banker = new OnlineBankTransaction();
    public static void main(String[] args){
        while(true){
            System.out.print("Nhập mã PIN của bạn: ");
            String pin = sc.nextLine();
            if(banker.authenticate(pin)){
                while(true){
                    System.out.print("Nhập số tiền cần rút: ");
                    String amount = sc.nextLine();
                    if(CheckNumberAmount.checkNumberAmount(amount)){
                        banker.executeTransaction(Double.parseDouble(amount));
                        System.out.println("Banj đã rút tiền thành công!");
                        System.out.println("Số tiền hiện tại của bạn là: " + banker.getBalance());
                        break;
                    }
                    else System.out.println("Vui nhập con số hợp lệ!");
                }
            }
            String line;
            while(true){
                System.out.print("Nhập continue để tiếp tục hoặc exit để rời đi: ");
                line = sc.nextLine();
                if(line.equals("continue") || line.equals("exit")) break;
                System.out.println("Nhập sai định dạng. ");
            }
            if(line.equals("exit")) break;
        }
    }
}

