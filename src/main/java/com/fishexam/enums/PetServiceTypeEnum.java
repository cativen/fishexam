package com.fishexam.enums;

public enum PetServiceTypeEnum {
    WASH(1, "洗护"),
    REMOVE_WORM(2, "驱虫"),
    BUY_FOOD(3, "购粮"),
    ADOPT(4, "寄养");



    private int code;
    private String text;

    PetServiceTypeEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * 判断状态码是否相同.
     *
     * @param code   状态码.
     * @param status 枚举.
     */
    public static boolean isEquals(String code, PetServiceTypeEnum status) {
        if (code == null || status == null) {
            return false;
        }
        return code.equals(status.getCode());
    }



    public String getText() {
        return text;
    }

    /**
     * 通过状态码获取枚举内容.
     *
     * @param code 状态码.
     */
    public static String getText(int code) {
        PetServiceTypeEnum status = get(code);
        if (status == null) {
            return null;
        }
        return status.getText();
    }

    /**
     * 通过状态码获取枚举.
     *
     * @param code 状态码.
     */
    public static PetServiceTypeEnum get(int code) {
        for (PetServiceTypeEnum item : PetServiceTypeEnum.values()) {
            if (item.getCode()==code) {
                return item;
            }
        }
        return null;
    }

    public static int getCode(String text) {
        if (text != null) {
            for (PetServiceTypeEnum item : PetServiceTypeEnum.values()) {
                if (item.getText().equals(text)) {
                    return item.getCode();
                }
            }
        }
        return 0;
    }
}
