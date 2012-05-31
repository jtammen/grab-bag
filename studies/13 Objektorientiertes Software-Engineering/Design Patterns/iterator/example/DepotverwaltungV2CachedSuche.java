package iterator.example;

public class DepotverwaltungV2CachedSuche extends DepotverwaltungV2 {

	@Override
	protected void einf�genImpl(Element o) {
		System.out.println("DepotverwaltungV2CachedSuche.einf�genImpl()");
		elemente.add(o);
	}

	@Override
	protected void l�schenImpl(Element o) {
		System.out.println("DepotverwaltungV2CachedSuche.l�schenImpl()");
		elemente.remove(o);
	}

	@Override
	protected void suchenImpl(Element o) {
		System.out.println("DepotverwaltungV2CachedSuche.suchenImpl()");
	}
}