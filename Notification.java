interface INotification{
    void sendNotification(String recipient, String message);
}

class EmailNotification implements INotification{
    @Override
    public void sendNotification(String recipient, String message){
        System.out.println("[Email to " + recipient + "] Title: CRM Notice -> Body: " + message+".");
    }
}
class SMSNotification implements INotification{
    public void sendNotification(String recipient, String message){
        System.out.println( "[SMS to " + recipient + "] Message: " + message+".");
    }
}
public class Notification{
    private static INotification[] listNot = new INotification[5001];
    private static int i = 0;
    public static void main(String[] args){
        listNot[i++] = new EmailNotification();
        listNot[i++] = new SMSNotification();
        for(int j = 0; j < i; j++){
            if(listNot[j] instanceof EmailNotification){
                listNot[j].sendNotification(" an.nguyen@rikkei.edu.vn", "Chao An Nguyen");
            }
            else{
                listNot[j].sendNotification("0987654321", "Who are you ?");
            }
        }
    }
}
