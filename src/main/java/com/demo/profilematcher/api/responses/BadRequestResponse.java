package com.demo.profilematcher.api.responses;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class BadRequestResponse extends JsonResponse {

    public BadRequestResponse(String message) {
        super("400", message);
    }

}
