package com.example.preprojec3.user.mapper;

import com.example.preprojec3.user.dto.UserPageResponseDto;
import com.example.preprojec3.user.dto.UserPatchDto;
import com.example.preprojec3.user.dto.UserPostDto;

import com.example.preprojec3.user.dto.UserResponseDto;
import org.mapstruct.Mapper;
import com.example.preprojec3.user.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User UserPostDtoToEntity(UserPostDto userPostDto);

    UserResponseDto UserEntityToReponseDto(User save);

    List<UserPageResponseDto> UserPageDtoToList(List<User> content);

    User UserPatchDtoToEntity(UserPatchDto userPatchDto);
}
