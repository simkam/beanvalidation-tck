/*
* JBoss, Home of Professional Open Source
* Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual contributors
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.hibernate.beanvalidation.tck.tests.metadata;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintTarget;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Gunnar Morling
 */
@Documented
@Constraint(validatedBy = { CustomComposingConstraint.Validator.class })
@Target({ METHOD, FIELD, TYPE })
@Retention(RUNTIME)
@Size
public @interface CustomComposingConstraint {
	String message() default "CustomComposingConstraint.message";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	ConstraintTarget validationAppliesTo() default ConstraintTarget.IMPLICIT;

	@SupportedValidationTarget({ ValidationTarget.ANNOTATED_ELEMENT, ValidationTarget.PARAMETERS })
	public static class Validator implements ConstraintValidator<CustomComposingConstraint, Object> {

		@Override
		public void initialize(CustomComposingConstraint constraintAnnotation) {
		}

		@Override
		public boolean isValid(Object value, ConstraintValidatorContext context) {
			return false;
		}
	}
}
