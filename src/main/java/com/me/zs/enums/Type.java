package com.me.zs.enums;

public enum Type {
    A股("A股",11),
    B股("B股",12),
    权证("权证",13),
    债券("债券",15),
    ETF("ETF",22),
    LOF("LOF",23),
    货基("货基",24),
    QDII("QDII",25),
    港股("港股",31),
    港指数("港指",32),
    美股("美股",41),
    开基("开基",21),
    外汇("外汇",71),
    其他("其他",999)
    ;
    private String name;
    private Integer index;

    private Type(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public static Type getByCode(Integer code){
        Type[] types = Type.values();
        for(Type type : types){
            if(type.index == code){
                return type;
            }
        }
        return Type.其他;
    }
}
