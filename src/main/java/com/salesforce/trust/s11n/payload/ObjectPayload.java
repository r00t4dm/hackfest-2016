package com.salesforce.trust.s11n.payload;

public interface ObjectPayload <T>{

    T getObject(String command) throws Exception;

}
