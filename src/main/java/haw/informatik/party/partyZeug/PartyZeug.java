package haw.informatik.party.partyZeug;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PartyZeug {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private ZeugArt zeugArt;
  private Zustand zustand;
  private Status status;

  public PartyZeug(ZeugArt zeugArt, Zustand zustand, Status status) {
    this.zeugArt = zeugArt;
    this.zustand = zustand;
    this.status = status;
  }
}
