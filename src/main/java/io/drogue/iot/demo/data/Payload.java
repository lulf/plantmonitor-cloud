package io.drogue.iot.demo.data;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Payload {
    private double temperature;
    private double humidity;
    private int soil;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getSoil() {
        return soil;
    }

    public void setSoil(int soil) {
        this.soil = soil;
    }
}
