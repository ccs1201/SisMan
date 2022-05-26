package com.doiscs.sisman.exceptions.utils;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class StingToIntegerConverter implements Converter<String, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 7117542371081929911L;

    @Override
    public Result<Integer> convertToModel(String value, ValueContext context) {

        try {
            int number = Integer.parseInt(value);

            return Result.ok(number);

        } catch (NumberFormatException e) {
            return Result.error("Erro ao converter: " + value + " para um inteiro.");

        }

    }

    @Override
    public String convertToPresentation(Integer value, ValueContext context) {

        return new String(value.toString());
    }

}
