package com.example.demodocker.dto;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DialogFlowCardReponseDto {
    List<CardDto> fulfillmentMessages;
}

/*
{
        "fulfillmentMessages": [
            {
            "card": {
                    "title": "card title",
                    "subtitle": "card text",
                    "imageUri": "https://example.com/images/example.png",
                    "buttons": [
                            {
                            "text": "button text",
                            "postback": "https://example.com/path/for/end-user/to/follow"
                            }
                        ]
                   }
            }
        ]
}
 */