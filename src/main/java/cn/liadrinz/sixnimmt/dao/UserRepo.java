package cn.liadrinz.sixnimmt.dao;

import cn.liadrinz.sixnimmt.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
