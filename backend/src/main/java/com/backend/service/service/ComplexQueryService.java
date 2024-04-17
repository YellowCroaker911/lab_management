package com.backend.service.service;

import com.backend.model.entity.Lab;
import com.backend.model.entity.Session;
import javafx.util.Pair;

import java.util.List;

public interface ComplexQueryService {
    List<Pair<Lab, Session>> getAvailableLab(Integer type,Integer student_num,
                                             String semester,String startingWeek,
                                             String endingWeek);
}
