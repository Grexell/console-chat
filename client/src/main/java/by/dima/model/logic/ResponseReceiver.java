package by.dima.model.logic;

import by.dima.model.logic.request.RequestHandler;
import by.dima.model.logic.request.RequestHandlerBuilder;

public class ResponseReceiver implements Runnable {

    private RequestHandler requestHandler;
    private boolean working;


    public ResponseReceiver() {
        this(RequestHandlerBuilder.build(RequestHandlerBuilder.MESSAGE_COMMAND));
    }

    public ResponseReceiver(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        working = true;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    @Override
    public void run() {
        while (working) {
            requestHandler.handle(new String());
        }
    }
}
