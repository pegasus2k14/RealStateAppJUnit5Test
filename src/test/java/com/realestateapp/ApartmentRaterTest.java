package com.realestateapp;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ApartmentRaterTest {
	
	/***
	 * probaremos el metodo rateApartement() para cada uno de sus posibles valores
	 * de retornos para apartamentos con datos correctos
	 * @param price
	 * @param area
	 * @param expected
	 */

	@ParameterizedTest(name="price= {0},area= {1},expected= {2}")
	@CsvSource(value= {"350000,120,0","1000000,140,1","1500000,160,2"})
	void should_ReturnCorrectRating_When_CorrectApartment(float price, double area,int expected) {
		//Given
		  Apartment apartment = new Apartment(area, new BigDecimal(price));
		//When
		  int resultado = ApartmentRater.rateApartment(apartment);
		 System.out.println("Valor esperado: "+ expected + " resultado: " +resultado);
		//Then
		 assertEquals(expected, resultado);
		  
	}
	
	
	/***
	 * Probaremos el metodo rateApartment() para el caso de un apartamento con 
	 * datos incorrectos (apartamento con area = 0)
	 */
	@Test
	void should_ReturnErrorValue_When_IncorrectApartment() {
		//Given
		  Apartment apartment = new Apartment(0, new BigDecimal(100000));
		//When
		  int resultado = ApartmentRater.rateApartment(apartment);
		//Then
		  assertEquals(-1, resultado);
		  
	}
	
	
	@Test
	void should_CalculateAverageRating_When_CorrectApartmentList() {
		//Given
		  //Creamos lista de apartamentos 
		  List<Apartment> apartamentos = new ArrayList<>();
		  apartamentos.add(new Apartment(120, new BigDecimal(350000)));
		  apartamentos.add(new Apartment(140, new BigDecimal(1000000)));
		  apartamentos.add(new Apartment(160, new BigDecimal(1500000)));
		//When
		  double averageRating = ApartmentRater.calculateAverageRating(apartamentos);
		//Then
		  assertEquals(1.0, averageRating);
	}
	
	
	@Test
	void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
		//Given
		  List<Apartment> apartamentos = new ArrayList<>();
		  //apartamentos.add(new Apartment(120, new BigDecimal(350000)));
		//When
		  Executable executable = ()-> ApartmentRater.calculateAverageRating(apartamentos);
		//Then
		  assertThrows(RuntimeException.class, executable);
	}

}
