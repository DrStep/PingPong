package testREST.ResponseJSON;

/**
 * Created by stepa on 29.06.15.
 */
public class Result {

    String result;

    public Result(String result, int numberOfCalls) {
        this.result = result + " " + numberOfCalls;
    }

    public String getResult() {
        return this.result;
    }
}
