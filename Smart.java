import java.awt.image.SampleModel;
import java.util.Scanner;

interface RemoteControl{
    void powerOn();
    void powerOff();
}

interface EnergyMonitor{
    double getDailyPowerConsumptionKWh();
}

abstract class SmartDevice implements RemoteControl, EnergyMonitor{
    protected String id;
    protected String name;
    protected boolean isOn = false;
    public SmartDevice(String id, String name, boolean isOn){
        this.id = id;
        this.name = name;
        this.isOn = isOn;
    }
    @Override
    public abstract void powerOn();
    @Override
    public abstract void powerOff();
    @Override
    public abstract double getDailyPowerConsumptionKWh();
    public boolean stateDevice(){
        return this.isOn;
    }
    public String getName(){
        return this.name;
    }
    public void display(){
        System.out.printf("[%s] %s | Trạng thái: %s| %.2f KWh\n", this.id, this.name, stateDevice()?"ON":"Off", getDailyPowerConsumptionKWh());
    }
}

class SmartAirConditioner extends SmartDevice{
    public SmartAirConditioner(String id, String name, boolean isOn){
        super(id, name, isOn);
    }
    @Override
    public void powerOn(){
        this.isOn = true;
    }
    @Override
    public void powerOff(){
        this.isOn = false;
    }
    @Override
    public double getDailyPowerConsumptionKWh(){
        if(this.isOn) return 12.0d;
        return 0.0d;
    }
}

class SmartLight extends SmartDevice{
    public SmartLight(String id, String name, boolean isOn){
        super(id, name, isOn);
    }
    @Override
    public void powerOn(){
        this.isOn = true;
    }
    @Override
    public void powerOff(){
        this.isOn = false;
    }
    @Override
    public double getDailyPowerConsumptionKWh(){
        if(this.isOn) return 5.0d;
        return 0.0d;
    }
}
public class Smart{
    private static SmartDevice[] listD = new SmartDevice[2];
    private static int i = 0;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        SmartDevice sac = new SmartAirConditioner("AC-01", "Điều hòa DaiKin 18000BTU", false );
        listD[i++] = sac;
        SmartDevice sl = new SmartLight("LT-01", "Đèn LED philips", false);
        listD[i++] = sl;
        for(int j = 0; j<i; j++){
            if(listD[j] instanceof SmartAirConditioner){
                System.out.printf("Đây điều hòa thông minh: %s\n", listD[j].getName());
                String isOn;
                while(true){
                    System.out.print("Bạn có muốn bật nó không: ");
                    isOn = sc.nextLine();
                    if(isOn.equals("yes") || isOn.equals("no")) break;
                    System.out.println("Sai định dạng");
                }
                if (isOn.equals("yes")) {
                    listD[j].powerOn();
                }
            }
            else{
                System.out.printf("Đây đèn thông minh: %s\n", listD[j].getName());
                String isOn;
                while(true){
                    System.out.print("Bạn có muốn bật nó không: ");
                    isOn = sc.nextLine();
                    if(isOn.equals("yes") || isOn.equals("no")) break;
                    System.out.println("Sai định dạng");
                }
                if (isOn.equals("yes")) {
                    listD[j].powerOn();
                }
            }
        }
        System.out.println("Dây là kết quả: ");
        System.out.println("      Giám sát tòa nhà thông minh         ");
        double sum = 0d;
        for(SmartDevice x : listD){
            x.display();
            sum += x.getDailyPowerConsumptionKWh();
        }
        System.out.printf("Tổng điện năng tiêu thụ : %.2f kWh", sum);
    }
}
