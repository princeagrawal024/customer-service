package com.demo.project.converter;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static org.aspectj.util.LangUtil.isEmpty;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Converter;


@Converter(autoApply = false)
public class DateEncryptDecryptConverter extends AbstractEncryptDecryptConverter<LocalDate> {

    public DateEncryptDecryptConverter() {
        this(new CipherMaker());
    }

    public DateEncryptDecryptConverter(CipherMaker cipherMaker) {
        super(cipherMaker);
    }

    @Override
    boolean isNotNullOrEmpty(LocalDate attribute) {
        return attribute != null;
    }

    @Override
    LocalDate convertStringToEntityAttribute(String dbData) {
        return isEmpty(dbData) ? null : LocalDate.parse(dbData, ISO_DATE);
    }

    @Override
    String convertEntityAttributeToString(LocalDate attribute) {
        return ((Objects.isNull(attribute)) ? null : attribute.format(ISO_DATE));
    }
}