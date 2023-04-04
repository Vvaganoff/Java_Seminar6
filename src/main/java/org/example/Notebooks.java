package org.example;

import java.util.Objects;

/**
 * Класс ноутбуков
 * @ram - тип ОЗУ, String
 * @ramSize - размер ОЗУ в МБ, String
 * @processor - производитель процессора, String
 * @numberOfCore - количество ядер, String
 * @hdd - тип жесткого диска, String
 * @hddSize - размер жесткого диска в МБ, String
 * @monitorSize - размер монитора в дюймах, String
 * @label - марка ноутбука, String
 */
public class Notebooks {
    private String ram;
    private String ramSize;
    private String processor;
    private String numberOfCore;
    private String hdd;
    private String hddSize;
    private String monitorSize;
    private String label;

    public Notebooks(String ram, String ramSize, String processor, String numberOfCore, String hdd, String hddSize, String monitorSize, String label) {
        this.ram = ram;
        this.ramSize = ramSize;
        this.processor = processor;
        this.numberOfCore = numberOfCore;
        this.hdd = hdd;
        this.hddSize = hddSize;
        this.monitorSize = monitorSize;
        this.label = label;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRamSize() {
        return ramSize;
    }

    public void setRamSize(String ramSize) {
        this.ramSize = ramSize;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getNumberOfCore() {
        return numberOfCore;
    }

    public void setNumberOfCore(String numberOfCore) {
        this.numberOfCore = numberOfCore;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getHddSize() {
        return hddSize;
    }

    public void setHddSize(String hddSize) {
        this.hddSize = hddSize;
    }

    public String getMonitorSize() {
        return monitorSize;
    }

    public void setMonitorSize(String monitorSize) {
        this.monitorSize = monitorSize;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notebooks notebooks = (Notebooks) o;
        return getRam().equals(notebooks.getRam()) && getRamSize().equals(notebooks.getRamSize()) && getProcessor().equals(notebooks.getProcessor()) && getNumberOfCore().equals(notebooks.getNumberOfCore()) && getHdd().equals(notebooks.getHdd()) && getHddSize().equals(notebooks.getHddSize()) && getMonitorSize().equals(notebooks.getMonitorSize()) && getLabel().equals(notebooks.getLabel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRam(), getRamSize(), getProcessor(), getNumberOfCore(), getHdd(), getHddSize(), getMonitorSize(), getLabel());
    }

    @Override
    public String toString() {
        return ram +","
                + ramSize +  ","
                + processor + ","
                + numberOfCore + ","
                + hdd + ","
                + hddSize + ","
                + monitorSize + ","
                + label;
    }

    public String toPrint() {
        return "Ноутбук: " +
                label + '\n' +
                "ОЗУ: " + ram +
                ", " + ramSize +  "MB" + '\n' +
                "Процессор: " + processor +
                ", " + numberOfCore + " ядра" +'\n' +
                "HDD: " + hdd +
                ", " + hddSize + "MB" + '\n' +
                "Размер дисплея: " + monitorSize + " дюймов";
    }
}
