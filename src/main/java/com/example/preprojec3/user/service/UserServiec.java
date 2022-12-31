package com.example.preprojec3.user.service;

import com.example.preprojec3.Exception.BusinessException;
import com.example.preprojec3.Exception.ErrorCode;
import com.example.preprojec3.dto.LoginType;
import com.example.preprojec3.dto.SingleResponseDto;
import com.example.preprojec3.dto.UserStatus;
import com.example.preprojec3.user.dto.UserPatchDto;
import com.example.preprojec3.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.preprojec3.user.entity.User;
import org.springframework.util.function.SingletonSupplier;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiec {

    private final UserRepository userRepository;

    /* 데이터 저장 */
    public User save(User user){

        user.setUserStatus(UserStatus.ACTIVITY);
        user.setEmailNotice(true);
        user.setLoginType(LoginType.BASIC);
        User save = userRepository.save(user);
        return save;
    }

    /* 단건 조회 */
    public User usetGet(Long userId) {
        Optional<User> optionalUser= userRepository.findById(userId);
        User user = optionalUser.orElseThrow(
                ()->new BusinessException(ErrorCode.USER_NOT_FOUND));

        return user;
    }

    public Page<User> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable);

    }

    public User get(Long userId) {
           User user = verifiedUser(userId);
           return user;
    }




    public User verifiedUser(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(
                        ()-> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }


    public User patch(User user, Long userId) {
        User findUser = verifiedUser(userId);
        Optional.ofNullable(user.getDisplayname())
                 .ifPresent(name -> findUser.setDisplayname(name));
        Optional.ofNullable(user.getUserStatus())
                .ifPresent(status -> findUser.setUserStatus(status));
        Optional.ofNullable(user.getPassword())
                .ifPresent(password -> findUser.setPassword(password));

        return findUser;
    }

    public void delete(Long userId) {
        User user = verifiedUser(userId);
        userRepository.delete(user);

    }
}
