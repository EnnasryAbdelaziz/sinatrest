package eai.devass.sinistreat.appli.restClient;

public class Banda {

    private String url="";
    private String jsonRequest="";
    private boolean withProxy=true;
    private String proxyHost="";
    private String proxyPort="";
    private String timeout="";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJsonRequest() {
        return jsonRequest;
    }

    public void setJsonRequest(String jsonRequest) {
        this.jsonRequest = jsonRequest;
    }

    public boolean isWithProxy() {
        return withProxy;
    }

    public void setWithProxy(boolean withProxy) {
        this.withProxy = withProxy;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }
}
