/*
* generated by Xtext
*/
package org.eclipse.xtext.ui.tests.validation;
 
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;

public class AbstractTestLanguageJavaValidator extends org.eclipse.xtext.validation.AbstractDeclarativeValidator {

	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(org.eclipse.xtext.ui.tests.foo.FooPackage.eINSTANCE);
		return result;
	}

}
