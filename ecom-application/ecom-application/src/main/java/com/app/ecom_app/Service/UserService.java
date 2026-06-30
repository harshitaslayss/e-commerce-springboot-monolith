package com.app.ecom_app.Service;

import com.app.ecom_app.DTO.AddressDTO;
import com.app.ecom_app.DTO.UserRequest;
import com.app.ecom_app.DTO.UserResponse;
import com.app.ecom_app.Entity.Address;
import com.app.ecom_app.Entity.User;
import com.app.ecom_app.Entity.UserRole;
import com.app.ecom_app.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    //  private List<User> userList= new ArrayList<>();
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<UserResponse> fetchAllUsers(){
        return userRepository.findAll().stream()
                .map(this:: mapToUserResponse)
                .collect(Collectors.toList());
    }



    public void createUser(@RequestBody UserRequest userRequest){
    //        userList.add(user);
    //        return userList;

        User user= new User();
        updateUserFromUserRequest(user, userRequest);

        if (user.getRole() == null) {
            user.setRole(UserRole.CUSTOMER);
        }


        userRepository.save(user);
    }

    private void updateUserFromUserRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());

        if(userRequest.getAddress()!=null){
            Address address= new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());
        }
    }

    public Optional<UserResponse> fetchUser(Long id) {
        //       return userList.stream()
        //                .filter(user->user.getId().equals(id))
        //                .findFirst();
        return userRepository.findById(id)
                .map(this::mapToUserResponse);

    }

    public boolean updateUser(@RequestBody Long id, UserRequest updatedUserRequest){
    //            return userList.stream()
    //                    .filter(user -> user.getId().equals(id))
    //                    .findFirst()
    //                    .map(existingUser-> {
    //                        existingUser.setFirstName(updatedUser.getFirstName());
    //                        existingUser.setLastName(updatedUser.getLastName());
    //                        return true;
    //                    }).orElse( false);

        return userRepository.findById(id)
                .map(existingUser->{
                    updateUserFromUserRequest(existingUser,updatedUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse( false);

    }

    private UserResponse mapToUserResponse(User user){
        UserResponse response= new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if(user.getAddress()!=null){
            AddressDTO addressDTO= new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddress(addressDTO);
        }

        return response;
    }


}
