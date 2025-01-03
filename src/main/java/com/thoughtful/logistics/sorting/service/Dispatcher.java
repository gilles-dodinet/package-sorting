package com.thoughtful.logistics.sorting.service;

import com.thoughtful.logistics.sorting.model.ShippingPackage;

import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Dispatcher {
    private static final Logger logger = Logger.getLogger(Dispatcher.class.getName());

    private static final ValidatorFactory validatorFactory;

    static {
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }

    public ShippingStack dispatch(ShippingPackage shippingPackage) {
        validate(shippingPackage);

        if ( shippingPackage.isBulky() && shippingPackage.isHeavy() ) {
            return ShippingStack.REJECTED;
        }

        if ( shippingPackage.isBulky() || shippingPackage.isHeavy() ) {
            return ShippingStack.SPECIAL;
        }

        return ShippingStack.STANDARD;
    }

    private void validate(ShippingPackage shippingPackage) {
        Validator validator = validatorFactory.getValidator();
        var violations = validator.validate(shippingPackage);
        if (!violations.isEmpty()) {
            logger.log(Level.SEVERE, "invalid package: {0}", new Object[]{ shippingPackage });
            throw new ValidationException("invalid package");
        }
    }


}
