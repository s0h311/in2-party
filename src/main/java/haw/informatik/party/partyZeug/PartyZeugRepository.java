package haw.informatik.party.partyZeug;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PartyZeugRepository extends JpaRepository<PartyZeug, Long> {

  @Modifying
  @Query("update PartyZeug p set p.status = :status where p.id = :id")
  void updateStatus(@Param("id") long id, @Param("status") Status status);

  @Modifying
  @Query("update PartyZeug p set p.zustand = :zustand where p.id = :id ")
  void updateZustand(@Param("id") long id, @Param("zustand") Zustand zustand);
}
