package cn.com.scitc.demo7.utils;

public class Message {
    //0表示成功;-1表示失败
    int status;
    //向前端返回的内容
    String massage;
    public Message() {
        super();
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Message(int status, String massage) {
        super();
        this.status = status;
        this.massage = massage;
    }
    //get/set方法
}
