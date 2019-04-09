package com.pan.nurseStation.bean.request;

/**
 * Copyright 2019 bejson.com
 */

/**
 * Auto-generated: 2019-04-08 9:22:42
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SignsDoRequestBean {

    private Info info = new Info();

    public void setInfo(Info info) {
        this.info = info;
    }

    public Info getInfo() {
        return info;
    }

    public static class Info {
        /**
         * 病人住院号
         */
        private String hos_number = "";
        /**
         * 录入人工号
         */
        private String number = "";
        /**
         * 体温类型，1:口表;2:掖表;3:肛表
         */
        private String temp_type = "";
        /**
         * 体温
         */
        private String temperature = "";
        /**
         * 脉搏类型，0:自然脉率;1:脉搏短绌
         */
        private String pulse_type = "";
        /**
         * 心率类型，0:自然心率;1:起搏器
         */
        private String heart_rate_type = "";
        /**
         * 心率
         */
        private String heart_rate = "";
        /**
         * 脉搏
         */
        private String pulse = "";
        /**
         * 呼吸类型，0:自然呼吸;1:辅助呼吸
         */
        private String breath_type = "";
        /**
         * 呼吸
         */
        private String breath = "";
        /**
         * 血压
         */
        private String blood_pressure = "";
        /**
         * 大便次数
         */
        private String stool_frequency = "";
        /**
         * 尿量
         */
        private String urine_volume = "";
        /**
         * 输入液量
         */
        private String input_fluid_volume = "";
        /**
         * 体重
         */
        private String weight = "";
        /**
         * 其他
         */
        private String other = "";
        /**
         * 录入日期，例：2018.05.07
         */
        private String eday = "";
        /**
         * 录入时间，例：10:00
         */
        private String etime = "";

        public void setHos_number(String hos_number) {
            this.hos_number = hos_number;
        }

        public String getHos_number() {
            return hos_number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getNumber() {
            return number;
        }

        public void setTemp_type(String temp_type) {
            this.temp_type = temp_type;
        }

        public String getTemp_type() {
            return temp_type;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setPulse_type(String pulse_type) {
            this.pulse_type = pulse_type;
        }

        public String getPulse_type() {
            return pulse_type;
        }

        public void setPulse(String pulse) {
            this.pulse = pulse;
        }

        public String getPulse() {
            return pulse;
        }

        public void setBreath_type(String breath_type) {
            this.breath_type = breath_type;
        }

        public String getBreath_type() {
            return breath_type;
        }

        public void setBreath(String breath) {
            this.breath = breath;
        }

        public String getBreath() {
            return breath;
        }

        public void setBlood_pressure(String blood_pressure) {
            this.blood_pressure = blood_pressure;
        }

        public String getBlood_pressure() {
            return blood_pressure;
        }

        public void setStool_frequency(String stool_frequency) {
            this.stool_frequency = stool_frequency;
        }

        public String getStool_frequency() {
            return stool_frequency;
        }

        public void setUrine_volume(String urine_volume) {
            this.urine_volume = urine_volume;
        }

        public String getUrine_volume() {
            return urine_volume;
        }

        public void setInput_fluid_volume(String input_fluid_volume) {
            this.input_fluid_volume = input_fluid_volume;
        }

        public String getInput_fluid_volume() {
            return input_fluid_volume;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getWeight() {
            return weight;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getOther() {
            return other;
        }

        public void setEday(String eday) {
            this.eday = eday;
        }

        public String getEday() {
            return eday;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public String getEtime() {
            return etime;
        }

        public void setHeart_rate(String heart_rate) {
            this.heart_rate = heart_rate;
        }

        public String getHeart_rate() {
            return heart_rate;
        }

        public void setHeart_rate_type(String heart_rate_type) {
            this.heart_rate_type = heart_rate_type;
        }

        public String getHeart_rate_type() {
            return heart_rate_type;
        }
    }
}
