package webserver.http;

import utils.KeyValueParser;

import java.util.HashMap;
import java.util.Map;

public class Uri {
    private String path;
    private Extension extension;
    private Map<String, String> attributes = new HashMap<>();

    public Uri(String uri) {
        String[] split = uri.split("\\?");
        this.path = split[0];
        this.extension = initExtension();
        if (split.length > 1) {
            this.attributes = KeyValueParser.parse(split[1], "&");
        }
    }

    private Extension initExtension() {
        if (path.contains(".")) {
            return Extension.from(path.substring(path.lastIndexOf(".")));
        }
        return null;
    }

    public String getPath() {
        return this.path;
    }

    public Extension getExtension() {
        return this.extension;
    }

    public String getAttribute(String key) {
        return attributes.get(key);
    }
}
