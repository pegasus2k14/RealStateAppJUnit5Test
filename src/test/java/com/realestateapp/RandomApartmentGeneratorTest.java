package com.realestateapp;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class RandomApartmentGeneratorTest {
	private static final double MAX_MULTIPLIER = 4.0;

	@Nested
	class GeneratorDefaultParameterTest {
		private RandomApartmentGenerator generator;
		
		//Se genera una nueva instancia de RandomApartmentGenerator() antes de cada test
		@BeforeEach
		private void setUp() {
			this.generator = new RandomApartmentGenerator();
		}

		@RepeatedTest(10)
		void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
			// Given
			 //Areas minimas y maximas
			 double minArea = 30.0;
			 double maxArea = minArea * MAX_MULTIPLIER;
			 //Precios minimos y maximos por metro cuadrado
			 BigDecimal minPricePerMether = new BigDecimal(3000.0);
			 BigDecimal maxPricePerMether = minPricePerMether.multiply(new BigDecimal(MAX_MULTIPLIER));
			
			 // When
              Apartment apartment = generator.generate(); 
			
             // Then
              //Calculamos el precio minimo y maximo del apartamente generado en base a su area multiplicada
              //por el precio por metro cuadrado
              BigDecimal minApartmentPrice = new BigDecimal(apartment.getArea()).multiply(minPricePerMether);
              BigDecimal maxApartmentPrice = new BigDecimal(apartment.getArea()).multiply(maxPricePerMether);
              
              //Comprobamos si el precio y area del apartamento se encuentra dentro de los limites definidos
              assertAll(
            		 ()-> assertTrue(apartment.getPrice().compareTo(minApartmentPrice)>=0), //si el precio es >= al minimo
            		 ()-> assertTrue(apartment.getPrice().compareTo(maxApartmentPrice)<=0), //Si el precio es <= maximo
            		 ()-> assertTrue(apartment.getArea()>= minArea),  //Si el area del apartament es >= minima
            		 ()-> assertTrue(apartment.getArea()<=maxArea)
            		  
              );
		}

	}
	
	
	@Nested
	class GeneratorCustomParameterTest{
		
	}

	
	
	
	
	

}
