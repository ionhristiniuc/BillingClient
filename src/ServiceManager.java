/**
 * Created by Mihai on 5/16/2015.
 */
public class ServiceManager
{
    private static ServiceManager manager = null;
    ServiceManager() {

    }
    public static ServiceManager getManager() {
        if (manager == null) {
            manager = new ServiceManager();
        }
        return manager;
    }
    boolean Authenticate(String number) {

        return true;
    }
    String getBalance(String number) {

        return "10";
    }
}

