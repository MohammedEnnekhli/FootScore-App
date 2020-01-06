package com.score.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.score.entities.Athletic;

@Transactional
public interface AthleticRepository extends JpaRepository<Athletic, Long> {

}
