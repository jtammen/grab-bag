package de.vsys.ueb.converter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class Client {
	public static void main(String[] args) {
		try {
			Context initial = new InitialContext();
			Object objref = initial.lookup("java:comp/env/ejb/Converter");
			ConverterHome home = (ConverterHome) PortableRemoteObject.narrow(
					objref, ConverterHome.class);

			Converter demEuroConverter = home.create();
			double betrag = demEuroConverter.demToEuro(100.00);
			System.out.println(String.valueOf(betrag));

			betrag = demEuroConverter.euroToDem(100.00);
			System.out.println(String.valueOf(betrag));

		} catch (Exception ex) {
			System.err.println("Caught an unexpected exception!");
			ex.printStackTrace();
		}
	}
}
