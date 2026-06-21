package infrastructure.out.notification;

import application.port.out.*;

class PowiadomienieAdapter implements PowiadomieniePort {

	private String ostatniePowiadomienie;

	/**
	 * 
	 * @param idKupujacego
	 * @param idZamowienia
	 */
	public void powiadomOUkonczeniu(int idKupujacego, int idZamowienia) {
		ostatniePowiadomienie = "Kupujacy " + idKupujacego + " otrzymal powiadomienie o zamowieniu " + idZamowienia + ".";
	}

	String getOstatniePowiadomienie() {
		return ostatniePowiadomienie;
	}

}
