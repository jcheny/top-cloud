package com.ihave.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/26 下午3:29
 */
public class UserOAuth2ExceptionSerializer extends StdSerializer<UserOAuth2Exception> {

    protected UserOAuth2ExceptionSerializer() {
        super(UserOAuth2Exception.class);
    }

    @Override
    public void serialize(UserOAuth2Exception e, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeStartObject();
        generator.writeObjectField("code", e.getHttpErrorCode());
        String message = e.getMessage();
        if (message != null) {
            message = HtmlUtils.htmlEscape(message);
        }
        generator.writeStringField("data", message);
        if (e.getAdditionalInformation() != null) {
            for (Map.Entry<String, String> entry : e.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                generator.writeStringField(key, add);
            }
        }
        generator.writeEndObject();
    }
}
