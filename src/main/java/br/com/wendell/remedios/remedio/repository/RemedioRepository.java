package br.com.wendell.remedios.remedio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wendell.remedios.remedio.model.RemedioModel;

@Repository
public interface RemedioRepository extends JpaRepository<RemedioModel, Long> {
                            /* segundo generico é o tipo do ID do repositort */

    List<RemedioModel> findByAtivoTrue();
} 

