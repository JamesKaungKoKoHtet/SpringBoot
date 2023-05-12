package co.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
	public int getIdByUserNameAndPassword(String mail , String password);
}
