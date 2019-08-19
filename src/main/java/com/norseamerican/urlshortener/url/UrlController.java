package com.norseamerican.urlshortener.url;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@Api(value="Url System")
class UrlController {

  private final UrlRepository repository;

  UrlController(UrlRepository repository) {
    this.repository = repository;
  }


  @ApiOperation(value = "View a list of available urls", response = List.class)
  @GetMapping("/url")
  Page<Url> all(
                      @RequestParam(defaultValue = "0") Integer pageNo,
                      @RequestParam(defaultValue = "5") Integer pageSize,
                      @RequestParam(defaultValue = "id") String sortBy) 
  {
    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    return repository.findAll(pageable);
  }

  @ApiOperation(value = "Add an url", response = Url.class)
  @PostMapping("/url")
  Url newUrl(@RequestBody Url newUrl) {
    return repository.save(newUrl);
  }

  @ApiOperation(value = "Retrieve an url", response = Url.class)
  @GetMapping("/url/{id}")
  Url one(@PathVariable Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Could not find url " + id));
  }

  @ApiOperation(value = "Update an url", response = Url.class)
  @PutMapping("/url/{id}")
  Url replaceUrl(@RequestBody Url newUrl, @PathVariable Long id) {

    return repository.findById(id)
      .map(url -> {
        url.setUrl(newUrl.getUrl());
        return repository.save(url);
      })
      .orElseGet(() -> {
        newUrl.setId(id);
        return repository.save(newUrl);
      });
  }

  @ApiOperation(value = "Delete an url")
  @DeleteMapping("/url/{id}")
  void deleteUrl(@PathVariable Long id) {
    repository.deleteById(id);
  }
}