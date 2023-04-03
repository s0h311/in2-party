package haw.informatik.party;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import haw.informatik.party.partyZeug.PartyZeug;
import haw.informatik.party.partyZeug.PartyZeugRepository;
import haw.informatik.party.partyZeug.PartyZeugService;
import haw.informatik.party.partyZeug.Status;
import haw.informatik.party.partyZeug.ZeugArt;
import haw.informatik.party.partyZeug.Zustand;

@SpringBootTest
class PartyApplicationTests {

  @Autowired
  private PartyZeugService partyZeugService;
  @Autowired
  private PartyZeugRepository partyZeugRepository;

  @BeforeEach
  public void setup() {
    partyZeugRepository.deleteAll();

    PartyZeug partyZeug = new PartyZeug(ZeugArt.STUHL, Zustand.UNREPARIERT, Status.VERFUEGBAR);
    partyZeugService.add(partyZeug);
  }

  @Test
  public void testServiceSave() {
    assertThat(partyZeugRepository.findAll().size() > 0);
  }

  @Test
  public void testServiceBorrow() {
    partyZeugService.borrow(ZeugArt.STUHL);
    assertThat(partyZeugRepository.findAll().get(0).getStatus().equals(Status.AUSGELIEHEN));
  }

  @Test
  public void testServiceReturn() {
    partyZeugService.borrow(ZeugArt.STUHL);
    assertThat(partyZeugRepository.findAll().get(0).getStatus().equals(Status.AUSGELIEHEN));

    partyZeugService.returnBack(ZeugArt.STUHL);
    assertThat(partyZeugRepository.findAll().get(0).getStatus().equals(Status.VERFUEGBAR));
  }

  @Test
  public void testGetStatus() {
    PartyZeug zeug = partyZeugRepository.findAll().get(0);
    assertThat(zeug.equals(Status.VERFUEGBAR));
  }

  @Test
  public void testMarkAsRepaired() {
    partyZeugService.markAsRepaired(1L);
    PartyZeug zeug = partyZeugRepository.findAll().get(0);
    assertThat(zeug.getZustand().equals(Zustand.REPARIERT));
  }

  @Test
  public void testMarkAsBroken() {
    partyZeugService.markAsBroken(1L);
    PartyZeug zeug = partyZeugRepository.findAll().get(0);
    assertThat(zeug.getStatus().equals(Status.DEFEKT));
  }
}
