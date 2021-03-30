/**
 * 
 */
package com.suitemetrics.model;

/**
 *
 */
public enum Language {

	ASSEMBLER("Assembler"), ADA95("Ada 95"), C("C"), CPP("C++"), CSHARP("C#"), COBOL("COBOL"), FORTRAN("FORTRAN"), HTML(
			"HTML"), JAVA("Java"), JAVASCRIPT("JavaScript"), VBSCRIPT("VBScript"), VISUALBASIC("Visual Basic");

	private String language;

	private Language(String language) {
		this.language = language;
	}

	public String toString() {
		return language;
	}

}
