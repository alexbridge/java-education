package net;

import java.net.*;

public class Urls {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://google.com:443/path");

        assert url.getProtocol().equals("https");
        assert url.getHost().equals("google.com");
        assert url.getPort() == 443;
        assert url.getPath().equals("/path");

        httpConnection();
    }

    public static void httpConnection() {
        try {
            URL url = new URL("http://google.com");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            for (int i = 1; i <= 8; i++) {
                System.out.printf("%s: %s\n", huc.getHeaderFieldKey(i), huc.getHeaderField(i));
            }
            huc.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            InetAddress ip = InetAddress.getByName("google.com");
            System.out.println("Host Name: " + ip.getHostName());
            System.out.println("IP Address: " + ip.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
