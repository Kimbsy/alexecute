package alexecute;

import alexecute.request.Request;
import alexecute.request.RequestType;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;

import java.util.List;

/**
 * @author kimbsy
 */
public class MessageSender {

    static MessageSender INSTANCE = new MessageSender();

    private static AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
    private static String QUEUE_URL = "https://sqs.eu-west-1.amazonaws.com/076309951820/alexecute";
    private static ObjectMapper mapper = new ObjectMapper();

    void sendMessage(RequestType requestType, List<String> arguments) throws JsonProcessingException {
        Preconditions.checkNotNull(requestType, "the request type cannot be null");

        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(QUEUE_URL)
                .withMessageBody(mapper.writeValueAsString(new Request(requestType, arguments)))
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);
    }
}
