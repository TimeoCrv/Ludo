package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Adherent;
import model.AdherentDAO;


class AdherentDAOTestSuite {
	
	static Adherent adherent = new Adherent("Nom", "Prénom", "0123456789", "adresse", "a", "9876543210",
			30, "Observations", "MDP", "SALT");
	private int idAdherent;


//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}

	@Test
	void testCreate() {
		assertEquals(AdherentDAO.getInstance().create(adherent),true);
		;
	}
	
	@Test
	void testRead() {
		Adherent adherentRead = AdherentDAO.getInstance().read(adherent.getIdProfil());
		assertTrue(adherentRead.getCaution()==30);
	}
	
	@Test
	void testUpdate() {
		Adherent adherentToUpdate = AdherentDAO.getInstance().read(adherent.getIdProfil());
		adherentToUpdate.setAdresse("Autre adresse");
		assertTrue(AdherentDAO.getInstance().update(adherentToUpdate)==true);
		Adherent adherentToCheck = AdherentDAO.getInstance().read(adherent.getIdProfil());
		assertEquals(adherentToCheck.getAdresse(),"Autre adresse");
	}
	
	@Test
	void testDelete() {
		Adherent adherentRead = AdherentDAO.getInstance().read(adherent.getIdProfil());
		assertTrue(adherentRead.getCaution()==30);
	}

}
