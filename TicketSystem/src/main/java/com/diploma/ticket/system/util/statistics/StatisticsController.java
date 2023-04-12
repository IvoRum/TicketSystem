package com.diploma.ticket.system.util.statistics;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/stats")
public class StatisticsController {
    private static LinearRegressionComponent linearRegressionComponent;
    private static SimpleLinearRegressionComponent simpleLinearRegression;


}
