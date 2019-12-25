package com.example.myhead.second.common.utils;

import com.example.myhead.second.common.entity.Condition;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParameterProcess {

    public static Map<String, String> process(Map<String, String[]> parameterMap) {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; ; i++) {
            String searchValue = "columns[" + i + "][search][value]";
            String searchData = "columns[" + i + "][data]";

            String[] valueStrings = parameterMap.get(searchValue);
            String[] dataStrings = parameterMap.get(searchData);

            if (null == valueStrings || null == dataStrings) {
                return result;
            }

            if (dataStrings.length > 0 && !StringUtils.isEmpty(dataStrings[0]) && valueStrings.length > 0 && !StringUtils.isEmpty(valueStrings[0])) {
                result.put(dataStrings[0], valueStrings[0]);
            }
        }
    }


    public static List<Condition> process(Map<String, String[]> parameterMap, String op) {
        List<Condition> result = new ArrayList<>();
        String[] strings = parameterMap.get("search[value]");
        String value = "";
        if (null != strings && !StringUtils.isEmpty(strings[0])) {
            value = strings[0];
        }

        for (int i = 0; ; i++) {
            String searchValue = "columns[" + i + "][search][value]";
            String searchData = "columns[" + i + "][data]";

            String[] valueStrings = parameterMap.get(searchValue);
            String[] dataStrings = parameterMap.get(searchData);

            if (null == valueStrings || null == dataStrings) {
                return result;
            }

            if (dataStrings.length > 0 && !StringUtils.isEmpty(dataStrings[0])) {

                Condition condition = new Condition();
                condition.setFieldName(dataStrings[0]);
                condition.setOp(op);

                if (!StringUtils.isEmpty(value)) {
                    condition.setFieldValue(value);
                    result.add(condition);
                    continue;
                }

                if (valueStrings.length > 0 && !StringUtils.isEmpty(valueStrings[0])) {
                    condition.setFieldValue(valueStrings[0]);
                    result.add(condition);

                }


            }

        }
    }
}
