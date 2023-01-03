package com.example.preprojec3.user.controller;

import com.example.preprojec3.dto.PageResponseDto;
import com.example.preprojec3.dto.SingleResponseDto;
import com.example.preprojec3.user.dto.UserPageResponseDto;
import com.example.preprojec3.user.dto.UserPatchDto;
import com.example.preprojec3.user.dto.UserPostDto;
import com.example.preprojec3.user.dto.UserResponseDto;
import com.example.preprojec3.user.mapper.UserMapper;
import com.example.preprojec3.user.service.UserService;
import lombok.RequiredArgsConstructor;

import com.example.preprojec3.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userServiec;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity userPost(@RequestBody UserPostDto userPostDto){
        User user = userMapper.UserPostDtoToEntity(userPostDto);
        User save = userServiec.save(user);
        UserResponseDto userResponseDto = userMapper.UserEntityToReponseDto(save);

        return new ResponseEntity<>(
                SingleResponseDto.of(userResponseDto), HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity userGets(
            @PageableDefault(page=0,size=10,sort="userId",direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        Page<User> findUser = userServiec.findUsers(pageable);
        List<UserPageResponseDto> userPageResponseDtoList=
                userMapper.UserPageDtoToList(findUser.getContent());

        PageResponseDto pageResponseDto = PageResponseDto.of(
                userPageResponseDtoList,
                new PageImpl(
                        userPageResponseDtoList,
                        findUser.getPageable(),
                        findUser.getTotalElements()
                )
        );
        return new ResponseEntity<>(pageResponseDto,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity userGet(@PathVariable Long userId){
           User user = userServiec.get(userId);
           UserResponseDto userResponseDto = userMapper.UserEntityToReponseDto(user);
           return new ResponseEntity<>(
                   SingleResponseDto.of(userResponseDto), HttpStatus.OK
           );
    }

    @PatchMapping("/{userId}")
    public ResponseEntity userPatch(@PathVariable Long userId,
                                    @RequestBody UserPatchDto userPatchDto){
           User user = userMapper.UserPatchDtoToEntity(userPatchDto);
           User patch = userServiec.patch(user,userId);
           UserResponseDto userResponseDto = userMapper.UserEntityToReponseDto(patch);
           return new ResponseEntity<>(
                   SingleResponseDto.of(userResponseDto),HttpStatus.OK
           );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity userDelete(@PathVariable Long userId){
        userServiec.delete(userId);
        return ResponseEntity.noContent().build();
    }


}
