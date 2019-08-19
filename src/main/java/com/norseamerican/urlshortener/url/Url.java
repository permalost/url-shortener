package com.norseamerican.urlshortener.url;

import java.io.Serializable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@Entity
@Slf4j
@ApiModel(description = "The main url class")
class Url implements Serializable {

    @ApiModelProperty(notes = "Database generated id")
    private @Id @GeneratedValue Long id;

    @ApiModelProperty(notes = "url to be shortened")
    private String url;

    private String shortUrl;
    
    Url() {}

    Url(String url) {
        this.url = url;
    }

    public String getShortUrl() {
        return Url.hashId(this.id);
    }

    public static String hashId(Long id) {
        return Long.toString(id, Character.MAX_RADIX);
    }

    public static long deHashId(String id) {
        return Long.parseLong(id, Character.MAX_RADIX);
    }
}