package com.example.demodocker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardResponseDto {
    String title;
    String subtitle;
    String imageUri;
    List<ButtonDto> buttons;
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