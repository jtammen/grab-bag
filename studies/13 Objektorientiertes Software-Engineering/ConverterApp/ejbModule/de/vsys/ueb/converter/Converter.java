/*
 * Generated by XDoclet - Do not edit!
 */
package de.vsys.ueb.converter;

/**
 * Remote interface for Converter.
 * @generated 
 * @wtp generated
 */
public interface Converter
   extends javax.ejb.EJBObject
{
   /**
    * <!-- begin-xdoclet-definition -->
    */
   public double demToEuro( double param )
      throws java.rmi.RemoteException;

   public double euroToDem( double param )
      throws java.rmi.RemoteException;

}