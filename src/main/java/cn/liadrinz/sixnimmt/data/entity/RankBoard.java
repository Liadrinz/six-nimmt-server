package cn.liadrinz.sixnimmt.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rank_board")
@Data
public class RankBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private long score;

}
