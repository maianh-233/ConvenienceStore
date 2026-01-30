package com.conveniencestore.mapper;

import com.conveniencestore.DTO.UserDTO;
import com.conveniencestore.DTO.UserRequestDTO;
import com.conveniencestore.DTO.UserResponseDTO;
import com.conveniencestore.entity.User;

public class UserMapper {

    // ==================== TO DTO (Entity → DTO) ====================

    /**
     * Chuyển đổi Entity User thành DTO UserDTO (cho AuthContext)
     * Chỉ lấy những thông tin cần thiết để tránh expose dữ liệu nhạy cảm
     */
    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFullName(user.getFullName());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setImgUrl(user.getImgUrl());
        userDTO.setImgUrlID(user.getImgUrlID());
        userDTO.setGender(user.getGender());
        userDTO.setRoleId(user.getRole() != null ? user.getRole().getId() : null);
        userDTO.setRoleName(user.getRole() != null ? user.getRole().getName() : null);
        userDTO.setActive(user.getActive());
        
        return userDTO;
    }

    /**
     * Chuyển đổi Entity User thành UserResponseDTO (cho API response)
     */
    public static UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }
        
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setFullName(user.getFullName());
        responseDTO.setPasswordHash(user.getPasswordHash());
        responseDTO.setDateOfBirth(user.getDateOfBirth());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setPhone(user.getPhone());
        responseDTO.setAddress(user.getAddress());
        responseDTO.setImgUrl(user.getImgUrl());
        responseDTO.setImgUrlID(user.getImgUrlID());
        responseDTO.setGender(user.getGender());
        responseDTO.setRoleId(user.getRole() != null ? user.getRole().getId() : null);
        responseDTO.setRoleName(user.getRole() != null ? user.getRole().getName() : null);
        responseDTO.setActive(user.getActive());
        responseDTO.setCreatedAt(user.getCreatedAt());
        responseDTO.setUpdatedAt(user.getUpdatedAt());
        responseDTO.setLastLogin(user.getLastLogin());
        
        return responseDTO;
    }

    // ==================== TO ENTITY (DTO → Entity) ====================

    /**
     * Chuyển đổi UserRequestDTO thành Entity User
     * Sử dụng để tạo hoặc cập nhật user từ API request
     */
    public static User toEntity(UserRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        
        User user = new User();
        user.setId(requestDTO.getId());
        user.setUsername(requestDTO.getUsername());
        user.setFullName(requestDTO.getFullName()); 
        user.setDateOfBirth(requestDTO.getDateOfBirth());
        user.setEmail(requestDTO.getEmail());
        user.setPhone(requestDTO.getPhone());
        user.setAddress(requestDTO.getAddress());
        user.setImgUrl(requestDTO.getImgUrl());
        user.setGender(requestDTO.getGender());
        user.setActive(requestDTO.getActive());
        
        return user;
    }

    /**
     * Chuyển đổi DTO UserDTO thành Entity User
     * (Sử dụng trong trường hợp đặc biệt)
     */
    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setImgUrl(userDTO.getImgUrl());
        user.setGender(userDTO.getGender());
        user.setActive(userDTO.getActive());
        
        return user;
    }

    // ==================== DTO TO DTO (Chuyển đổi giữa các DTO) ====================

    /**
     * Chuyển đổi UserResponseDTO thành UserDTO
     */
    public static UserDTO responseDTOtoDTO(UserResponseDTO responseDTO) {
        if (responseDTO == null) {
            return null;
        }
        
        UserDTO userDTO = new UserDTO();
        userDTO.setId(responseDTO.getId());
        userDTO.setUsername(responseDTO.getUsername());
        userDTO.setFullName(responseDTO.getFullName());
        userDTO.setEmail(responseDTO.getEmail());
        userDTO.setPhone(responseDTO.getPhone());
        userDTO.setAddress(responseDTO.getAddress());
        userDTO.setImgUrl(responseDTO.getImgUrl());
        userDTO.setGender(responseDTO.getGender());
        userDTO.setRoleId(responseDTO.getRoleId());
        userDTO.setRoleName(responseDTO.getRoleName());
        userDTO.setActive(responseDTO.getActive());
        
        return userDTO;
    }

    /**
     * Chuyển đổi UserRequestDTO thành UserResponseDTO
     * (Thường dùng để trả về sau khi tạo/cập nhật user)
     */
    public static UserResponseDTO requestDTOtoResponseDTO(UserRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(requestDTO.getId());
        responseDTO.setUsername(requestDTO.getUsername());
        responseDTO.setDateOfBirth(requestDTO.getDateOfBirth());
        responseDTO.setEmail(requestDTO.getEmail());
        responseDTO.setPhone(requestDTO.getPhone());
        responseDTO.setAddress(requestDTO.getAddress());
        responseDTO.setImgUrl(requestDTO.getImgUrl());
        responseDTO.setGender(requestDTO.getGender());
        responseDTO.setRoleId(requestDTO.getRoleId());
        responseDTO.setActive(requestDTO.getActive());
        
        return responseDTO;
    }

    // Đổi từ UserDTO sang UserResquestDTO
    /**
 * Chuyển đổi UserDTO sang UserRequestDTO
 * Thường dùng khi lấy dữ liệu từ AuthContext để chỉnh sửa profile
 */
public static UserRequestDTO toRequestDTO(UserDTO userDTO) {
    if (userDTO == null) {
        return null;
    }

    UserRequestDTO requestDTO = new UserRequestDTO();
    requestDTO.setId(userDTO.getId());
    requestDTO.setUsername(userDTO.getUsername());
    requestDTO.setFullName(userDTO.getFullName());
    requestDTO.setEmail(userDTO.getEmail());
    requestDTO.setPhone(userDTO.getPhone());
    requestDTO.setAddress(userDTO.getAddress());
    requestDTO.setImgUrl(userDTO.getImgUrl());
    requestDTO.setImgUrlID(userDTO.getImgUrlID());
    requestDTO.setGender(userDTO.getGender());
    requestDTO.setRoleId(userDTO.getRoleId());
    requestDTO.setActive(userDTO.getActive());

    return requestDTO;
}


}
