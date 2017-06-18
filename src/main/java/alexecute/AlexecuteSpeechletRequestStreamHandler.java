package alexecute;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kimbsy
 */
public class AlexecuteSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<>();

    static {
        supportedApplicationIds.add("amzn1.ask.skill.69c48753-1835-419f-8a4b-d412755ae8b9");
    }

    public AlexecuteSpeechletRequestStreamHandler() {
        super(new AlexecuteSpeechlet(), supportedApplicationIds);
    }
}
