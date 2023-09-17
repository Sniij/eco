package com.homework.eco.api.restpark.repository;

import com.homework.eco.api.restpark.entity.RestPark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestParkRepository extends JpaRepository<RestPark, String > {

    @Query(nativeQuery = true, value = "SELECT * FROM rest_parks WHERE REFINE_LOTNO_ADDR = :lotno_addr")
    Optional<RestPark> findByLotnoAddr(@Param("lotno_addr") String lotnoAddr);

}
