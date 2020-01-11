package org.kashiyatra.ky20.services;

public class UploadImage {
    private String mName;
    private String mImageUrl;
    private String header;
    private String footer;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    @Override
    public String toString() {
        return "UploadImage{" +
                "header='" + header + '\'' +
                ", footer='" + footer + '\'' +
                '}';
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }



    public UploadImage() {
        //empty constructor needed
    }

    public UploadImage(String name, String imageUrl,String header,String footer) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
        this.header=header;
        this.footer=footer;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}