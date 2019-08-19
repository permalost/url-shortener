package com.norseamerican.urlshortener.url;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@Entity
@Slf4j
@ApiModel(description = "The main url class")
class Url {

    @ApiModelProperty(notes = "Database generated id")
    private @Id @GeneratedValue Long id;

    @ApiModelProperty(notes = "url to be shortened")
    private String url;
    
    Url() {}

    Url(String url) {
        this.url = url;
        log.info(this.id);
    }
}