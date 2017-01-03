package edu.mswiate.lab9.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.junit.Test;

import edu.mswiate.lab9.Posel;
import edu.mswiate.lab9.Sejm;

public class SejmTest {

	@Test
	public void testAddPosel() {
		Sejm sejm = new Sejm(7);
		sejm.addPosel(new Posel(1, "xyz"));
		assertEquals(sejm.getPosel("xyz").getId(), 1);
	}

	@Test
	public void testGetPosel() {
		Sejm sejm = new Sejm(7);
		sejm.addPosel(new Posel(1, "xyz"));
		assertEquals(sejm.getPosel("xyz").getId(), 1);
	}

	@Test
	public void testGetNumberOfPosels() {
		Sejm sejm = new Sejm(7);
		sejm.addPosel(new Posel(1, "xyz"));
		sejm.addPosel(new Posel(1, "xa"));
		sejm.addPosel(new Posel(1, "xysz"));
		sejm.addPosel(new Posel(1, "xaayz"));
		assertEquals(sejm.getNumberOfPosels(), 4);
	}

	@Test
	public void testGetPosels() {
		Sejm sejm = new Sejm(7);
		Posel posel1 = new Posel(1, "xyz");
		Posel posel2 = new Posel(1, "xa");
		Posel posel3 = new Posel(1, "xysz");
		Posel posel4 = new Posel(1, "xaayz");
		sejm.addPosel(posel1);
		sejm.addPosel(posel2);
		sejm.addPosel(posel3);
		sejm.addPosel(posel4);
		Collection<Posel> c = new ArrayList<Posel>();
		c.add(posel1);
		c.add(posel2);
		c.add(posel3);
		c.add(posel4);
		assertTrue(sejm.getPosels().containsAll(c));
	}

	@Test
	public void testToJson() {
		Sejm sejm = new Sejm(7);
		Posel posel = new Posel(53, "Zbigniew Chmielowiec", 282986.23 , 62.52 , 1, 6 , false, 15276.78);
		sejm.addPosel(posel);
		JSONArray jsonPosels = sejm.toJson().getJSONArray("posels");
		assertEquals(jsonPosels.getJSONObject(0).getString("name"), "Zbigniew Chmielowiec");
		assertEquals(sejm.toJson().getInt("kadencja"), 7);
		
	}

}
