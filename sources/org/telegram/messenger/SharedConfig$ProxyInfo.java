package org.telegram.messenger;

public class SharedConfig$ProxyInfo {
    public String address;
    public boolean available;
    public long availableCheckTime;
    public boolean checking;
    public String password;
    public long ping;
    public int port;
    public long proxyCheckPingId;
    public String secret;
    public String username;

    public SharedConfig$ProxyInfo(String a, int p, String u, String pw, String s) {
        this.address = a;
        this.port = p;
        this.username = u;
        this.password = pw;
        this.secret = s;
        if (this.address == null) {
            this.address = "";
        }
        if (this.password == null) {
            this.password = "";
        }
        if (this.username == null) {
            this.username = "";
        }
        if (this.secret == null) {
            this.secret = "";
        }
    }
}
