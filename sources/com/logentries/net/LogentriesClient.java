package com.logentries.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class LogentriesClient {
    private static final String LE_HTTP_API = "http://js.logentries.com/v1/logs/";
    private static final int LE_PORT = 80;
    private static final int LE_SSL_PORT = 443;
    private static final String LE_TOKEN_API = "data.logentries.com";
    static final Charset UTF8 = Charset.forName("UTF-8");
    private int dataHubPort = 0;
    private String dataHubServer = null;
    private String endpointToken;
    private boolean httpChoice = false;
    private HttpClient httpClient;
    private HttpPost postRequest;
    private Socket socket;
    private boolean sslChoice = false;
    private final SSLSocketFactory sslFactory;
    private OutputStream stream;
    private StringBuilder streamFormatter = new StringBuilder();
    private boolean useDataHub = false;

    public LogentriesClient(boolean z, boolean z2, boolean z3, String str, int i, String str2) {
        if (z && z3) {
            throw new IllegalArgumentException("'httpPost' parameter cannot be set to true if 'isUsingDataHub' is set to true.");
        } else if (z && z2) {
            throw new IllegalArgumentException("'httpPost' parameter cannot be set to true if 'useSsl' is set to true.");
        } else if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException("Token parameter cannot be empty!");
        } else {
            this.useDataHub = z3;
            this.sslChoice = z2;
            this.httpChoice = z;
            this.endpointToken = str2;
            if (this.useDataHub) {
                if (str == null || str.isEmpty()) {
                    throw new InstantiationException("'server' parameter is mandatory if 'isUsingDatahub' parameter is set to true.");
                } else if (i <= 0 || i > 65535) {
                    throw new InstantiationException("Incorrect port number " + Integer.toString(i) + ". Port number must " + "be greater than zero and less than 65535.");
                } else {
                    this.dataHubServer = str;
                    this.dataHubPort = i;
                }
            }
            if (z2) {
                try {
                    this.sslFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                    return;
                } catch (Exception e) {
                    throw new InstantiationException("Cannot create LogentriesClient instance. Error: " + e.getMessage());
                }
            }
            this.sslFactory = null;
        }
    }

    public void close() {
        try {
            if (this.socket != null) {
                this.socket.close();
                this.socket = null;
            }
        } catch (Exception e) {
        }
    }

    public void connect() {
        if (this.httpChoice) {
            this.httpClient = new DefaultHttpClient();
            this.postRequest = new HttpPost(getAddress() + this.endpointToken);
            return;
        }
        Socket socket = new Socket(getAddress(), getPort());
        if (!this.sslChoice) {
            this.socket = socket;
        } else if (this.sslFactory == null) {
            throw new IllegalArgumentException("SSL Socket Factory is not initialized!");
        } else {
            SSLSocket sSLSocket = (SSLSocket) this.sslFactory.createSocket(socket, getAddress(), getPort(), true);
            sSLSocket.setTcpNoDelay(true);
            this.socket = sSLSocket;
        }
        this.stream = this.socket.getOutputStream();
    }

    public String getAddress() {
        return this.useDataHub ? this.dataHubServer : this.httpChoice ? LE_HTTP_API : LE_TOKEN_API;
    }

    public int getPort() {
        return this.useDataHub ? this.dataHubPort : this.sslChoice ? LE_SSL_PORT : LE_PORT;
    }

    public void write(String str) {
        if (this.httpChoice) {
            this.postRequest.setEntity(new StringEntity(str, "UTF8"));
            this.httpClient.execute(this.postRequest);
        } else if (this.stream == null) {
            throw new IOException("OutputStream is not initialized!");
        } else {
            this.streamFormatter.setLength(0);
            this.streamFormatter.append(this.endpointToken).append(" ");
            this.streamFormatter.append(str);
            if (!str.endsWith("\n")) {
                this.streamFormatter.append("\n");
            }
            this.stream.write(this.streamFormatter.toString().getBytes(UTF8));
            this.stream.flush();
        }
    }
}
