package com.nextoneday.customview.bean;

/**
 * Created by Administrator on 2018/3/22.
 * 饼图数据
 */

public class PieData {

    public String name;      //名字
    public float percentage;         //百分比
    public double value;       //数值

    public float angle;         //角度
    public int color;     //颜色

    public PieData(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "PieData{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", percentage=" + percentage +
                ", angle=" + angle +
                ", color=" + color +
                '}';
    }
}
