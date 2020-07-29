package cn.liadrinz.sixnimmt.data.protocol;

import lombok.Data;

@Data
public class Header {
    private String biz;
    private String method;
    private RespMethod respMethod;
}
