package com.company.innowise.airticketsapp.businessservice.mapper;

public interface Mapper<M, D> {

    M toModel(D dto);
    D toDto(M model);

}
