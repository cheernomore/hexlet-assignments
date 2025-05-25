package exercise.mapper;

import exercise.dto.*;
import exercise.model.Category;
import exercise.model.Product;
import org.mapstruct.*;

// BEGIN
@Mapper(
    uses = { JsonNullableMapper.class, ReferenceMapper.class },
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CategoryMapper{
    @Mapping(target = "name", source = "name")
    public abstract Category map(CategoryCreateDTO productCreateDTO);

    @Mapping(target = "name", source = "name")
    public abstract CategoryDTO map(Category model);

}
// END
