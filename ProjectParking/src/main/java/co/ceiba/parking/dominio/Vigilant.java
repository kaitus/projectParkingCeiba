package co.ceiba.parking.dominio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

import co.ceiba.parking.dominio.exception.VehicleException;
import co.ceiba.parking.service.InvoiceService;
import co.ceiba.parking.service.RateService;
import co.ceiba.parking.service.UserService;
import co.ceiba.parking.service.VehicleService;

public class Vigilant {

	public static final String NO_MORE_AVAILABLE_QUOTAS = "No hay cupos Disponibles";
	public static final String CAR_IS_ENTRY = "El carro ya se encuentra parqueado";
	public static final String CAR_NOT_IS_AUTORIZED_BY_PLACA = "El vehiculo no esta autorizado para parquear";
	public static final int SPACE_AVAILABLE_CAR = 20;
	public static final int SPACE_AVAILABLE_MOTORBYKE = 10;

	private UserService userService = new UserService();
	private VehicleService vehicleService = new VehicleService();
	private InvoiceService invoiceService  = new InvoiceService();
	private RateService rateService = new RateService();
	public LocalDateTime inputDate = LocalDateTime.now();

	public Vigilant(UserService userService, VehicleService vehicleService, InvoiceService invoiceService,
			RateService rateService) {
		this.userService = userService;
		this.vehicleService = vehicleService;
		this.invoiceService = invoiceService;
		this.rateService = rateService;
	}

	public void inputVehicle(Vehicle vehicle) {
		String placaValidate = vehicle.getPlaque().toUpperCase();
		if (isOccuped(vehicle.getPlaque())) {
			throw new VehicleException(CAR_IS_ENTRY);
		}
		if (placaValidate.charAt(0) == 'A') {
			if (!isAuthorized(inputDate)) {
				throw new VehicleException(CAR_NOT_IS_AUTORIZED_BY_PLACA);
			}
		}
		if (!spaceAvailable(vehicle)) {
			throw new VehicleException(NO_MORE_AVAILABLE_QUOTAS);
		}
		if (!vehicleExist(vehicle.getPlaque())) {
			vehicleService.PreSave(vehicle);
		}

	}

	public void inputInvoice(Invoice invoice) {
		inputDate = LocalDateTime.now();
		
		
	}

	public void outputVehicle() {
		LocalDate outputDate = LocalDate.now();
	}

	/**
	 * 
	 * @param plaque
	 * @return
	 */
	public boolean isOccuped(String plaque) {
		if (!vehicleExist(plaque)) {
			return false;
		}
		Vehicle vehicle = isVehicleExist(plaque);
		if (vehicle == null || vehicle.getPlaque() == null) {
			return false;
		}

		Invoice invoice = isInvoiceExist(vehicle);
		if (invoice == null || invoice.getDateoutput() == null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param plaque
	 * @return
	 */
	public boolean vehicleExist(String plaque) {
		vehicleService = new VehicleService();
		Vehicle vehicle = this.vehicleService.getByPlaque(plaque);
		if (vehicle != null && vehicle.getPlaque() != null && vehicle.getPlaque().equals(plaque)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param plaque
	 * @return
	 */
	public Vehicle isVehicleExist(String plaque) {
		Vehicle vehicle = this.vehicleService.getByPlaque(plaque);
		if (vehicle != null && vehicle.getPlaque() != null && vehicle.getPlaque().equals(plaque)) {
			return vehicle;
		}
		return vehicle;
	}

	/**
	 * 
	 * @param vehicle
	 * @return
	 */
	public Invoice isInvoiceExist(Vehicle vehicle) {
		Invoice invoice = this.invoiceService.getVehiculo(vehicle);
		if (invoice != null && invoice.getVehicle().getPlaque() != null) {
			return invoice;
		}
		return invoice;
	}

	/**
	 * 
	 * @param StartDate
	 * @return
	 */
	public boolean isAuthorized(LocalDateTime StartDate) {
		DayOfWeek dateOfWeek = StartDate.getDayOfWeek();
		if (dateOfWeek.equals(DayOfWeek.SUNDAY) || dateOfWeek.equals(DayOfWeek.MONDAY)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param vehicle
	 * @return
	 */
	public boolean spaceAvailable(Vehicle vehicle) {
		if (vehicle.getType() == "CARRO") {
			if (isSpaceAviableCar(vehicle, SPACE_AVAILABLE_CAR)) {
				return true;
			}
			return false;
		} else {
			if (isSpaceAviableMotorByke(vehicle, SPACE_AVAILABLE_MOTORBYKE)) {
				return true;
			}
			return false;
		}
	}

	/**
	 * 
	 * @param vehicle
	 * @param spaceAvialbleCar
	 * @return
	 */
	public boolean isSpaceAviableCar(Vehicle vehicle, int spaceAvialbleCar) {
		if (vehicle == null || vehicle.getType() == null) {
			return false;
		}
		Long countCarStore = this.invoiceService.getVehicleAndInvoiceStore(vehicle.getType());
		if (countCarStore > spaceAvialbleCar) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param vehicle
	 * @param spaceAvialble
	 * @return
	 */
	public boolean isSpaceAviableMotorByke(Vehicle vehicle, int spaceAvialbleMotorByke) {
		if (vehicle == null || vehicle.getType() == null) {
			return false;
		}
		Long countCarStore = this.invoiceService.getVehicleAndInvoiceStore(vehicle.getType());
		if (countCarStore > spaceAvialbleMotorByke) {
			return false;
		}
		return true;
	}

}
