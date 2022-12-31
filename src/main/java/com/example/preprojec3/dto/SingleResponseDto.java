package com.example.preprojec3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@Data
public class SingleResponseDto {

    private Object data;

    public static SingleResponseDto of(Object data){
        return new SingleResponseDto(data);
    }


}
