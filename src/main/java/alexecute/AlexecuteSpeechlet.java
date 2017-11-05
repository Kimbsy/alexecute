package alexecute;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kimbsy.dispatch.lib.request.RequestType;

import java.util.Collections;

/**
 * @author kimbsy
 */
public class AlexecuteSpeechlet implements Speechlet {

    private static Logger log = LoggerFactory.getLogger(AlexecuteSpeechlet.class);

    private static final String SLOT_SHOW = "show";

    public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", sessionStartedRequest.getRequestId(), session.getSessionId());
    }

    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", launchRequest.getRequestId(), session.getSessionId());
        return getWelcomeResponse();
    }

    public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", intentRequest.getRequestId(), session.getSessionId());

        Intent intent = intentRequest.getIntent();
        String intentName = intent.getName();

        switch (intentName) {
            case "WatchNextIntent":
                return getWatchNextResponse(intent);
            case "AMAZON.HelpIntent":
                return getHelpResponse();
            default:
                throw new SpeechletException("Invalid Intent");
        }
    }

    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {

    }

    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Dispatch can be used to send commands to machines on your local network.";

        SimpleCard card = new SimpleCard();
        card.setTitle("Dispatch Start invoked");
        card.setContent(speechText);

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SpeechletResponse getWatchNextResponse(Intent intent) {
        String showName = intent.getSlot(SLOT_SHOW).getValue();
        showName = showName.replaceAll(" ", "-");

        //@TODO: ensure show is on list?

        String speechText;
        try {
            MessageSender.INSTANCE.sendMessage(RequestType.WATCH_NEXT, Collections.singletonList(showName));
            speechText = String.format("Attempting to play the next episode of %s.", showName);
        } catch (JsonProcessingException e) {
            speechText = String.format("Unable to play the next episode of %s.", showName);
            log.error(speechText, e);
        }

        SimpleCard card = new SimpleCard();
        card.setTitle("Sending dispatch request");
        card.setContent(speechText);

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SpeechletResponse getHelpResponse() {
        String speechText = "Dispatch can be used to send commands to machines on your local network.";

        SimpleCard card = new SimpleCard();
        card.setTitle("Dispatch help");
        card.setContent(speechText);

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
}
