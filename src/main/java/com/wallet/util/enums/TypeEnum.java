package com.wallet.util.enums;

public enum TypeEnum {

    EN("ENTRADA"), SD("SAÍDA");

    private final String type;

    TypeEnum(String type) {
        this.type = type;
    }

    public String getValue(){
        return this.type;
    }

    public static TypeEnum getEnum(String type){
        for(TypeEnum t : values()){
            if(type.equals(t.getValue())){
                return t;
            }
        }
        return null;
    }
}
