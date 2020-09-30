package hetu;

import java.security.MessageDigest;

/**
 * Created by jiangchao08 on 16/11/24.
 */
public class InnerToken {

    private String tokenType;
    private String appid;
    private String uid;
    private String sk;
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public InnerToken(String tokenType, String appid, String uid, String sk) {
        this.tokenType = tokenType;
        this.appid = appid;
        this.uid = uid;
        this.sk = sk;
        this.time = System.currentTimeMillis() / 1000;
    }

    public String getSignString() {
        String str = this.getTime() + this.getUid() + this.getAppid() + this.getSk();
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    //生成浏览器端token
    public String generateBrowserToken() {
        String sign = getSignString();
        String res = "";
        if (this.getTokenType() != null && sign != null && this.getUid() != null && this.getAppid() != null) {
            res = this.getTokenType() + "." + sign + "." + this.getTime() + "." + this.getAppid();
        }
        return res;
    }

    //生成服务器端token
    public String generateServerToken() {
        String sign = getSignString();
        String res = "";
        if (this.getTokenType() != null && sign != null && this.getUid() != null && this.getAppid() != null) {
            res = this.getTokenType() + "." + sign + "." + this.getTime() + "." + this.getUid() + "-" + this.getAppid();
        }
        return res;
    }

    public static void main(String args[]) {
        //浏览器端
        //InnerToken innerToken = new InnerToken("10", "8939204", "0FA64088CA4B479531A849B0F1F111B8:FG=1", "NT2NKUXOdDQxEzvCHOnndMk78UGZhmab");
        //System.out.println("token:" + innerToken.generateBrowserToken());

        //服务器端
        InnerToken innerToken = new InnerToken("11", "17781254", "1884599608", "AZf0ptN0ThZbueNEPDzdvWWOmpk92d3n");
        System.out.println("token:" + innerToken.generateServerToken());
    }

}
