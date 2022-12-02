package models;

public class Car {
	
	private String[] aparencias = {
			"assets/cars/Ambulance.png",
			"assets/cars/Beetle.png",
			"assets/cars/black-sedan.png",
			"assets/cars/Bus.png",
			"assets/cars/fire-truck.png",
			"assets/cars/sportive-black-car.png",	
			"assets/cars/taxi.png",	
			"assets/cars/Trator.png",	
	};

	private boolean IPVA;
	private int gasCapacity;
	private boolean turbo;
	private Wheel de,dd,te,td;
	private String model;
	private float gas;
	
	public Car(int model) {// o carro será gerado sempre com pré definiçoes, com IPVA, abastecido e calibrado pronto para rodar
		this.model = aparencias[model];
		this.gasCapacity = (int) ((Math.random() * 30 + 20));
		this.gas = (float) this.gasCapacity;
		
		this.de = new Wheel(15, 33, 1.0);
		this.dd = new Wheel(15, 33, 1.0);
		this.te = new Wheel(15, 33, 1.0);
		this.te = new Wheel(15, 33, 1.0);
		
		this.IPVA = true;
	}

	public boolean isIPVA() {
		return IPVA;
	}

	public void setIPVA(boolean iPVA) {
		IPVA = iPVA;
	}

	public boolean isTurbo() {
		return turbo;
	}

	public void setTurbo(boolean turbo) {
		this.turbo = turbo;
	}

	public Wheel getDe() {
		return de;
	}

	public Wheel getDd() {
		return dd;
	}


	public Wheel getTe() {
		return te;
	}


	public Wheel getTd() {
		return td;
	}

	public float getGas() {
		return gas;
	}

	public void setGas(float gas) {
		this.gas = gas;
	}

	public String getModel() {
		return model;
	}
		
}
