package bean;

/**
 *  { "message_type":"register_client","client_type":3,"mac_address":"","user_name":"","password":""}
 */
public class RegisterInfo {


    /**
     * message_type : register_client
     * client_type : 3
     * mac_address :
     * user_name :
     * password :
     */

    private String message_type;
    private int client_type;
    private String mac_address;
    private String user_name;
    private String password;

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public int getClient_type() {
        return client_type;
    }

    public void setClient_type(int client_type) {
        this.client_type = client_type;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
