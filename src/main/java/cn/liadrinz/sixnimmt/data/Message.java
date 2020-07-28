package cn.liadrinz.sixnimmt.data;

import lombok.Data;

@Data
public class Message {
    private Header header;
    private String data;
}
