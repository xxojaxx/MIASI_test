package infrastructure.out.payment;

class ZewnetrznySystemPlatnosci {

	/**
	 * 
	 * @param idKupujacego
	 * @param kwota
	 */
	boolean zaplac(int idKupujacego, float kwota) {
		return idKupujacego > 0 && kwota > 0;
	}

}
