package iterator.example;

public class DepotverwaltungV2CachedSuche extends DepotverwaltungV2 {

	@Override
	protected void einfügenImpl(Element o) {
		System.out.println("DepotverwaltungV2CachedSuche.einfügenImpl()");
		elemente.add(o);
	}

	@Override
	protected void löschenImpl(Element o) {
		System.out.println("DepotverwaltungV2CachedSuche.löschenImpl()");
		elemente.remove(o);
	}

	@Override
	protected void suchenImpl(Element o) {
		System.out.println("DepotverwaltungV2CachedSuche.suchenImpl()");
	}
}