package com.demo.project.converter;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class StringEncryptDecryptConverter extends AbstractEncryptDecryptConverter<String> {
    public StringEncryptDecryptConverter() {
        this(new CipherMaker());
    }

    public StringEncryptDecryptConverter(CipherMaker cipherMaker) {
        super(cipherMaker);
    }

    @Override
    boolean isNotNullOrEmpty(String attribute) {
        return isNotEmpty(attribute);
    }

    @Override
    String convertStringToEntityAttribute(String dbData) {
        return dbData;
    }

    @Override
    String convertEntityAttributeToString(String attribute) {
        return attribute;
    }
}