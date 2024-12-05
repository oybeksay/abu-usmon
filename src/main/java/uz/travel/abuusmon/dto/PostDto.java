package uz.travel.abuusmon.dto;

import uz.travel.abuusmon.entity.Post;

/**
 * DTO for {@link Post}
 */

public record PostDto(String title, String description, String photoPath) {

}