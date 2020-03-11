package com.rolfie.webdetector.mappings.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmptyResponse extends Response {

    public EmptyResponse(String uri) {
        this.uri = uri;
    }
}
