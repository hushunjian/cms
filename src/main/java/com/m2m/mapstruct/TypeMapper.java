package com.m2m.mapstruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeMapper {
    public Integer[] split(String target) {
        List<Integer> rtn = new ArrayList<>();
        if (target == null) {
            return new Integer[0];
        }
        String[] tokens = target.split(",");
        Arrays.asList(tokens).forEach(token -> {
            String trimmed = token.trim();
            if (!"".equals(trimmed)) {
                Integer i = Integer.valueOf(trimmed);
                rtn.add(i);
            }
        });
        Integer[] result = new Integer[rtn.size()];
        return rtn.toArray(result);
    }

    public String concat(Integer[] target) {
        if (target == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Arrays.asList(target).forEach(i -> {
            sb.append(String.format(",%d", i));
        });
        return sb.toString().replaceFirst("^,", "");
    }
}
