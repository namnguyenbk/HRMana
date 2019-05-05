package com.springweb.application.controller.util;

import java.util.ArrayList;
import java.util.HashMap;

public class ValidateUtil {
    public  static String checkRequiredParam(HashMap<String, Object> dataInput, ArrayList<String> requiredParam){
        for ( String param : requiredParam ) {
            if(dataInput.get(param) != null && !dataInput.get(param).equals("")){
                return param;
            }
        }
        return null;
    }
}
