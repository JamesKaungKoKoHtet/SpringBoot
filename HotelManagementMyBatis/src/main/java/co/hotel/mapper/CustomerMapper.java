package co.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
	public int getIdByUserMailAndPassword(String mail , String password);
	public String getUserNameById(int id);
}
