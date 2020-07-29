package cn.liadrinz.sixnimmt.dao;

import cn.liadrinz.sixnimmt.data.entity.RankBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankBoardRepo extends JpaRepository<RankBoard, Long> {
}
