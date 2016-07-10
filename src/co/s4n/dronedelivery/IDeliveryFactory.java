package co.s4n.dronedelivery;

public interface IDeliveryFactory {
	public Delivery getDelivery(String infilename, String outfilename);
}
