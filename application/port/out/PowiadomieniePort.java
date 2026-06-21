package application.port.out;

public interface PowiadomieniePort {

	/**
	 * 
	 * @param idKupujacego
	 * @param idZamowienia
	 */
	abstract void powiadomOUkonczeniu(int idKupujacego, int idZamowienia);

}