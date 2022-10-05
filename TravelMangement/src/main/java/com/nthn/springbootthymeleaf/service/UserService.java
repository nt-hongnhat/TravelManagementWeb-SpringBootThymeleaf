package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.UserDTO;
import com.nthn.springbootthymeleaf.VO.UserQueryVO;
import com.nthn.springbootthymeleaf.VO.UserUpdateVO;
import com.nthn.springbootthymeleaf.VO.UserVO;
import com.nthn.springbootthymeleaf.model.User;
import com.nthn.springbootthymeleaf.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Integer save(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user = userRepository.save(user);
        return user.getId();
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public void update(Integer id, UserUpdateVO vO) {
        User bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userRepository.save(bean);
    }

    public UserDTO getById(Integer id) {
        User original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserDTO> query(UserQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserDTO toDTO(User original) {
        UserDTO bean = new UserDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private User requireOne(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
