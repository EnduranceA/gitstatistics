package ru.itis.gitstats.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.gitstats.dto.github.CharacteristicsDto;
import ru.itis.gitstats.dto.github.UserDto;
import ru.itis.gitstats.dto.github.pullrequest.RepoDto;
import ru.itis.gitstats.models.Characteristics;
import ru.itis.gitstats.models.Repo;
import ru.itis.gitstats.models.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GitHubMapper {
    Repo repoDtoToModel(RepoDto repoDto);

    RepoDto repoModelToDto(Repo repo);

    List<RepoDto> repoModelsToDtos(List<Repo> repos);

//    @Mapping(target = "contributors", ignore = true)
    List<Repo> repoDtosToModels(List<RepoDto> repoDtos);

    UserDto userModelToDto(User user);

    User userDtoToModel(UserDto userDto);

    List<UserDto> userModelsToDto(List<User> users);

    Characteristics characteristicsDtoToModel(CharacteristicsDto characteristicsDto);

    CharacteristicsDto characteristicsModelToDto(Characteristics characteristics);
}
