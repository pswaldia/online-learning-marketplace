package com.example.demodocker.services;

import com.example.demodocker.entity.Course;
import com.google.api.services.dialogflow.v2beta1.model.GoogleCloudDialogflowV2beta1IntentMessageCard;
import org.springframework.stereotype.Service;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dialogflow.v2beta1.model.GoogleCloudDialogflowV2EventInput;
import com.google.api.services.dialogflow.v2beta1.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2beta1.model.GoogleCloudDialogflowV2WebhookResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Service
public class DialogFlowWebhookResponseService {

    private static JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();

    @Autowired
    private CourseService courseService;

    public ResponseEntity<?> dialogFlowWebHook(String requestStr, Course course) throws IOException {
        try {
            GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
            GoogleCloudDialogflowV2WebhookRequest request = jacksonFactory.createJsonParser(requestStr).parse(GoogleCloudDialogflowV2WebhookRequest.class);
            GoogleCloudDialogflowV2beta1IntentMessageCard cardResponse = new GoogleCloudDialogflowV2beta1IntentMessageCard();
            cardResponse.setTitle(course.getName());
            response.setFulfillmentText(cardResponse.toPrettyString());
            cardResponse.setSubtitle(course.getShortDescription());
            cardResponse.setImageUri("https://camo.githubusercontent.com/349292960f68b0b78d21b82142a65cb16bb7d1abefcf9f18cba68f50e571c6d1/68747470733a2f2f696d6775722e636f6d2f34694f625431382e706e67");
            Map<String,Object> params = request.getQueryResult().getParameters();
            if (params.size() > 0) {
                System.out.println(params.keySet());
            }
            else {
                response.setFulfillmentText("Sorry you didn't send enough to process");
            }
            return new ResponseEntity<GoogleCloudDialogflowV2WebhookResponse>(response,HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
