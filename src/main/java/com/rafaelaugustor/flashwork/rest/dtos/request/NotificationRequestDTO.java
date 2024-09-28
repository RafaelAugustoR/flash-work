package com.rafaelaugustor.flashwork.rest.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDTO {

    private String content;
    private UUID receiver;
    private UUID sender;
}
