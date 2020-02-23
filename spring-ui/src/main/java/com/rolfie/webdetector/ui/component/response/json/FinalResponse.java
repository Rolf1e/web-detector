package com.rolfie.webdetector.ui.component.response.json;

import com.rolfie.webdetector.retriever.infra.UrlHolder;
import com.rolfie.webdetector.ui.component.job.JobHandler;

public class FinalResponse implements JsonResponse {

    private JobHandler handler;

    public FinalResponse(JobHandler handler) {
        this.handler = handler;
    }

    @Override
    public String getJson() {
        return "\"uri\":\"" + getUri() + "\"{"
                + getJsonBody()
                + "}";
    }

    public String getJsonBody() {
        StringBuilder body = new StringBuilder();

        for (JsonResponse response : handler.getJobsToDo()) {
            body.append(response.getJson())
                    .append(",");
        }

        return body.toString();
    }


    private String getUri() {
        return UrlHolder.getInstance().getUrl();
    }

}
