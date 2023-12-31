package com.cotiviti.Pasaw.dto;

import com.cotiviti.Pasaw.model.CartStatus;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Getter
@Setter
public class CartDto {

    private Long userid;
    private Long productid;
    private Long orderid;
    private Long quantity;
    
    @Enumerated(EnumType.ORDINAL)
    private CartStatus status;
}



