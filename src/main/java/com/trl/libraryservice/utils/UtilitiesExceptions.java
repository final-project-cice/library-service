package com.trl.libraryservice.utils;

import com.trl.libraryservice.exception.IllegalMethodParameterException;

import org.slf4j.Logger;

public final class UtilitiesExceptions {

    private UtilitiesExceptions() {
    }

    public static void handlingIllegalParameter(final Logger logger, final boolean flag, String nameMethod) throws IllegalMethodParameterException {
        if (flag) {
            logger.debug("************ " + nameMethod + " ---> "
                    + "Parameter is illegal, check the parameter that are passed to the method.");

            throw new IllegalMethodParameterException(
                    "Parameter is illegal, check the parameter that are passed to the method.");
        }
    }

    public static void handlingIllegalParameters(final Logger logger, final boolean flag, String nameMethod) throws IllegalMethodParameterException {
        if (flag) {
            logger.debug("************ " + nameMethod + " ---> "
                    + "One of the parameters is illegal, check the parameters that are passed to the method.");

            throw new IllegalMethodParameterException(
                    "One of the parameters is illegal, check the parameters that are passed to the method.");

        }
    }
}
