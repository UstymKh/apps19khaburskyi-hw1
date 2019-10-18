package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    double[] temperatureSeries;
    double minpr = 0.000001;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double element : temperatureSeries) {
            if (element < -273) {
                throw new InputMismatchException();
            }
        }
        this.temperatureSeries = temperatureSeries;
    }

    public double average() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("EMPTY");
        }
        double avr = 0;
        for (int i = 0; i < this.temperatureSeries.length; i++) {
            avr += this.temperatureSeries[i];
        }
        return avr / (this.temperatureSeries.length);
    }

    public double deviation() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("EMPTY");
        }
        double result = 0;
        for (int i = 0; i < this.temperatureSeries.length; i++) {
            result += Math.pow((this.temperatureSeries[i] - this.average()), 2);
        }
        result = Math.pow(result / this.temperatureSeries.length, 0.5);
        return result;
    }

    public double min() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("EMPTY");
        }
        double mini = this.temperatureSeries[0];
        for (int i = 1; i < this.temperatureSeries.length; i++) {
            if (this.temperatureSeries[i] < mini) {
                mini = this.temperatureSeries[i];
            }
        }
        return mini;
    }

    public double max() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("EMPTY");
        }
        double maxi = this.temperatureSeries[0];
        for (int i = 1; i < this.temperatureSeries.length; i++) {
            if (this.temperatureSeries[i] > maxi) {
                maxi = this.temperatureSeries[i];
            }
        }
        return maxi;
    }

    public double findTempClosestToZero() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("EMPTY");
        }
        double closest = this.temperatureSeries[0];
        for (int i = 0; i < this.temperatureSeries.length; i++) {
            if (Math.abs(this.temperatureSeries[i]) <= Math.abs(closest)) {
                if (this.temperatureSeries[i] > 0) {
                    closest = this.temperatureSeries[i];
                } else if (Math.abs(Math.abs(this.temperatureSeries[i]) - Math.abs(closest)) < this.minpr) {
                    closest = this.temperatureSeries[i];
                }
            }
        }
        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("EMPTY");
        }
        double closest = this.temperatureSeries[0];
        for (int i = 0; i < this.temperatureSeries.length; i++) {
            if (Math.abs(this.temperatureSeries[i] - tempValue) <= Math.abs(closest - tempValue)) {
                closest = this.temperatureSeries[i];
            } else if (Math.abs(this.temperatureSeries[0] - tempValue) == Math.abs(closest - tempValue) && this.temperatureSeries[0] > closest) {
                closest = this.temperatureSeries[i];
            }
        }
        return closest;
    }

    public double[] findTempsLessThan(double tempValue) {
        int len = 0;
        for (int i = 0; i < this.temperatureSeries.length; i++) {
            if (this.temperatureSeries[i] < tempValue) {
                len++;
            }
        }
        double[] result = new double[len];
        len = 0;
        for (int i = 0; i < this.temperatureSeries.length; i++) {
            if (this.temperatureSeries[i] < tempValue) {
                result[len] = this.temperatureSeries[i];
                len++;
            }
        }

        return result;
    }

    public double[] findTempsGreaterThan(double tempValue) {
        int len = 0;
        for (int i = 0; i < this.temperatureSeries.length; i++) {
            if (this.temperatureSeries[i] > tempValue) {
                len++;
            }
        }
        double[] result = new double[len];
        len = 0;
        for (int i = 0; i < this.temperatureSeries.length; i++) {
            if (this.temperatureSeries[i] > tempValue) {
                result[len] = this.temperatureSeries[i];
                len++;
            }
        }

        return result;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("EMPTY");
        }
        TempSummaryStatistics result = new TempSummaryStatistics(this.average(), this.deviation(), this.min(),
                this.max());
        return result;
    }

    public int addTemps(double... temps) {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException("EMPTY");
        }
        double[] new_temp_series = new double[this.temperatureSeries.length * 2];
        int i = 0;
        for (double element : this.temperatureSeries) {
            new_temp_series[i] = element;
            i++;
        }
        for (double temp : temps) {
            new_temp_series[i] = temp;
            i++;
        }
        this.temperatureSeries = new_temp_series;
        return i;
    }
}
