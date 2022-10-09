package org.swe632.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Response {
    public enum Type{OK, FAILED}

    private Type type;
    private String message;
    private Object object;
}

