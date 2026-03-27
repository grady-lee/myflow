package com.lee.enums;

public enum Gender {

    MAN(1,"男"),
    FEMALE(0,"女");

    private final Integer code;
    private final String desc;

    Gender(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    // 根据code获取
    public static Gender fromCode(Integer code) {
        if(code == null){
            return null;
        }
/*        int c;
        try {
            c = Integer.parseInt(code.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid Gender code" + code);
        }*/
        for (Gender gender : Gender.values()) {
            if(code == gender.getCode()){
                return gender;
            }
        }
        throw new IllegalArgumentException("Unkown gender code:" + code);
    }

}
