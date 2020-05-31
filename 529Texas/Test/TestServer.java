import java.rmi.Naming;


public class TestServer {

    public static void main(String args[]){
        try {
            String[] sl = Naming.list("rmi://115.205.19.47:12200");
            for(int i=0;i<sl.length;i++) {
                System.out.println(sl[i]);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}