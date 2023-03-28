package com.diploma.TicketSystem.Statistics;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/stats")
public class StatisticsController {
    private static LinearRegression linearRegression;
    private static SimpleLinearRegression simpleLinearRegression;


}
