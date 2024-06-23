package com.hei.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter

public class WorkingHour {
    private int hour;
    private List<Type> type;

    @Override
    public String toString() {
        return " WorkingHour:" +
               "hour:" + hour +
               ", type:" + type +
               '}';
    }
}
