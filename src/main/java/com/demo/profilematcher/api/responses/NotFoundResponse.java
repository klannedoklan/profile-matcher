package com.demo.profilematcher.api.responses;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class NotFoundResponse extends JsonResponse {

    public NotFoundResponse(String message) {
        super("404", message);
    }

}
