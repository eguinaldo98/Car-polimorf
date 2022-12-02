package vehicles;

import models.EntityCar;
import models.Wheel;

public class Bus extends EntityCar {

	private boolean IPVA;
	private int gasCapacity;
	private boolean turbo;
	private Wheel de,dd,te,td;
	private float gas;
	
	public Bus(int x, int y, double velocidade) {// o carro será gerado sempre com pré definiçoes, com IPVA, abastecido e calibrado pronto para rodar
		super(x, y, velocidade, "assets/cars/Bus.png");
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
		
}
