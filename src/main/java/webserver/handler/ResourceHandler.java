package webserver.handler;

import utils.FileIoUtils;
import webserver.http.*;

public class ResourceHandler implements Handler {
    @Override
    public void handling(HttpRequest httpRequest, HttpResponse httpResponse) {
        try {
            Extension extension = httpRequest.getExtension();
            httpResponse.setStatusCode(HttpStatus.OK);
            httpResponse.addHeader("Content-Type", extension.getContentType());
            httpResponse.setBody(FileIoUtils.loadFileFromClasspath(extension.getBasicFilePath() + httpRequest.getPath()));
        } catch (Exception e) {
            httpResponse = HttpResponse.error();
        }
    }
}
