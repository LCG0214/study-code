package cn.lcgui.springbootlog.mapper;


import java.util.List;
import java.util.Map;

public interface LogMapper {
    List<Map<String, Object>> selectAll();
}
