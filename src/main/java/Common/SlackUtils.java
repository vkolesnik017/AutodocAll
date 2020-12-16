package Common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.Serializable;

@AllArgsConstructor
@Builder(builderClassName = "Builder")
@Getter
@Setter

public class SlackUtils implements Serializable {

    private String as_user;
    private String channel;
    private String username;
    private String text;
    private String icon_emoji;

    private static String slackWebhookUrl = "https://hooks.slack.com/services/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";

    public static void sendMessage(SlackUtils message) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(slackWebhookUrl);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(message);

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            client.execute(httpPost);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

