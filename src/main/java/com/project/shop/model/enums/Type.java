package com.project.shop.model.enums;

import lombok.Getter;

@Getter
public enum Type {

    USER("USER"),
    ADMIN("ADMIN");

    private final String TypeName;

    Type(String TypeName) {
        this.TypeName = TypeName;
    }

    public String getTypeName() {
        return TypeName;
    }
}
