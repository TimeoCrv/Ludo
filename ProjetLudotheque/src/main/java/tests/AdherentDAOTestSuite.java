package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.Adherent;
import model.AdherentDAO;

@TestMethodOrder(OrderAnnotation.class)
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
	@BeforeEach
	void setUp() throws Exception {
		idAdherent = adherent.getIdProfil();
	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}

	@Test
	@Order(1)
	void testCreate() {
		assertEquals(AdherentDAO.getInstance().create(adherent),true);
	}
	
	@Test
	@Order(2)
	void testRead() {
		Adherent adherentRead = AdherentDAO.getInstance().read(idAdherent);
		assertTrue(adherentRead.getCaution()==30);
	}
	
	@Test
	@Order(3)
	void testUpdate() {
		Adherent adherentToUpdate = AdherentDAO.getInstance().read(idAdherent);
		adherentToUpdate.setAdresse("Autre adresse");
		assertTrue(AdherentDAO.getInstance().update(adherentToUpdate)==true);
		Adherent adherentToCheck = AdherentDAO.getInstance().read(idAdherent);
		assertEquals(adherentToCheck.getAdresse(),"Autre adresse");
	}
	
	@Test
	@Order(4)
	void testDelete() {
		boolean deleteSuccess = false;
		Adherent adherentRead = AdherentDAO.getInstance().read(idAdherent);
		assertTrue(adherentRead.getCaution()==30);
		try {
			AdherentDAO.getInstance().read(idAdherent);
		} catch (Exception e) {
			deleteSuccess = true;
			assertTrue(deleteSuccess);
		}
	}

}
