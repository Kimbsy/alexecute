package alexecute.request;

import java.util.List;

/**
 * This class represents a alexecute.request for a machine to perform an action.
 *
 * @author kimbsy
 */
public class Request {

    private RequestType requestType;
    private List<String> arguments;

    /**
     * Class constructor specifying the {@link RequestType} and the list of arguments.
     *
     * @param requestType The type of alexecute.request.
     * @param arguments   List of arguments.
     */
    public Request(RequestType requestType, List<String> arguments) {
        this.requestType = requestType;
        this.arguments = arguments;
    }

    /**
     * Get the alexecute.request type.
     *
     * @return The alexecute.request type.
     */
    public RequestType getRequestType() {
        return requestType;
    }

    /**
     * Set the alexecute.request type.
     *
     * @param requestType The alexecute.request type.
     */
    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    /**
     * Get the list of arguments.
     *
     * @return The list of arguments.
     */
    public List<String> getArguments() {
        return arguments;
    }

    /**
     * Set the list of arguments.
     *
     * @param arguments The list of arguments.
     */
    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (requestType != request.requestType) return false;
        return arguments != null ? arguments.equals(request.arguments) : request.arguments == null;
    }

    @Override
    public int hashCode() {
        int result = requestType != null ? requestType.hashCode() : 0;
        result = 31 * result + (arguments != null ? arguments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("requestType", requestType)
                .append("arguments", arguments)
                .toString();
    }
}
