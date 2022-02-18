package br.com.back.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.back.domain.model.Grouping;

@Repository
public interface GroupingRepository extends JpaRepository<Grouping, Long>{

}
