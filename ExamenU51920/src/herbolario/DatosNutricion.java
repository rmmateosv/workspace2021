package herbolario;

public class DatosNutricion {
	private int kcal, grasas, hidratos;

	public DatosNutricion(int kcal, int grasas, int hidratos) {
		super();
		this.kcal = kcal;
		this.grasas = grasas;
		this.hidratos = hidratos;
	}

	public DatosNutricion() {
		super();
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public int getGrasas() {
		return grasas;
	}

	public void setGrasas(int grasas) {
		this.grasas = grasas;
	}

	public int getHidratos() {
		return hidratos;
	}

	public void setHidratos(int hidratos) {
		this.hidratos = hidratos;
	}
	
	
}
