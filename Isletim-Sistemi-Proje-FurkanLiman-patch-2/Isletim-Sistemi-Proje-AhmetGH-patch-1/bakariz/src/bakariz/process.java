package bakariz;

public class process {
	
	int Id;
	int varisZamani;
	int calismaZamani;
	int oncelik;

	public int getVarisZamani() {
		return varisZamani;
	}
	
	@Override
	public String toString() {
		return "process [Id=" + Id + ", varisZamani=" + varisZamani + ", calismaZamani=" + calismaZamani + ", oncelik="
				+ oncelik + "]";
	}

	public void setVarisZamani(int varisZamani) {
		this.varisZamani = varisZamani;
	}

	public int getcalismaZamani() {
		return calismaZamani;
	}

	public void setcalismaZamani(int calismaZamani) {
		this.calismaZamani = calismaZamani;
	}

	public int getOncelik() {
		return oncelik;
	}

	public void setOncelik(int oncelik) {
		this.oncelik = oncelik;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	
	public process (int[] dizi){
	
		
	this.Id=dizi[0];
	this.varisZamani=dizi[1];
	this.oncelik=dizi[2];
	this.calismaZamani=dizi[3];
	}
	
}
