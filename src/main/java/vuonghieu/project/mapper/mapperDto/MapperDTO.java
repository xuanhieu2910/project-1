package vuonghieu.project.mapper.mapperDto;

public interface MapperDTO<T>{
    T convertEntityToDTO(Object object);
    Object convertDTOToEntity(T t);
}
