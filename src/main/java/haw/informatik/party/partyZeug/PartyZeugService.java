package haw.informatik.party.partyZeug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PartyZeugService {

  @Autowired
  private PartyZeugRepository partyZeugRepository;

  public PartyZeug add(PartyZeug partyZeug) {
    return partyZeugRepository.save(partyZeug);
  }

  public void remove(long id) {
    partyZeugRepository.deleteById(id);
  }

  public PartyZeug getPartyzeugById(long id) {
    return partyZeugRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public void borrow(ZeugArt zeugArt) {
    ExampleMatcher matcher = ExampleMatcher.matchingAny()
            .withIgnorePaths("zustand");
    Example<PartyZeug> example = Example.of(new PartyZeug(zeugArt, null, Status.VERFUEGBAR), matcher );

    long id = partyZeugRepository.findOne(example).get().getId();
    partyZeugRepository.updateStatus(id, Status.AUSGELIEHEN);
  }

  public void returnBack(ZeugArt zeugArt) {
    ExampleMatcher matcher = ExampleMatcher.matchingAny()
        .withIgnorePaths("zustand");
    Example<PartyZeug> example = Example.of(new PartyZeug(zeugArt, null, Status.AUSGELIEHEN), matcher);

    long id = partyZeugRepository.findOne(example).get().getId();
    partyZeugRepository.updateStatus(id, Status.VERFUEGBAR);
  }

  public Status getStatus(long id) {
    return getPartyzeugById(id).getStatus();
  }

  public void markAsRepaired(long id) {
    partyZeugRepository.updateZustand(id, Zustand.REPARIERT);
  }

  public void markAsBroken(long id) {
    partyZeugRepository.updateStatus(id, Status.DEFEKT);
  }


}
