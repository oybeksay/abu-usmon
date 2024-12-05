package uz.travel.abuusmon.mapper;


import org.mapstruct.Mapper;
import uz.travel.abuusmon.entity.Post;
import uz.travel.abuusmon.dto.PostDto;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toEntity(PostDto postDto);

}
