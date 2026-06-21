package infrastructure.in.web;

import application.port.in.*;

public class ZamowienieController {

	private ZamowienieProduktowUseCase zamowienieProduktowUseCase;

	/**
	 * 
	 * @param zamowienieProduktowUseCase
	 */
	public ZamowienieController(ZamowienieProduktowUseCase zamowienieProduktowUseCase) {
		this.zamowienieProduktowUseCase = zamowienieProduktowUseCase;
	}

	/**
	 * 
	 * @param request
	 */
	public ZamowienieResponse zamowProdukty(ZamowienieRequest request) {
		int idZamowienia = zamowienieProduktowUseCase.zamowProdukty(request.getIdKupujacego(), request.getPozycje());
		return new ZamowienieResponse(idZamowienia, "Zamowienie zostalo zlozone, oplacone i sfinalizowane.");
	}

}
