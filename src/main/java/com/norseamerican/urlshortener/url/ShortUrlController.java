package com.norseamerican.urlshortener.url;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Short Url System")
class ShortUrlController {

  private final UrlRepository repository;

  ShortUrlController(UrlRepository repository) {
    this.repository = repository;
  }

  @ApiOperation(value = "Retrieve an url", response = Url.class)
  @GetMapping("short/{id}")
  ResponseEntity<String> one(@PathVariable String id) throws Exception {

    Url url = repository.findById(Url.deHashId(id))
        .orElseThrow(() -> new IllegalArgumentException("Could not find url for " + id));
    String location = url.getUrl();

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(new URI(location));

    return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(location);
  }

}