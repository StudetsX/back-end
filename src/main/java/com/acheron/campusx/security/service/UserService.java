package com.acheron.campusx.security.service;


import com.acheron.campusx.entity.Group;
import com.acheron.campusx.security.dto.RegistrationRequest;
import com.acheron.campusx.security.entity.User;
import com.acheron.campusx.security.entity.Role;
import com.acheron.campusx.security.repository.ChairRepository;
import com.acheron.campusx.security.repository.GroupRepository;
import com.acheron.campusx.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;
    private final ChairRepository chairRepository;
    private final GroupRepository groupRepository;

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.findByEmail(username).orElseThrow();
    }

    public User save(RegistrationRequest request) {
        List<Group> all = groupRepository.findAll();
        System.out.println(all);
        if(request==null){
            throw new RuntimeException();
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        String image = null;
        if(request.getImage()!=null){
            image = imageService.saveImage(request.getImage());
        }
        if(request.getRole().equals("STUDENT")){
            return userRepository.save(new User(null, request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(),null,groupRepository.findById(request.getGroup()).orElseThrow(), image, Role.STUDENT));
        }else if(request.getRole().equals("TEACHER")){
            return userRepository.save(new User(null, request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(),chairRepository.findById(Long.valueOf(request.getChair())).orElseThrow(), null, image, Role.TEACHER));
        }
        throw new RuntimeException();
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User saveUserImage(User user, MultipartFile image){
        if(image!=null){
            String url = imageService.saveImage(image);
            user.setImage(url);
            return userRepository.save(user);
        }else {
            throw new RuntimeException("image don't save");
        }
    }
}
