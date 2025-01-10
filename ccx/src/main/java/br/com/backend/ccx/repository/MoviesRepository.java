package br.com.backend.ccx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.backend.ccx.entities.Movies;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long> {

}
