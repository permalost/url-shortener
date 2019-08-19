package com.norseamerican.urlshortener.url;

import org.springframework.data.repository.PagingAndSortingRepository;

interface UrlRepository extends PagingAndSortingRepository<Url, Long> {

}