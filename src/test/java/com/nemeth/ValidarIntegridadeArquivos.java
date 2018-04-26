package com.nemeth;

import java.io.File;

import junit.framework.TestCase;

public class ValidarIntegridadeArquivos extends TestCase {

	   public void validarArquivos() {
	    	File file = new File ("logs");
	    	assertTrue(file.exists());
	    }
	
}
