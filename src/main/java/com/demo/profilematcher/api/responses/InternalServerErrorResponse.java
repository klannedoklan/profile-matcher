package com.demo.profilematcher.api.responses;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class InternalServerErrorResponse extends JsonResponse {

    public InternalServerErrorResponse(String message) {
        super("500", message);
    }

}
