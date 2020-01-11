package org.kashiyatra.ky20;

public class UpdatesModel {
    public String update;
    public String url;

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public  UpdatesModel(){

    }

    public UpdatesModel(String update, String url) {
        this.update = update;
        this.url = url;
    }
}
