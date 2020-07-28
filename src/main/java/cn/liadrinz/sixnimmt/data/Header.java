package cn.liadrinz.sixnimmt.data;

import lombok.Data;
import cn.liadrinz.sixnimmt.data.EnumTypes.*;

@Data
public class Header {
    private String biz;
    private String method;
    private RespMethod respMethod;
}
