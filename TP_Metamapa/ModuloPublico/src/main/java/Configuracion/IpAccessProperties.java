package Configuracion; 

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@ConfigurationProperties(prefix = "security.ip-access")
public class IpAccessProperties {

    private List<String> blacklist;
    private List<String> whitelist;
    
    public List<String> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(List<String> blacklist) {
        this.blacklist = blacklist;
    }

    public List<String> getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(List<String> whitelist) {
        this.whitelist = whitelist;
    }
}