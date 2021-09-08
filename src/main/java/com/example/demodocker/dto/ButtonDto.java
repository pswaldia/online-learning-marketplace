package com.example.demodocker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Struct;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButtonDto {
    String text;
    String postback;
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