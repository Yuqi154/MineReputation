package org.hiedacamellia.minereputation.core.util;

public enum ReputationChangeType {
    DECREASE,
    INCREASE,
    NORMAL;


    public static ReputationChangeType calculate(int change){
        if(change>0){
            return INCREASE;
        }else if(change<0){
            return DECREASE;
        }else {
            return NORMAL;
        }
    }
}
