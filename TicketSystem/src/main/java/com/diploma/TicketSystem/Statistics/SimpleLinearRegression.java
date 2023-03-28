package com.diploma.TicketSystem.Statistics;

public class SimpleLinearRegression {

    private double[] x;
    private double[] y;
    private double slope;
    private double intercept;

    public SimpleLinearRegression(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        fit();
    }

    public void fit() {
        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;

        for (int i = 0; i < x.length; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumX2 += x[i] * x[i];
        }

        double n = x.length;
        slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        intercept = (sumY - slope * sumX) / n;
    }

    public double predict(double x) {
        return slope * x + intercept;
    }
}
