package com.example.alino4ka.test5.test4;

public class Weather {
    private String temp;
    private String pressure;
    private String humidity;
    private String direction;
    private String speed;
    private String clouds;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public Weather(String temp, String pressure, String humidity, String direction, String speed, String clouds) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.direction = direction;
        this.speed = speed;
        this.clouds = clouds;
    }
}
