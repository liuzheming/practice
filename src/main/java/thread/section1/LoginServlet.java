package thread.section1;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/22.
 */
public class LoginServlet {

    private static String usernameRef;

    private static String passwordRef;

    public static void doPost(String username, String password) {
        try {
            usernameRef = username;
            if (usernameRef.equals("a")) {
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("username=" + usernameRef + "; password=" + passwordRef);
        } catch (Exception e) {

        }

    }

}
